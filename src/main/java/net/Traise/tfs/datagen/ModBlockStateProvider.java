package net.Traise.tfs.datagen;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.tfs;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, tfs.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(TFSBlocks.TIN_BLOCK);
        blockWithItem(TFSBlocks.SILVER_BLOCK);
        blockWithItem(TFSBlocks.BRONZE_BLOCK);
        blockWithItem(TFSBlocks.STEEL_BLOCK);
        blockWithItem(TFSBlocks.CAST_IRON_BLOCK);
        blockWithItem(TFSBlocks.UNKNOWN_METAL_BLOCK);

        blockWithItem(TFSBlocks.POOR_STONE_CUPRITE_ORE);
        blockWithItem(TFSBlocks.STONE_CUPRITE_ORE);
        blockWithItem(TFSBlocks.RICH_CUPRITE_ORE);
        blockWithItem(TFSBlocks.POOR_DEEP_CUPRITE_ORE);
        blockWithItem(TFSBlocks.DEEP_CUPRITE_ORE);
        blockWithItem(TFSBlocks.POOR_STONE_LIMONITE_ORE);
        blockWithItem(TFSBlocks.STONE_LIMONITE_ORE);
        blockWithItem(TFSBlocks.RICH_LIMONITE_ORE);
        blockWithItem(TFSBlocks.POOR_DEEP_LIMONITE_ORE);
        blockWithItem(TFSBlocks.DEEP_LIMONITE_ORE);
        blockWithItem(TFSBlocks.POOR_STONE_TIN_ORE);
        blockWithItem(TFSBlocks.STONE_TIN_ORE);
        blockWithItem(TFSBlocks.RICH_TIN_ORE);
        blockWithItem(TFSBlocks.POOR_DEEP_TIN_ORE);
        blockWithItem(TFSBlocks.DEEP_TIN_ORE);
        blockWithItem(TFSBlocks.POOR_STONE_ZINC_ORE);
        blockWithItem(TFSBlocks.STONE_ZINC_ORE);
        blockWithItem(TFSBlocks.RICH_ZINC_ORE);
        blockWithItem(TFSBlocks.POOR_DEEP_ZINC_ORE);
        blockWithItem(TFSBlocks.DEEP_ZINC_ORE);
        blockWithItem(TFSBlocks.POOR_STONE_SILVER_ORE);
        blockWithItem(TFSBlocks.STONE_SILVER_ORE);
        blockWithItem(TFSBlocks.RICH_SILVER_ORE);
        blockWithItem(TFSBlocks.POOR_DEEP_SILVER_ORE);
        blockWithItem(TFSBlocks.DEEP_SILVER_ORE);
        blockWithItem(TFSBlocks.POOR_STONE_GOLD_ORE);
        blockWithItem(TFSBlocks.STONE_GOLD_ORE);
        blockWithItem(TFSBlocks.RICH_GOLD_ORE);
        blockWithItem(TFSBlocks.POOR_DEEP_GOLD_ORE);
        blockWithItem(TFSBlocks.DEEP_GOLD_ORE);
        blockWithItem(TFSBlocks.STONE_DIAMOND_ORE);
        blockWithItem(TFSBlocks.STONE_EMERALD_ORE);
        blockWithItem(TFSBlocks.DEEP_DIAMOND_ORE);
        blockWithItem(TFSBlocks.DEEP_EMERALD_ORE);
        blockWithItem(TFSBlocks.STONE_CINNABAR_ORE);
        blockWithItem(TFSBlocks.STONE_LAZULI_ORE);
        blockWithItem(TFSBlocks.DEEP_CINNABAR_ORE);
        blockWithItem(TFSBlocks.DEEP_LAZULI_ORE);

        blockWithItem(TFSBlocks.SAND_DIAMOND);
        blockWithItem(TFSBlocks.GRAVEL_DIAMOND);
        blockWithItem(TFSBlocks.SAND_EMERALD);
        blockWithItem(TFSBlocks.GRAVEL_EMERALD);
        blockWithItem(TFSBlocks.SAND_LAZULI);
        blockWithItem(TFSBlocks.GRAVEL_LAZULI);
        blockWithItem(TFSBlocks.SAND_CINNABAR);
        blockWithItem(TFSBlocks.GRAVEL_CINNABAR);
        blockWithItem(TFSBlocks.SAND_COAL);
        blockWithItem(TFSBlocks.GRAVEL_COAL);
        blockWithItem(TFSBlocks.SAND_CUPRITE);
        blockWithItem(TFSBlocks.GRAVEL_CUPRITE);
        blockWithItem(TFSBlocks.SAND_LIMONITE);
        blockWithItem(TFSBlocks.GRAVEL_LIMONITE);
        blockWithItem(TFSBlocks.SAND_TIN);
        blockWithItem(TFSBlocks.GRAVEL_TIN);
        blockWithItem(TFSBlocks.SAND_ZINC);
        blockWithItem(TFSBlocks.GRAVEL_ZINC);
        blockWithItem(TFSBlocks.SAND_GOLD);
        blockWithItem(TFSBlocks.GRAVEL_GOLD);
        blockWithItem(TFSBlocks.SAND_SILVER);
        blockWithItem(TFSBlocks.GRAVEL_SILVER);

        blockWithItem(TFSBlocks.MIMIC);

        simpleBlockWithItem(TFSBlocks.FORGE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/forges")));

        simpleBlockWithItem(TFSBlocks.FOUNDRY.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/forges")));

        horizontalBlock(TFSBlocks.ROCK_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/rock_object")), 0);
        horizontalBlock(TFSBlocks.TWIG_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/twig_block")), 0);

        horizontalBlock(TFSBlocks.DIAMOND_FRAGMENT_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/diamond")), 0);
        horizontalBlock(TFSBlocks.EMERALD_FRAGMENT_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/emerald")), 0);
        horizontalBlock(TFSBlocks.LAZULI_FRAGMENT_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/lazuli")), 0);
        horizontalBlock(TFSBlocks.CINNABAR_FRAGMENT_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/cinnabar")), 0);
        horizontalBlock(TFSBlocks.COAL_FRAGMENT_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/coal")), 0);
        horizontalBlock(TFSBlocks.POOR_CUPRITE_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/cuprite")), 0);
        horizontalBlock(TFSBlocks.POOR_LIMONITE_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/limonite")), 0);
        horizontalBlock(TFSBlocks.POOR_TIN_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/tin")), 0);
        horizontalBlock(TFSBlocks.POOR_ZINC_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/zinc")), 0);
        horizontalBlock(TFSBlocks.POOR_GOLD_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gold")), 0);
        horizontalBlock(TFSBlocks.POOR_SILVER_OBJECT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/silver")), 0);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
