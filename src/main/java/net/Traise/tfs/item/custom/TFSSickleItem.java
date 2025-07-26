package net.Traise.tfs.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class TFSSickleItem extends DiggerItem {

    public TFSSickleItem(Tier pTier, Properties pProperties) {
        super(2, -2.2f, pTier, BlockTags.MINEABLE_WITH_HOE, pProperties.durability(pTier.getUses() * 9));
    }

    public boolean mineBlock(ItemStack pStack, Level world, BlockState pState, BlockPos pPos, LivingEntity entity) {
        pStack.hurtAndBreak(1, entity, (p_40992_) -> {
            p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        double x = pPos.getX();
        double y = pPos.getY();
        double z = pPos.getZ();
        double Tx;
        double Ty;
        double Tz;

        if (!entity.isShiftKeyDown()){
            if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("tfs:plants_hoe")))) {
                for (int G = 1; G <= 5; G++) {
                    for (int i = 1; i <= 25; i++) {
                        Tx = x;
                        Ty = y;
                        Tz = z;

                        if (i <= 5) {
                            Tx = x + 2;
                        } else if (i <= 10) {
                            Tx = x + 1;
                        } else if (i <= 15) {
                            Tx = x;
                        } else if (i <= 20) {
                            Tx = x - 1;
                        } else if (i <= 25) {
                            Tx = x - 2;
                        }

                        if (G == 1) {
                            Ty = y - 2;
                        } else if (G == 2) {
                            Ty = y - 1;
                        } else if (G == 3) {
                            Ty = y;
                        } else if (G == 4) {
                            Ty = y + 1;
                        } else if (G == 5) {
                            Ty = y + 2;
                        }

                        if (i == 0 + 1 || i == 5 + 1 || i == 10 + 1 || i == 15 + 1 || i == 20 + 1) {
                            Tz = z - 2;
                        }
                        if (i == 0 + 2 || i == 5 + 2 || i == 10 + 2 || i == 15 + 2 || i == 20 + 2) {
                            Tz = z - 1;
                        }
                        if (i == 0 + 3 || i == 5 + 3 || i == 10 + 3 || i == 15 + 3 || i == 20 + 3) {
                            Tz = z;
                        }
                        if (i == 0 + 4 || i == 5 + 4 || i == 10 + 4 || i == 15 + 4 || i == 20 + 4) {
                            Tz = z + 1;
                        }
                        if (i == 0 + 5 || i == 5 + 5 || i == 10 + 5 || i == 15 + 5 || i == 20 + 5) {
                            Tz = z + 2;
                        }

                        if ((world.getBlockState(BlockPos.containing(Tx, Ty, Tz))).is(BlockTags.create(new ResourceLocation("tfs:plants_hoe")))) {
                            BlockPos newPos = new BlockPos((int)Tx, (int)Ty, (int)Tz);
                            BlockState newState = world.getBlockState(BlockPos.containing(Tx, Ty, Tz));
                            pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                            });
                            pState.getBlock().playerDestroy(world, (Player) entity, newPos, newState, null, pStack);
                            world.setBlock(BlockPos.containing(Tx, Ty, Tz), Blocks.AIR.defaultBlockState(), 3);
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
        Level world = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        BlockState pState = world.getBlockState(BlockPos.containing(x, y, z));
        LivingEntity entity = context.getPlayer();
        ItemStack pStack = context.getItemInHand();
        double Tx;
        double Tz;

        if (!entity.isShiftKeyDown()) {
            for (int i = 1; i <= 25; i++) {
                Tx = x;
                Tz = z;

                if (i <= 5) {
                    Tx = x + 2;
                } else if (i <= 10) {
                    Tx = x + 1;
                } else if (i <= 15) {
                    Tx = x;
                } else if (i <= 20) {
                    Tx = x - 1;
                } else if (i <= 25) {
                    Tx = x - 2;
                }


                if (i == 0 + 1 || i == 5 + 1 || i == 10 + 1 || i == 15 + 1 || i == 20 + 1) {
                    Tz = z - 2;
                }
                if (i == 0 + 2 || i == 5 + 2 || i == 10 + 2 || i == 15 + 2 || i == 20 + 2) {
                    Tz = z - 1;
                }
                if (i == 0 + 3 || i == 5 + 3 || i == 10 + 3 || i == 15 + 3 || i == 20 + 3) {
                    Tz = z;
                }
                if (i == 0 + 4 || i == 5 + 4 || i == 10 + 4 || i == 15 + 4 || i == 20 + 4) {
                    Tz = z + 1;
                }
                if (i == 0 + 5 || i == 5 + 5 || i == 10 + 5 || i == 15 + 5 || i == 20 + 5) {
                    Tz = z + 2;
                }

                BlockState block = world.getBlockState(BlockPos.containing(Tx, y, Tz));
                int Y = 7;

                if ((world.getBlockState(BlockPos.containing(Tx, y, Tz))).is(BlockTags.create(new ResourceLocation("tfs:age_3")))) {
                    Y = 3;
                }

                if (((world.getBlockState(BlockPos.containing(Tx, y, Tz))).getBlock().getStateDefinition().getProperty("age") instanceof IntegerProperty _getip1 ? (world.getBlockState(BlockPos.containing(Tx, y, Tz))).getValue(_getip1) : -1) == Y) {
                    BlockPos newPos = new BlockPos((int)Tx, (int)y, (int)Tz);
                    BlockState newState = world.getBlockState(BlockPos.containing(Tx, y, Tz));
                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                    });
                    pState.getBlock().playerDestroy(world, (Player) entity, newPos, newState, null, pStack);
                    world.setBlock(BlockPos.containing(Tx, y, Tz), Blocks.AIR.defaultBlockState(), 3);
                    world.setBlock(BlockPos.containing(Tx, y, Tz), (new Object() {
                        public BlockState with(BlockState _bs, String _property, int _newValue) {
                            Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty(_property);
                            return _prop instanceof IntegerProperty _ip && _prop.getPossibleValues().contains(_newValue) ? _bs.setValue(_ip, _newValue) : _bs;
                        }
                    }.with(block, "age", 0)), 3);
                }
            }
        }


        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction);
    }
}
