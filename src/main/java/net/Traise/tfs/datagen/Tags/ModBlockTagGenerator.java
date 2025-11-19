package net.Traise.tfs.datagen.Tags;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, tfs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        {this.tag(ModTags.Blocks.NEED_1)
                .addTags(BlockTags.NEEDS_STONE_TOOL,
                        BlockTags.NEEDS_IRON_TOOL,
                        BlockTags.NEEDS_DIAMOND_TOOL);}

        {this.tag(ModTags.Blocks.NEED_2)
                .addTags(BlockTags.NEEDS_IRON_TOOL,
                        BlockTags.NEEDS_DIAMOND_TOOL);}

        {this.tag(ModTags.Blocks.NEED_3)
                .addTags(BlockTags.NEEDS_DIAMOND_TOOL);}

        {this.tag(ModTags.Blocks.AGE_3)
                .add(Blocks.BEETROOTS);}

        {this.tag(ModTags.Blocks.PLANTS_HOE)
                .addTags(BlockTags.MINEABLE_WITH_HOE,
                        BlockTags.FLOWERS)
                .add(Blocks.GRASS,
                        Blocks.TALL_GRASS,
                        Blocks.FERN,
                        Blocks.LARGE_FERN,
                        Blocks.SEAGRASS,
                        Blocks.TALL_SEAGRASS,
                        Blocks.KELP,
                        Blocks.VINE,
                        Blocks.GLOW_LICHEN);}

        this.tag(ModTags.Blocks.MINEABLE_WITH_MULTI_TOOL)
                .addTags(BlockTags.MINEABLE_WITH_AXE,
                        BlockTags.MINEABLE_WITH_SHOVEL,
                        BlockTags.MINEABLE_WITH_PICKAXE);

        this.tag(ModTags.Blocks.REMOVE)
                .addTags(BlockTags.MINEABLE_WITH_HOE,
                        BlockTags.MINEABLE_WITH_AXE,
                        BlockTags.MINEABLE_WITH_SHOVEL,
                        BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Blocks.STONE,
                        Blocks.DEEPSLATE,
                        Blocks.WATER,
                        Blocks.WATER_CAULDRON,
                        Blocks.LAVA,
                        Blocks.LAVA_CAULDRON,
                        Blocks.ANDESITE,
                        Blocks.DIORITE,
                        Blocks.GRANITE,
                        Blocks.TUFF,
                        AllPaletteStoneTypes.LIMESTONE.getBaseBlock().get(),
                        AllPaletteStoneTypes.SCORIA.getBaseBlock().get(),
                        AllPaletteStoneTypes.CRIMSITE.getBaseBlock().get(),
                        Blocks.SMOOTH_BASALT,
                        Blocks.CALCITE,
                        Blocks.AMETHYST_BLOCK,
                        Blocks.BUDDING_AMETHYST);

        this.tag(ModTags.Blocks.GRAVEL).add(Blocks.GRAVEL);

        {
            this.tag(BlockTags.NEEDS_STONE_TOOL)
                    .addTags(ModTags.Blocks.TFS_NEEDS_STONE_TOOL, ModTags.Blocks.TFS_NEEDS_COPPER_TOOL, ModTags.Blocks.TFS_NEEDS_BRONZE_TOOL);

            this.tag(BlockTags.NEEDS_IRON_TOOL)
                    .addTags(ModTags.Blocks.TFS_NEEDS_IRON_TOOL, ModTags.Blocks.TFS_NEEDS_STEEL_TOOL);

            this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                    .addTags(ModTags.Blocks.TFS_NEEDS_DIAMOND_TOOL, ModTags.Blocks.TFS_NEEDS_NETHERITE_TOOL);

            this.tag(ModTags.Blocks.TFS_NEEDS_STONE_TOOL)
                    .addTags(BlockTags.NEEDS_STONE_TOOL)
                    .remove(ModTags.Blocks.TFS_NEEDS_COPPER_TOOL, ModTags.Blocks.TFS_NEEDS_BRONZE_TOOL);

            this.tag(ModTags.Blocks.TFS_NEEDS_COPPER_TOOL);

            this.tag(ModTags.Blocks.TFS_NEEDS_BRONZE_TOOL);

            this.tag(ModTags.Blocks.TFS_NEEDS_IRON_TOOL)
                    .addTags(BlockTags.NEEDS_IRON_TOOL)
                    .remove(ModTags.Blocks.TFS_NEEDS_STEEL_TOOL);

            this.tag(ModTags.Blocks.TFS_NEEDS_STEEL_TOOL);

            this.tag(ModTags.Blocks.TFS_NEEDS_DIAMOND_TOOL)
                    .addTags(BlockTags.NEEDS_DIAMOND_TOOL);

            this.tag(ModTags.Blocks.TFS_NEEDS_NETHERITE_TOOL);
        }

        {this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(TFSBlocks.TIN_BLOCK.get(),
                        TFSBlocks.SILVER_BLOCK.get(),
                        TFSBlocks.BRONZE_BLOCK.get(),
                        TFSBlocks.STEEL_BLOCK.get(),
                        TFSBlocks.CAST_IRON_BLOCK.get(),
                        TFSBlocks.UNKNOWN_METAL_BLOCK.get(),
                        TFSBlocks.POOR_STONE_CUPRITE_ORE.get(),
                        TFSBlocks.POOR_DEEP_CUPRITE_ORE.get(),
                        TFSBlocks.STONE_CUPRITE_ORE.get(),
                        TFSBlocks.DEEP_CUPRITE_ORE.get(),
                        TFSBlocks.RICH_CUPRITE_ORE.get(),
                        TFSBlocks.POOR_STONE_LIMONITE_ORE.get(),
                        TFSBlocks.POOR_DEEP_LIMONITE_ORE.get(),
                        TFSBlocks.STONE_LIMONITE_ORE.get(),
                        TFSBlocks.DEEP_LIMONITE_ORE.get(),
                        TFSBlocks.RICH_LIMONITE_ORE.get(),
                        TFSBlocks.POOR_STONE_TIN_ORE.get(),
                        TFSBlocks.POOR_DEEP_TIN_ORE.get(),
                        TFSBlocks.STONE_TIN_ORE.get(),
                        TFSBlocks.DEEP_TIN_ORE.get(),
                        TFSBlocks.RICH_TIN_ORE.get(),
                        TFSBlocks.POOR_STONE_ZINC_ORE.get(),
                        TFSBlocks.POOR_DEEP_ZINC_ORE.get(),
                        TFSBlocks.STONE_ZINC_ORE.get(),
                        TFSBlocks.DEEP_ZINC_ORE.get(),
                        TFSBlocks.RICH_ZINC_ORE.get(),
                        TFSBlocks.POOR_STONE_SILVER_ORE.get(),
                        TFSBlocks.POOR_DEEP_SILVER_ORE.get(),
                        TFSBlocks.STONE_SILVER_ORE.get(),
                        TFSBlocks.DEEP_SILVER_ORE.get(),
                        TFSBlocks.RICH_SILVER_ORE.get(),
                        TFSBlocks.POOR_STONE_GOLD_ORE.get(),
                        TFSBlocks.POOR_DEEP_GOLD_ORE.get(),
                        TFSBlocks.STONE_GOLD_ORE.get(),
                        TFSBlocks.DEEP_GOLD_ORE.get(),
                        TFSBlocks.RICH_GOLD_ORE.get(),
                        TFSBlocks.STONE_DIAMOND_ORE.get(),
                        TFSBlocks.DEEP_DIAMOND_ORE.get(),
                        TFSBlocks.STONE_EMERALD_ORE.get(),
                        TFSBlocks.DEEP_EMERALD_ORE.get(),
                        TFSBlocks.STONE_CINNABAR_ORE.get(),
                        TFSBlocks.DEEP_CINNABAR_ORE.get(),
                        TFSBlocks.STONE_LAZULI_ORE.get(),
                        TFSBlocks.DEEP_LAZULI_ORE.get(),
                        TFSBlocks.FOUNDRY.get(),
                        TFSBlocks.DIAMOND_FRAGMENT_OBJECT.get(),
                        TFSBlocks.EMERALD_FRAGMENT_OBJECT.get(),
                        TFSBlocks.LAZULI_FRAGMENT_OBJECT.get(),
                        TFSBlocks.CINNABAR_FRAGMENT_OBJECT.get(),
                        TFSBlocks.COAL_FRAGMENT_OBJECT.get(),
                        TFSBlocks.POOR_CUPRITE_OBJECT.get(),
                        TFSBlocks.POOR_LIMONITE_OBJECT.get(),
                        TFSBlocks.POOR_TIN_OBJECT.get(),
                        TFSBlocks.POOR_ZINC_OBJECT.get(),
                        TFSBlocks.POOR_GOLD_OBJECT.get(),
                        TFSBlocks.POOR_SILVER_OBJECT.get()
                        );
        }

        {
            this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                    .add(TFSBlocks.SAND_DIAMOND.get(),
                            TFSBlocks.GRAVEL_DIAMOND.get(),
                            TFSBlocks.SAND_EMERALD.get(),
                            TFSBlocks.GRAVEL_EMERALD.get(),
                            TFSBlocks.SAND_LAZULI.get(),
                            TFSBlocks.GRAVEL_LAZULI.get(),
                            TFSBlocks.SAND_CINNABAR.get(),
                            TFSBlocks.GRAVEL_CINNABAR.get(),
                            TFSBlocks.SAND_COAL.get(),
                            TFSBlocks.GRAVEL_COAL.get(),
                            TFSBlocks.SAND_CUPRITE.get(),
                            TFSBlocks.GRAVEL_CUPRITE.get(),
                            TFSBlocks.SAND_LIMONITE.get(),
                            TFSBlocks.GRAVEL_LIMONITE.get(),
                            TFSBlocks.SAND_TIN.get(),
                            TFSBlocks.GRAVEL_TIN.get(),
                            TFSBlocks.SAND_ZINC.get(),
                            TFSBlocks.GRAVEL_ZINC.get(),
                            TFSBlocks.SAND_GOLD.get(),
                            TFSBlocks.GRAVEL_GOLD.get(),
                            TFSBlocks.SAND_SILVER.get(),
                            TFSBlocks.GRAVEL_SILVER.get());
        }

        {this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(TFSBlocks.TIN_BLOCK.get(),
                        TFSBlocks.SILVER_BLOCK.get(),
                        TFSBlocks.BRONZE_BLOCK.get(),
                        TFSBlocks.STEEL_BLOCK.get(),
                        TFSBlocks.CAST_IRON_BLOCK.get(),
                        TFSBlocks.UNKNOWN_METAL_BLOCK.get(),
                        TFSBlocks.POOR_STONE_LIMONITE_ORE.get(),
                        TFSBlocks.POOR_DEEP_LIMONITE_ORE.get(),
                        TFSBlocks.STONE_LIMONITE_ORE.get(),
                        TFSBlocks.DEEP_LIMONITE_ORE.get(),
                        TFSBlocks.RICH_LIMONITE_ORE.get(),
                        TFSBlocks.STONE_LAZULI_ORE.get(),
                        TFSBlocks.DEEP_LAZULI_ORE.get()
                );}

        {this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(TFSBlocks.STONE_DIAMOND_ORE.get(),
                        TFSBlocks.DEEP_DIAMOND_ORE.get(),
                        TFSBlocks.STONE_EMERALD_ORE.get(),
                        TFSBlocks.DEEP_EMERALD_ORE.get(),
                        TFSBlocks.STONE_CINNABAR_ORE.get(),
                        TFSBlocks.DEEP_CINNABAR_ORE.get(),
                        TFSBlocks.POOR_STONE_ZINC_ORE.get(),
                        TFSBlocks.POOR_DEEP_ZINC_ORE.get(),
                        TFSBlocks.STONE_ZINC_ORE.get(),
                        TFSBlocks.DEEP_ZINC_ORE.get(),
                        TFSBlocks.RICH_ZINC_ORE.get(),
                        TFSBlocks.POOR_STONE_SILVER_ORE.get(),
                        TFSBlocks.POOR_DEEP_SILVER_ORE.get(),
                        TFSBlocks.STONE_SILVER_ORE.get(),
                        TFSBlocks.DEEP_SILVER_ORE.get(),
                        TFSBlocks.RICH_SILVER_ORE.get(),
                        TFSBlocks.POOR_STONE_GOLD_ORE.get(),
                        TFSBlocks.POOR_DEEP_GOLD_ORE.get(),
                        TFSBlocks.STONE_GOLD_ORE.get(),
                        TFSBlocks.DEEP_GOLD_ORE.get(),
                        TFSBlocks.RICH_GOLD_ORE.get()
                );}
    }
}
