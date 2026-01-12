package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolActions;

public class TFSSickleTool extends TFSBaseItem {
    public TFSSickleTool(Properties pProperties) {
        super(ModTags.Blocks.PLANTS_HOE, 0.5f, 2, 0.5f, ToolActions.DEFAULT_HOE_ACTIONS, pProperties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return super.getMaxDamage(stack) * 3;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        super.mineBlock(pStack, pLevel, pState, pPos, entity);
        double x = pPos.getX();
        double y = pPos.getY();
        double z = pPos.getZ();

        if (!entity.isShiftKeyDown()) {
            if (pState.is(this.blocks)) {
                for (int Y = -2; Y <= 2; Y++) {
                    for (int X = -2; X <= 2; X++) {
                        for (int Z = -2; Z <= 2; Z++) {
                            if (!(X == 0 && Y == 0 && Z == 0)) {
                                if ((pLevel.getBlockState(BlockPos.containing(x + X, y + Y, z + Z))).is(this.blocks)) {
                                    BlockPos newPos = new BlockPos((int) x + X, (int) y + Y, (int) z + Z);
                                    BlockState newState = pLevel.getBlockState(BlockPos.containing(x + X, y + Y, z + Z));
                                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                    });
                                    pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                    pLevel.setBlock(BlockPos.containing(x + X, y + Y, z + Z), Blocks.AIR.defaultBlockState(), 3);
                                    pLevel.playSound((Player) entity, x + X, y + Y, z + Z, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
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
        ItemStack pStack = context.getItemInHand();

        if (!entity.isShiftKeyDown()) {
            if (pState.getBlock() instanceof CropBlock pCropBlock && pCropBlock.isMaxAge(pState)) {
                for (int Y = -1; Y <= 1; Y++) {
                    for (int X = -2; X <= 2; X++) {
                        for (int Z = -2; Z <= 2; Z++) {
                            BlockState newState = pLevel.getBlockState(BlockPos.containing(x + X, y + Y, z + Z));

                            if (newState.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(newState)) {
                                BlockPos newPos = new BlockPos((int) (x + X), (int) (y + Y), (int) (z + Z));

                                pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                    p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                });

                                pState.getBlock().playerDestroy(pLevel, entity, newPos, newState, null, pStack);
                                pLevel.setBlock(newPos, cropBlock.getStateForAge(0), 2);
                                pLevel.sendBlockUpdated(newPos, newState, newState, 3);
                                pLevel.playSound(entity, x + X, y + Y, z + Z, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                            }
                        }
                    }
                }
                return InteractionResult.sidedSuccess(pLevel.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }
}