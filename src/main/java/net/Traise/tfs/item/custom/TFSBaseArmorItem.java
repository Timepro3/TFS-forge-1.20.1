package net.Traise.tfs.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.Traise.tfs.datagen.ModItemModelProvider;
import net.Traise.tfs.item.TFSArmorMaterials;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.tools.TFSToolMaterial;
import net.Traise.tfs.tools.toolItem.TFSArmorTool;
import net.Traise.tfs.util.ICustomItemModel;
import net.Traise.tfs.util.ModTags;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.NonNullList;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.EnumMap;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber
public class TFSBaseArmorItem extends ArmorItem implements ICustomItemModel {
    public static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266744_) -> {
        p_266744_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        p_266744_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        p_266744_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        p_266744_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    public Multimap<Attribute, AttributeModifier> defaultModifiers;
    public final TagKey<Block> blocks;
    public final float mineSpeed;
    public final float attackDamage;
    public final float attackSpeed;
    public final ArmorItem.Type type;
    public static final int[] BASE_DURABILITY = { 13, 16, 16, 11 };
    public static final float[] BASE_DEFENSE = { 0.4f, 1f, 0.75f, 0.3f };

    public TFSBaseArmorItem(ArmorItem.Type pType, TagKey<Block> pBlocks, float mineSpeed, float attackDamage, float attackSpeed, Properties pProperties) {
        super(TFSArmorMaterials.NONE, pType, pProperties);
        DispenserBlock.registerBehavior(this, DISPENSE_ITEM_BEHAVIOR);
        this.type = pType;
        this.blocks = pBlocks;
        this.mineSpeed = mineSpeed;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

    public static final DispenseItemBehavior DISPENSE_ITEM_BEHAVIOR = new DefaultDispenseItemBehavior() {
        protected ItemStack execute(BlockSource p_40408_, ItemStack p_40409_) {
            return ArmorItem.dispenseArmor(p_40408_, p_40409_) ? p_40409_ : super.execute(p_40408_, p_40409_);
        }
    };

    /*
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.isArmor()) {
                if ((slot.equals(EquipmentSlot.HEAD) && event.getSource().is(DamageTypeTags.DAMAGES_HELMET)) || !event.getSource().is(DamageTypeTags.BYPASSES_ARMOR)) {
                    ItemStack armorStack = entity.getItemBySlot(slot);
                    if (armorStack.isEmpty()) continue;

                    if (armorStack.getItem() instanceof TFSArmorTool) {

                        if (armorStack.isDamageableItem()) {
                            armorStack.hurtAndBreak(1, entity, (player) -> {
                                player.broadcastBreakEvent(slot);
                            });
                        }
                    }
                }
            }
        }
    }*/

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
        }
    }

    @Override
    public boolean isFireResistant() {
        return super.isFireResistant();
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(itemStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).use(pLevel, pPlayer, pUsedHand);
        }

        return this.swapWithEquipmentSlot(this, pLevel, pPlayer, pUsedHand);
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }

    public boolean isDefense(ItemStack pStack) {
        return true;
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).hurtEnemy(pStack, pTarget, pAttacker);
        }
        return true;
    }

    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).mineBlock(pStack, pLevel, pState, pPos, entity);
        }
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        ItemStack pStack = context.getItemInHand();

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).useOn(context);
        }
        return InteractionResult.PASS;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(this.type);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", getDefense(stack), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", getToughness(stack), AttributeModifier.Operation.ADDITION));
        if (getKnockbackResistance(stack) > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", getKnockbackResistance(stack), AttributeModifier.Operation.ADDITION));
        }

        this.defaultModifiers = builder.build();
        return slot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    public int getDefense(ItemStack stack) {
        return Math.max((int) Math.round(BASE_DEFENSE[this.type.ordinal()] * MaterialStorageHandler.getAllAttackDamage(stack)), 1);
    }

    public float getToughness(ItemStack stack) {
        float g = (float) Math.pow(2, MaterialStorageHandler.getMineLevel(stack) - 6);
        return g < 0.5f ? 0 : g;
    }

    public float getKnockbackResistance(ItemStack stack) {
        return Math.max(0, ((MaterialStorageHandler.getMineLevel(stack) - 6) / 10));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return BASE_DURABILITY[this.type.ordinal()] * (this.getMaxDamages(stack) / 100);
    }

    public int getMaxDamages(ItemStack stack) {
        return MaterialStorageHandler.getAllDurability(stack);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return MaterialStorageHandler.getAllEnchantmentValue(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (isDefense(stack) && enchantment.category.equals(EnchantmentCategory.ARMOR)) {
            return true;
        } else if (this.type.equals(Type.BOOTS) || enchantment.equals(EnchantmentCategory.ARMOR_FEET)) {
            return true;
        } else if (this.type.equals(Type.LEGGINGS) || enchantment.equals(EnchantmentCategory.ARMOR_LEGS)) {
            return true;
        } else if (this.type.equals(Type.CHESTPLATE) || enchantment.equals(EnchantmentCategory.ARMOR_CHEST)) {
            return true;
        } else if (this.type.equals(Type.HELMET) || enchantment.equals(EnchantmentCategory.ARMOR_HEAD)) {
            return true;
        } else if (enchantment.equals(Enchantments.MENDING) || enchantment.equals(EnchantmentCategory.VANISHABLE)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return false;
    }

    public static int getColor(ItemStack itemStack, int Index) {
        if (MaterialStorageHandler.getSize(itemStack) > Index) {
            return MaterialStorageHandler.load(itemStack).get(Index).color;
        } else {
            return -1;
        }
    }

    @Override
    public void generateCustomItemModel(ModItemModelProvider generator, String name) {
      /*  generator.withExistingParent(name, "item/handheld")
                .texture("layer0", generator.modLoc("item/tools/" + name + "_r_part"))
                .texture("layer1", generator.modLoc("item/tools/" + name + "_main_part"))
                .texture("layer2", generator.modLoc("item/tools/" + name + "_l_part"));*/
    }
}