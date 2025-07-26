package net.Traise.tfs.datagen.Tags;

import com.simibubi.create.AllBlocks;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ModTags;
import net.Traise.tfs.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModFeatureTagGenerator extends TagsProvider<PlacedFeature> {
    public ModFeatureTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                  @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, Registries.PLACED_FEATURE, lookupProvider, tfs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.PlacedFeatur.OWER_ORE).add(OrePlacements.ORE_COAL_UPPER, OrePlacements.ORE_COAL_LOWER,
                OrePlacements.ORE_IRON_UPPER, OrePlacements.ORE_IRON_MIDDLE, OrePlacements.ORE_IRON_SMALL,
                OrePlacements.ORE_GOLD, OrePlacements.ORE_GOLD_LOWER, OrePlacements.ORE_REDSTONE,
                OrePlacements.ORE_REDSTONE_LOWER, OrePlacements.ORE_DIAMOND, OrePlacements.ORE_DIAMOND_LARGE,
                OrePlacements.ORE_DIAMOND_BURIED, OrePlacements.ORE_LAPIS, OrePlacements.ORE_LAPIS_BURIED,
                OrePlacements.ORE_COPPER_LARGE, OrePlacements.ORE_COPPER, OrePlacements.ORE_EMERALD);
    }
}
