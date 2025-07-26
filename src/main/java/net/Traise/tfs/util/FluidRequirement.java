package net.Traise.tfs.util;

import net.minecraft.world.level.material.Fluid;

public class FluidRequirement {
    public final Fluid fluid;
    public final int minAmount;
    public final int maxAmount;

    public FluidRequirement(Fluid fluid, int minAmount, int maxAmount) {
        this.fluid = fluid;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }
}
