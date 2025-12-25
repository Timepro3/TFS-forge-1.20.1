package net.Traise.tfs.tools;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TFSPartItem extends Item {
    public TFSPartItem(Properties pProperties) {
        super(pProperties);
    }

    public static int getColor(ItemStack itemStack) {
        return getMaterial(itemStack).color;
    }

    public static void setMaterial(TFSToolMaterial material, ItemStack itemStack) {
        CompoundTag pCompoundTag = itemStack.getOrCreateTag();
        TFSToolMaterial.save(pCompoundTag, material);
    }

    public static TFSToolMaterial getMaterial(ItemStack itemStack) {
        CompoundTag pCompoundTag = itemStack.getOrCreateTag();
        if (TFSToolMaterial.load(pCompoundTag) != null) {
            return TFSToolMaterial.load(pCompoundTag);
        } else return TFSToolMaterials.NONE.get();
    }
}
