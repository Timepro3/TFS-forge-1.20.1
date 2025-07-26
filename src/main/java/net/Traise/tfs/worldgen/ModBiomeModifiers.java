package net.Traise.tfs.worldgen;

import com.simibubi.create.infrastructure.worldgen.AllFeatures;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.OreVeinifier;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;

public class ModBiomeModifiers{
    public static final ResourceKey<BiomeModifier> ADD_ROCK = registerKey("add_rock");
    public static final ResourceKey<BiomeModifier> REMOVE_ORE = registerKey("remove_ore");
    public static final ResourceKey<BiomeModifier> ADD_OCEAN_ORE = registerKey("add_ocean_ore");
    public static final ResourceKey<BiomeModifier> ADD_RIVER_ORE = registerKey("add_river_ore");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ROCK, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.OWER)
                ,HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ROCK_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(REMOVE_ORE, new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD)
                ,(placedFeatures.getOrThrow(ModTags.PlacedFeatur.OWER_ORE)),
                Collections.singleton(GenerationStep.Decoration.UNDERGROUND_ORES)));

        context.register(ADD_OCEAN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN)
                ,HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OCEAN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_RIVER_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_RIVER)
                ,HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.RIVER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(tfs.MOD_ID, name));
    }
}
