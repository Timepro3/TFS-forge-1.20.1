package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.tools.TFSToolMaterial;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.Optional;

/*public class AxeTool extends TFSBaseItem {
    public AxeTool(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level pLevel = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        InteractionHand hand = context.getHand();
        BlockState pState = pLevel.getBlockState(BlockPos.containing(x, y, z));
        Player entity = context.getPlayer();
        ItemStack pStack = context.getItemInHand();
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).useOn(context);
        }

        Optional<BlockState> optional = Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_STRIP, false));
        Optional<BlockState> optional1 = optional.isPresent() ? Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false));
        Optional<BlockState> optional2 = optional.isPresent() || optional1.isPresent() ? Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_WAX_OFF, false));
        ItemStack itemstack = context.getItemInHand();
        Optional<BlockState> optional3 = Optional.empty();
        if (optional.isPresent()) {
            pLevel.playSound(entity, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            optional3 = optional;
        } else if (optional1.isPresent()) {
            pLevel.playSound(entity, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.levelEvent(entity, 3005, blockpos, 0);
            optional3 = optional1;
        } else if (optional2.isPresent()) {
            pLevel.playSound(entity, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.levelEvent(entity, 3004, blockpos, 0);
            optional3 = optional2;
        }

        if (optional3.isPresent()) {
            if (entity instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)entity, blockpos, itemstack);
            }

            pLevel.setBlock(blockpos, optional3.get(), 11);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(entity, optional3.get()));
            if (entity != null) {
                itemstack.hurtAndBreak(1, entity, (p_150686_) -> {
                    p_150686_.broadcastBreakEvent(context.getHand());
                });
            }

            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
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
        return ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
    }
}*/
