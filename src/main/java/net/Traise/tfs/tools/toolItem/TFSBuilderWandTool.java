package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.util.ITrowelable;
import net.Traise.tfs.util.ItemNBTHelper;
import net.Traise.tfs.util.MiscUtil;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolActions;

import java.util.*;

public class TFSBuilderWandTool extends TFSBaseItem {
    public TFSBuilderWandTool(Properties pProperties) {
        super(ModTags.Blocks.STONE, 0, 0, -2, ToolActions.DEFAULT_SWORD_ACTIONS, pProperties);
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
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();

        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        Block selectedBlock = pState.getBlock();

        int volume = (int)Math.pow(2, (MaterialStorageHandler.getMineLevel(pStack) + 2)); // радиус действия

        if (entity.isShiftKeyDown()) {
            pStack.getOrCreateTag().putInt("BuildMode", pStack.getOrCreateTag().getInt("BuildMode") + 1);
            if (pStack.getOrCreateTag().getInt("BuildMode") > 3) pStack.getOrCreateTag().putInt("BuildMode", 0);
            entity.displayClientMessage(
                    Component.literal("Режим: " + Component.translatable("gui.tfs." +BuildMode.getBuildModeInNumber(pStack.getOrCreateTag().getInt("BuildMode")).getName()).getString()), true);
        } else {
            pStack.getOrCreateTag().putInt("BlockCount", volume);
            placeBlock2(entity, pStack, blockpos, pLevel, selectedBlock, direction, context, BuildMode.getBuildModeInNumber(pStack.getOrCreateTag().getInt("BuildMode")));
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public boolean canAction(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isDigger(ItemStack item) {
        return false;
    }

    private void placeBlock2(Player player, ItemStack pStack, BlockPos startPos, Level pLevel, Block pBlock, Direction startDirection, UseOnContext context, BuildMode buildMode) {
        int maxBlocks = pStack.getOrCreateTag().getInt("BlockCount");
        if (maxBlocks <= 0) return;

        boolean leftBlock = false;

        Block selectedBlock = pBlock;

        if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BlockItem blockItem && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
            leftBlock = true;
            selectedBlock = blockItem.getBlock();
        }

        BlockPos initialPos = startPos.relative(startDirection);
        BlockState initialState = pLevel.getBlockState(initialPos);

        if (!(initialState.isAir() || initialState.canBeReplaced()) ||
                !selectedBlock.getStateForPlacement(new BlockPlaceContext(context)).canSurvive(pLevel, initialPos)) {
            // Если блок не может выжить на стартовой позиции, прекращаем выполнение
            return;
        }

        Direction oppositeDirection = startDirection.getOpposite();

        Deque<BlockPos> queue = new LinkedList<>();
        queue.add(initialPos);

        while (!queue.isEmpty() && maxBlocks > 0) {
            BlockPos currentPos = queue.removeFirst();

            if (pStack.getDamageValue() >= pStack.getMaxDamage()) {
                break;
            }

            ItemStack InvItem = ItemStack.EMPTY;

            for (ItemStack invStack : player.getInventory().items) {
                if (invStack.getItem() instanceof BlockItem blockItem) {
                    if (blockItem.getBlock() == selectedBlock) {
                        InvItem = invStack;
                        break;
                    }
                }
            }

            if (isEntityAtPosition(pLevel, currentPos)) {
                continue;
            }

            if (!player.isCreative()) {
                if (InvItem.isEmpty()) {
                    if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BlockItem blockItem
                            && blockItem.getBlock() == selectedBlock) {
                        InvItem = player.getItemInHand(InteractionHand.OFF_HAND);
                    } else break;
                }
            }

            if ((!pLevel.getBlockState(currentPos).canBeReplaced() ||
                    pLevel.getBlockState(currentPos).getBlock() == selectedBlock) ||
                    !pLevel.getBlockState(currentPos).getBlock().canSurvive(pLevel.getBlockState(currentPos.relative(startDirection.getOpposite())), pLevel, currentPos) || (pLevel.getBlockState(currentPos.relative(startDirection.getOpposite())).getBlock() != selectedBlock && !leftBlock) || (leftBlock && pLevel.getBlockState(currentPos.relative(startDirection.getOpposite())).canBeReplaced())) {
                continue;
            }

            if (!player.isCreative()) {
                InvItem.shrink(1);
                pStack.hurtAndBreak(1, player, (p_40992_) -> {
                    p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
            }

            pLevel.setBlock(currentPos, selectedBlock.defaultBlockState(), 3);

            maxBlocks--;
            pStack.getOrCreateTag().putInt("BlockCount", maxBlocks);


            // Расширение в стороны (кроме стартовой и противоположной)
            for (Direction dir : Direction.values()) {
                if (buildMode.equals(BuildMode.PLACE)) {
                    if (dir != startDirection && dir != oppositeDirection) {
                        BlockPos neighborPos = currentPos.relative(dir);
                        BlockState neighborState = pLevel.getBlockState(neighborPos);
                        BlockState baseState = pLevel.getBlockState(currentPos);

                        if ((neighborState.canBeReplaced() && neighborState.getBlock() != selectedBlock && selectedBlock.canSurvive(baseState, pLevel, neighborPos)) && (baseState.getBlock() == selectedBlock || (leftBlock && !baseState.isAir()))) {
                            queue.add(neighborPos);
                        }
                    }
                } else {
                    if (dir.getAxis().equals(buildMode.axis) && dir != startDirection && dir != oppositeDirection) {
                        BlockPos neighborPos = currentPos.relative(dir);
                        BlockState neighborState = pLevel.getBlockState(neighborPos);
                        BlockState baseState = pLevel.getBlockState(currentPos);

                        if ((neighborState.canBeReplaced() && neighborState.getBlock() != selectedBlock && selectedBlock.canSurvive(baseState, pLevel, neighborPos)) && (baseState.getBlock() == selectedBlock || (leftBlock && !baseState.isAir()))) {
                            queue.add(neighborPos);
                        }
                    }
                }
            }
        }
    }

    private boolean isEntityAtPosition(Level level, BlockPos pos) {
        List<Entity> entities = level.getEntities((Entity) null, new AABB(pos), entity -> {
            return !(entity instanceof ItemEntity);
        });
        return !entities.isEmpty();
    }

    public enum BuildMode {
        PLACE("place", Direction.Axis.X),
        X_LINE("x_line", Direction.Axis.X),
        Y_LINE("y_line", Direction.Axis.Y),
        Z_LINE("z_line", Direction.Axis.Z);

        private final String name;
        private final Direction.Axis axis;

        BuildMode(String pName, Direction.Axis pAxis) {
            name = pName;
            axis = pAxis;
        }

        public String getName() {
            return name;
        }

        public Direction.Axis getAxis() {
            return axis;
        }

        public static TFSBuilderWandTool.BuildMode getBuildModeInNumber(int u) {
            return switch (u) {
                case 1 -> X_LINE;
                case 2 -> Y_LINE;
                case 3 -> Z_LINE;
                default -> PLACE;
            };
        }
    }
}