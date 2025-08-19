package net.Traise.tfs.util;

import net.Traise.tfs.fluid.util.DoubleFluidStorageHandler;
import net.Traise.tfs.fluid.util.TFSFluidStack;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;


public class FluidContainer extends SimpleContainer {
    private final DoubleFluidStorageHandler fluids;

    public FluidContainer(DoubleFluidStorageHandler fluids) {
        super(fluids.getSize());
        this.fluids = fluids;
        // Можно дополнительно заполнить ItemStacks или оставить так
        for (int i=0; i<fluids.getSize(); i++) {
            setItem(i, ItemStack.EMPTY); // или создать ItemStack с тегами для жидкости
        }
    }

    public TFSFluidStack getFluidInSlot(int slot) {
        if (slot >= 0 && slot < fluids.getSize()) {
            return fluids.getFluidInSlot(slot);
        }
        return TFSFluidStack.EMPTY;
    }

    public void setFluidInSlot(int slot, TFSFluidStack stack) {
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
