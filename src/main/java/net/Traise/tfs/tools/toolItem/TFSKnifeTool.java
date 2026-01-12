package net.Traise.tfs.tools.toolItem;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolActions;

public class TFSKnifeTool extends TFSBaseItem {

    public TFSKnifeTool(Properties pProperties) {
        super(BlockTags.SWORD_EFFICIENT, 0.5f, 3, 0, ToolActions.DEFAULT_SWORD_ACTIONS, pProperties);
        block_reach = -1;
        entity_reach = -1;
    }

    @Override
    public boolean isWeapon(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean canAction(ItemStack pStack) {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", block_reach, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", entity_reach, AttributeModifier.Operation.ADDITION));

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", MaterialStorageHandler.getAllAttackDamage(stack) + MaterialStorageHandler.getAllAttackSpeed(stack) - 4 + attackSpeed, AttributeModifier.Operation.ADDITION));

        this.defaultModifiers = builder.build();

        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        double x = pPos.getX();
        double y = pPos.getY();
        double z = pPos.getZ();
        if (pState.getBlock() instanceof TallGrassBlock || pState.getBlock() instanceof DoublePlantBlock) {
            pLevel.addFreshEntity(new ItemEntity(pLevel, x + 0.5f, y + 0.5f, z + 0.5f, new ItemStack(TFSItems.GRASS_CUT.get())));
            pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, entity);
    }
}