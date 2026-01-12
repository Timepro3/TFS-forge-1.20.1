package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.util.ModTags;
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

import static java.util.Set.of;

public class TFSPaxelTool extends TFSBaseItem {
    public TFSPaxelTool(Properties pProperties) {
        super(ModTags.Blocks.MINEABLE_WITH_MULTI_TOOL, 1, 3.5f, -0.7f,
                of(ToolActions.AXE_DIG, ToolActions.AXE_STRIP, ToolActions.AXE_SCRAPE, ToolActions.AXE_WAX_OFF,
                        ToolActions.SHOVEL_DIG, ToolActions.SHOVEL_FLATTEN, ToolActions.PICKAXE_DIG), pProperties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return super.getMaxDamage(stack) * 3;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Level pLevel = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        BlockState pState = pLevel.getBlockState(BlockPos.containing(x, y, z));
        Player entity = context.getPlayer();
        BlockPos blockpos = context.getClickedPos();
        Optional<BlockState> optional = Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_STRIP, false));
        Optional<BlockState> optional1 = optional.isPresent() ?
                Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false));
        Optional<BlockState> optional2 = optional.isPresent() ||
                optional1.isPresent() ? Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_WAX_OFF, false));
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
        }

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
                    pLevel.levelEvent(null, 1009, blockpos, 0);
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