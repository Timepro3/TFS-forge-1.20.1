package net.Traise.tfs.datagen.Tags;

import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ModTags;
import net.Traise.tfs.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

public class ModBiomeTagGenerator extends BiomeTagsProvider {
    public ModBiomeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, lookupProvider, tfs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Biomes.OWER).addTags(BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA, BiomeTags.IS_HILL,
                BiomeTags.IS_BADLANDS, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_JUNGLE, BiomeTags.IS_SAVANNA)
                .add(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS);

        this.tag(ModTags.Biomes.ORE)
                .addOptional(ModBiomes.DIAMOND_DEPOSIT.location()).addOptional(ModBiomes.EMERALD_DEPOSIT.location())
                .addOptional(ModBiomes.LAZULI_DEPOSIT.location()).addOptional(ModBiomes.CINNABAR_DEPOSIT.location())
                .addOptional(ModBiomes.COAL_DEPOSIT.location()).addOptional(ModBiomes.CUPRITE_DEPOSIT.location())
                .addOptional(ModBiomes.LIMONITE_DEPOSIT.location()).addOptional(ModBiomes.TIN_DEPOSIT.location())
                .addOptional(ModBiomes.ZINC_DEPOSIT.location()).addOptional(ModBiomes.GOLD_DEPOSIT.location())
                .addOptional(ModBiomes.SILVER_DEPOSIT.location());
    }
}
