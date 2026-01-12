package net.Traise.tfs.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.Traise.tfs.datagen.ModItemModelProvider;
import net.Traise.tfs.entity.projectile.ThrownSpear;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.tools.TFSToolMaterial;
import net.Traise.tfs.tools.TFSToolMaterials;
import net.Traise.tfs.util.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Set.of;
import static net.minecraft.world.item.HoeItem.changeIntoState;

@Mod.EventBusSubscriber
public class TFSBaseItem extends Item implements Vanishable, ICustomItemModel {
    public Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID BASE_BLOCK_RANGE_UUID = UUID.fromString("7f7dbdb2-0d0d-458a-aa40-ac7633691f66");
    protected static final UUID BASE_ENTITY_RANGE_UUID = UUID.fromString("7f6dbdb1-0d0d-438a-aa40-ac7622691f56");
    public final TagKey<Block> blocks;
    public final float mineSpeed;
    public final float attackDamage;
    public final float attackSpeed;
    public final Set<ToolAction> actions;

    public float entity_reach = 0;
    public float block_reach = 0;

    public TFSBaseItem(TagKey<Block> pBlocks, float mineSpeed, float attackDamage, float attackSpeed, Set<ToolAction> actions, Properties pProperties) {
        super(pProperties.durability(100));

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
        this.blocks = pBlocks;
        this.mineSpeed = mineSpeed;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.actions = actions;
    }

    /*@SubscribeEvent
    public void onItemExpire(TickEvent.ClientTickEvent event) {
        Item item = event.getEntity().getItem().getItem();
        if (item instanceof TFSBaseItem) {
            event.setCanceled(true);
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

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return pState.is(blocks) ? (float) (MaterialStorageHandler.getAllMineSpeed(pStack) + mineSpeed <= 1 ? 1 : MaterialStorageHandler.getAllMineSpeed(pStack) + mineSpeed) : 1.0F;
    }

    public boolean isWeapon(ItemStack pStack) {
        return false;
    }

    public boolean canAction(ItemStack pStack) {
        return true;
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
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", Math.max(0.1f, (MaterialStorageHandler.getAllAttackDamage(stack) + attackDamage)), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", Math.max(-3.9f, (MaterialStorageHandler.getAllAttackSpeed(stack) - 4 + attackSpeed)), AttributeModifier.Operation.ADDITION));

        if (entity_reach != 0) {
            builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", entity_reach, AttributeModifier.Operation.ADDITION));
        }

        if (block_reach != 0) {
            builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", block_reach, AttributeModifier.Operation.ADDITION));
        }

        this.defaultModifiers = builder.build();

        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    public boolean isDigger(ItemStack item) {
        return true;
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
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (isWeapon(stack) && enchantment.category.equals(EnchantmentCategory.WEAPON)) {
            return true;
        } else if (!isWeapon(stack) && enchantment.category.equals(EnchantmentCategory.DIGGER) && isDigger(stack)) {
            return true;
        } else if (enchantment.equals(Enchantments.MENDING)) {
            return true;
        } else if (enchantment.equals(EnchantmentCategory.VANISHABLE)) {
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
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        int i = MaterialStorageHandler.getMineLevel(stack);
        if (i < 7 && state.is(ModTags.Blocks.TFS_NEEDS_NETHERITE_TOOL)) {
            return false;
        } else if (i < 6 && state.is(ModTags.Blocks.TFS_NEEDS_DIAMOND_TOOL)) {
            return false;
        } else if (i < 5 && state.is(ModTags.Blocks.TFS_NEEDS_STEEL_TOOL)) {
            return false;
        } else if (i < 4 && state.is(ModTags.Blocks.TFS_NEEDS_IRON_TOOL)) {
            return false;
        } else if (i < 3 && state.is(ModTags.Blocks.TFS_NEEDS_BRONZE_TOOL)) {
            return false;
        } else if (i < 2 && state.is(ModTags.Blocks.TFS_NEEDS_COPPER_TOOL)) {
            return false;
        } else {
            return i < 1 && state.is(ModTags.Blocks.TFS_NEEDS_STONE_TOOL) ? false : state.is(blocks);
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (canAction(stack)) {
            return actions.contains(toolAction);
        } return false;
    }

    @Override
    public void generateCustomItemModel(ModItemModelProvider generator, String name) {
        generator.withExistingParent(name, "item/handheld")
                .texture("layer0", generator.modLoc("item/tools/" + name + "_stick"))
                .texture("layer1", generator.modLoc("item/tools/" + name + "_head"))
                .texture("layer2", generator.modLoc("item/tools/" + name + "_rope"));
    }
}