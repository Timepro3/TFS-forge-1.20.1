package net.Traise.tfs.worldgen;

import net.Traise.tfs.tfs;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.matchesBlocks;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ROCK_PLACED_KEY = registerKey("rock_placed");

    public static final ResourceKey<PlacedFeature> DIAMOND_VEINS_PLACED_KEY = registerKey("diamond_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_DIAMOND_VEINS_PLACED_KEY = registerKey("big_diamond_veins_placed");
    public static final ResourceKey<PlacedFeature> DIAMOND_PLACED_KEY = registerKey("diamond_placed");
    public static final ResourceKey<PlacedFeature> EMERALD_VEINS_PLACED_KEY = registerKey("emerald_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_EMERALD_VEINS_PLACED_KEY = registerKey("big_emerald_veins_placed");
    public static final ResourceKey<PlacedFeature> EMERALD_PLACED_KEY = registerKey("emerald_placed");
    public static final ResourceKey<PlacedFeature> LAZULI_VEINS_PLACED_KEY = registerKey("lazuli_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_LAZULI_VEINS_PLACED_KEY = registerKey("big_lazuli_veins_placed");
    public static final ResourceKey<PlacedFeature> LAZULI_PLACED_KEY = registerKey("lazuli_placed");
    public static final ResourceKey<PlacedFeature> CINNABAR_VEINS_PLACED_KEY = registerKey("cinnabar_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_CINNABAR_VEINS_PLACED_KEY = registerKey("big_cinnabar_veins_placed");
    public static final ResourceKey<PlacedFeature> CINNABAR_PLACED_KEY = registerKey("cinnabar_placed");
    public static final ResourceKey<PlacedFeature> COAL_VEINS_PLACED_KEY = registerKey("coal_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_COAL_VEINS_PLACED_KEY = registerKey("big_coal_veins_placed");
    public static final ResourceKey<PlacedFeature> COAL_PLACED_KEY = registerKey("coal_placed");
    public static final ResourceKey<PlacedFeature> CUPRITE_VEINS_PLACED_KEY = registerKey("cuprite_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_CUPRITE_VEINS_PLACED_KEY = registerKey("big_cuprite_veins_placed");
    public static final ResourceKey<PlacedFeature> CUPRITE_PLACED_KEY = registerKey("cuprite_placed");
    public static final ResourceKey<PlacedFeature> LIMONITE_VEINS_PLACED_KEY = registerKey("limonite_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_LIMONITE_VEINS_PLACED_KEY = registerKey("big_limonite_veins_placed");
    public static final ResourceKey<PlacedFeature> LIMONITE_PLACED_KEY = registerKey("limonite_placed");
    public static final ResourceKey<PlacedFeature> TIN_VEINS_PLACED_KEY = registerKey("tin_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_TIN_VEINS_PLACED_KEY = registerKey("big_tin_veins_placed");
    public static final ResourceKey<PlacedFeature> TIN_PLACED_KEY = registerKey("tin_placed");
    public static final ResourceKey<PlacedFeature> ZINC_VEINS_PLACED_KEY = registerKey("zinc_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_ZINC_VEINS_PLACED_KEY = registerKey("big_zinc_veins_placed");
    public static final ResourceKey<PlacedFeature> ZINC_PLACED_KEY = registerKey("zinc_placed");
    public static final ResourceKey<PlacedFeature> GOLD_VEINS_PLACED_KEY = registerKey("gold_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_GOLD_VEINS_PLACED_KEY = registerKey("big_gold_veins_placed");
    public static final ResourceKey<PlacedFeature> GOLD_PLACED_KEY = registerKey("gold_placed");
    public static final ResourceKey<PlacedFeature> SILVER_VEINS_PLACED_KEY = registerKey("silver_veins_placed");
    public static final ResourceKey<PlacedFeature> BIG_SILVER_VEINS_PLACED_KEY = registerKey("big_silver_veins_placed");
    public static final ResourceKey<PlacedFeature> SILVER_PLACED_KEY = registerKey("silver_placed");

    public static final ResourceKey<PlacedFeature> OCEAN_ORE_PLACED_KEY = registerKey("ocean_ore_placed");
    public static final ResourceKey<PlacedFeature> RIVER_ORE_PLACED_KEY = registerKey("river_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        BlockPredicate ONLY_WATER = matchesBlocks(Blocks.WATER);

        register(context, ROCK_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ROCK_KEY),
                VegetationPlacements.worldSurfaceSquaredWithCount(10));

        {
            register(context, BIG_DIAMOND_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_DIAMOND_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, DIAMOND_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DIAMOND_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, DIAMOND_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DIAMOND_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_EMERALD_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_EMERALD_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, EMERALD_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EMERALD_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, EMERALD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EMERALD_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_LAZULI_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_LAZULI_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, LAZULI_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAZULI_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, LAZULI_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAZULI_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_CINNABAR_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_CINNABAR_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, CINNABAR_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CINNABAR_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, CINNABAR_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CINNABAR_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_COAL_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_COAL_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, COAL_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COAL_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, COAL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COAL_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_CUPRITE_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_CUPRITE_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, CUPRITE_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CUPRITE_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, CUPRITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CUPRITE_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_LIMONITE_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_LIMONITE_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, LIMONITE_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIMONITE_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, LIMONITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIMONITE_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_TIN_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_TIN_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, TIN_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, TIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_ZINC_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_ZINC_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, ZINC_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, ZINC_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_GOLD_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_GOLD_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, GOLD_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLD_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, GOLD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLD_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

            register(context, BIG_SILVER_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_SILVER_VEINS_KEY),
                    commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            register(context, SILVER_VEINS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_VEINS_KEY),
                    commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(320))));
            PlacementUtils.register(context, SILVER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_KEY),
                    CountPlacement.of(UniformInt.of(3, 21)), InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP,
                            BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                    RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());
        }

        register(context, OCEAN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OCEAN_ORE_KEY),
                commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(45), VerticalAnchor.absolute(62))));

        register(context, RIVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RIVER_ORE_KEY),
                commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(45), VerticalAnchor.absolute(62))));

    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(tfs.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

}
