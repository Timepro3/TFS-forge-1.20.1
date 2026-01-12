package net.Traise.tfs.datagen.Tags;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                               CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, tfs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.METALS)
                .add(Items.COAL, Items.CHARCOAL, TFSItems.INGOT_FORM.get())
                .addTags(ModTags.Items.METAL_COPPER, ModTags.Items.METAL_IRON, ModTags.Items.METAL_TIN,
                        ModTags.Items.METAL_ZINC, ModTags.Items.METAL_GOLD, ModTags.Items.METAL_SILVER,
                        ModTags.Items.METAL_BRONZE, ModTags.Items.METAL_BRASS, ModTags.Items.METAL_STEEL,
                        ModTags.Items.METAL_CAST_IRON);

        {this.tag(ModTags.Items.METAL_COPPER)
                .add(TFSItems.POOR_CUPRITE.get(), TFSItems.CUPRITE.get(), TFSItems.RICH_CUPRITE.get(),
                        Items.COPPER_INGOT);

        this.tag(ModTags.Items.METAL_IRON)
                .add(TFSItems.POOR_LIMONITE.get(), TFSItems.LIMONITE.get(), TFSItems.RICH_LIMONITE.get(),
                        Items.IRON_INGOT);

        this.tag(ModTags.Items.METAL_TIN)
                .add(TFSItems.POOR_TIN.get(), TFSItems.TIN.get(), TFSItems.RICH_TIN.get(),
                        TFSItems.TIN_INGOT.get());

        this.tag(ModTags.Items.METAL_ZINC)
                .add(TFSItems.POOR_ZINC.get(), TFSItems.ZINC.get(), TFSItems.RICH_ZINC.get(),
                        AllItems.ZINC_INGOT.get());

        this.tag(ModTags.Items.METAL_GOLD)
                .add(TFSItems.POOR_GOLD.get(), TFSItems.GOLD.get(), TFSItems.RICH_GOLD.get(),
                        Items.GOLD_INGOT);

        this.tag(ModTags.Items.METAL_SILVER)
                .add(TFSItems.POOR_SILVER.get(), TFSItems.SILVER.get(), TFSItems.RICH_SILVER.get(),
                        TFSItems.SILVER_INGOT.get());

        this.tag(ModTags.Items.METAL_BRONZE)
                .add(TFSItems.BRONZE_INGOT.get());

        this.tag(ModTags.Items.METAL_BRASS)
                .add(AllItems.BRASS_INGOT.get());

        this.tag(ModTags.Items.METAL_STEEL)
                .add(TFSItems.STEEL_INGOT.get());

        this.tag(ModTags.Items.METAL_CAST_IRON)
                .add(TFSItems.CAST_IRON_INGOT.get());}

        this.tag(ModTags.Items.METAL_15)
                .add(TFSItems.POOR_CUPRITE.get(), TFSItems.POOR_LIMONITE.get(), TFSItems.POOR_TIN.get(),
                        TFSItems.POOR_ZINC.get(), TFSItems.POOR_SILVER.get(), TFSItems.POOR_GOLD.get());

        this.tag(ModTags.Items.METAL_20)
                .add(TFSItems.CUPRITE.get(), TFSItems.LIMONITE.get(), TFSItems.TIN.get(),
                TFSItems.ZINC.get(), TFSItems.SILVER.get(), TFSItems.GOLD.get());

        this.tag(ModTags.Items.METAL_35)
                .add(TFSItems.RICH_CUPRITE.get(), TFSItems.RICH_LIMONITE.get(), TFSItems.RICH_TIN.get(),
                        TFSItems.RICH_ZINC.get(), TFSItems.RICH_SILVER.get(), TFSItems.RICH_GOLD.get());

        this.tag(ModTags.Items.METAL_100)
                .add(Items.COPPER_INGOT, Items.IRON_INGOT, TFSItems.TIN_INGOT.get(),
                        AllItems.ZINC_INGOT.get(), Items.GOLD_INGOT, TFSItems.SILVER_INGOT.get(),
                        TFSItems.BRONZE_INGOT.get(), AllItems.BRASS_INGOT.get(), TFSItems.STEEL_INGOT.get(),
                        TFSItems.CAST_IRON_INGOT.get());

        this.tag(ModTags.Items.TOOL_PART)
                .addTags(ModTags.Items.TOOL_HEAD, ModTags.Items.TOOL_STRING, ModTags.Items.TOOL_STICK);

        this.tag(ModTags.Items.TOOL_HEAD).add(TFSItems.SWORD_PART.get(), TFSItems.AXE_PART.get(),
                TFSItems.HOE_PART.get(), TFSItems.PICKAXE_PART.get(), TFSItems.SHOVEL_PART.get(),
                TFSItems.KNIFE_PART.get(), TFSItems.SPEAR_PART.get(), TFSItems.HAMMER_PART.get(),
                TFSItems.SPADE_PART.get(), TFSItems.SICKLE_PART.get(), TFSItems.TROWEL_PART.get(),
                TFSItems.GEOLOGICAL_HAMMER_PART.get(), TFSItems.BUILDER_WAND_PART.get(),
                TFSItems.PAXEL_PART.get());

        this.tag(ModTags.Items.TOOL_STICK).add(TFSItems.STICK_PART.get(), Items.STICK, Items.BONE
                , Items.BLAZE_ROD);

        this.tag(ModTags.Items.TOOL_STRING).add(TFSItems.STRING_PART.get(), Items.STRING, Items.LEATHER
                , Items.SLIME_BALL, Items.HONEY_BOTTLE, TFSItems.GRASS_ROPE.get());


        {
            this.tag(ModTags.Items.FORMS)
                    .add(TFSItems.INGOT_FORM.get());

            this.tag(ItemTags.TRIMMABLE_ARMOR)
                    .add(TFSItems.SILVER_HELMET.get(),
                            TFSItems.SILVER_CHESTPLATE.get(),
                            TFSItems.SILVER_LEGGINGS.get(),
                            TFSItems.SILVER_BOOTS.get(),
                            TFSItems.BRONZE_HELMET.get(),
                            TFSItems.BRONZE_CHESTPLATE.get(),
                            TFSItems.BRONZE_LEGGINGS.get(),
                            TFSItems.BRONZE_BOOTS.get(),
                            TFSItems.STEEL_HELMET.get(),
                            TFSItems.STEEL_CHESTPLATE.get(),
                            TFSItems.STEEL_LEGGINGS.get(),
                            TFSItems.STEEL_BOOTS.get(),
                            TFSItems.HELMET.get(),
                            TFSItems.CHESTPLATE.get(),
                            TFSItems.LEGGINGS.get(),
                            TFSItems.BOOTS.get());

            this.tag(ModTags.Items.KNIFE)
                    .add(TFSItems.ROCK_KNIFE.get(), TFSItems.PIK_ROCK_SPEAR.get(), TFSItems.IRON_KNIFE.get(),
                            TFSItems.GOLD_KNIFE.get(), TFSItems.SILVER_KNIFE.get(), TFSItems.BRONZE_KNIFE.get(),
                            TFSItems.STEEL_KNIFE.get(), TFSItems.DIAMOND_KNIFE.get(), TFSItems.NETHERITE_KNIFE.get());

            this.tag(ModTags.Items.BULLET)
                    .add(TFSItems.CARTRIDGE_STEEL.get(), TFSItems.CARTRIDGE_SILVER.get());

            this.tag(ModTags.Items.ROCK)
                    .add(TFSItems.ROCK.get());

            this.tag(ModTags.Items.SILVER)
                    .add(TFSItems.SILVER_SWORD.get(), TFSItems.SILVER_HAMMER.get(), TFSItems.SILVER_SPADE.get(),
                            TFSItems.SILVER_SHOVEL.get(), TFSItems.SILVER_PICKAXE.get(), TFSItems.SILVER_AXE.get(),
                            TFSItems.SILVER_KNIFE.get(), TFSItems.SILVER_HOE.get(), TFSItems.SILVER_SPEAR.get(),
                            TFSItems.SILVER_INGOT.get(), TFSItems.SILVER_NUGGET.get(), TFSItems.SILVER_SHEET.get(),
                            TFSBlocks.SILVER_BLOCK.get().asItem(), TFSItems.SILVER_SICKLE.get(), TFSItems.SILVER_HELMET.get(),
                            TFSItems.SILVER_CHESTPLATE.get(), TFSItems.SILVER_LEGGINGS.get(), TFSItems.SILVER_BOOTS.get());


            this.tag(AllTags.forgeItemTag("string"))
                    .add(TFSItems.GRASS_ROPE.get());

            this.tag(AllTags.forgeItemTag("stick"))
                    .add(Items.STICK);

            this.tag(AllTags.forgeItemTag("helmet"))
                    .add(TFSItems.HELMET.get());

            this.tag(AllTags.forgeItemTag("chestplate"))
                    .add(TFSItems.CHESTPLATE.get());

            this.tag(AllTags.forgeItemTag("leggings"))
                    .add(TFSItems.LEGGINGS.get());

            this.tag(AllTags.forgeItemTag("boots"))
                    .add(TFSItems.BOOTS.get());

            this.tag(AllTags.forgeItemTag("armors"))
                    .add(TFSItems.HELMET.get(), TFSItems.CHESTPLATE.get(),
                            TFSItems.LEGGINGS.get(), TFSItems.BOOTS.get());


            this.tag(Tags.Items.TOOLS_BOWS).add(TFSItems.BOW.get());
        }
    }
}
