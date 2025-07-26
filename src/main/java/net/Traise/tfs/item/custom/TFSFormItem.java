package net.Traise.tfs.item.custom;

import net.Traise.tfs.recipe.AlloyRecipe;
import net.Traise.tfs.recipe.RemovingFromMoldRecipe;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.FluidContainer;
import net.Traise.tfs.util.MoldType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.Traise.tfs.util.FluidStorageHandler;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TFSFormItem extends TFSItemTexts implements IForgeBlockEntity {
    private int Size = 30;
    private int Capacity = 100;
    private Level level;
    private MinecraftServer server;
    private final MoldType moldType;
    private FluidStorageHandler fluidHandler = new FluidStorageHandler(this.Size, this.Capacity);

    private LazyOptional<FluidStorageHandler> lazyFluidHandler = LazyOptional.empty();

    public TFSFormItem(int number, MoldType pMoldType, Item.Properties pProperties) {
        super(number, pProperties);
        moldType = pMoldType;
    }

    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            setServer(player);
        }
        myTick(pStack, pLevel, false);
    }

    public void myTick(ItemStack pStack, Level pLevel, boolean block) {
        loadFromNBT(pStack);
        сompact(pStack);
        setTexture(pStack);
        setLevel(pLevel, pStack, block);

    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        loadFromNBT(itemstack);
        loadLevelFromNBT(itemstack);
        if (sumAllFluidSlot() >= this.Capacity) {
            Optional<RemovingFromMoldRecipe> recipe = getCurrentRecipe2(level, itemstack, this.fluidHandler);
            ItemStack item;

            if (recipe.isPresent()) {
                item = recipe.get().getResultItem(this.level.registryAccess());
            } else {
                item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tfs.MOD_ID + ":unknown_metal_" + moldType.getName())).getDefaultInstance();
            }

            ItemHandlerHelper.giveItemToPlayer(pPlayer, item);

            TFSFormItem newItem = (TFSFormItem) itemstack.getItem();
            ItemStack newItemStack = new ItemStack(newItem);

            if (this.level != null) {
                ResourceLocation dimensionId = this.level.dimension().location();
                newItemStack.getOrCreateTag().putString("Level", dimensionId.toString());
            } else {
                ResourceLocation dimensionId = pLevel.dimension().location();
                newItemStack.getOrCreateTag().putString("Level", dimensionId.toString());
            }
            FluidStorageHandler newFluidStorage = this.fluidHandler;
            for (int i = 0; i < Size; i++) {
                FluidStack newFluid = new FluidStack(Fluids.EMPTY, 0);
                newFluidStorage.setFluidInSlot(i, newFluid);
            }
            newItemStack.getOrCreateTag().put("FluidStorage", newFluidStorage.serializeNBT());
            newItemStack.getOrCreateTag().putInt("CustomModelData", 0);

            ItemHandlerHelper.giveItemToPlayer(pPlayer, newItemStack);
            itemstack.shrink(1);
            return InteractionResultHolder.success(itemstack);
        }

        return InteractionResultHolder.fail(itemstack);
    }

    private void setServer(Player player) {
        if (this.server == null) {
            this.server = player.getServer();
        }
    }

    private void setLevel(Level level, ItemStack pStack, boolean block) {
        if (this.server != null && !block) {
            loadLevelFromNBT(pStack);
        } else {
            this.level = level;
            saveLevelToNBT(pStack);
        }
    }

    public int getRealSize(ItemStack itemStack) {
        FluidStorageHandler fluidStorage = FluidStorageHandler.deserializeNBT(itemStack.getOrCreateTag().getCompound("FluidStorage"), this.Size, this.Capacity);
        int T = 0;
        for (int i = 0; i < fluidStorage.getSize(); i++) {
            if (!fluidStorage.getFluidInSlot(i).isEmpty()) {
                T += 1;
            }
        }
        return T;
    }

    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        FluidStorageHandler fluidStorage = FluidStorageHandler.deserializeNBT(itemstack.getOrCreateTag().getCompound("FluidStorage"), this.Size, this.Capacity);

        int U = 0;
        for (int i = 0; i < Size; i++) {
            U += fluidStorage.getAmount(i);
        }

        int T = 0;
        for (int i = 0; i < Size; i++) {
            T += fluidStorage.getAmount(i);
        }

        ResourceLocation dimensionId = new ResourceLocation(itemstack.getOrCreateTag().getString("Level"));
        Level le = server.getLevel(ResourceKey.create(Registries.DIMENSION, dimensionId));

        if (T > 0) {
            list.add(Component.literal("§7Содержит: " + getAlloyName(level, itemstack) + ": " + U + "/" + fluidStorage.getCapacity() + "мб"));
        } else if (le == null) {
            list.add(Component.literal("§7Новая и пустая"));
        } else {
            list.add(Component.literal("§7Пустая"));
        }


        if (Screen.hasShiftDown()) {
            for (int i = 0; i < Size; i++) {
                if (!fluidStorage.getFluidInSlot(i).isEmpty()) {
                    list.add(Component.literal("§7" + fluidStorage.getFluidInSlot(i).getDisplayName().getString() + ": " + fluidStorage.getAmount(i) + "мб " + ((float)((int)(((double) fluidStorage.getAmount(i) / T) * 10000)) / 100) + "%"));
                }
            }
        } else if (T > 0) {
            list.add(Component.literal("§7<§eSHIFT§7>"));
        }
    }

    private Optional<AlloyRecipe> getCurrentRecipe(Level pLevel, ItemStack itemstack, FluidStorageHandler fluidStorage) {
        FluidStorageHandler fl = new FluidStorageHandler(getRealSize(itemstack) + 1, this.Capacity);
        FluidContainer inventory = new FluidContainer(fl);
        for(int i = 0; i < getRealSize(itemstack) + 1; i++) {
            inventory.setFluidInSlot(i, fluidStorage.getFluidInSlot(i));
        }

        if (this.level == null) {
            ResourceLocation dimensionId = new ResourceLocation(itemstack.getOrCreateTag().getString("Level"));
            Level le = server.getLevel(ResourceKey.create(Registries.DIMENSION, dimensionId));
            if (this.server != null && le != null) {

                return le.getRecipeManager().getRecipeFor(AlloyRecipe.Type.INSTANCE, inventory, le);
            }

            return pLevel.getRecipeManager().getRecipeFor(AlloyRecipe.Type.INSTANCE, inventory, pLevel);
        }

        return this.level.getRecipeManager().getRecipeFor(AlloyRecipe.Type.INSTANCE, inventory, this.level);
    }

    private Optional<RemovingFromMoldRecipe> getCurrentRecipe2(Level pLevel, ItemStack itemstack, FluidStorageHandler fluidStorage) {
        FluidContainer inventory = new FluidContainer(fluidStorage);
        Optional<AlloyRecipe> recipe = getCurrentRecipe(pLevel, itemstack, fluidStorage);
        if (recipe.isPresent()) {
            inventory.setFluidInSlot(0, recipe.get().getResultFluid(pLevel.registryAccess()));
        } else {
            inventory.setFluidInSlot(0, FluidStack.EMPTY);
        }
        inventory.setMoldType(this.moldType);

        return this.level.getRecipeManager().getRecipeFor(RemovingFromMoldRecipe.Type.INSTANCE, inventory, this.level);
    }

    public String getAlloyName(Level level, ItemStack itemstack) {
        FluidStorageHandler fluidStorage = FluidStorageHandler.deserializeNBT(itemstack.getOrCreateTag().getCompound("FluidStorage"), this.Size, this.Capacity);
        String name = "Неизвестный метал";
        Optional<AlloyRecipe> recipe = getCurrentRecipe(level, itemstack, fluidStorage);

        if (getRealSize(itemstack) == 1) {
            name = fluidStorage.getFluidInSlot(0).getDisplayName().getString();
        } else if (recipe.isPresent()) {
            name = recipe.get().getResultFluid(level.registryAccess()).getDisplayName().getString();
        }

        return name;
    }

    public void setTexture(ItemStack itemstack) {
        if (this.fluidHandler.getFluidInSlot(0).isEmpty()) {
            itemstack.getOrCreateTag().putInt("CustomModelData", 0);
        } else {
            itemstack.getOrCreateTag().putInt("CustomModelData", 1);
        }
    }

    public void fill(int slotIndex, FluidStack fluidStack, ItemStack itemStack) {
        this.fluidHandler.fill(slotIndex, fluidStack, false);
        saveToNBT(itemStack);
    }

    public void drain(int slotIndex, int count, ItemStack itemStack) {
        this.fluidHandler.drain(slotIndex, count, false);
        saveToNBT(itemStack);
    }

    public void neededFill(Fluid fluid, int count, ItemStack itemStack) {
        neededFill(new FluidStack(fluid, count), itemStack);
    }

    public void neededFill(FluidStack fluidStack, ItemStack itemStack) {
        for (int i = 0; i < Size; i++) {
            if (this.fluidHandler.getFluidInSlot(i).isEmpty() || this.fluidHandler.getFluidInSlot(i).isFluidEqual(fluidStack)) {
                fill(i, fluidStack, itemStack);
                break;
            }
        }
    }

    public int sumAllFluidSlot() {
        int T = 0;
        for (int i = 0; i < Size; i++) {
            T += this.fluidHandler.getAmount(i);
        }
        return T;
    }

    public int getCapacity() {
        return Capacity;
    }

    public FluidStorageHandler getFluidHandler(ItemStack itemstack) {
        return FluidStorageHandler.deserializeNBT(itemstack.getOrCreateTag().getCompound("FluidStorage"), this.Size, this.Capacity);
    }

    public void saveToNBT(ItemStack itemstack) {
        itemstack.getOrCreateTag().put("FluidStorage", this.fluidHandler.serializeNBT());

    }

    // Загружаем состояние из NBT
    public void loadFromNBT(ItemStack itemstack) {
        this.fluidHandler = FluidStorageHandler.deserializeNBT(itemstack.getOrCreateTag().getCompound("FluidStorage"), this.Size, this.Capacity);
    }

    public void saveLevelToNBT(ItemStack itemstack) {
        ResourceLocation dimensionId = this.level.dimension().location();
        itemstack.getOrCreateTag().putString("Level", dimensionId.toString());

    }

    public void loadLevelFromNBT(ItemStack itemstack) {
        ResourceLocation dimensionId = new ResourceLocation(itemstack.getOrCreateTag().getString("Level"));
        ResourceKey<Level> dimKey = ResourceKey.create(Registries.DIMENSION, dimensionId);
        this.level = server.getLevel(dimKey);

    }


    @Override
    public CompoundTag getPersistentData() {
        return null;
    }

    public void сompact(ItemStack itemStack) {
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
        saveToNBT(itemStack);
    }

    private void addAndRemoveFluidSlots(ItemStack itemStack) {
        if (Size > 1) {
            if (this.fluidHandler.getFluidInSlot(Size - 2).isEmpty()) {
                rewrite(itemStack, -1);
                this.Size -= 1;
            }
        }
        if (!this.fluidHandler.getFluidInSlot(Size - 1).isEmpty()) {
            rewrite(itemStack, 1);
            this.Size += 1;
        }

    }

    private void rewrite(ItemStack itemStack, int Count) {
        FluidStorageHandler fluidHandlers = new FluidStorageHandler(this.Size + Count, this.Capacity);

        for (int i = 0; i < fluidHandlers.getSize(); i++) {
            fluidHandlers.setFluidInSlot(i, this.fluidHandler.getFluidInSlot(i));
        }
        this.fluidHandler = fluidHandlers;
        saveToNBT(itemStack);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.FLUID_HANDLER) {
            return this.lazyFluidHandler.cast();
        }

        return getCapability(cap, side);
    }
}