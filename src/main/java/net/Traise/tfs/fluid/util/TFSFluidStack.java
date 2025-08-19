package net.Traise.tfs.fluid.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TFSFluidStack {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final TFSFluidStack EMPTY = new TFSFluidStack(Fluids.EMPTY, 0);

    private Holder.Reference<Fluid> fluidDelegate;
    private boolean isEmpty;
    private CompoundTag tag;
    private double amount;

    public TFSFluidStack(Fluid pFluid, double pAmount) {
       amount = pAmount;
        if (pFluid == null)
        {
            LOGGER.fatal("Null fluid supplied to fluidstack. Did you try and create a stack for an unregistered fluid?");
            throw new IllegalArgumentException("Cannot create a fluidstack from a null fluid");
        }
        else if (ForgeRegistries.FLUIDS.getKey(pFluid) == null)
        {
            LOGGER.fatal("Failed attempt to create a FluidStack for an unregistered Fluid {} (type {})", ForgeRegistries.FLUIDS.getKey(pFluid), pFluid.getClass().getName());
            throw new IllegalArgumentException("Cannot create a fluidstack from an unregistered fluid");
        }
        this.fluidDelegate = ForgeRegistries.FLUIDS.getDelegateOrThrow(pFluid);

        updateEmpty();
    }

    public TFSFluidStack(Fluid pFluid, double pAmount, CompoundTag pTag) {
        this(pFluid, pAmount);

        if (pTag != null)
        {
            tag = pTag.copy();
        }
    }

    public TFSFluidStack(TFSFluidStack stack, double amount)
    {
        this(stack.getFluid(), amount, stack.tag);
    }

    public TFSFluidStack(FluidStack stack, double amount)
    {
        this(stack.getFluid(), amount);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    protected void updateEmpty() {
        isEmpty = getRawFluid() == Fluids.EMPTY || amount <= 0;
    }

    public final Fluid getRawFluid()
    {
        return fluidDelegate.get();
    }

    public final Fluid getFluid()
    {
        return isEmpty ? Fluids.EMPTY : fluidDelegate.get();
    }

    public double getAmount()
    {
        return isEmpty ? 0 : amount ;
    }


    public void setAmount(double amount)
    {
        if (getRawFluid() == Fluids.EMPTY) throw new IllegalStateException("Can't modify the empty stack.");
        this.amount = amount;
        updateEmpty();
    }

    public void grow(double amount) {
        setAmount(this.amount + amount);
    }

    public void shrink(double amount) {
        setAmount(this.amount - amount);
    }

    public CompoundTag getTag()
    {
        return tag;
    }

    public void setTag(CompoundTag tag)
    {
        if (getRawFluid() == Fluids.EMPTY) throw new IllegalStateException("Can't modify the empty stack.");
        this.tag = tag;
    }

    public boolean hasTag()
    {
        return tag != null;
    }

    public CompoundTag getOrCreateTag()
    {
        if (tag == null)
            setTag(new CompoundTag());
        return tag;
    }

    public CompoundTag getChildTag(String childName)
    {
        if (tag == null)
            return null;
        return tag.getCompound(childName);
    }

    public CompoundTag getOrCreateChildTag(String childName)
    {
        getOrCreateTag();
        CompoundTag child = tag.getCompound(childName);
        if (!tag.contains(childName, Tag.TAG_COMPOUND))
        {
            tag.put(childName, child);
        }
        return child;
    }

    public void removeChildTag(String childName)
    {
        if (tag != null)
            tag.remove(childName);
    }

    public static TFSFluidStack loadFluidStackFromNBT(CompoundTag nbt)
    {
        if (nbt == null)
        {
            return EMPTY;
        }
        if (!nbt.contains("FluidName", Tag.TAG_STRING))
        {
            return EMPTY;
        }

        ResourceLocation fluidName = new ResourceLocation(nbt.getString("FluidName"));
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);
        if (fluid == null)
        {
            return EMPTY;
        }
        TFSFluidStack stack = new TFSFluidStack(fluid, nbt.getDouble("Amount"));

        if (nbt.contains("Tag", Tag.TAG_COMPOUND))
        {
            stack.tag = nbt.getCompound("Tag");
        }
        return stack;
    }

    public CompoundTag writeToNBT(CompoundTag nbt)
    {
        nbt.putString("FluidName", ForgeRegistries.FLUIDS.getKey(getFluid()).toString());
        nbt.putDouble("Amount", amount);

        if (tag != null)
        {
            nbt.put("Tag", tag);
        }
        return nbt;
    }

    public void writeToPacket(FriendlyByteBuf buf)
    {
        buf.writeRegistryId(ForgeRegistries.FLUIDS, getFluid());
        buf.writeDouble(getAmount());
        buf.writeNbt(tag);
    }

    public static TFSFluidStack readFromPacket(FriendlyByteBuf buf)
    {
        Fluid fluid = buf.readRegistryId();
        double amount = buf.readDouble();
        CompoundTag tag = buf.readNbt();
        if (fluid == Fluids.EMPTY) return EMPTY;
        return new TFSFluidStack(fluid, amount, tag);
    }

    public Component getDisplayName()
    {
        return this.getFluid().getFluidType().getDescription(getFluidStack());
    }

    public String getTranslationKey()
    {
        return this.getFluid().getFluidType().getDescriptionId(getFluidStack());
    }

    public FluidStack getFluidStack() {
        return new FluidStack(getFluid(), (int) amount);
    }

    public TFSFluidStack copy()
    {
        return new TFSFluidStack(getFluid(), amount, tag);
    }

    /**
     * Determines if the FluidIDs and NBT Tags are equal. This does not check amounts.
     *
     * @param other
     *            The FluidStack for comparison
     * @return true if the Fluids (IDs and NBT Tags) are the same
     */
    public boolean isFluidEqual(@NotNull TFSFluidStack other)
    {
        return getFluid() == other.getFluid() && isFluidStackTagEqual(other);
    }

    private boolean isFluidStackTagEqual(TFSFluidStack other)
    {
        return tag == null ? other.tag == null : other.tag != null && tag.equals(other.tag);
    }

    /**
     * Determines if the NBT Tags are equal. Useful if the FluidIDs are known to be equal.
     */
    public static boolean areFluidStackTagsEqual(@NotNull TFSFluidStack stack1, @NotNull TFSFluidStack stack2)
    {
        return stack1.isFluidStackTagEqual(stack2);
    }

    public boolean containsFluid(@NotNull TFSFluidStack other)
    {
        return isFluidEqual(other) && amount >= other.amount;
    }

    public boolean isFluidStackIdentical(TFSFluidStack other)
    {
        return isFluidEqual(other) && amount == other.amount;
    }

    @Override
    public final int hashCode()
    {
        int code = 1;
        code = 31*code + getFluid().hashCode();
        if (tag != null)
            code = 31*code + tag.hashCode();
        return code;
    }

    @Override
    public final boolean equals(Object o)
    {
        if (!(o instanceof TFSFluidStack))
        {
            return false;
        }
        return isFluidEqual((TFSFluidStack) o);
    }
}
