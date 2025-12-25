package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.tools.TFSToolMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

/*public class SwordTool extends TFSBaseItem {
    public SwordTool(Properties pProperties) {
        super(pProperties);
    }

    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return pState.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_41007_) -> {
            p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).hurtEnemy(pStack, pTarget, pAttacker);
        }
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(2, entity, (p_40992_) -> {
                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).mineBlock(pStack, pLevel, pState, pPos, entity);
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, entity);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.category.equals(EnchantmentCategory.WEAPON) || enchantment.equals(Enchantments.MENDING)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }
}*/
