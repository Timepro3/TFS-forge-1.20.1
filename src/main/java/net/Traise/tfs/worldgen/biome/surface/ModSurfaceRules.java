package net.Traise.tfs.worldgen.biome.surface;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource AIR = makeStateRule(TFSBlocks.MIMIC.get());


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DIAMOND_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.EMERALD_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.CINNABAR_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.LAZULI_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.COAL_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.CUPRITE_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.LIMONITE_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TIN_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ZINC_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GOLD_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SILVER_DEPOSIT), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, AIR))),

                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );

    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
