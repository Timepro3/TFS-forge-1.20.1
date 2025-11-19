package net.Traise.tfs.fluid.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.List;

public class DoubleFluidStorageHandler {
    private final List<DoubleFluidSlot> slots;
    private int Size;
    private double DefaultCapacity;

    public DoubleFluidStorageHandler(int size, double defaultCapacity) {
        Size = size;
        DefaultCapacity = defaultCapacity;
        this.slots = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            slots.add(new DoubleFluidSlot(defaultCapacity));
        }
    }

    public TFSFluidStack getFluidInSlot(int index) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        return slots.get(index).getFluid();
    }

    public void setFluidInSlot(int index, TFSFluidStack stack) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        slots.get(index).setFluid(stack);
    }

    public double getAmount(int index) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        return slots.get(index).getAmount();
    }

    public void setAmount(int index, double amount) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        slots.get(index).setAmount(amount);
    }

    public double getCapacity() {
        return DefaultCapacity;
    }

    public void setCapacity(int capacity) {
        DefaultCapacity = capacity;
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).setCapacity(capacity);
        }
    }

    public List<DoubleFluidSlot> getSlots() {
        return this.slots;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        if (Size > size) {
            for (int i = size; i < Size; i++) {
                this.slots.remove(i);
            }
        } else if (Size < size) {
            for (int i = Size; i < size; i++) {
                this.slots.add(new DoubleFluidSlot(DefaultCapacity));
            }
        }
        Size = size;
    }

    public boolean isEmpty() {
        int T = 0;
        for (int i = 0; i < slots.size(); i++) {
            T += slots.get(i).getAmount();
        }

        return T <= 0;
    }

    // Заполнение жидкости в слот
    public double fill(int slotIndex, TFSFluidStack resource, boolean simulate) {
        if (resource.isEmpty() || slotIndex < 0 || slotIndex >= slots.size()) return 0;

        DoubleFluidSlot slot = slots.get(slotIndex);
        TFSFluidStack current = slot.getFluid();

        if (current.isEmpty()) {
            double fillAmount = Math.min(resource.getAmount(), slot.getCapacity());
            if (!simulate) {
                TFSFluidStack newStack = resource.copy();
                newStack.setAmount(fillAmount);
                slot.setFluid(newStack);
            }
            return fillAmount;
        } else if (current.isFluidEqual(resource)) {
            double spaceLeft = slot.getCapacity() - current.getAmount();
            double fillAmount = Math.min(resource.getAmount(), spaceLeft);
            if (!simulate && fillAmount > 0) {
                TFSFluidStack newStack = current.copy();
                newStack.setAmount(current.getAmount() + fillAmount);
                slot.setFluid(newStack);
            }
            return fillAmount;
        }
        return 0; // Разные типы жидкостей
    }

    // Выкачивание жидкости из слота
    public TFSFluidStack drain(int slotIndex, double count, boolean simulate) {
        if (slotIndex < 0 || slotIndex >= slots.size()) return TFSFluidStack.EMPTY;

        DoubleFluidSlot slot = slots.get(slotIndex);
        TFSFluidStack current = slot.getFluid();

        if (current.isEmpty()) return TFSFluidStack.EMPTY;

        double drainedAmount = Math.min(current.getAmount(), count);
        TFSFluidStack drained = current.copy();
        drained.setAmount(drainedAmount);

        if (!simulate) {
            current.setAmount(current.getAmount() - drainedAmount);
            if (current.getAmount() <= 0) {
                slot.setFluid(TFSFluidStack.EMPTY);
            } else {
                slot.setFluid(current);
            }
        }
        return drained;
    }

    // Сериализация
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag list = new ListTag();

        for (int i = 0; i < slots.size(); i++) {
            DoubleFluidSlot slot = slots.get(i);
            CompoundTag slotTag = new CompoundTag();

            // Сохраняем индекс слота
            slotTag.putInt("Slot", i);

            // Сохраняем жидкость, если есть
            if (!slot.getFluid().isEmpty()) {
                CompoundTag fluidTag = new CompoundTag();
                slot.getFluid().writeToNBT(fluidTag);
                slotTag.put("Fluid", fluidTag);
            }

            // Сохраняем емкость слота
            slotTag.putDouble("Capacity", slot.getCapacity());

            list.add(slotTag);
        }

        nbt.put("Slots", list);
        return nbt;
    }

    // Десериализация
    public static DoubleFluidStorageHandler deserializeNBT(CompoundTag nbt, int size, int defaultCapacity) {
        DoubleFluidStorageHandler handler = new DoubleFluidStorageHandler(size, defaultCapacity);

        ListTag list = nbt.getList("Slots", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag slotTag = list.getCompound(i);
            int slotIdx = slotTag.getInt("Slot");
            if (slotIdx < 0 || slotIdx >= size) continue;

            if (slotTag.contains("Fluid")) {
                TFSFluidStack stack = TFSFluidStack.loadFluidStackFromNBT(slotTag.getCompound("Fluid"));
                handler.setFluidInSlot(slotIdx, stack);
            } else {
                handler.setFluidInSlot(slotIdx, TFSFluidStack.EMPTY);
            }

            if (slotTag.contains("Capacity")) {
                double capacity = slotTag.getDouble("Capacity");
                handler.slots.get(slotIdx).setCapacity(capacity);
            } else {
                handler.slots.get(slotIdx).setCapacity(defaultCapacity);
            }
        }

        return handler;
    }
}
