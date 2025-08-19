package net.Traise.tfs.fluid.util;

public class DoubleFluidSlot {
    private TFSFluidStack fluid = TFSFluidStack.EMPTY;
    private double capacity;

    public DoubleFluidSlot(double capacity) {
        this.capacity = capacity;
    }

    public TFSFluidStack getFluid() {
        return fluid;
    }

    public void setFluid(TFSFluidStack fluid) {
        this.fluid = fluid;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getAmount() {
        return this.fluid.getAmount();
    }

    public void setAmount(double amount) {
        this.fluid.setAmount(amount);
    }
}
