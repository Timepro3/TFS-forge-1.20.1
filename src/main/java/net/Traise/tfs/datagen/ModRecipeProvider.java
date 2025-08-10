package net.Traise.tfs.datagen;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.datagen.recipe.AlloyRecipeBuilder;
import net.Traise.tfs.datagen.recipe.CreateRecipe;
import net.Traise.tfs.datagen.recipe.FoundryRecipeBuilder;
import net.Traise.tfs.datagen.recipe.RemovingFromMoldRecipeBuilder;
import net.Traise.tfs.fluid.TFSFluids;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ModTags;
import net.Traise.tfs.util.MoldType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static net.minecraft.world.item.crafting.Ingredient.of;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> INGOT_FORM = List.of(TFSItems.RAW_INGOT_FORM.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);

    }

    protected static String getFluidName(Fluid pItemLike) {
        return BuiltInRegistries.FLUID.getKey(pItemLike).getPath();
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void netheriteSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem), Ingredient.of(Items.NETHERITE_INGOT), pCategory, pResultItem).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(pFinishedRecipeConsumer, getItemName(pResultItem) + "_smithing");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, tfs.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void foundry(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Fluid pFluid, int Amount, ItemLike pItem) {
        FoundryRecipeBuilder.foundry(pFluid, Amount)
                .requires(pItem)
                .unlockedBy(getHasName(pItem), has(pItem))
                .save(pFinishedRecipeConsumer, tfs.MOD_ID + ":" + getFluidName(pFluid) + "_from_melting_" + getItemName(pItem));
    }

    protected static void mold(Consumer<FinishedRecipe> pFinishedRecipeConsumer, MoldType moldType, Item item, Fluid fluid) {
        RemovingFromMoldRecipeBuilder.mold(moldType, item)
                .requires(fluid)
                .unlockedBy(getHasName(item), has(item))
                .save(pFinishedRecipeConsumer, tfs.MOD_ID + ":" + getItemName(item) + "_from_molding_" + getFluidName(fluid));
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_SPEAR.get(), 1)
                    .requires(TFSItems.PIK_ROCK_SPEAR.get()).requires(AllTags.forgeItemTag("string")).requires(Items.STICK).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.PIK_ROCK_SPEAR.get()), has(TFSItems.PIK_ROCK_SPEAR.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_SPEAR.get(), 1)
                    .requires(TFSItems.ROCK_KNIFE.get()).requires(AllTags.forgeItemTag("string")).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.ROCK_KNIFE.get()), has(TFSItems.ROCK_KNIFE.get()))
                    .save(pWriter, "rock_spear_in_rock_knife");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_KNIFE.get(), 1)
                    .requires(TFSItems.PIK_ROCK_SPEAR.get()).requires(AllTags.forgeItemTag("string")).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.PIK_ROCK_SPEAR.get()), has(TFSItems.PIK_ROCK_SPEAR.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_AXE.get(), 1)
                    .requires(TFSItems.PIK_ROCK_AXE.get()).requires(AllTags.forgeItemTag("string")).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.PIK_ROCK_AXE.get()), has(TFSItems.PIK_ROCK_AXE.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_HOE.get(), 1)
                    .requires(TFSItems.PIK_ROCK_HOE.get()).requires(AllTags.forgeItemTag("string")).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.PIK_ROCK_HOE.get()), has(TFSItems.PIK_ROCK_HOE.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_PICKAXE.get(), 1)
                    .requires(TFSItems.PIK_ROCK_PICK.get()).requires(AllTags.forgeItemTag("string")).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.PIK_ROCK_PICK.get()), has(TFSItems.PIK_ROCK_PICK.get()))
                    .save(pWriter, "rock_pickaxe_in_double_hoe");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_PICKAXE.get(), 1)
                    .requires(TFSItems.ROCK_HOE.get()).requires(AllTags.forgeItemTag("string")).requires(TFSItems.ROCK_HOE.get())
                    .unlockedBy(getHasName(TFSItems.ROCK_HOE.get()), has(TFSItems.ROCK_HOE.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK_SHOVEL.get(), 1)
                    .requires(TFSItems.PIK_ROCK_SHOVEL.get()).requires(AllTags.forgeItemTag("string")).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.PIK_ROCK_SHOVEL.get()), has(TFSItems.PIK_ROCK_SHOVEL.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.WOODEN_SPEAR.get(), 1)
                    .requires(TFSItems.POLE.get()).requires(Items.STICK)
                    .unlockedBy(getHasName(TFSItems.POLE.get()), has(TFSItems.POLE.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.CUDGEL.get(), 1)
                    .requires(Items.STICK).requires(ItemTags.LOGS)
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.RAW_INGOT_FORM.get())
                    .pattern("SS")
                    .define('S', Items.CLAY_BALL)
                    .unlockedBy(getHasName(Items.CLAY_BALL), has(Items.CLAY_BALL))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSBlocks.FORGE.get())
                    .pattern("SSS")
                    .pattern("SFS")
                    .pattern("SSS")
                    .define('F', Items.FURNACE)
                    .define('S', Items.CLAY_BALL)
                    .unlockedBy(getHasName(Items.FURNACE), has(Items.FURNACE))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COBBLESTONE)
                    .pattern("SS")
                    .pattern("SS")
                    .define('S', TFSItems.ROCK.get())
                    .unlockedBy(getHasName(TFSItems.ROCK.get()), has(TFSItems.ROCK.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ROCK.get(), 4)
                    .requires(Items.COBBLESTONE)
                    .unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
                    .save(pWriter);

            netheriteSmithing(pWriter, TFSItems.DIAMOND_HAMMER.get(), RecipeCategory.MISC, TFSItems.NETHERITE_HAMMER.get());
            netheriteSmithing(pWriter, TFSItems.DIAMOND_SPADE.get(), RecipeCategory.MISC, TFSItems.NETHERITE_SPADE.get());
            netheriteSmithing(pWriter, TFSItems.DIAMOND_SICKLE.get(), RecipeCategory.MISC, TFSItems.NETHERITE_SICKLE.get());
            netheriteSmithing(pWriter, TFSItems.DIAMOND_KNIFE.get(), RecipeCategory.MISC, TFSItems.NETHERITE_KNIFE.get());
            netheriteSmithing(pWriter, TFSItems.DIAMOND_SPEAR.get(), RecipeCategory.MISC, TFSItems.NETHERITE_SPEAR.get());

        } //all

        {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.TIN_INGOT.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.TIN_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.TIN_NUGGET.get()), has(TFSItems.TIN_NUGGET.get()))
                    .save(pWriter, "ingot_in_tin_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.SILVER_INGOT.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.SILVER_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.SILVER_NUGGET.get()), has(TFSItems.SILVER_NUGGET.get()))
                    .save(pWriter, "ingot_in_silver_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.BRONZE_INGOT.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.BRONZE_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.BRONZE_NUGGET.get()), has(TFSItems.BRONZE_NUGGET.get()))
                    .save(pWriter, "ingot_in_bronze_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.STEEL_INGOT.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.STEEL_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.STEEL_NUGGET.get()), has(TFSItems.STEEL_NUGGET.get()))
                    .save(pWriter, "ingot_in_steel_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.CAST_IRON_INGOT.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.CAST_IRON_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.CAST_IRON_NUGGET.get()), has(TFSItems.CAST_IRON_NUGGET.get()))
                    .save(pWriter, "ingot_in_cast_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.UNKNOWN_METAL_INGOT.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.UNKNOWN_METAL_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.UNKNOWN_METAL_NUGGET.get()), has(TFSItems.UNKNOWN_METAL_NUGGET.get()))
                    .save(pWriter, "ingot_in_unk_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NETHERITE_INGOT)
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.NETHERITE_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.NETHERITE_NUGGET.get()), has(TFSItems.NETHERITE_NUGGET.get()))
                    .save(pWriter, "ingot_in_netherite_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.ANDESITE_ALLOY)
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.ANDEZITE_ALLOY_NUGGET.get())
                    .unlockedBy(getHasName(TFSItems.ANDEZITE_ALLOY_NUGGET.get()), has(TFSItems.ANDEZITE_ALLOY_NUGGET.get()))
                    .save(pWriter, "ingot_in_andesall_nug");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.EMERALD)
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.EMERALD_FRAGMENT.get())
                    .unlockedBy(getHasName(TFSItems.EMERALD_FRAGMENT.get()), has(TFSItems.EMERALD_FRAGMENT.get()))
                    .save(pWriter, "cristal_in_emerald_fragment");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DIAMOND)
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.DIAMOND_FRAGMENT.get())
                    .unlockedBy(getHasName(TFSItems.DIAMOND_FRAGMENT.get()), has(TFSItems.DIAMOND_FRAGMENT.get()))
                    .save(pWriter, "cristal_in_diamond_fragment");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LAPIS_LAZULI)
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.LAZULI_FRAGMENT.get())
                    .unlockedBy(getHasName(TFSItems.LAZULI_FRAGMENT.get()), has(TFSItems.LAZULI_FRAGMENT.get()))
                    .save(pWriter, "cristal_in_lazuli_fragment");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSItems.CINNABAR.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.CINNABAR_FRAGMENT.get())
                    .unlockedBy(getHasName(TFSItems.CINNABAR_FRAGMENT.get()), has(TFSItems.CINNABAR_FRAGMENT.get()))
                    .save(pWriter, "cristal_in_cinnabar_fragment");

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COAL)
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.COAL_FRAGMENT.get())
                    .unlockedBy(getHasName(TFSItems.COAL_FRAGMENT.get()), has(TFSItems.COAL_FRAGMENT.get()))
                    .save(pWriter, "coal_in_fragment");
        } //ingot

        {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSBlocks.TIN_BLOCK.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.TIN_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.TIN_INGOT.get()), has(TFSItems.TIN_INGOT.get()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSBlocks.SILVER_BLOCK.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.SILVER_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.SILVER_INGOT.get()), has(TFSItems.SILVER_INGOT.get()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSBlocks.BRONZE_BLOCK.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.BRONZE_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.BRONZE_INGOT.get()), has(TFSItems.BRONZE_INGOT.get()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSBlocks.STEEL_BLOCK.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.STEEL_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.STEEL_INGOT.get()), has(TFSItems.STEEL_INGOT.get()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSBlocks.CAST_IRON_BLOCK.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.CAST_IRON_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.CAST_IRON_INGOT.get()), has(TFSItems.CAST_IRON_INGOT.get()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TFSBlocks.UNKNOWN_METAL_BLOCK.get())
                    .pattern("SSS")
                    .pattern("SSS")
                    .pattern("SSS")
                    .define('S', TFSItems.UNKNOWN_METAL_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.UNKNOWN_METAL_INGOT.get()), has(TFSItems.UNKNOWN_METAL_INGOT.get()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.TIN_INGOT.get(), 9)
                    .requires(TFSBlocks.TIN_BLOCK.get())
                    .unlockedBy(getHasName(TFSBlocks.TIN_BLOCK.get()), has(TFSBlocks.TIN_BLOCK.get()))
                    .save(pWriter, "ingot_in_tin_block");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.SILVER_INGOT.get(), 9)
                    .requires(TFSBlocks.SILVER_BLOCK.get())
                    .unlockedBy(getHasName(TFSBlocks.SILVER_BLOCK.get()), has(TFSBlocks.SILVER_BLOCK.get()))
                    .save(pWriter, "ingot_in_silv_block");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.BRONZE_INGOT.get(), 9)
                    .requires(TFSBlocks.BRONZE_BLOCK.get())
                    .unlockedBy(getHasName(TFSBlocks.BRONZE_BLOCK.get()), has(TFSBlocks.BRONZE_BLOCK.get()))
                    .save(pWriter, "ingot_in_bronze_block");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.STEEL_INGOT.get(), 9)
                    .requires(TFSBlocks.STEEL_BLOCK.get())
                    .unlockedBy(getHasName(TFSBlocks.STEEL_BLOCK.get()), has(TFSBlocks.STEEL_BLOCK.get()))
                    .save(pWriter, "ingot_in_steel_block");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.CAST_IRON_INGOT.get(), 9)
                    .requires(TFSBlocks.CAST_IRON_BLOCK.get())
                    .unlockedBy(getHasName(TFSBlocks.CAST_IRON_BLOCK.get()), has(TFSBlocks.CAST_IRON_BLOCK.get()))
                    .save(pWriter, "ingot_in_cast_block");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.UNKNOWN_METAL_INGOT.get(), 9)
                    .requires(TFSBlocks.UNKNOWN_METAL_BLOCK.get())
                    .unlockedBy(getHasName(TFSBlocks.UNKNOWN_METAL_BLOCK.get()), has(TFSBlocks.UNKNOWN_METAL_BLOCK.get()))
                    .save(pWriter, "ingot_in_unk_block");
        } //blocks

        {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.TIN_NUGGET.get(), 9)
                    .requires(TFSItems.TIN_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.TIN_INGOT.get()), has(TFSItems.TIN_INGOT.get()))
                    .save(pWriter, "nug_in_tin_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.SILVER_NUGGET.get(), 9)
                    .requires(TFSItems.SILVER_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.SILVER_INGOT.get()), has(TFSItems.SILVER_INGOT.get()))
                    .save(pWriter, "nug_in_silver_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.BRONZE_NUGGET.get(), 9)
                    .requires(TFSItems.BRONZE_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.BRONZE_INGOT.get()), has(TFSItems.BRONZE_INGOT.get()))
                    .save(pWriter, "nug_in_bronze_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.STEEL_NUGGET.get(), 9)
                    .requires(TFSItems.STEEL_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.STEEL_INGOT.get()), has(TFSItems.STEEL_INGOT.get()))
                    .save(pWriter, "nug_in_steel_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.CAST_IRON_NUGGET.get(), 9)
                    .requires(TFSItems.CAST_IRON_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.CAST_IRON_INGOT.get()), has(TFSItems.CAST_IRON_INGOT.get()))
                    .save(pWriter, "nug_in_cast_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.UNKNOWN_METAL_NUGGET.get(), 9)
                    .requires(TFSItems.UNKNOWN_METAL_INGOT.get())
                    .unlockedBy(getHasName(TFSItems.UNKNOWN_METAL_INGOT.get()), has(TFSItems.UNKNOWN_METAL_INGOT.get()))
                    .save(pWriter, "nug_in_unk_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.NETHERITE_NUGGET.get(), 9)
                    .requires(Items.NETHERITE_INGOT)
                    .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                    .save(pWriter, "nug_in_netherite_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.ANDEZITE_ALLOY_NUGGET.get(), 9)
                    .requires(AllItems.ANDESITE_ALLOY)
                    .unlockedBy(getHasName(AllItems.ANDESITE_ALLOY), has(AllItems.ANDESITE_ALLOY))
                    .save(pWriter, "nug_in_adesall_ingot");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.EMERALD_FRAGMENT.get(), 9)
                    .requires(Items.EMERALD)
                    .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                    .save(pWriter, "fragment_in_emerald_cristal");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.DIAMOND_FRAGMENT.get(), 9)
                    .requires(Items.DIAMOND)
                    .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                    .save(pWriter, "fragment_in_diamond_cristal");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.LAZULI_FRAGMENT.get(), 9)
                    .requires(Items.LAPIS_LAZULI)
                    .unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
                    .save(pWriter, "fragment_in_lazuli_cristal");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.CINNABAR_FRAGMENT.get(), 9)
                    .requires(TFSItems.CINNABAR.get())
                    .unlockedBy(getHasName(TFSItems.CINNABAR.get()), has(TFSItems.CINNABAR.get()))
                    .save(pWriter, "fragment_in_cinnabar_cristal");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TFSItems.COAL_FRAGMENT.get(), 9)
                    .requires(Items.COAL)
                    .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                    .save(pWriter, "fragment_in_coal");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLUE_DYE, 1)
                    .requires(TFSItems.LAZULI_DUST.get())
                    .unlockedBy(getHasName(TFSItems.LAZULI_DUST.get()), has(TFSItems.LAZULI_DUST.get()))
                    .save(pWriter, "blue_dye_in_lazuli_dust");
        } //Nuggets

        for (int i = 1; i <= 14; i++) {
            Component lit = Component.literal("_sword");
            if (i == 2) {
                lit = Component.literal("_shovel");
            } else if (i == 3) {
                lit = Component.literal("_pickaxe");
            } else if (i == 4) {
                lit = Component.literal("_axe");
            } else if (i == 5) {
                lit = Component.literal("_hoe");
            } else if (i == 6) {
                lit = Component.literal("_spear");
            } else if (i == 7) {
                lit = Component.literal("_knife");
            } else if (i == 8) {
                lit = Component.literal("_hammer");
            } else if (i == 9) {
                lit = Component.literal("_spade");
            } else if (i == 10) {
                lit = Component.literal("_sickle");
            } else if (i == 11) {
                lit = Component.literal("_helmet");
            } else if (i == 12) {
                lit = Component.literal("_chestplate");
            } else if (i == 13) {
                lit = Component.literal("_leggings");
            } else if (i == 14) {
                lit = Component.literal("_boots");
            }

            Component place = Component.literal("tfs:");
            if (i <= 5 || i > 10) {
                place = Component.literal("minecraft:");
            }

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new
                            ResourceLocation(place.getString() + "diamond" + lit.getString()))))
                    .pattern("TTT")
                    .pattern("TST")
                    .pattern("TTT")
                    .define('S', Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("tfs:steel" + lit.getString()))))
                    .define('T', TFSItems.DIAMOND_DUST.get())
                    .unlockedBy(getHasName(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("tfs:steel" + lit.getString())))),
                            has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("tfs:steel" + lit.getString())))))
                    .save(pWriter, "diamond" + lit.getString() + "_remove");
        }

        for (int i = 1; i <= 5; i++) {
            Component lit = Component.literal("iron");
            if (i == 2) {
                lit = Component.literal("silver");
            } else if (i == 3) {
                lit = Component.literal("gold");
            } else if (i == 4) {
                lit = Component.literal("bronze");
            } else if (i == 5) {
                lit = Component.literal("steel");
            }

            Component place = Component.literal("tfs:");
            if (i == 1 || i == 3) {
                place = Component.literal("minecraft:");
            }

            if (i != 1) {
                if (i != 3) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation(place.getString() + lit.getString() + "_sword"))))
                            .pattern("S")
                            .pattern("S")
                            .pattern("T")
                            .define('S', Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    (place.getString() + lit.getString() + "_ingot"))))
                            .define('T', AllTags.forgeItemTag("stick"))
                            .unlockedBy(getHasName(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            (place.getString() + lit.getString() + "_ingot")))),
                                    has(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            (place.getString() + lit.getString() + "_ingot")))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("" + place.getString() + lit.getString() + "_shovel")))
                            .pattern("S")
                            .pattern("T")
                            .pattern("T")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .define('T', AllTags.forgeItemTag("stick"))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("" + place.getString() + lit.getString() + "_pickaxe")))
                            .pattern("SSS")
                            .pattern(" T ")
                            .pattern(" T ")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .define('T', AllTags.forgeItemTag("stick"))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("" + place.getString() + lit.getString() + "_axe")))
                            .pattern("SS")
                            .pattern("ST")
                            .pattern(" T")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .define('T', AllTags.forgeItemTag("stick"))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("" + place.getString() + lit.getString() + "_hoe")))
                            .pattern("SS")
                            .pattern(" T")
                            .pattern(" T")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .define('T', AllTags.forgeItemTag("stick"))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("tfs:" + lit.getString() + "_helmet")))
                            .pattern("SSS")
                            .pattern("S S")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("tfs:" + lit.getString() + "_chestplate")))
                            .pattern("S S")
                            .pattern("SSS")
                            .pattern("SSS")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("tfs:" + lit.getString() + "_leggings")))
                            .pattern("SSS")
                            .pattern("S S")
                            .pattern("S S")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                                    ResourceLocation("tfs:" + lit.getString() + "_boots")))
                            .pattern("S S")
                            .pattern("S S")
                            .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot")))
                            .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))),
                                    has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                            ("" + place.getString() + lit.getString() + "_ingot"))))
                            .save(pWriter);
                }

            }

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                            ResourceLocation("tfs:" + lit.getString() + "_hammer")))
                    .pattern("USU")
                    .pattern(" T ")
                    .pattern(" T ")
                    .define('U', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_block")))
                    .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_ingot")))
                    .define('T', AllTags.forgeItemTag("stick"))
                    .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))),
                            has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                            ResourceLocation("tfs:" + lit.getString() + "_spade")))
                    .pattern(" S ")
                    .pattern("UTU")
                    .pattern(" T ")
                    .define('U', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_block")))
                    .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_ingot")))
                    .define('T', AllTags.forgeItemTag("stick"))
                    .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))),
                            has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                            ResourceLocation("tfs:" + lit.getString() + "_sickle")))
                    .pattern(" U ")
                    .pattern("S U")
                    .pattern("  T")
                    .define('U', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_block")))
                    .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_ingot")))
                    .define('T', AllTags.forgeItemTag("stick"))
                    .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))),
                            has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                            ResourceLocation("tfs:" + lit.getString() + "_knife")))
                    .pattern(" S")
                    .pattern("T ")
                    .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_ingot")))
                    .define('T', AllTags.forgeItemTag("stick"))
                    .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))),
                            has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                            ResourceLocation("tfs:" + lit.getString() + "_spear")))
                    .pattern("  S")
                    .pattern(" T ")
                    .pattern("T  ")
                    .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("" + place.getString() + lit.getString() + "_ingot")))
                    .define('T', AllTags.forgeItemTag("stick"))
                    .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))),
                            has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(new
                            ResourceLocation("tfs:" + lit.getString() + "_spear")))
                    .pattern("US")
                    .pattern("T ")
                    .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            ("tfs:" + lit.getString() + "_knife")))
                    .define('T', AllTags.forgeItemTag("stick"))
                    .define('U', AllTags.forgeItemTag("string"))
                    .unlockedBy(getHasName(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))),
                            has(ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                    ("" + place.getString() + lit.getString() + "_ingot"))))
                    .save(pWriter, "" + lit.getString() + "_spear_in_knife");
        }

        for (int i = 0; i < 11; i++) {
            foundry(pWriter, ForgeRegistries.FLUIDS.getValue(new ResourceLocation
                    (tfs.MOD_ID, material(i).getString())), 100,
                    ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            (place(i).getString() + material(i).getString() + "_ingot")));

            foundry(pWriter, ForgeRegistries.FLUIDS.getValue(new ResourceLocation
                            (tfs.MOD_ID, material(i).getString())), 900,
                    ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            (place(i).getString() + material(i).getString() + "_block")));

            mold(pWriter, MoldType.INGOT,
                    ForgeRegistries.ITEMS.getValue(new ResourceLocation
                            (place(i).getString() + material(i).getString() + "_ingot")),
                    ForgeRegistries.FLUIDS.getValue(new ResourceLocation
                            (tfs.MOD_ID, material(i).getString())));

            if (i < 6) {
                Component c = material(i);
                if (i == 0) {
                    c = Component.literal("limonite");
                } else if (i == 1) {
                    c = Component.literal("cuprite");
                }

                foundry(pWriter, ForgeRegistries.FLUIDS.getValue(new ResourceLocation
                                (tfs.MOD_ID, material(i).getString())), 15,
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                (tfs.MOD_ID, "poor_" + c.getString())));

                foundry(pWriter, ForgeRegistries.FLUIDS.getValue(new ResourceLocation
                                (tfs.MOD_ID, material(i).getString())), 20,
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                (tfs.MOD_ID, c.getString())));

                foundry(pWriter, ForgeRegistries.FLUIDS.getValue(new ResourceLocation
                                (tfs.MOD_ID, material(i).getString())), 35,
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation
                                (tfs.MOD_ID, "rich_" + c.getString())));
            }
        }

        {
            FoundryRecipeBuilder.foundry(TFSFluids.CARBON.get(), 40)
                    .requires(ItemTags.COALS)
                    .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                    .save(pWriter, "carbon_from_melting_coals");

            AlloyRecipeBuilder.alloy(TFSFluids.BRONZE.get())
                    .requires(TFSFluids.COPPER.get(), 90f)
                    .requires(TFSFluids.TIN.get(), 10f)
                    .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                    .save(pWriter, "bronze_from_alloying_copper_tin");

            AlloyRecipeBuilder.alloy(TFSFluids.BRASS.get())
                    .requires(TFSFluids.COPPER.get(), 70f)
                    .requires(TFSFluids.ZINC.get(), 30f)
                    .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                    .save(pWriter, "brass_from_alloying_copper_zinc");

            AlloyRecipeBuilder.alloy(TFSFluids.STEEL.get())
                    .requires(TFSFluids.IRON.get(), 95f)
                    .requires(TFSFluids.CARBON.get(), 5f)
                    .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                    .save(pWriter, "steel_from_alloying_iron_carbon");

            AlloyRecipeBuilder.alloy(TFSFluids.CAST_IRON.get())
                    .requires(TFSFluids.IRON.get(), 89f)
                    .requires(TFSFluids.CARBON.get(), 11f)
                    .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                    .save(pWriter, "cast_iron_from_alloying_iron_carbon");
        }

        {

            oreSmelting(pWriter, INGOT_FORM, RecipeCategory.MISC, TFSItems.INGOT_FORM.get(), 0.25f, 200, "ingot_form");

            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.RAW_DIAMOND.get()), RecipeCategory.DECORATIONS, Items.DIAMOND).unlockedBy("has_raw_diamond", has(TFSItems.RAW_DIAMOND.get())).save(pWriter, "diamond_in_raw_diamond");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.RAW_EMERALD.get()), RecipeCategory.DECORATIONS, Items.EMERALD).unlockedBy("has_raw_emerald", has(TFSItems.RAW_EMERALD.get())).save(pWriter, "emerald_in_raw_emerald");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.RAW_LAZULI.get()), RecipeCategory.DECORATIONS, Items.LAPIS_LAZULI).unlockedBy("has_raw_lazuli", has(TFSItems.RAW_LAZULI.get())).save(pWriter, "lazuli_in_raw_lazuli");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.CINNABAR.get()), RecipeCategory.DECORATIONS, Items.REDSTONE).unlockedBy("has_cinnabar", has(TFSItems.CINNABAR.get())).save(pWriter, "redstone_in_cinnabar");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.TWIG.get()), RecipeCategory.DECORATIONS, TFSItems.DISTORTED_TWIG.get()).unlockedBy("has_twig", has(TFSItems.TWIG.get())).save(pWriter, "dtwig_in_twig");
            CreateRecipe.SandPaperPolishing(Ingredient.of(Items.EMERALD), RecipeCategory.DECORATIONS, TFSItems.EMERALD_DUST.get()).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter, "dust_in_emerald");
            CreateRecipe.SandPaperPolishing(Ingredient.of(Items.DIAMOND), RecipeCategory.DECORATIONS, TFSItems.DIAMOND_DUST.get()).unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND)).save(pWriter, "dust_in_diamond");
            CreateRecipe.SandPaperPolishing(Ingredient.of(Items.LAPIS_LAZULI), RecipeCategory.DECORATIONS, TFSItems.LAZULI_DUST.get()).unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI)).save(pWriter, "lapis_lazuli");
        }

        {
            CreateRecipe.SandPaperPolishing(Ingredient.of(AllItems.ZINC_INGOT), RecipeCategory.DECORATIONS, TFSItems.ZINC_SHEET.get()).unlockedBy("has_zinc", has(AllItems.ZINC_INGOT)).save(pWriter, "shzinc_in_zinc");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.TIN_INGOT.get()), RecipeCategory.DECORATIONS, TFSItems.TIN_SHEET.get()).unlockedBy("has_tin", has(TFSItems.TIN_INGOT.get())).save(pWriter, "shtin_in_tin");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.SILVER_INGOT.get()), RecipeCategory.DECORATIONS, TFSItems.SILVER_SHEET.get()).unlockedBy("has_silver", has(TFSItems.SILVER_INGOT.get())).save(pWriter, "shsilver_in_silver");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.BRONZE_INGOT.get()), RecipeCategory.DECORATIONS, TFSItems.BRONZE_SHEET.get()).unlockedBy("has_bronze", has(TFSItems.BRONZE_INGOT.get())).save(pWriter, "shbronze_in_bronze");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.STEEL_INGOT.get()), RecipeCategory.DECORATIONS, TFSItems.STEEL_SHEET.get()).unlockedBy("has_steel", has(TFSItems.STEEL_INGOT.get())).save(pWriter, "shsteel_in_steel");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.CAST_IRON_INGOT.get()), RecipeCategory.DECORATIONS, TFSItems.CAST_IRON_SHEET.get()).unlockedBy("has_cast", has(TFSItems.CAST_IRON_INGOT.get())).save(pWriter, "shcast_in_cast");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.UNKNOWN_METAL_INGOT.get()), RecipeCategory.DECORATIONS, TFSItems.UNKNOWN_METAL_SHEET.get()).unlockedBy("has_unk", has(TFSItems.UNKNOWN_METAL_INGOT.get())).save(pWriter, "shunk_in_unk");
            CreateRecipe.SandPaperPolishing(Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.DECORATIONS, TFSItems.NETHERITE_SHEET.get()).unlockedBy("has_netherite", has(Items.NETHERITE_INGOT)).save(pWriter, "shnetherite_in_netherite");
            CreateRecipe.SandPaperPolishing(Ingredient.of(AllItems.ANDESITE_ALLOY), RecipeCategory.DECORATIONS, TFSItems.ANDEZITE_ALLOY_SHEET.get()).unlockedBy("has_andesall", has(AllItems.ANDESITE_ALLOY)).save(pWriter, "shandesall_in_andesall");
        }

        {
            CreateRecipe.SandPaperPolishing(Ingredient.of(Items.STICK), RecipeCategory.TOOLS, TFSItems.REVOLVER.get()).unlockedBy("has_rev", has(Items.STICK)).save(pWriter, "revolver_combinate");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.BRONZE_SHEET.get()), RecipeCategory.TOOLS, TFSItems.SLEEVE.get()).unlockedBy("has_bronze_she", has(TFSItems.BRONZE_SHEET.get())).save(pWriter, "sleeve_combinate");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.SLEEVE.get()), RecipeCategory.TOOLS, TFSItems.CARTRIDGE_SILVER.get()).unlockedBy("has_casilv", has(TFSItems.SLEEVE.get())).save(pWriter, "silv_combinate");
            CreateRecipe.SandPaperPolishing(Ingredient.of(TFSItems.SLEEVE.get()), RecipeCategory.TOOLS, TFSItems.CARTRIDGE_STEEL.get()).unlockedBy("has_casteel", has(TFSItems.SLEEVE.get())).save(pWriter, "steel_combinate");
        }
    }

    private Component material(int index) {
        return switch (index) {
            case 0 -> Component.literal("iron");
            case 1 -> Component.literal("copper");
            case 2 -> Component.literal("tin");
            case 3 -> Component.literal("zinc");
            case 4 -> Component.literal("gold");
            case 5 -> Component.literal("silver");
            case 6 -> Component.literal("bronze");
            case 7 -> Component.literal("brass");
            case 8 -> Component.literal("steel");
            case 9 -> Component.literal("cast_iron");
            case 10 -> Component.literal("unknown_metal");
            default -> Component.literal("iron");
        };
    }

    private Component place(int index) {
        return switch (index) {
            case 2 -> Component.literal("tfs:");
            case 3 -> Component.literal("create:");
            case 5 -> Component.literal("tfs:");
            case 6 -> Component.literal("tfs:");
            case 7 -> Component.literal("create:");
            case 8 -> Component.literal("tfs:");
            case 9 -> Component.literal("tfs:");
            case 10 -> Component.literal("tfs:");
            default -> Component.literal("minecraft:");
        };
    }
}
