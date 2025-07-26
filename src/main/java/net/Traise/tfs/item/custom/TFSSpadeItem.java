package net.Traise.tfs.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolActions;

import java.util.Comparator;
import java.util.List;

public class TFSSpadeItem extends DiggerItem{

    public TFSSpadeItem(Tier pTier, Properties pProperties) {
        super(7, -3.3f, pTier, BlockTags.MINEABLE_WITH_SHOVEL, pProperties.durability(pTier.getUses() * 9));
    }

    public boolean mineBlock(ItemStack pStack, Level world, BlockState pState, BlockPos pPos, LivingEntity entity) {
        pStack.hurtAndBreak(1, entity, (p_40992_) -> {
            p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        double x = pPos.getX(); double y = pPos.getY(); double z = pPos.getZ();
        if (!entity.isShiftKeyDown()) {
            if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
                for (int XZ = -1; XZ <= 1; XZ++) {
                    for (int Y = -1; Y <= 1; Y++) {
                        if (!(XZ == 0 && Y == 0)) {
                            if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.EAST || (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.WEST) {
                                if ((world.getBlockState(BlockPos.containing(x, y + Y, z + XZ))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
                                    BlockPos newPos = new BlockPos((int) x, (int) y + Y, (int) z + XZ);
                                    BlockState newState = world.getBlockState(BlockPos.containing(x, y + Y, z + XZ));
                                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                    });
                                    pState.getBlock().playerDestroy(world, (Player) entity, newPos, newState, null, pStack);
                                    world.setBlock(BlockPos.containing(x, y + Y, z + XZ), Blocks.AIR.defaultBlockState(), 3);
                                }
                            } else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.NORTH || (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.SOUTH) {
                                if ((world.getBlockState(BlockPos.containing(x + XZ, y + Y, z))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
                                    BlockPos newPos = new BlockPos((int) x + XZ, (int) y + Y, (int) z);
                                    BlockState newState = world.getBlockState(BlockPos.containing(x + XZ, y + Y, z));
                                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                    });
                                    pState.getBlock().playerDestroy(world, (Player) entity, newPos, newState, null, pStack);
                                    world.setBlock(BlockPos.containing(x + XZ, y + Y, z), Blocks.AIR.defaultBlockState(), 3);
                                }
                            } else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.UP || (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.DOWN) {
                                if ((world.getBlockState(BlockPos.containing(x + XZ, y, z + Y))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
                                    BlockPos newPos = new BlockPos((int) x + XZ, (int) y, (int) z + Y);
                                    BlockState newState = world.getBlockState(BlockPos.containing(x + XZ, y, z + Y));
                                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                    });
                                    pState.getBlock().playerDestroy(world, (Player) entity, newPos, newState, null, pStack);
                                    world.setBlock(BlockPos.containing(x + XZ, y, z + Y), Blocks.AIR.defaultBlockState(), 3);
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
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
    }
}
