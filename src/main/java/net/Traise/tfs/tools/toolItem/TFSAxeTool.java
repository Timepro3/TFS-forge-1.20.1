package net.Traise.tfs.tools.toolItem;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolActions;

import java.util.Optional;

public class TFSAxeTool extends TFSBaseItem {
    public TFSAxeTool(Properties pProperties) {
        super(BlockTags.MINEABLE_WITH_AXE, 2, 8, -1.5f, ToolActions.DEFAULT_AXE_ACTIONS, pProperties);
    }



    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier",  - 4 + Math.max(0.1f, attackSpeed + MaterialStorageHandler.getAllAttackSpeed(stack) + (MaterialStorageHandler.getAllAttackDamage(stack) / 2)), AttributeModifier.Operation.ADDITION));

        this.defaultModifiers = builder.build();

        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
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
}