package net.Traise.tfs.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import static net.minecraft.network.chat.Component.literal;

public class TFSHammerItem extends DiggerItem implements Vanishable {
    private Tier tier;

    public TFSHammerItem(Tier pTier, Properties pProperties) {
        super(9, -3.4f, pTier, BlockTags.MINEABLE_WITH_PICKAXE, pProperties.durability(pTier.getUses() * 9));
        tier = pTier;
    }

    public boolean mineBlock(ItemStack pStack, Level world, BlockState pState, BlockPos pPos, LivingEntity entity) {
        pStack.hurtAndBreak(1, entity, (p_40992_) -> {
            p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });

        Component list = literal("tfs:need_1");
        if (tier.getLevel() == 1) {
            list = literal("tfs:need_2");
        } else if (tier.getLevel() == 2) {
            list = literal("tfs:need_3");
        } else if (tier.getLevel() == 3) {
            list = literal("minecraft:mineable/shovel");
        }
        double x = pPos.getX(); double y = pPos.getY(); double z = pPos.getZ();
        if (!entity.isShiftKeyDown()) {
            if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/pickaxe"))) && !(world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation(list.getString())))) {
                for (int XZ = -1; XZ <= 1; XZ++) {
                    for (int Y = -1; Y <= 1; Y++) {
                        if (!(XZ == 0 && Y == 0)) {
                            if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.EAST || (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.WEST) {
                                if ((world.getBlockState(BlockPos.containing(x, y + Y, z + XZ))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/pickaxe"))) && !(world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BlockTags.create(new ResourceLocation(list.getString())))) {
                                    BlockPos newPos = new BlockPos((int) x, (int) y + Y, (int) z + XZ);
                                    BlockState newState = world.getBlockState(BlockPos.containing(x, y + Y, z + XZ));
                                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                    });
                                    pState.getBlock().playerDestroy(world, (Player) entity, newPos, newState, null, pStack);
                                    world.setBlock(BlockPos.containing(x, y + Y, z + XZ), Blocks.AIR.defaultBlockState(), 3);
                                }
                            } else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.NORTH || (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.SOUTH) {
                                if ((world.getBlockState(BlockPos.containing(x + XZ, y + Y, z))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/pickaxe"))) && !(world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BlockTags.create(new ResourceLocation(list.getString())))) {
                                    BlockPos newPos = new BlockPos((int) x + XZ, (int) y + Y, (int) z);
                                    BlockState newState = world.getBlockState(BlockPos.containing(x + XZ, y + Y, z));
                                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                    });
                                    pState.getBlock().playerDestroy(world, (Player) entity, newPos, newState, null, pStack);
                                    world.setBlock(BlockPos.containing(x + XZ, y + Y, z), Blocks.AIR.defaultBlockState(), 3);
                                }
                            } else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.UP || (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.DOWN) {
                                if ((world.getBlockState(BlockPos.containing(x + XZ, y, z + Y))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/pickaxe"))) && !(world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BlockTags.create(new ResourceLocation(list.getString())))) {
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
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
}
