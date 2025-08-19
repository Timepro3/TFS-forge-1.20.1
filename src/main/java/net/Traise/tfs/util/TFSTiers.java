package net.Traise.tfs.util;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum TFSTiers {
    NONE(0, 1f, 1f, 1f, 1f, 1f),
    WOOD(0, 1f, 1f, 1f, 1f, 7f),
    STONE(0, 1.2f, 1.3f, 1.2f, 1.1f, 4f),
    COPPER(1, 1.5f, 1.9f, 2f, 1.3f, 5f),
    TIN(1, 0.9f, 1.3f, 1.3f, 1.1f, 7f),
    ZINC(1, 1.1f, 1.5f, 1.7f, 1.2f, 6f),
    GOLD(1, 0.7f, 5.5f, 1.2f, 0.9f, 8f),
    BRASS(1, 1.1f, 1.5f, 1f, 1.3f, 7f),
    BRONZE(1, 1.1f, 1.5f, 1f, 1.4f, 7f),
    SILVER(2, 1.1f, 1.5f, 1f, 1.4f, 7f),
    IRON(2, 1.1f, 1.5f, 1f, 1.5f, 7f),
    CAST_IRON(2, 1.1f, 1.5f, 1f, 0.8f, 7f),
    STEEL(2, 1.1f, 1.5f, 1f, 1.6f, 7f),
    DIAMOND(3, 1.1f, 1.5f, 1f, 1.7f, 7f),
    NETHERITE(4, 1.1f, 1.5f, 1f, 1.8f, 7f),
    UNKNOWN_METAL(0, 0.7f, 0.9f, 0.9f, 0.9f, 3f);

    private final int Level;
    private final float Durability;
    private final float Speed;
    private final float Damage;
    private final float AttackSpeedModifier;
    private final float EnchantmentValue;

    TFSTiers(int pLevel, float pDurability, float pSpeed, float pDamage, float pAttackSpeedModifier, float pEnchantmentValue) {
        Level = pLevel;
        Durability = pDurability;
        Speed = pSpeed;
        Damage = pDamage;
        AttackSpeedModifier = pAttackSpeedModifier;
        EnchantmentValue = pEnchantmentValue;

    }

    public int getLevel() {
        return Level;
    }

    public float getDurability() {
        return Durability;
    }

    public float getSpeed() {
        return Speed;
    }

    public float getDamage() {
        return Damage;
    }

    public float getAttackSpeedModifier() {
        return AttackSpeedModifier;
    }

    public float getEnchantmentValue() {
        return EnchantmentValue;
    }
}
