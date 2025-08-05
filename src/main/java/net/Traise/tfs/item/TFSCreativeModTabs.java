package net.Traise.tfs.item;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.tfs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class TFSCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, tfs.MOD_ID);

    public static RegistryObject<CreativeModeTab> TFS_TAB = CREATIVE_MODE_TABS.register("tfs_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TFSItems.BLUEBERRY.get()))
                    .title(Component.translatable("creativetab.tfs_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TFSItems.CUDGEL.get());
                        pOutput.accept(TFSItems.POLE.get());
                        pOutput.accept(TFSItems.WOODEN_SPEAR.get());
                        pOutput.accept(TFSItems.ROCK_SHOVEL.get());
                        pOutput.accept(TFSItems.ROCK_PICKAXE.get());
                        pOutput.accept(TFSItems.ROCK_AXE.get());
                        pOutput.accept(TFSItems.ROCK_HOE.get());
                        pOutput.accept(TFSItems.ROCK_KNIFE.get());
                        pOutput.accept(TFSItems.ROCK_SPEAR.get());
                        pOutput.accept(TFSItems.IRON_HAMMER.get());
                        pOutput.accept(TFSItems.IRON_SPADE.get());
                        pOutput.accept(TFSItems.IRON_SICKLE.get());
                        pOutput.accept(TFSItems.IRON_KNIFE.get());
                        pOutput.accept(TFSItems.IRON_SPEAR.get());
                        pOutput.accept(TFSItems.GOLD_HAMMER.get());
                        pOutput.accept(TFSItems.GOLD_SPADE.get());
                        pOutput.accept(TFSItems.GOLD_SICKLE.get());
                        pOutput.accept(TFSItems.GOLD_KNIFE.get());
                        pOutput.accept(TFSItems.GOLD_SPEAR.get());
                        pOutput.accept(TFSItems.SILVER_SWORD.get());
                        pOutput.accept(TFSItems.SILVER_SHOVEL.get());
                        pOutput.accept(TFSItems.SILVER_PICKAXE.get());
                        pOutput.accept(TFSItems.SILVER_AXE.get());
                        pOutput.accept(TFSItems.SILVER_HOE.get());
                        pOutput.accept(TFSItems.SILVER_HAMMER.get());
                        pOutput.accept(TFSItems.SILVER_SPADE.get());
                        pOutput.accept(TFSItems.SILVER_SICKLE.get());
                        pOutput.accept(TFSItems.SILVER_KNIFE.get());
                        pOutput.accept(TFSItems.SILVER_SPEAR.get());
                        pOutput.accept(TFSItems.BRONZE_SWORD.get());
                        pOutput.accept(TFSItems.BRONZE_SHOVEL.get());
                        pOutput.accept(TFSItems.BRONZE_PICKAXE.get());
                        pOutput.accept(TFSItems.BRONZE_AXE.get());
                        pOutput.accept(TFSItems.BRONZE_HOE.get());
                        pOutput.accept(TFSItems.BRONZE_HAMMER.get());
                        pOutput.accept(TFSItems.BRONZE_SPADE.get());
                        pOutput.accept(TFSItems.BRONZE_SICKLE.get());
                        pOutput.accept(TFSItems.BRONZE_KNIFE.get());
                        pOutput.accept(TFSItems.BRONZE_SPEAR.get());
                        pOutput.accept(TFSItems.STEEL_SWORD.get());
                        pOutput.accept(TFSItems.STEEL_SHOVEL.get());
                        pOutput.accept(TFSItems.STEEL_PICKAXE.get());
                        pOutput.accept(TFSItems.STEEL_AXE.get());
                        pOutput.accept(TFSItems.STEEL_HOE.get());
                        pOutput.accept(TFSItems.STEEL_HAMMER.get());
                        pOutput.accept(TFSItems.STEEL_SPADE.get());
                        pOutput.accept(TFSItems.STEEL_SICKLE.get());
                        pOutput.accept(TFSItems.STEEL_KNIFE.get());
                        pOutput.accept(TFSItems.STEEL_SPEAR.get());
                        pOutput.accept(TFSItems.DIAMOND_HAMMER.get());
                        pOutput.accept(TFSItems.DIAMOND_SPADE.get());
                        pOutput.accept(TFSItems.DIAMOND_SICKLE.get());
                        pOutput.accept(TFSItems.DIAMOND_KNIFE.get());
                        pOutput.accept(TFSItems.DIAMOND_SPEAR.get());
                        pOutput.accept(TFSItems.NETHERITE_HAMMER.get());
                        pOutput.accept(TFSItems.NETHERITE_SPADE.get());
                        pOutput.accept(TFSItems.NETHERITE_SICKLE.get());
                        pOutput.accept(TFSItems.NETHERITE_KNIFE.get());
                        pOutput.accept(TFSItems.NETHERITE_SPEAR.get());

                        pOutput.accept(TFSItems.REVOLVER.get());
                        pOutput.accept(TFSItems.SLEEVE.get());
                        pOutput.accept(TFSItems.CARTRIDGE_STEEL.get());
                        pOutput.accept(TFSItems.CARTRIDGE_SILVER.get());

                        pOutput.accept(TFSItems.SILVER_HELMET.get());
                        pOutput.accept(TFSItems.SILVER_CHESTPLATE.get());
                        pOutput.accept(TFSItems.SILVER_LEGGINGS.get());
                        pOutput.accept(TFSItems.SILVER_BOOTS.get());
                        pOutput.accept(TFSItems.BRONZE_HELMET.get());
                        pOutput.accept(TFSItems.BRONZE_CHESTPLATE.get());
                        pOutput.accept(TFSItems.BRONZE_LEGGINGS.get());
                        pOutput.accept(TFSItems.BRONZE_BOOTS.get());
                        pOutput.accept(TFSItems.STEEL_HELMET.get());
                        pOutput.accept(TFSItems.STEEL_CHESTPLATE.get());
                        pOutput.accept(TFSItems.STEEL_LEGGINGS.get());
                        pOutput.accept(TFSItems.STEEL_BOOTS.get());

                        pOutput.accept(TFSItems.BLUEBERRY.get());
                        pOutput.accept(TFSItems.BLU.get());
                        pOutput.accept(TFSItems.TIN_SWORD.get());

                        pOutput.accept(TFSItems.GRASS_CUT.get());
                        pOutput.accept(TFSItems.GRASS_ROPE.get());
                        pOutput.accept(TFSItems.ROCK.get());
                        pOutput.accept(TFSItems.TWIG.get());
                        pOutput.accept(TFSItems.DISTORTED_TWIG.get());
                        pOutput.accept(TFSItems.PIK_ROCK_HOE.get());
                        pOutput.accept(TFSItems.PIK_ROCK_AXE.get());
                        pOutput.accept(TFSItems.PIK_ROCK_PICK.get());
                        pOutput.accept(TFSItems.PIK_ROCK_SHOVEL.get());
                        pOutput.accept(TFSItems.PIK_ROCK_SPEAR.get());

                        pOutput.accept(TFSItems.TIN_INGOT.get());
                        pOutput.accept(TFSItems.SILVER_INGOT.get());
                        pOutput.accept(TFSItems.BRONZE_INGOT.get());
                        pOutput.accept(TFSItems.STEEL_INGOT.get());
                        pOutput.accept(TFSItems.CAST_IRON_INGOT.get());
                        pOutput.accept(TFSItems.UNKNOWN_METAL_INGOT.get());

                        pOutput.accept(TFSItems.TIN_NUGGET.get());
                        pOutput.accept(TFSItems.SILVER_NUGGET.get());
                        pOutput.accept(TFSItems.BRONZE_NUGGET.get());
                        pOutput.accept(TFSItems.STEEL_NUGGET.get());
                        pOutput.accept(TFSItems.CAST_IRON_NUGGET.get());
                        pOutput.accept(TFSItems.UNKNOWN_METAL_NUGGET.get());
                        pOutput.accept(TFSItems.NETHERITE_NUGGET.get());
                        pOutput.accept(TFSItems.ANDEZITE_ALLOY_NUGGET.get());
                        pOutput.accept(TFSItems.EMERALD_FRAGMENT.get());
                        pOutput.accept(TFSItems.DIAMOND_FRAGMENT.get());
                        pOutput.accept(TFSItems.LAZULI_FRAGMENT.get());
                        pOutput.accept(TFSItems.CINNABAR_FRAGMENT.get());
                        pOutput.accept(TFSItems.COAL_FRAGMENT.get());

                        pOutput.accept(TFSItems.ZINC_SHEET.get());
                        pOutput.accept(TFSItems.TIN_SHEET.get());
                        pOutput.accept(TFSItems.SILVER_SHEET.get());
                        pOutput.accept(TFSItems.BRONZE_SHEET.get());
                        pOutput.accept(TFSItems.STEEL_SHEET.get());
                        pOutput.accept(TFSItems.CAST_IRON_SHEET.get());
                        pOutput.accept(TFSItems.UNKNOWN_METAL_SHEET.get());
                        pOutput.accept(TFSItems.NETHERITE_SHEET.get());
                        pOutput.accept(TFSItems.ANDEZITE_ALLOY_SHEET.get());
                        pOutput.accept(TFSItems.EMERALD_DUST.get());
                        pOutput.accept(TFSItems.DIAMOND_DUST.get());
                        pOutput.accept(TFSItems.LAZULI_DUST.get());

                        pOutput.accept(TFSItems.RAW_INGOT_FORM.get());
                        pOutput.accept(TFSItems.INGOT_FORM.get());

                        pOutput.accept(TFSItems.BUCKET_OF_IRON.get());
                        pOutput.accept(TFSItems.BUCKET_OF_COPPER.get());
                        pOutput.accept(TFSItems.BUCKET_OF_ZINC.get());
                        pOutput.accept(TFSItems.BUCKET_OF_TIN.get());
                        pOutput.accept(TFSItems.BUCKET_OF_GOLD.get());
                        pOutput.accept(TFSItems.BUCKET_OF_SILVER.get());
                        pOutput.accept(TFSItems.BUCKET_OF_BRONZE.get());
                        pOutput.accept(TFSItems.BUCKET_OF_BRASS.get());
                        pOutput.accept(TFSItems.BUCKET_OF_STEEL.get());
                        pOutput.accept(TFSItems.BUCKET_OF_CAST_IRON.get());
                        pOutput.accept(TFSItems.BUCKET_OF_CARBON.get());
                        pOutput.accept(TFSItems.BUCKET_OF_UNKNOWN_METAL.get());

                        pOutput.accept(TFSItems.RAW_DIAMOND.get());
                        pOutput.accept(TFSItems.RAW_EMERALD.get());
                        pOutput.accept(TFSItems.CINNABAR.get());
                        pOutput.accept(TFSItems.RAW_LAZULI.get());
                        pOutput.accept(TFSItems.RICH_CUPRITE.get());
                        pOutput.accept(TFSItems.CUPRITE.get());
                        pOutput.accept(TFSItems.POOR_CUPRITE.get());
                        pOutput.accept(TFSItems.RICH_LIMONITE.get());
                        pOutput.accept(TFSItems.LIMONITE.get());
                        pOutput.accept(TFSItems.POOR_LIMONITE.get());
                        pOutput.accept(TFSItems.RICH_TIN.get());
                        pOutput.accept(TFSItems.TIN.get());
                        pOutput.accept(TFSItems.POOR_TIN.get());
                        pOutput.accept(TFSItems.RICH_ZINC.get());
                        pOutput.accept(TFSItems.ZINC.get());
                        pOutput.accept(TFSItems.POOR_ZINC.get());
                        pOutput.accept(TFSItems.RICH_SILVER.get());
                        pOutput.accept(TFSItems.SILVER.get());
                        pOutput.accept(TFSItems.POOR_SILVER.get());
                        pOutput.accept(TFSItems.RICH_GOLD.get());
                        pOutput.accept(TFSItems.GOLD.get());
                        pOutput.accept(TFSItems.POOR_GOLD.get());

                        pOutput.accept(TFSBlocks.FORGE.get());
                        pOutput.accept(TFSBlocks.FOUNDRY.get());

                        pOutput.accept(TFSBlocks.TIN_BLOCK.get());
                        pOutput.accept(TFSBlocks.SILVER_BLOCK.get());
                        pOutput.accept(TFSBlocks.BRONZE_BLOCK.get());
                        pOutput.accept(TFSBlocks.STEEL_BLOCK.get());
                        pOutput.accept(TFSBlocks.CAST_IRON_BLOCK.get());
                        pOutput.accept(TFSBlocks.UNKNOWN_METAL_BLOCK.get());

                        pOutput.accept(TFSBlocks.SAND_DIAMOND.get());
                        pOutput.accept(TFSBlocks.GRAVEL_DIAMOND.get());
                        pOutput.accept(TFSBlocks.SAND_EMERALD.get());
                        pOutput.accept(TFSBlocks.GRAVEL_EMERALD.get());
                        pOutput.accept(TFSBlocks.SAND_LAZULI.get());
                        pOutput.accept(TFSBlocks.GRAVEL_LAZULI.get());
                        pOutput.accept(TFSBlocks.SAND_CINNABAR.get());
                        pOutput.accept(TFSBlocks.GRAVEL_CINNABAR.get());
                        pOutput.accept(TFSBlocks.SAND_COAL.get());
                        pOutput.accept(TFSBlocks.GRAVEL_COAL.get());
                        pOutput.accept(TFSBlocks.SAND_CUPRITE.get());
                        pOutput.accept(TFSBlocks.GRAVEL_CUPRITE.get());
                        pOutput.accept(TFSBlocks.SAND_LIMONITE.get());
                        pOutput.accept(TFSBlocks.GRAVEL_LIMONITE.get());
                        pOutput.accept(TFSBlocks.SAND_TIN.get());
                        pOutput.accept(TFSBlocks.GRAVEL_TIN.get());
                        pOutput.accept(TFSBlocks.SAND_ZINC.get());
                        pOutput.accept(TFSBlocks.GRAVEL_ZINC.get());
                        pOutput.accept(TFSBlocks.SAND_GOLD.get());
                        pOutput.accept(TFSBlocks.GRAVEL_GOLD.get());
                        pOutput.accept(TFSBlocks.SAND_SILVER.get());
                        pOutput.accept(TFSBlocks.GRAVEL_SILVER.get());

                        pOutput.accept(TFSBlocks.RICH_CUPRITE_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_CUPRITE_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_STONE_CUPRITE_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_CUPRITE_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_DEEP_CUPRITE_ORE.get());
                        pOutput.accept(TFSBlocks.RICH_LIMONITE_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_LIMONITE_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_STONE_LIMONITE_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_LIMONITE_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_DEEP_LIMONITE_ORE.get());
                        pOutput.accept(TFSBlocks.RICH_TIN_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_TIN_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_STONE_TIN_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_TIN_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_DEEP_TIN_ORE.get());
                        pOutput.accept(TFSBlocks.RICH_ZINC_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_ZINC_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_STONE_ZINC_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_ZINC_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_DEEP_ZINC_ORE.get());
                        pOutput.accept(TFSBlocks.RICH_SILVER_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_SILVER_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_STONE_SILVER_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_SILVER_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_DEEP_SILVER_ORE.get());
                        pOutput.accept(TFSBlocks.RICH_GOLD_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_GOLD_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_STONE_GOLD_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_GOLD_ORE.get());
                        pOutput.accept(TFSBlocks.POOR_DEEP_GOLD_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_DIAMOND_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_DIAMOND_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_EMERALD_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_EMERALD_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_CINNABAR_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_CINNABAR_ORE.get());
                        pOutput.accept(TFSBlocks.STONE_LAZULI_ORE.get());
                        pOutput.accept(TFSBlocks.DEEP_LAZULI_ORE.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
