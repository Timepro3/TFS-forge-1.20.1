package net.Traise.tfs.datagen.loot;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.item.TFSItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(TFSBlocks.TIN_BLOCK.get());
        this.dropSelf(TFSBlocks.SILVER_BLOCK.get());
        this.dropSelf(TFSBlocks.BRONZE_BLOCK.get());
        this.dropSelf(TFSBlocks.STEEL_BLOCK.get());
        this.dropSelf(TFSBlocks.CAST_IRON_BLOCK.get());
        this.dropSelf(TFSBlocks.UNKNOWN_METAL_BLOCK.get());
        this.dropSelf(TFSBlocks.FOUNDRY.get());

        this.add(TFSBlocks.MIMIC.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.MIMIC.get(), Item.byBlock(Blocks.AIR)));

        this.add(TFSBlocks.POOR_STONE_CUPRITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_STONE_CUPRITE_ORE.get(), TFSItems.POOR_CUPRITE.get()));
        this.add(TFSBlocks.POOR_DEEP_CUPRITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_DEEP_CUPRITE_ORE.get(), TFSItems.POOR_CUPRITE.get()));
        this.add(TFSBlocks.STONE_CUPRITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_CUPRITE_ORE.get(), TFSItems.CUPRITE.get()));
        this.add(TFSBlocks.DEEP_CUPRITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_CUPRITE_ORE.get(), TFSItems.CUPRITE.get()));
        this.add(TFSBlocks.RICH_CUPRITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.RICH_CUPRITE_ORE.get(), TFSItems.RICH_CUPRITE.get()));
        this.add(TFSBlocks.POOR_STONE_LIMONITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_STONE_LIMONITE_ORE.get(), TFSItems.POOR_LIMONITE.get()));
        this.add(TFSBlocks.POOR_DEEP_LIMONITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_DEEP_LIMONITE_ORE.get(), TFSItems.POOR_LIMONITE.get()));
        this.add(TFSBlocks.STONE_LIMONITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_LIMONITE_ORE.get(), TFSItems.LIMONITE.get()));
        this.add(TFSBlocks.DEEP_LIMONITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_LIMONITE_ORE.get(), TFSItems.LIMONITE.get()));
        this.add(TFSBlocks.RICH_LIMONITE_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.RICH_LIMONITE_ORE.get(), TFSItems.RICH_LIMONITE.get()));
        this.add(TFSBlocks.POOR_STONE_TIN_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_STONE_TIN_ORE.get(), TFSItems.POOR_TIN.get()));
        this.add(TFSBlocks.POOR_DEEP_TIN_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_DEEP_TIN_ORE.get(), TFSItems.POOR_TIN.get()));
        this.add(TFSBlocks.STONE_TIN_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_TIN_ORE.get(), TFSItems.TIN.get()));
        this.add(TFSBlocks.DEEP_TIN_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_TIN_ORE.get(), TFSItems.TIN.get()));
        this.add(TFSBlocks.RICH_TIN_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.RICH_TIN_ORE.get(), TFSItems.RICH_TIN.get()));
        this.add(TFSBlocks.POOR_STONE_ZINC_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_STONE_ZINC_ORE.get(), TFSItems.POOR_ZINC.get()));
        this.add(TFSBlocks.POOR_DEEP_ZINC_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_DEEP_ZINC_ORE.get(), TFSItems.POOR_ZINC.get()));
        this.add(TFSBlocks.STONE_ZINC_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_ZINC_ORE.get(), TFSItems.ZINC.get()));
        this.add(TFSBlocks.DEEP_ZINC_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_ZINC_ORE.get(), TFSItems.ZINC.get()));
        this.add(TFSBlocks.RICH_ZINC_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.RICH_ZINC_ORE.get(), TFSItems.RICH_ZINC.get()));
        this.add(TFSBlocks.POOR_STONE_SILVER_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_STONE_SILVER_ORE.get(), TFSItems.POOR_SILVER.get()));
        this.add(TFSBlocks.POOR_DEEP_SILVER_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_DEEP_SILVER_ORE.get(), TFSItems.POOR_SILVER.get()));
        this.add(TFSBlocks.STONE_SILVER_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_SILVER_ORE.get(), TFSItems.SILVER.get()));
        this.add(TFSBlocks.DEEP_SILVER_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_SILVER_ORE.get(), TFSItems.SILVER.get()));
        this.add(TFSBlocks.RICH_SILVER_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.RICH_SILVER_ORE.get(), TFSItems.RICH_SILVER.get()));
        this.add(TFSBlocks.POOR_STONE_GOLD_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_STONE_GOLD_ORE.get(), TFSItems.POOR_GOLD.get()));
        this.add(TFSBlocks.POOR_DEEP_GOLD_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.POOR_DEEP_GOLD_ORE.get(), TFSItems.POOR_GOLD.get()));
        this.add(TFSBlocks.STONE_GOLD_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_GOLD_ORE.get(), TFSItems.GOLD.get()));
        this.add(TFSBlocks.DEEP_GOLD_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_GOLD_ORE.get(), TFSItems.GOLD.get()));
        this.add(TFSBlocks.RICH_GOLD_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.RICH_GOLD_ORE.get(), TFSItems.RICH_GOLD.get()));
        this.add(TFSBlocks.STONE_DIAMOND_ORE.get(),
                block -> createLikeOreDrop(TFSBlocks.STONE_DIAMOND_ORE.get(), TFSItems.RAW_DIAMOND.get()));
        this.add(TFSBlocks.DEEP_DIAMOND_ORE.get(),
                block -> createLikeOreDrop(TFSBlocks.DEEP_DIAMOND_ORE.get(), TFSItems.RAW_DIAMOND.get()));
        this.add(TFSBlocks.STONE_EMERALD_ORE.get(),
                block -> createLikeOreDrop(TFSBlocks.STONE_EMERALD_ORE.get(), TFSItems.RAW_EMERALD.get()));
        this.add(TFSBlocks.DEEP_EMERALD_ORE.get(),
                block -> createLikeOreDrop(TFSBlocks.DEEP_EMERALD_ORE.get(), TFSItems.RAW_EMERALD.get()));
        this.add(TFSBlocks.STONE_CINNABAR_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_CINNABAR_ORE.get(), TFSItems.CINNABAR.get()));
        this.add(TFSBlocks.DEEP_CINNABAR_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_CINNABAR_ORE.get(), TFSItems.CINNABAR.get()));
        this.add(TFSBlocks.STONE_LAZULI_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.STONE_LAZULI_ORE.get(), TFSItems.RAW_LAZULI.get()));
        this.add(TFSBlocks.DEEP_LAZULI_ORE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.DEEP_LAZULI_ORE.get(), TFSItems.RAW_LAZULI.get()));

        this.add(TFSBlocks.SAND_DIAMOND.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_DIAMOND.get(), TFSItems.DIAMOND_FRAGMENT.get()));
        this.add(TFSBlocks.GRAVEL_DIAMOND.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_DIAMOND.get(), TFSItems.DIAMOND_FRAGMENT.get()));
        this.add(TFSBlocks.SAND_EMERALD.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_EMERALD.get(), TFSItems.EMERALD_FRAGMENT.get()));
        this.add(TFSBlocks.GRAVEL_EMERALD.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_EMERALD.get(), TFSItems.EMERALD_FRAGMENT.get()));
        this.add(TFSBlocks.SAND_LAZULI.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_LAZULI.get(), TFSItems.LAZULI_FRAGMENT.get()));
        this.add(TFSBlocks.GRAVEL_LAZULI.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_LAZULI.get(), TFSItems.LAZULI_FRAGMENT.get()));
        this.add(TFSBlocks.SAND_CINNABAR.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_CINNABAR.get(), TFSItems.CINNABAR_FRAGMENT.get()));
        this.add(TFSBlocks.GRAVEL_CINNABAR.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_CINNABAR.get(), TFSItems.CINNABAR_FRAGMENT.get()));
        this.add(TFSBlocks.SAND_COAL.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_COAL.get(), TFSItems.COAL_FRAGMENT.get()));
        this.add(TFSBlocks.GRAVEL_COAL.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_COAL.get(), TFSItems.COAL_FRAGMENT.get()));
        this.add(TFSBlocks.SAND_CUPRITE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_CUPRITE.get(), TFSItems.POOR_CUPRITE.get()));
        this.add(TFSBlocks.GRAVEL_CUPRITE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_CUPRITE.get(), TFSItems.POOR_CUPRITE.get()));
        this.add(TFSBlocks.SAND_LIMONITE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_LIMONITE.get(), TFSItems.POOR_LIMONITE.get()));
        this.add(TFSBlocks.GRAVEL_LIMONITE.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_LIMONITE.get(), TFSItems.POOR_LIMONITE.get()));
        this.add(TFSBlocks.SAND_TIN.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_TIN.get(), TFSItems.POOR_TIN.get()));
        this.add(TFSBlocks.GRAVEL_TIN.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_TIN.get(), TFSItems.POOR_TIN.get()));
        this.add(TFSBlocks.SAND_ZINC.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_ZINC.get(), TFSItems.POOR_ZINC.get()));
        this.add(TFSBlocks.GRAVEL_ZINC.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_ZINC.get(), TFSItems.POOR_ZINC.get()));
        this.add(TFSBlocks.SAND_GOLD.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_GOLD.get(), TFSItems.POOR_GOLD.get()));
        this.add(TFSBlocks.GRAVEL_GOLD.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_GOLD.get(), TFSItems.POOR_GOLD.get()));
        this.add(TFSBlocks.SAND_SILVER.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.SAND_SILVER.get(), TFSItems.POOR_SILVER.get()));
        this.add(TFSBlocks.GRAVEL_SILVER.get(),
                block -> createCopperLikeOreDrops(TFSBlocks.GRAVEL_SILVER.get(), TFSItems.POOR_SILVER.get()));

        this.add(TFSBlocks.ROCK_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.ROCK_OBJECT.get(), TFSItems.ROCK.get()));
        this.add(TFSBlocks.TWIG_BLOCK.get(),
                block -> createLikeOreDrop(TFSBlocks.TWIG_BLOCK.get(), TFSItems.TWIG.get()));

        this.add(TFSBlocks.DIAMOND_FRAGMENT_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.DIAMOND_FRAGMENT_OBJECT.get(), TFSItems.DIAMOND_FRAGMENT.get()));
        this.add(TFSBlocks.EMERALD_FRAGMENT_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.EMERALD_FRAGMENT_OBJECT.get(), TFSItems.EMERALD_FRAGMENT.get()));
        this.add(TFSBlocks.LAZULI_FRAGMENT_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.LAZULI_FRAGMENT_OBJECT.get(), TFSItems.LAZULI_FRAGMENT.get()));
        this.add(TFSBlocks.CINNABAR_FRAGMENT_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.CINNABAR_FRAGMENT_OBJECT.get(), TFSItems.CINNABAR_FRAGMENT.get()));
        this.add(TFSBlocks.COAL_FRAGMENT_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.COAL_FRAGMENT_OBJECT.get(), TFSItems.COAL_FRAGMENT.get()));
        this.add(TFSBlocks.POOR_CUPRITE_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.POOR_CUPRITE_OBJECT.get(), TFSItems.POOR_CUPRITE.get()));
        this.add(TFSBlocks.POOR_LIMONITE_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.POOR_LIMONITE_OBJECT.get(), TFSItems.POOR_LIMONITE.get()));
        this.add(TFSBlocks.POOR_TIN_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.POOR_TIN_OBJECT.get(), TFSItems.POOR_TIN.get()));
        this.add(TFSBlocks.POOR_ZINC_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.POOR_ZINC_OBJECT.get(), TFSItems.POOR_ZINC.get()));
        this.add(TFSBlocks.POOR_GOLD_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.POOR_GOLD_OBJECT.get(), TFSItems.POOR_GOLD.get()));
        this.add(TFSBlocks.POOR_SILVER_OBJECT.get(),
                block -> createLikeOreDrop(TFSBlocks.POOR_SILVER_OBJECT.get(), TFSItems.POOR_SILVER.get()));


    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createLikeOreDrop(Block pBlock, Item pItem) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(pItem)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TFSBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
