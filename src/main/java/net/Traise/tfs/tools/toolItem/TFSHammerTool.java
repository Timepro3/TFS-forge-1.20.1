package net.Traise.tfs.tools.toolItem;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolActions;

public class TFSHammerTool extends TFSBaseItem {
    public TFSHammerTool(Properties pProperties) {
        super(BlockTags.MINEABLE_WITH_PICKAXE, 0.5f, 5, 0.4f, ToolActions.DEFAULT_PICKAXE_ACTIONS, pProperties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return super.getMaxDamage(stack) * 3;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", attackDamage + MaterialStorageHandler.getAllAttackDamage(stack) + (MaterialStorageHandler.getAllAttackSpeed(stack) * 2), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier",  - 4 + attackSpeed, AttributeModifier.Operation.ADDITION));

        this.defaultModifiers = builder.build();

        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        super.mineBlock(pStack, pLevel, pState, pPos, entity);
        double x = pPos.getX();
        double y = pPos.getY();
        double z = pPos.getZ();

        if (!entity.isShiftKeyDown()) {
            if (pState.is(this.blocks)) {
                for (int XZ = -1; XZ <= 1; XZ++) {
                    for (int Y = -1; Y <= 1; Y++) {
                        if (!(XZ == 0 && Y == 0)) {
                            if (entity.pick(20.0D, 0.0F, false) instanceof BlockHitResult blockHitResult) {
                                if (blockHitResult.getDirection().equals(Direction.EAST) || blockHitResult.getDirection().equals(Direction.WEST)) {
                                    if ((pLevel.getBlockState(BlockPos.containing(x, y + Y, z + XZ))).is(this.blocks)) {
                                        BlockPos newPos = new BlockPos((int) x, (int) y + Y, (int) z + XZ);
                                        BlockState newState = pLevel.getBlockState(BlockPos.containing(x, y + Y, z + XZ));
                                        pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                            p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                        });
                                        pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                        pLevel.setBlock(BlockPos.containing(x, y + Y, z + XZ), Blocks.AIR.defaultBlockState(), 3);
                                        pLevel.playSound((Player) entity, x, y + Y, z + XZ, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                    }
                                } else if (blockHitResult.getDirection().equals(Direction.NORTH) || blockHitResult.getDirection().equals(Direction.SOUTH)) {
                                    if ((pLevel.getBlockState(BlockPos.containing(x + XZ, y + Y, z))).is(this.blocks)) {
                                        BlockPos newPos = new BlockPos((int) x + XZ, (int) y + Y, (int) z);
                                        BlockState newState = pLevel.getBlockState(BlockPos.containing(x + XZ, y + Y, z));
                                        pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                            p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                        });
                                        pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                        pLevel.setBlock(BlockPos.containing(x + XZ, y + Y, z), Blocks.AIR.defaultBlockState(), 3);
                                        pLevel.playSound((Player) entity, x + XZ, y + Y, z, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                    }
                                } else if (blockHitResult.getDirection().equals(Direction.UP) || blockHitResult.getDirection().equals(Direction.DOWN)) {
                                    if ((pLevel.getBlockState(BlockPos.containing(x + XZ, y, z + Y))).is(this.blocks)) {
                                        BlockPos newPos = new BlockPos((int) x + XZ, (int) y, (int) z + Y);
                                        BlockState newState = pLevel.getBlockState(BlockPos.containing(x + XZ, y, z + Y));
                                        pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                            p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                        });
                                        pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                        pLevel.setBlock(BlockPos.containing(x + XZ, y, z + Y), Blocks.AIR.defaultBlockState(), 3);
                                        pLevel.playSound((Player) entity, x + XZ, y, z + Y, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}