package net.Traise.tfs.worldgen;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.block.custom.TFSOrePlaced;
import net.Traise.tfs.block.custom.TFSRock;
import net.Traise.tfs.block.custom.TFStwig;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_KEY = registerKey("rock");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_VEINS_KEY = registerKey("diamond_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_DIAMOND_VEINS_KEY = registerKey("big_diamond_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_VEINS_KEY = registerKey("emerald_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_EMERALD_VEINS_KEY = registerKey("big_emerald_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAZULI_VEINS_KEY = registerKey("lazuli_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_LAZULI_VEINS_KEY = registerKey("big_lazuli_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CINNABAR_VEINS_KEY = registerKey("cinnabar_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_CINNABAR_VEINS_KEY = registerKey("big_cinnabar_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_VEINS_KEY = registerKey("coal_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_COAL_VEINS_KEY = registerKey("big_coal_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CUPRITE_VEINS_KEY = registerKey("cuprite_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_CUPRITE_VEINS_KEY = registerKey("big_cuprite_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMONITE_VEINS_KEY = registerKey("limonite_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_LIMONITE_VEINS_KEY = registerKey("big_limonite_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_VEINS_KEY = registerKey("tin_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_TIN_VEINS_KEY = registerKey("big_tin_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZINC_VEINS_KEY = registerKey("zinc_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_ZINC_VEINS_KEY = registerKey("big_zinc_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_VEINS_KEY = registerKey("gold_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_GOLD_VEINS_KEY = registerKey("big_gold_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_VEINS_KEY = registerKey("silver_veins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_SILVER_VEINS_KEY = registerKey("big_silver_veins");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_KEY = registerKey("diamond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_KEY = registerKey("emerald");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAZULI_KEY = registerKey("lazuli");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CINNABAR_KEY = registerKey("cinnabar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_KEY = registerKey("coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CUPRITE_KEY = registerKey("cuprite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMONITE_KEY = registerKey("limonite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_KEY = registerKey("tin");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZINC_KEY = registerKey("zinc");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_KEY = registerKey("gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_KEY = registerKey("silver");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OCEAN_ORE_KEY = registerKey("ocean_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RIVER_ORE_KEY = registerKey("river_ore");

    /*public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_DIAMOND_KEY = registerKey("water_diamond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_EMERALD_KEY = registerKey("water_emerald");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_LAZULI_KEY = registerKey("water_lazuli");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_CINNABAR_KEY = registerKey("water_cinnabar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_COAL_KEY = registerKey("water_coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_CUPRITE_KEY = registerKey("water_cuprite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_LIMONITE_KEY = registerKey("water_limonite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_TIN_KEY = registerKey("water_tin");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_ZINC_KEY = registerKey("water_zinc");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_GOLD_KEY = registerKey("water_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_SILVER_KEY = registerKey("water_silver");*/

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        {
            register(context, ROCK_KEY, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                    PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, new RandomPatchConfiguration(1, 3, 3,
                            PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder(
                                    ).add(TFSBlocks.ROCK_OBJECT.get().defaultBlockState().setValue(TFSRock.FACING, Direction.EAST), 1
                                    ).add(TFSBlocks.ROCK_OBJECT.get().defaultBlockState().setValue(TFSRock.FACING, Direction.WEST), 1
                                    ).add(TFSBlocks.ROCK_OBJECT.get().defaultBlockState().setValue(TFSRock.FACING, Direction.NORTH), 1
                                    ).add(TFSBlocks.ROCK_OBJECT.get().defaultBlockState().setValue(TFSRock.FACING, Direction.SOUTH), 1))),
                                    BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE)))),
                    PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, new RandomPatchConfiguration(1, 3, 3,
                            PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                    new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder(
                                    ).add(TFSBlocks.TWIG_BLOCK.get().defaultBlockState().setValue(TFStwig.FACING, Direction.EAST), 1
                                    ).add(TFSBlocks.TWIG_BLOCK.get().defaultBlockState().setValue(TFStwig.FACING, Direction.WEST), 1
                                    ).add(TFSBlocks.TWIG_BLOCK.get().defaultBlockState().setValue(TFStwig.FACING, Direction.NORTH), 1
                                    ).add(TFSBlocks.TWIG_BLOCK.get().defaultBlockState().setValue(TFStwig.FACING, Direction.SOUTH), 1))),
                                    BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.ONLY_IN_AIR_PREDICATE)))))));
        }

        {
            int normal_t = 30; int normal_xz = 3; int normal_y = 2;
            int big_t = 2500; int big_xz = 25; int big_y = 15;

            for (int i = 1; i <= 11; i++) {
                Component material = Component.literal("diamond");
                if (i == 2) {
                    material = Component.literal("emerald");
                } else if (i == 3) {
                    material = Component.literal("lazuli");
                } else if (i == 4) {
                    material = Component.literal("cinnabar");
                } else if (i == 5) {
                    material = Component.literal("coal");
                } else if (i == 6) {
                    material = Component.literal("cuprite");
                } else if (i == 7) {
                    material = Component.literal("limonite");
                } else if (i == 8) {
                    material = Component.literal("tin");
                } else if (i == 9) {
                    material = Component.literal("zinc");
                } else if (i == 10) {
                    material = Component.literal("gold");
                } else if (i == 11) {
                    material = Component.literal("silver");
                }

                Component poor = Component.literal("");
                Component rich = Component.literal("");
                Component f = Component.literal("_fragment");
                Component lock = Component.literal("tfs:");
                Component s = Component.literal("stone_");
                Component d = Component.literal("deep_");
                Component o = Component.literal("_ore");
                Component s2 = s;
                Component d2 = d;

                if (i > 5) {
                    f = Component.literal("");
                    s2 = Component.literal("");
                    d2 = Component.literal("");
                    poor = Component.literal("poor_");
                    rich = Component.literal("rich_");
                }

                if (i == 5) {
                    o = Component.literal("_block");
                    s2 = Component.literal("");
                    d2 = Component.literal("");
                    s = Component.literal("");
                    d = Component.literal("deepslate_");
                    lock = Component.literal("minecraft:");
                }

                register(context, registerKey(material.getString() + "_veins"), Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, new RandomPatchConfiguration(normal_t, normal_xz, normal_y,
                                PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + poor.getString() + s.getString() + material.getString() + "_ore")).defaultBlockState(), 10)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + s.getString() + material.getString() + "_ore")).defaultBlockState(), 5)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + rich.getString() + s2.getString() + material.getString() + o.getString())).defaultBlockState(), 1))),
                                        BlockPredicate.allOf(BlockPredicate.matchesTag(BlockTags.STONE_ORE_REPLACEABLES))))),
                        PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, new RandomPatchConfiguration(normal_t, normal_xz, normal_y,
                                PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + poor.getString() + d.getString() + material.getString() + "_ore")).defaultBlockState(), 10)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + d.getString() + material.getString() + "_ore")).defaultBlockState(), 5)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + rich.getString() + d2.getString() + material.getString() + o.getString())).defaultBlockState(), 1))),
                                        BlockPredicate.allOf(BlockPredicate.matchesTag(BlockTags.DEEPSLATE_ORE_REPLACEABLES))))))));

                register(context, registerKey("big_" + material.getString() + "_veins"), Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, new RandomPatchConfiguration(big_t, big_xz, big_y,
                                PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + poor.getString() + s.getString() + material.getString() + "_ore")).defaultBlockState(), 10)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + s.getString() + material.getString() + "_ore")).defaultBlockState(), 5)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + rich.getString() + s2.getString() + material.getString() + o.getString())).defaultBlockState(), 1))),
                                        BlockPredicate.allOf(BlockPredicate.matchesTag(BlockTags.STONE_ORE_REPLACEABLES))))),
                        PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, new RandomPatchConfiguration(big_t, big_xz, big_y,
                                PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + poor.getString() + d.getString() + material.getString() + "_ore")).defaultBlockState(), 10)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + d.getString() + material.getString() + "_ore")).defaultBlockState(), 5)
                                                        .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(lock.getString() + rich.getString() + d2.getString() + material.getString() + o.getString())).defaultBlockState(), 1))),
                                        BlockPredicate.allOf(BlockPredicate.matchesTag(BlockTags.DEEPSLATE_ORE_REPLACEABLES))))))));

                /*register(context, registerKey("water_" + material.getString()), Feature.ORE, new OreConfiguration(
                        List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                        ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tfs:sand_" + material.getString())).defaultBlockState()),
                                OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                        ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tfs:gravel_"+ material.getString())).defaultBlockState())),
                        12, 1.0F));

                FeatureUtils.register(context, registerKey("water_" + material.getString()), Feature.ROOT_SYSTEM, new RootSystemConfiguration(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(registerKey("water_" + material.getString() + "_part"))
                        ), 1, 3, ModTags.Blocks.STONE,
                        BlockStateProvider.simple(Blocks.STONE), 20, 100, 3,
                        2, BlockStateProvider.simple(Blocks.AIR), 20, 2,
                        BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.GRAVEL))),
                                BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.AZALEA_GROWS_ON))));*/

                register(context, registerKey(material.getString() + "_part"), Feature.RANDOM_PATCH, new RandomPatchConfiguration(30, 6, 2,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                                        SimpleWeightedRandomList.<BlockState>builder()
                                                .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tfs:" + poor.getString() + material.getString() + f.getString() + "_object")).defaultBlockState().setValue(TFSOrePlaced.FACING, Direction.EAST), 1)
                                                .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tfs:" + poor.getString() + material.getString() + f.getString() + "_object")).defaultBlockState().setValue(TFSOrePlaced.FACING, Direction.WEST), 1)
                                                .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tfs:" + poor.getString() + material.getString() + f.getString() + "_object")).defaultBlockState().setValue(TFSOrePlaced.FACING, Direction.NORTH), 1)
                                                .add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tfs:" + poor.getString() + material.getString() + f.getString() + "_object")).defaultBlockState().setValue(TFSOrePlaced.FACING, Direction.SOUTH), 1))),
                                BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE))));

                FeatureUtils.register(context, registerKey(material.getString()), Feature.ROOT_SYSTEM, new RootSystemConfiguration(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(registerKey(material.getString() + "_part"))
                        ), 3, 3, ModTags.Blocks.STONE,
                        BlockStateProvider.simple(Blocks.STONE), 20, 100, 3,
                        2, BlockStateProvider.simple(Blocks.AIR), 20, 2,
                        BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(List.of(Blocks.AIR,
                                        Blocks.CAVE_AIR, Blocks.VOID_AIR)), BlockPredicate.matchesTag(BlockTags.REPLACEABLE_BY_TREES)),
                                BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.AZALEA_GROWS_ON))));
            }

            register(context, OCEAN_ORE_KEY, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                                    List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                                    TFSBlocks.SAND_DIAMOND.get().defaultBlockState()),
                                            OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                                    TFSBlocks.GRAVEL_DIAMOND.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_EMERALD.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_EMERALD.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_LAZULI.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_LAZULI.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_CINNABAR.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_CINNABAR.get().defaultBlockState())), 12, 1.0F)))));



                    register(context, RIVER_ORE_KEY, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_COAL.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_COAL.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_CUPRITE.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_CUPRITE.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_LIMONITE.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_LIMONITE.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_TIN.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_TIN.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_ZINC.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_ZINC.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_GOLD.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_GOLD.get().defaultBlockState())), 12, 1.0F)),

                    PlacementUtils.inlinePlaced(Feature.ORE, new OreConfiguration(
                            List.of(OreConfiguration.target(new TagMatchTest(BlockTags.SAND),
                                            TFSBlocks.SAND_SILVER.get().defaultBlockState()),
                                    OreConfiguration.target(new TagMatchTest(ModTags.Blocks.GRAVEL),
                                            TFSBlocks.GRAVEL_SILVER.get().defaultBlockState())), 12, 1.0F)))));
        } //NewOre
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(tfs.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

