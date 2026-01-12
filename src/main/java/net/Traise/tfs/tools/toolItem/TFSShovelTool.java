package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolActions;

import java.util.Optional;

public class TFSShovelTool extends TFSBaseItem {
    public TFSShovelTool(Properties pProperties) {
        super(BlockTags.MINEABLE_WITH_SHOVEL, 2, 1, -1, ToolActions.DEFAULT_SHOVEL_ACTIONS, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Level pLevel = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        BlockState pState = pLevel.getBlockState(BlockPos.containing(x, y, z));
        BlockPos blockpos = context.getClickedPos();
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
            Player player = context.getPlayer();
            BlockState blockstate1 = pState.getToolModifiedState(context, ToolActions.SHOVEL_FLATTEN, false);
            BlockState blockstate2 = null;
            if (blockstate1 != null && pLevel.isEmptyBlock(blockpos.above())) {
                pLevel.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate1;
            } else if (pState.getBlock() instanceof CampfireBlock && pState.getValue(CampfireBlock.LIT)) {
                if (!pLevel.isClientSide()) {
                    pLevel.levelEvent((Player)null, 1009, blockpos, 0);
                }

                CampfireBlock.dowse(context.getPlayer(), pLevel, blockpos, pState);
                blockstate2 = pState.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
            }

            if (blockstate2 != null) {
                if (!pLevel.isClientSide) {
                    pLevel.setBlock(blockpos, blockstate2, 11);
                    pLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, (p_43122_) -> {
                            p_43122_.broadcastBreakEvent(context.getHand());
                        });
                    }
                }

                return InteractionResult.sidedSuccess(pLevel.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }
}