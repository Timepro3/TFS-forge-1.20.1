package net.Traise.tfs.util;

import net.minecraftforge.fluids.FluidStack;

public class FluidSlot {
    private FluidStack fluid = FluidStack.EMPTY;
    private int capacity;

    public FluidSlot(int capacity) {
        this.capacity = capacity;
    }

    public FluidStack getFluid() {
        return fluid;
    }

    public void setFluid(FluidStack fluid) {
        this.fluid = fluid;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAmount() {
        return this.fluid.getAmount();
    }

    public void setAmount(int amount) {
        this.fluid.setAmount(amount);
    }
}
