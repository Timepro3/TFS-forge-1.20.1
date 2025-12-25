package net.Traise.tfs.tools;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MaterialStorageHandler {
    public NonNullList<TFSToolMaterial> inventory;
    public int size;

    public MaterialStorageHandler(int pSize) {
        size = pSize;
        inventory = NonNullList.withSize(size, TFSToolMaterials.NONE.get());
    }

    public static void save(ItemStack itemStack, NonNullList<TFSToolMaterial> mat) {
        CompoundTag tag = new CompoundTag();
        for (int i = 0; i < mat.size(); i++) {
            CompoundTag tag1 = new CompoundTag();
            TFSToolMaterial.save(tag1, mat.get(i));
            tag.put("slot_" + i, tag1);
        }
        itemStack.getOrCreateTag().put("mat", tag);
    }

    public static NonNullList<TFSToolMaterial> load(ItemStack itemStack) {
        CompoundTag tag = (CompoundTag) itemStack.getOrCreateTag().get("mat");
        if (tag != null) {
            NonNullList<TFSToolMaterial> inv = NonNullList.withSize(tag.size(), TFSToolMaterials.NONE.get());
            for (int i = 0; i < tag.size(); i++) {
                CompoundTag tag1 = (CompoundTag) tag.get("slot_" + i);
                inv.set(i, TFSToolMaterial.load(tag1));
            }
            return inv;
        } else return NonNullList.withSize(3, TFSToolMaterials.NONE.get());
    }

    public static int getSize(ItemStack itemStack) {
        CompoundTag tag = (CompoundTag) itemStack.getOrCreateTag().get("mat");
        if (tag != null) {
            return tag.size();
        } else return 3;
    }

    public static int getMineLevel(ItemStack itemStack) {
        return load(itemStack).get(1).mineLevel;
    }

    public static int getAllDurability(ItemStack itemStack) {
        int t = 0;
        for (TFSToolMaterial material : load(itemStack)) {
            t += material.durability;
        }
        return t;
    }

    public static double getAllMineSpeed(ItemStack itemStack) {
        double t = 0;
        for (TFSToolMaterial material : load(itemStack)) {
            t += material.mineSpeed;
        }
        return t;
    }

    public static double getAllAttackDamage(ItemStack itemStack) {
        double t = 0;
        for (TFSToolMaterial material : load(itemStack)) {
            t += material.attackDamage;
        }
        return t;
    }

    public static double getAllAttackSpeed(ItemStack itemStack) {
        double t = 0;
        for (TFSToolMaterial material : load(itemStack)) {
            t += material.attackSpeed;
        }
        return t;
    }

    public static int getAllEnchantmentValue(ItemStack itemStack) {
        int t = 0;
        for (TFSToolMaterial material : load(itemStack)) {
            t += material.enchantmentValue;
        }
        return t;
    }
}
