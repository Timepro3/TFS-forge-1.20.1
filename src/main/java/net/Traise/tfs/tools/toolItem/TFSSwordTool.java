package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;

public class TFSSwordTool extends TFSBaseItem {
    public TFSSwordTool(Properties pProperties) {
        super(BlockTags.SWORD_EFFICIENT, 0, 3, 0, ToolActions.DEFAULT_SWORD_ACTIONS,pProperties);
    }

    @Override
    public boolean isWeapon(ItemStack pStack) {
        return true;
    }

    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return super.getDestroySpeed(pStack, pState);
        }
    }
}
