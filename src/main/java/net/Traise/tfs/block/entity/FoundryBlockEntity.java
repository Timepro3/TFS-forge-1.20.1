package net.Traise.tfs.block.entity;

import net.Traise.tfs.fluid.TFSFluids;
import net.Traise.tfs.fluid.util.DoubleFluidStorageHandler;
import net.Traise.tfs.fluid.util.TFSFluidStack;
import net.Traise.tfs.item.custom.TFSFormItem;
import net.Traise.tfs.recipe.AlloyRecipe;
import net.Traise.tfs.recipe.FoundryRecipe;
import net.Traise.tfs.screen.FoundryMenu;
import net.Traise.tfs.util.AddMth;
import net.Traise.tfs.util.FluidContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FoundryBlockEntity extends BlockEntity implements MenuProvider {
    private int Size = 1;
    private int Capacity = 10000;
    private static final int ItemSize = 4;
    private static final int OutPutSlot = 3;
    private int Id;
    private int[] SlotProgress = new int[ItemSize];
    private int MaxProgress = 100;
    private List<ItemStack> ingredient = new ArrayList<>(ItemSize);
    private final double correctCount = 1.007;

    private final ItemStackHandler itemHandler = new ItemStackHandler(ItemSize) { @Override public int getSlotLimit(int slot) {return 1;} };
    private DoubleFluidStorageHandler fluidHandler = new DoubleFluidStorageHandler(Size, Capacity);

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<DoubleFluidStorageHandler> lazyFluidHandler = LazyOptional.empty();

    protected final ContainerData data;

    public FoundryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(TFSBlockEntity.FOUNDRY_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> FoundryBlockEntity.this.Capacity;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> FoundryBlockEntity.this.Capacity = pValue;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }
        };

        for (int i = 0; i < ItemSize; i++) {
            ingredient.add(ItemStack.EMPTY);
        }
    }
    
    private boolean form = false;
    public void tick(ServerLevel level, BlockPos pos, BlockState state) {
        FoundryMenu.tickUpdate(this, this.Id);
        addAndRemoveFluidSlots();
        сompact();
        hasBurned(level, pos);
        sort();

        for (int i = 0; i <= ItemSize - 2; i++) {
            if (hasMelting(i)) {
                if (progressFinished(i)) {
                    melting(i);
                }
            } else SlotProgress[i] = 0;
        }

        if (hasFusion()) {
            fusion();
        }

        setChanged(level, pos, state);
    }

    private void melting(int index) {
        if (!(this.itemHandler.getStackInSlot(index).getItem() instanceof TFSFormItem formItem)) {
            Optional<FoundryRecipe> recipe = getCurrentRecipe(index);
            TFSFluidStack result = recipe.get().getResultFluid(null);

            this.itemHandler.extractItem(index, 1, false);

            this.fluidHandler.fill(freeFluidSlot(result.getFluid()), result, false);

        } else {
            formItem.myTick(this.itemHandler.getStackInSlot(index));
            for (int i = 0; i < formItem.getFluidHandler(this.itemHandler.getStackInSlot(index)).getSize(); i++) {
                double count = (formItem.getFluidHandler(this.itemHandler.getStackInSlot(index)).getFluidInSlot(i).getAmount() / formItem.sumAllFluidSlot());

                if (count > formItem.getFluidHandler(this.itemHandler.getStackInSlot(index)).getFluidInSlot(i).getAmount()) {
                    count = formItem.getFluidHandler(this.itemHandler.getStackInSlot(index)).getFluidInSlot(i).getAmount();
                }

                TFSFluidStack fluidStack = new TFSFluidStack(formItem.getFluidHandler(this.itemHandler.getStackInSlot(index)).getFluidInSlot(i).getFluid(), count * correctCount);

                this.fluidHandler.fill(freeFluidSlot(formItem.getFluidHandler(this.itemHandler.getStackInSlot(index)).getFluidInSlot(i).getFluid()), fluidStack, false);
                formItem.myTick(this.itemHandler.getStackInSlot(index));
                formItem.drain(i, count, this.itemHandler.getStackInSlot(index));
                formItem.myTick(this.itemHandler.getStackInSlot(index));

                ingredient.set(index, this.itemHandler.getStackInSlot(index));
            }
        }

    }

    private boolean hasMelting(int index) {
        Optional<FoundryRecipe> recipe = getCurrentRecipe(index);
        double count;
        if (this.itemHandler.getStackInSlot(index).getItem() instanceof TFSFormItem formItem) {
            SlotProgress[index] = 0;
            if (ingredient.get(index) != this.itemHandler.getStackInSlot(index)) {
                ingredient.set(index, this.itemHandler.getStackInSlot(index));

            }

            form = true;

            formItem.myTick(this.itemHandler.getStackInSlot(index));
            if (!(formItem.getRealSize(this.itemHandler.getStackInSlot(index)) > 0 && !formItem.getFluidHandler(this.itemHandler.getStackInSlot(index)).getFluidInSlot(0).isEmpty())) {
                return false;
            }

            count = 1;
        } else {
            if (recipe.isEmpty()) {
                return false;
            }
            count = recipe.get().getResultFluid(getLevel().registryAccess()).getAmount();

            form = false;

            if (!recipe.get().getIngredient().get(0).test(ingredient.get(index))) {
                SlotProgress[index] = 0;
                ingredient.set(index, this.itemHandler.getStackInSlot(index));
            }
        }

        return canInsertAmountIntoOutputSlot(count) && burned;
    }

    private boolean progressFinished(int Index) {
        if (!form) {
            if (SlotProgress[Index] >= MaxProgress) {
                SlotProgress[Index] = 0;
                return true;
            } else {
                SlotProgress[Index] += 1;
                return false;
            }
        } else return true;
    }

    private void fusion() {
        if (Size > 0) {
            allDrain();
        }
    }

    private boolean hasFusion() {
        if (!(this.itemHandler.getStackInSlot(OutPutSlot).getItem() instanceof TFSFormItem)) {
            return false;
        }

        if (sumAllFluidSlots() <= 0) {
            remove();
            return false;
        }

        TFSFormItem formItem = (TFSFormItem) this.itemHandler.getStackInSlot(OutPutSlot).getItem();
        formItem.myTick(this.itemHandler.getStackInSlot(OutPutSlot));
        return Size > 0 && !this.fluidHandler.getFluidInSlot(0).isEmpty() && formItem.sumAllFluidSlot() + 1 <= formItem.getCapacity();
    }

    private void allDrain() {
        for (int i = 0; i < Size; i++) {
            if (this.fluidHandler.getFluidInSlot(i).isEmpty()) {
                break;
            }
            double count = (this.fluidHandler.getFluidInSlot(i).getAmount() / sumAllFluidSlots());

            if (count > this.fluidHandler.getFluidInSlot(i).getAmount()) {
                count = this.fluidHandler.getFluidInSlot(i).getAmount();
            }

            TFSFormItem formItem = (TFSFormItem) this.itemHandler.getStackInSlot(OutPutSlot).getItem();
            formItem.myTick(this.itemHandler.getStackInSlot(OutPutSlot));
            formItem.neededFill(this.fluidHandler.getFluidInSlot(i).getFluid(), count * correctCount, this.itemHandler.getStackInSlot(OutPutSlot));

            this.fluidHandler.drain(i, count, false);
        }
    }

    private void remove() {
        for (int i = 0; i < Size; i++) {
            this.fluidHandler.setFluidInSlot(i, TFSFluidStack.EMPTY);
        }
    }

    private Optional<FoundryRecipe> getCurrentRecipe(int index) {
        FoundryRecipe.setIndex(index);
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(FoundryRecipe.Type.INSTANCE, inventory, level);
    }

    private Optional<AlloyRecipe> getCurrentRecipe2() {
        FluidContainer inventory = new FluidContainer(this.fluidHandler);
        for(int i = 0; i < this.fluidHandler.getSize(); i++) {
            inventory.setFluidInSlot(i, this.fluidHandler.getFluidInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(AlloyRecipe.Type.INSTANCE, inventory, level);
    }

    public String getAlloyName() {
        String name = new TFSFluidStack(TFSFluids.UNKNOWN_METAL.get(), 1).getDisplayName().getString();
        Optional<AlloyRecipe> recipe = getCurrentRecipe2();

        if (this.Size < 2) {
            name = "Пусто";
        } else if (this.Size == 2) {
            name = this.fluidHandler.getFluidInSlot(0).getDisplayName().getString();
        } else if (recipe.isPresent()) {
            name = recipe.get().getResultFluid(getLevel().registryAccess()).getDisplayName().getString();
        }

        return name;
    }

    boolean burned = false;
    private void hasBurned(Level pLevel, BlockPos pPose) {
        if (pLevel.getBlockState(BlockPos.containing(pPose.getX(), pPose.getY() - 1, pPose.getZ())).getBlock() instanceof CampfireBlock && pLevel.getBlockState(BlockPos.containing(pPose.getX(), pPose.getY() - 1, pPose.getZ())).getValue(CampfireBlock.LIT)) {
            burned = true;
        } else burned = false;
    }

    private SimpleContainer getInputContainer() {
        SimpleContainer container = new SimpleContainer(3);
        for (int i = 0; i < Math.min(this.itemHandler.getSlots(), 3); i++) {
            container.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return container;
    }

    private boolean canInsertAmountIntoOutputSlot(double count) {
        return sumAllFluidSlots() + count <= this.fluidHandler.getCapacity();
    }

    public boolean isBurned() {
        return burned;
    }

    private int freeFluidSlot(Fluid fluid) {
        int T = this.Size - 1;
        for (int i = 0; i < this.Size; i++) {
            if (this.fluidHandler.getFluidInSlot(i).isEmpty() || this.fluidHandler.getFluidInSlot(i).getFluid().isSame(fluid)) {
                T = i;
                break;
            }
        }
        return T;
    }

    private void addAndRemoveFluidSlots() {
        if (Size > 1) {
            if (this.fluidHandler.getFluidInSlot(Size - 2).isEmpty()) {
                this.fluidHandler.setSize(Size - 1);
                Size -= 1;
            }
        }
        if (!this.fluidHandler.getFluidInSlot(Size - 1).isEmpty()) {
            this.fluidHandler.setSize(Size + 1);
            Size += 1;
        }
    }

    public int sumAllFluidSlots() {
        double T = 0;

        for (int i = 0; i < this.Size; i++) {
            T += this.fluidHandler.getAmount(i);
        }

        return (int) Math.round(T);
    }

    public void сompact() {
        for (int i = 1; i < this.Size; i++) {
            for (int t = 0; t < i; t++) {
                if (!this.fluidHandler.getFluidInSlot(i).isEmpty()) {
                    if (this.fluidHandler.getFluidInSlot(t).isEmpty() || this.fluidHandler.getFluidInSlot(i).getFluid() == this.fluidHandler.getFluidInSlot(t).getFluid()) {
                        this.fluidHandler.fill(t, this.fluidHandler.getFluidInSlot(i), false);
                        this.fluidHandler.drain(i, this.fluidHandler.getAmount(i), false);
                    }
                    t = i;
                }
            }
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.lazyItemHandler.cast();
        }

        if(cap == ForgeCapabilities.FLUID_HANDLER) {
            return this.lazyFluidHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    public String getFluidName(int Index) {
        return this.fluidHandler.getFluidInSlot(Index).getDisplayName().getString();
    }

    public double getFluidAmount(int Index) {
        return this.fluidHandler.getAmount(Index);
    }

    public int getSize() {
        return Size;
    }

    public boolean hawFluid(int Index) {
        return !this.fluidHandler.getFluidInSlot(Index).isEmpty();
    }

    public int getSlotProgress(int i) {
        return SlotProgress[i];
    }

    public int getMaxProgress() {
        return MaxProgress;
    }

    public void sort() {
        for (int i = 1; i < this.fluidHandler.getSize(); i++) {
            if (this.fluidHandler.getFluidInSlot(i).getAmount() > this.fluidHandler.getFluidInSlot(i - 1).getAmount()) {
            TFSFluidStack fluidStack = this.fluidHandler.getFluidInSlot(i - 1);

                this.fluidHandler.setFluidInSlot(i - 1, this.fluidHandler.getFluidInSlot(i));
                this.fluidHandler.setFluidInSlot(i, fluidStack);
            }

        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.lazyItemHandler = LazyOptional.of(() -> this.itemHandler);
        this.lazyFluidHandler = LazyOptional.of(() -> this.fluidHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyItemHandler.invalidate();
        this.lazyFluidHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", this.itemHandler.serializeNBT());
        pTag.putInt("Size", Size);
        pTag.put("FluidStorage", this.fluidHandler.serializeNBT());
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        this.itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        if (pTag.contains("Size")) {
            this.Size = pTag.getInt("Size");
            // при необходимости обновите обработчики или слоты
            // например, если размер влияет на количество слотов:
            this.fluidHandler.setSize(this.Size);
            // или пересоздайте обработчик с новым размером
        }
        this.fluidHandler = DoubleFluidStorageHandler.deserializeNBT(pTag.getCompound("FluidStorage"), this.Size, this.Capacity);

    }

    @Override
    public Component getDisplayName() {
        return Component.literal("литейная кузня");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        this.Id = pContainerId;
        return new FoundryMenu(pContainerId, pPlayerInventory, this, this.data);
    }
}