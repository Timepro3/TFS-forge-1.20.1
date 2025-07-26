package net.Traise.tfs.worldgen.biome;

import net.Traise.tfs.tfs;
import net.Traise.tfs.worldgen.biome.ore.*;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModDiamondOre(new ResourceLocation(tfs.MOD_ID, "diamond"), 5));
        Regions.register(new ModEmeraldOre(new ResourceLocation(tfs.MOD_ID, "emerald"), 5));
        Regions.register(new ModLazuliOre(new ResourceLocation(tfs.MOD_ID, "lazuli"), 5));
        Regions.register(new ModCinnabarOre(new ResourceLocation(tfs.MOD_ID, "cinnabar"), 5));
        Regions.register(new ModCoalOre(new ResourceLocation(tfs.MOD_ID, "coal"), 5));
        Regions.register(new ModCupriteOre(new ResourceLocation(tfs.MOD_ID, "cuprite"), 5));
        Regions.register(new ModLimoniteOre(new ResourceLocation(tfs.MOD_ID, "limonite"), 5));
        Regions.register(new ModTinOre(new ResourceLocation(tfs.MOD_ID, "tin"), 5));
        Regions.register(new ModZincOre(new ResourceLocation(tfs.MOD_ID, "zinc"), 5));
        Regions.register(new ModGoldOre(new ResourceLocation(tfs.MOD_ID, "gold"), 5));
        Regions.register(new ModSilverOre(new ResourceLocation(tfs.MOD_ID, "silver"), 5));
    }
}
