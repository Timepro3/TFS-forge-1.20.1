package net.Traise.tfs.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.Traise.tfs.datagen.ModItemModelProvider;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.tools.TFSToolMaterial;
import net.Traise.tfs.util.ICustomItemModel;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

@Mod.EventBusSubscriber
public class TFSBaseProjectileItem extends ProjectileWeaponItem implements Vanishable, ICustomItemModel {
    public Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID BASE_BLOCK_RANGE_UUID = UUID.fromString("7f7dbdb2-0d0d-458a-aa40-ac7633691f66");
    protected static final UUID BASE_ENTITY_RANGE_UUID = UUID.fromString("7f6dbdb1-0d0d-438a-aa40-ac7622691f56");
    public final float mineSpeed;
    public final float attackDamage;
    public final float attackSpeed;

    public TFSBaseProjectileItem(float mineSpeed, float attackDamage, float attackSpeed, Properties pProperties) {
        super(pProperties.durability(100));

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
        this.mineSpeed = mineSpeed;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

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

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public boolean isWeapon(ItemStack pStack) {
        return false;
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(isWeapon(pStack) ? 1 : 2, pAttacker, (p_41007_) -> {
            p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).hurtEnemy(pStack, pTarget, pAttacker);
        }
        return true;
    }

    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(isWeapon(pStack) ? 2 : 1, entity, (p_40992_) -> {
                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

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

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return MaterialStorageHandler.getAllDurability(stack);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return MaterialStorageHandler.getAllEnchantmentValue(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.equals(Enchantments.MENDING) || enchantment.equals(EnchantmentCategory.VANISHABLE)) {
            return true;
        }

        return false;
    }

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
        generator.withExistingParent(name, "item/handheld")
                .texture("layer0", generator.modLoc("item/tools/" + name + "_stick"))
                .texture("layer1", generator.modLoc("item/tools/" + name + "_head"))
                .texture("layer2", generator.modLoc("item/tools/" + name + "_rope"));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }
}