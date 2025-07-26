package net.Traise.tfs.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class FluidStorageHandler {
    private final List<FluidSlot> slots;
    private int Size;
    private int DefaultCapacity;

    public FluidStorageHandler(int size, int defaultCapacity) {
        Size = size;
        DefaultCapacity = defaultCapacity;
        this.slots = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            slots.add(new FluidSlot(defaultCapacity));
        }
    }

    public FluidStack getFluidInSlot(int index) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        return slots.get(index).getFluid();
    }

    public void setFluidInSlot(int index, FluidStack stack) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        slots.get(index).setFluid(stack);
    }

    public int getAmount(int index) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        return slots.get(index).getAmount();
    }

    public void setAmount(int index, int amount) {
        if (index < 0 || index >= slots.size()) throw new IndexOutOfBoundsException();
        slots.get(index).setAmount(amount);
    }

    public int getCapacity() {
        return DefaultCapacity;
    }

    public void setCapacity(int capacity) {
        DefaultCapacity = capacity;
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).setCapacity(capacity);
        }
    }

    public List<FluidSlot> getSlots() {
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
                this.slots.add(new FluidSlot(DefaultCapacity));
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
    public int fill(int slotIndex, FluidStack resource, boolean simulate) {
        if (resource.isEmpty() || slotIndex < 0 || slotIndex >= slots.size()) return 0;

        FluidSlot slot = slots.get(slotIndex);
        FluidStack current = slot.getFluid();

        if (current.isEmpty()) {
            int fillAmount = Math.min(resource.getAmount(), slot.getCapacity());
            if (!simulate) {
                FluidStack newStack = resource.copy();
                newStack.setAmount(fillAmount);
                slot.setFluid(newStack);
            }
            return fillAmount;
        } else if (current.isFluidEqual(resource)) {
            int spaceLeft = slot.getCapacity() - current.getAmount();
            int fillAmount = Math.min(resource.getAmount(), spaceLeft);
            if (!simulate && fillAmount > 0) {
                FluidStack newStack = current.copy();
                newStack.setAmount(current.getAmount() + fillAmount);
                slot.setFluid(newStack);
            }
            return fillAmount;
        }
        return 0; // Разные типы жидкостей
    }

    // Выкачивание жидкости из слота
    public FluidStack drain(int slotIndex, int count, boolean simulate) {
        if (slotIndex < 0 || slotIndex >= slots.size()) return FluidStack.EMPTY;

        FluidSlot slot = slots.get(slotIndex);
        FluidStack current = slot.getFluid();

        if (current.isEmpty()) return FluidStack.EMPTY;

        int drainedAmount = Math.min(current.getAmount(), count);
        FluidStack drained = current.copy();
        drained.setAmount(drainedAmount);

        if (!simulate) {
            current.setAmount(current.getAmount() - drainedAmount);
            if (current.getAmount() <= 0) {
                slot.setFluid(FluidStack.EMPTY);
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
            FluidSlot slot = slots.get(i);
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
            slotTag.putInt("Capacity", slot.getCapacity());

            list.add(slotTag);
        }

        nbt.put("Slots", list);
        return nbt;
    }

    // Десериализация
    public static FluidStorageHandler deserializeNBT(CompoundTag nbt, int size, int defaultCapacity) {
        FluidStorageHandler handler = new FluidStorageHandler(size, defaultCapacity);

        ListTag list = nbt.getList("Slots", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag slotTag = list.getCompound(i);
            int slotIdx = slotTag.getInt("Slot");
            if (slotIdx < 0 || slotIdx >= size) continue;

            if (slotTag.contains("Fluid")) {
                FluidStack stack = FluidStack.loadFluidStackFromNBT(slotTag.getCompound("Fluid"));
                handler.setFluidInSlot(slotIdx, stack);
            } else {
                handler.setFluidInSlot(slotIdx, FluidStack.EMPTY);
            }

            if (slotTag.contains("Capacity")) {
                int capacity = slotTag.getInt("Capacity");
                handler.slots.get(slotIdx).setCapacity(capacity);
            } else {
                handler.slots.get(slotIdx).setCapacity(defaultCapacity);
            }
        }

        return handler;
    }
}
