package net.Traise.tfs.worldgen.biome;

import net.Traise.tfs.tfs;
import net.Traise.tfs.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

public class ModBiomes {
    public static final ResourceKey<Biome> DIAMOND_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "diamond_deposit"));
    public static final ResourceKey<Biome> EMERALD_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "emerald_deposit"));
    public static final ResourceKey<Biome> LAZULI_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "lazuli_deposit"));
    public static final ResourceKey<Biome> CINNABAR_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "cinnabar_deposit"));
    public static final ResourceKey<Biome> COAL_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "coal_deposit"));
    public static final ResourceKey<Biome> CUPRITE_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "cuprite_deposit"));
    public static final ResourceKey<Biome> LIMONITE_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "limonite_deposit"));
    public static final ResourceKey<Biome> TIN_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "tin_deposit"));
    public static final ResourceKey<Biome> ZINC_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "zinc_deposit"));
    public static final ResourceKey<Biome> GOLD_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "gold_deposit"));
    public static final ResourceKey<Biome> SILVER_DEPOSIT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tfs.MOD_ID, "silver_deposit"));

    protected static int calculateSkyColor(float pTemperature) {
        float $$1 = pTemperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(boolean pHasPercipitation, float pTemperature, float pDownfall, MobSpawnSettings.Builder pMobSpawnSettings, BiomeGenerationSettings.Builder pGenerationSettings, @Nullable Music pBackgroundMusic) {
        return biome(pHasPercipitation, pTemperature, pDownfall, 4159204, 329011, (Integer)null, (Integer)null, pMobSpawnSettings, pGenerationSettings, pBackgroundMusic);
    }

    private static Biome biome(boolean pHasPrecipitation, float pTemperature, float pDownfall, int pWaterColor, int pWaterFogColor, @Nullable Integer pGrassColorOverride, @Nullable Integer pFoliageColorOverride, MobSpawnSettings.Builder pMobSpawnSettings, BiomeGenerationSettings.Builder pGenerationSettings, @Nullable Music pBackgroundMusic) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = (new BiomeSpecialEffects.Builder()).waterColor(pWaterColor).waterFogColor(pWaterFogColor).fogColor(12638463).skyColor(calculateSkyColor(pTemperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(pBackgroundMusic);
        if (pGrassColorOverride != null) {
            biomespecialeffects$builder.grassColorOverride(pGrassColorOverride);
        }

        if (pFoliageColorOverride != null) {
            biomespecialeffects$builder.foliageColorOverride(pFoliageColorOverride);
        }

        return (new Biome.BiomeBuilder()).hasPrecipitation(pHasPrecipitation).temperature(pTemperature).downfall(pDownfall).specialEffects(biomespecialeffects$builder.build()).mobSpawnSettings(pMobSpawnSettings.build()).generationSettings(pGenerationSettings.build()).build();
    }

    public static void boostrap(BootstapContext<Biome> context) {
        context.register(DIAMOND_DEPOSIT, diamondDeposit(context));
        context.register(EMERALD_DEPOSIT, emeraldDeposit(context));
        context.register(LAZULI_DEPOSIT, lazuliDeposit(context));
        context.register(CINNABAR_DEPOSIT, cinnabarDeposit(context));
        context.register(COAL_DEPOSIT, coalDeposit(context));
        context.register(CUPRITE_DEPOSIT, cupriteDeposit(context));
        context.register(LIMONITE_DEPOSIT, limoniteDeposit(context));
        context.register(TIN_DEPOSIT, tinDeposit(context));
        context.register(ZINC_DEPOSIT, zincDeposit(context));
        context.register(GOLD_DEPOSIT, goldDeposit(context));
        context.register(SILVER_DEPOSIT, silverDeposit(context));
    }



    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome diamondDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.DIAMOND_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_DIAMOND_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DIAMOND_PLACED_KEY);
        return biome(true, 0.5F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome emeraldDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.EMERALD_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_EMERALD_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.EMERALD_PLACED_KEY);
        return biome(true, -0.5F, 0.4F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome lazuliDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.LAZULI_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_LAZULI_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LAZULI_PLACED_KEY);
        return biome(true, -0.2F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome cinnabarDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.CINNABAR_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_CINNABAR_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CINNABAR_PLACED_KEY);
        return biome(true, 0.3F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome coalDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COAL_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_COAL_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.COAL_PLACED_KEY);
        return biome(true, 1.7F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome cupriteDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.CUPRITE_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_CUPRITE_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CUPRITE_PLACED_KEY);
        return biome(true, 0.5F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome limoniteDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.LIMONITE_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_LIMONITE_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LIMONITE_PLACED_KEY);
        return biome(true, 0.7F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome tinDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.TIN_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_TIN_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TIN_PLACED_KEY);
        return biome(true, 0.5F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome zincDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ZINC_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_ZINC_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ZINC_PLACED_KEY);
        return biome(true, 0.8F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome goldDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.GOLD_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_GOLD_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.GOLD_PLACED_KEY);
        return biome(true, 2.0F, 0.5F, spawnBuilder, biomeBuilder, null);
    }

    public static Biome silverDeposit(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SILVER_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BIG_SILVER_VEINS_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SILVER_PLACED_KEY);
       return biome(true, -0.4F, 0.5F, spawnBuilder, biomeBuilder, null);
    }
}
