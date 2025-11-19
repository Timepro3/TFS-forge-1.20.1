package net.Traise.tfs.item.custom;

import net.Traise.tfs.fluid.BaseFluidType;
import net.Traise.tfs.fluid.TFSFluids;
import net.Traise.tfs.fluid.util.DoubleFluidStorageHandler;
import net.Traise.tfs.fluid.util.TFSFluidStack;
import net.Traise.tfs.recipe.AlloyRecipe;
import net.Traise.tfs.recipe.RemovingFromMoldRecipe;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.FluidContainer;
import net.Traise.tfs.util.MoldType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TFSFormItem extends TFSItemTexts {
    public final static int Capacity = 100;
    public final MoldType moldType;

    public TFSFormItem(int number, MoldType pMoldType, Properties pProperties) {
        super(number, pProperties);
        moldType = pMoldType;
    }

    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        myTick(pStack);
    }

    public void myTick(ItemStack pStack) {
        сompact(pStack);
        setTexture(pStack);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (sumAllFluidSlot(itemstack) >= Capacity) {
            Optional<RemovingFromMoldRecipe> recipe = getCurrentRecipe2(pLevel, itemstack);
            ItemStack item;

            if (recipe.isPresent()) {
                item = recipe.get().getResultItem(pLevel.registryAccess());
            } else {
                item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(tfs.MOD_ID + ":unknown_metal_" + moldType.getName()))).getDefaultInstance();
            }

            ItemHandlerHelper.giveItemToPlayer(pPlayer, item);

            itemstack.getOrCreateTag().remove("FluidStorage");
            itemstack.getOrCreateTag().remove("size");
            itemstack.getOrCreateTag().remove("amount");

            return InteractionResultHolder.success(itemstack);
        }

        return InteractionResultHolder.fail(itemstack);
    }

    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);

        if (loadAmount(itemstack) > 0) {
            list.add(Component.literal("§7Содержит: " + getAlloyName(level, itemstack) + ": " + loadAmount(itemstack) + "/" + (int) loadFluidHandler(itemstack).getCapacity() + "мб"));
        } else {
            list.add(Component.literal("§7Пустая"));
        }

        if (Screen.hasShiftDown()) {
            for (int i = 0; i < loadSize(itemstack); i++) {
                if (!loadFluidHandler(itemstack).getFluidInSlot(i).isEmpty()) {
                    list.add(Component.literal("§7" + loadFluidHandler(itemstack).getFluidInSlot(i).getDisplayName().getString() + ": " + String.format("%.2f", loadFluidHandler(itemstack).getAmount(i)) + "мб (§5" + ((float)((int)(((double) loadFluidHandler(itemstack).getAmount(i) / loadAmount(itemstack)) * 10000)) / 100) + "%§7)"));
                }
            }
        } else if (loadAmount(itemstack) > 0) {
            list.add(Component.literal("§7<§eSHIFT§7>"));
        }
    }

    public static Optional<AlloyRecipe> getCurrentRecipe(Level pLevel, ItemStack itemstack) {
        FluidContainer inventory = new FluidContainer(loadFluidHandler(itemstack));
        for(int i = 0; i < loadSize(itemstack); i++) {
            inventory.setFluidInSlot(i, new TFSFluidStack(loadFluidHandler(itemstack).getFluidInSlot(i).getFluid(), loadFluidHandler(itemstack).getFluidInSlot(i).getAmount() * 100));
        }
        return pLevel.getRecipeManager().getRecipeFor(AlloyRecipe.Type.INSTANCE, inventory, pLevel);
    }

    private Optional<RemovingFromMoldRecipe> getCurrentRecipe2(Level pLevel, ItemStack itemstack) {
        FluidContainer inventory = new FluidContainer(loadFluidHandler(itemstack));
        Optional<AlloyRecipe> recipe = getCurrentRecipe(pLevel, itemstack);

        if (loadSize(itemstack) == 2) {
            inventory.setFluidInSlot(0, loadFluidHandler(itemstack).getFluidInSlot(0));
        } else if (recipe.isPresent()) {
            inventory.setFluidInSlot(0, recipe.get().getResultFluid(pLevel.registryAccess()));
        } else {
            inventory.setFluidInSlot(0, TFSFluidStack.EMPTY);
        }

        inventory.setMoldType(this.moldType);

        return pLevel.getRecipeManager().getRecipeFor(RemovingFromMoldRecipe.Type.INSTANCE, inventory, pLevel);
    }

    @Override
    public Component getName(ItemStack pStack) {
        Optional<AlloyRecipe> recipe = getCurrentRecipe(Minecraft.getInstance().level, pStack);

        if (loadSize(pStack) == 1) {
            return super.getName(pStack);
        } else if (loadSize(pStack) == 2) {
            return Component.translatable(Component.translatable(pStack.getItem().getDescriptionId()).getString() + Component.translatable("gui.tfs.with").getString() + Component.translatable(loadFluidHandler(pStack).getFluidInSlot(0).getDisplayName().getString()).getString());
        } else if (recipe.isPresent()) {
            return Component.translatable(Component.translatable(pStack.getItem().getDescriptionId()).getString() + Component.translatable("gui.tfs.with").getString() + recipe.get().getResultFluid(Minecraft.getInstance().level.registryAccess()).getDisplayName().getString());
        }

        return Component.literal(Component.translatable(pStack.getItem().getDescriptionId()).getString() + Component.translatable("gui.tfs.with").getString() + new TFSFluidStack(TFSFluids.UNKNOWN_METAL.get(), 1).getDisplayName().getString());
    }

    public String getAlloyName(Level level, ItemStack itemstack) {
        Optional<AlloyRecipe> recipe = getCurrentRecipe(level, itemstack);

        if (loadSize(itemstack) == 2) {
            return loadFluidHandler(itemstack).getFluidInSlot(0).getDisplayName().getString();
        } else if (recipe.isPresent()) {
            return recipe.get().getResultFluid(level.registryAccess()).getDisplayName().getString();
        }

        return new TFSFluidStack(TFSFluids.UNKNOWN_METAL.get(), 1).getDisplayName().getString();
    }

    public void setTexture(ItemStack itemstack) {
        if (loadFluidHandler(itemstack).getFluidInSlot(0).isEmpty()) {
            itemstack.getOrCreateTag().putInt("CustomModelData", 0);
        } else {
            itemstack.getOrCreateTag().putInt("CustomModelData", 1);
        }
    }

    public static int getColor(ItemStack itemstack, Level level) {
        Optional<AlloyRecipe> recipe = getCurrentRecipe(level, itemstack);

        if (loadSize(itemstack) == 2) {
            return loadFluidHandler(itemstack).getFluidInSlot(0).getFluid().getFluidType() instanceof BaseFluidType fluidType ? fluidType.getTintColor() : -1;
        } else if (recipe.isPresent()) {
            return recipe.get().getResultFluid(level.registryAccess()).getFluid().getFluidType() instanceof BaseFluidType fluidType ? fluidType.getTintColor() : -1;
        }

        return new TFSFluidStack(TFSFluids.UNKNOWN_METAL.get(), 1).getFluid().getFluidType() instanceof BaseFluidType fluidType ? fluidType.getTintColor() : -1;
    }

    public void fill(int slotIndex, TFSFluidStack fluidStack, ItemStack itemStack) {
        DoubleFluidStorageHandler fluidHandler = loadFluidHandler(itemStack);
        fluidHandler.fill(slotIndex, fluidStack, false);
        saveFluidHandler(itemStack, fluidHandler);
        addAndRemoveFluidSlots(itemStack);
    }

    public void drain(int slotIndex, double count, ItemStack itemStack) {
        DoubleFluidStorageHandler fluidHandler = loadFluidHandler(itemStack);
        fluidHandler.drain(slotIndex, count, false);
        saveFluidHandler(itemStack, fluidHandler);
        addAndRemoveFluidSlots(itemStack);
    }

    public void neededFill(Fluid fluid, double count, ItemStack itemStack) {
        neededFill(new TFSFluidStack(fluid, count), itemStack);
    }

    public void neededFill(TFSFluidStack fluidStack, ItemStack itemStack) {
        for (int i = 0; i < loadSize(itemStack); i++) {
            if (loadFluidHandler(itemStack).getFluidInSlot(i).isEmpty() || loadFluidHandler(itemStack).getFluidInSlot(i).isFluidEqual(fluidStack)) {
                fill(i, fluidStack, itemStack);
                break;
            }
        }
    }

    public int sumAllFluidSlot(ItemStack itemStack) {
        return (int) Math.round(loadAmount(itemStack));
    }

    public static DoubleFluidStorageHandler loadFluidHandler(ItemStack itemstack) {
        return DoubleFluidStorageHandler.deserializeNBT(itemstack.getOrCreateTag().getCompound("FluidStorage"), loadSize(itemstack), Capacity);
    }

    public void saveFluidHandler(ItemStack itemstack, DoubleFluidStorageHandler fluidHandler) {
        itemstack.getOrCreateTag().put("FluidStorage", fluidHandler.serializeNBT());
    }

    public static int loadSize(ItemStack itemstack) {
        return itemstack.getOrCreateTag().getInt("size") > 0 ? itemstack.getOrCreateTag().getInt("size") : 1;
    }

    public void saveSize(ItemStack itemstack, int size) {
        itemstack.getOrCreateTag().putInt("size", size);
    }

    public double loadAmount(ItemStack itemstack) {
        return itemstack.getOrCreateTag().getDouble("amount");
    }

    public void saveAmount(ItemStack itemstack, double amount) {
        itemstack.getOrCreateTag().putDouble("amount", amount);
    }

    public void removeAmount(ItemStack itemstack) {
        itemstack.getOrCreateTag().remove("amount");
        itemstack.getOrCreateTag().remove("FluidStorage");
        itemstack.getOrCreateTag().remove("size");
    }

    public void сompact(ItemStack itemStack) {
        DoubleFluidStorageHandler fluidHandler = loadFluidHandler(itemStack);
        for (int i = 1; i < loadSize(itemStack); i++) {
            for (int t = 0; t < i; t++) {
                if (!fluidHandler.getFluidInSlot(i).isEmpty()) {
                    if (fluidHandler.getFluidInSlot(t).isEmpty() || fluidHandler.getFluidInSlot(i).getFluid() == fluidHandler.getFluidInSlot(t).getFluid()) {
                        fluidHandler.fill(t, fluidHandler.getFluidInSlot(i), false);
                        fluidHandler.drain(i, fluidHandler.getAmount(i), false);
                    }
                    t = i;
                }
            }
        }
        saveFluidHandler(itemStack, fluidHandler);
    }

    private void addAndRemoveFluidSlots(ItemStack itemStack) {
        int size = loadSize(itemStack);
        if (size > 1) {
            if (loadFluidHandler(itemStack).getFluidInSlot(size - 2).isEmpty()) {
                size -= 1;
            }
        }
        if (!loadFluidHandler(itemStack).getFluidInSlot(size - 1).isEmpty()) {
            size += 1;
        }

        saveSize(itemStack, size);
    }
}