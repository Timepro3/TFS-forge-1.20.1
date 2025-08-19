package net.Traise.tfs.item.custom;

import com.google.common.collect.ImmutableMultimap;
import net.Traise.tfs.util.TFSTiers;
import net.Traise.tfs.util.TFSToolType;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;

public class TFSToolItem extends Item {
    private static final int BaseDurability = 100;
    private static final float BaseSpeed = 1;
    private static final float BaseDamage = 1;
    private static final float BaseEnchantmentValue = 1;
    private static final float BaseAttackSpeedModifier = 1;
    private final TagKey<Block> blocks;
    private final Tier tier;
    private final TFSToolType toolType;
    private int Level = 0;
    private float Durability;
    private float Speed;
    private float Damage;
    private float AttackSpeedModifier;
    private float EnchantmentValue;

    public TFSToolItem(NonNullList<TFSTiers> pTiers, TagKey<Block> pBlocks, TFSToolType PToolType, Properties pProperties) {
        super(pProperties.durability(BaseDurability));

        blocks = pBlocks;
        toolType = PToolType;
        Durability = BaseDurability;
        Speed = BaseSpeed;
        Damage = BaseDamage;
        AttackSpeedModifier = BaseAttackSpeedModifier;
        EnchantmentValue = BaseEnchantmentValue;

        if (Level < pTiers.get(0).getLevel()) {
            Level = pTiers.get(0).getLevel();
        }

        for (int i = 0; i < pTiers.size(); i++) {
            Durability *= pTiers.get(i).getDurability();
            Speed *= pTiers.get(i).getSpeed();
            Damage *= pTiers.get(i).getDamage();
            AttackSpeedModifier *= pTiers.get(i).getAttackSpeedModifier();
            EnchantmentValue *= pTiers.get(i).getEnchantmentValue();
        }

        tier = new Tier() {
            @Override
            public int getUses() {
                return (int) Durability;
            }

            @Override
            public float getSpeed() {
                return Speed;
            }

            @Override
            public float getAttackDamageBonus() {
                return Damage;
            }

            @Override
            public int getLevel() {
                return Level;
            }

            @Override
            public int getEnchantmentValue() {
                return (int) EnchantmentValue;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }
        };
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", this.Damage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", this.AttackSpeedModifier, AttributeModifier.Operation.ADDITION));
        pProperties.durability((int) Durability);
    }

    private static final NonNullList<TFSTiers> pTiers = NonNullList.withSize(5, TFSTiers.IRON);
    public TFSToolItem() {
        this(pTiers, BlockTags.MINEABLE_WITH_HOE, TFSToolType.PICKAXE, new Properties());


    }

    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return pState.is(this.blocks) ? this.Speed : 1.0F;
    }

    public int getEnchantmentValue() {
        return (int) this.EnchantmentValue;
    }

    @Deprecated // FORGE: Use stack sensitive variant below
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        if (net.minecraftforge.common.TierSortingRegistry.isTierSorted(tier)) {
            return net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(tier, pBlock) && pBlock.is(this.blocks);
        }
        int i = this.tier.getLevel();
        if (i < 3 && pBlock.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return false;
        } else if (i < 2 && pBlock.is(BlockTags.NEEDS_IRON_TOOL)) {
            return false;
        } else {
            return i < 1 && pBlock.is(BlockTags.NEEDS_STONE_TOOL) ? false : pBlock.is(this.blocks);
        }
    }

    // FORGE START
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(blocks) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(tier, state);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        if (toolType.isSame(TFSToolType.SWORD) || toolType.isSame(TFSToolType.KNIFE) || toolType.isSame(TFSToolType.SPEAR)) {
            return ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
        } else if (toolType.isSame(TFSToolType.AXE)) {
            return ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
        } else if (toolType.isSame(TFSToolType.SHOVEL) || toolType.isSame(TFSToolType.SPADE)) {
            return ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
        } else if (toolType.isSame(TFSToolType.HOE) || toolType.isSame(TFSToolType.SICKLE)) {
            return ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction);
        } else if (toolType.isSame(TFSToolType.PICKAXE) || toolType.isSame(TFSToolType.HAMMER)) {
            return ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
        } else {
            return false;
        }

    }
}
