package net.Traise.tfs.tools.toolItem;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.Traise.tfs.datagen.ModItemModelProvider;
import net.Traise.tfs.entity.projectile.ThrownSpear;
import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.util.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolActions;

public class TFSSpearTool extends TFSBaseItem {
    public TFSSpearTool(Properties pProperties) {
        super(ModTags.Blocks.STONE, 0, 2, -0.5f, ToolActions.DEFAULT_SWORD_ACTIONS, pProperties);
        block_reach = 1;
        entity_reach = 1;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
       return UseAnim.SPEAR;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        super.use(pLevel, pPlayer, pUsedHand);
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemStack);
        } else if (EnchantmentHelper.getRiptide(itemStack) > 0 && !pPlayer.isInWaterOrRain()) {
            return InteractionResultHolder.fail(itemStack);
        } else {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(itemStack);
        }
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
        int i = this.getUseDuration(pStack) - pTimeCharged;
        if (pLivingEntity instanceof Player player) {
            if (i >= 10) {
                if (!pLevel.isClientSide) {
                    pStack.hurtAndBreak(1, player, (p_43388_) -> {
                        p_43388_.broadcastBreakEvent(pLivingEntity.getUsedItemHand());
                    });

                    ThrownSpear thrownmodspearitem = new ThrownSpear(pLevel, player, pStack);
                    thrownmodspearitem.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 0.5f + (float) MaterialStorageHandler.getAllAttackSpeed(pStack), (MaterialStorageHandler.getAllMineSpeed(pStack) / (MaterialStorageHandler.getMineLevel(pStack) + 1) - 1 ) < 0 ? 0 : (float) (MaterialStorageHandler.getAllMineSpeed(pStack) / (MaterialStorageHandler.getMineLevel(pStack) + 1) - 1));
                    System.out.println("Vel: " + (0.5f + (float) MaterialStorageHandler.getAllAttackSpeed(pStack)) + " | Ina: " + ((MaterialStorageHandler.getAllMineSpeed(pStack) / (MaterialStorageHandler.getMineLevel(pStack) + 1) - 1 ) < 0 ? 0 : (float) (MaterialStorageHandler.getAllMineSpeed(pStack) / (MaterialStorageHandler.getMineLevel(pStack) + 1) - 1)));
                    if (player.getAbilities().instabuild) {
                        thrownmodspearitem.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    pLevel.addFreshEntity(thrownmodspearitem);
                    pLevel.playSound(null, thrownmodspearitem, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        player.getInventory().removeItem(pStack);
                    }
                }
            }
        }
    }

    @Override
    public boolean isWeapon(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean canAction(ItemStack pStack) {
        return false;
    }

    /*@Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", 1, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", 1, AttributeModifier.Operation.ADDITION));

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", (double)MaterialStorageHandler.getAllAttackDamage(stack) + attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)MaterialStorageHandler.getAllAttackSpeed(stack) - 4 + attackSpeed, AttributeModifier.Operation.ADDITION));

        this.defaultModifiers = builder.build();

        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }
     */

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.category.equals(EnchantmentCategory.TRIDENT) || enchantment.equals(Enchantments.MENDING)) {
            return true;
        }

        return false;
    }

    @Override
    public void generateCustomItemModel(ModItemModelProvider generator, String name) {
    }
}