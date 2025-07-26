package net.Traise.tfs.util;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FluidContainer extends SimpleContainer {
    private final FluidStorageHandler fluids;

    public FluidContainer(FluidStorageHandler fluids) {
        super(fluids.getSize());
        this.fluids = fluids;
        // Можно дополнительно заполнить ItemStacks или оставить так
        for (int i=0; i<fluids.getSize(); i++) {
            setItem(i, ItemStack.EMPTY); // или создать ItemStack с тегами для жидкости
        }
    }

    public FluidStack getFluidInSlot(int slot) {
        if (slot >= 0 && slot < fluids.getSize()) {
            return fluids.getFluidInSlot(slot);
        }
        return FluidStack.EMPTY;
    }

    public void setFluidInSlot(int slot, FluidStack stack) {
        fluids.setFluidInSlot(slot, stack);
    }

    public int getSumAllFluid() {
        int T = 0;
        for (int i = 0; i < getContainerSize(); i++) {
            T += getFluidInSlot(i).getAmount();
        }
        return T;
    }

    private MoldType moldType = MoldType.INGOT;

    public void setMoldType(MoldType pMoldType) {
        moldType = pMoldType;
    }

    public MoldType getMoldType() {
        return moldType;
    }

    @Override
    public int getContainerSize() {
        return fluids.getSize();
    }

    @Override
    public boolean isEmpty() {
        return fluids.isEmpty();
    }

}
