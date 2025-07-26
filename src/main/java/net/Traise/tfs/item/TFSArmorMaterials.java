package net.Traise.tfs.item;

import net.Traise.tfs.tfs;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum TFSArmorMaterials implements ArmorMaterial {
    SILVER("silver", 14, new int[]{ 2, 5, 4, 1}, 17,
            SoundEvents.ARMOR_EQUIP_GOLD, 0f, 0f, () -> Ingredient.of(TFSItems.SILVER_INGOT.get())),

    BRONZE("bronze", 13, new int[]{ 2, 4, 3, 1}, 9,
    SoundEvents.ARMOR_EQUIP_GOLD, 0f, 0f, () -> Ingredient.of(TFSItems.BRONZE_INGOT.get())),

    STEEL("steel", 21, new int[]{ 3, 7, 5, 2}, 10,
    SoundEvents.ARMOR_EQUIP_IRON, 1f, 0f, () -> Ingredient.of(TFSItems.STEEL_INGOT.get()));


    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 13, 16, 16, 11 };

    TFSArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return tfs.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
