package net.Traise.tfs.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TFSItemNameBlockItem extends TFSBlockItem {
    public TFSItemNameBlockItem(int number, Block pBlock, Item.Properties pProperties) {
        super(number, pBlock, pProperties);
    }

    /**
     * Returns the unlocalized name of this item.
     */
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }
}
