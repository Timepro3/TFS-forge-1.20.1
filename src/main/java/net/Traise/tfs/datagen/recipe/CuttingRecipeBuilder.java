package net.Traise.tfs.datagen.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.Traise.tfs.recipe.TFSRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.CraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class CuttingRecipeBuilder extends CraftingRecipeBuilder implements RecipeBuilder {
    private ItemStack result;
    private ItemStack ingredients;
    private List<String> map = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CuttingRecipeBuilder(ItemStack pResult, ItemStack ingredients) {
        this.result = pResult;
        this.ingredients = ingredients;
    }

    public static CuttingRecipeBuilder cutting(Item pResult, int pCount, Item ingredients, int Count) {
        return new CuttingRecipeBuilder(new ItemStack(pResult, pCount), new ItemStack(ingredients, Count));
    }

    public CuttingRecipeBuilder pattern(String pPattern) {
        if (!this.map.isEmpty() && pPattern.length() != this.map.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.map.add(pPattern);
            return this;
        }
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return null;
    }

    @Override
    public Item getResult() {
        return this.result.getItem();
    }

    public ItemStack getItemResult() {
        return this.result;
    }

    public ItemStack getIngredients() {
        return this.ingredients;
    }

    public List<String> getMap() {
        return map;
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new CuttingRecipeBuilder.Result(pRecipeId, CraftingBookCategory.MISC, this.result, this.ingredients, this.map, this.advancement, pRecipeId.withPrefix("recipes/alloying/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result extends CraftingRecipeBuilder.CraftingResult {
        private final ResourceLocation id;
        private final ItemStack result;
        private final ItemStack ingredients;
        private final List<String> map;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, CraftingBookCategory category, ItemStack pResult, ItemStack pIngredients, List<String> map, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            super(category);
            this.id = pId;
            this.result = pResult;
            this.ingredients = pIngredients;
            this.map = map;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        public void serializeRecipeData(JsonObject pJson) {
            JsonObject jsonarray = new JsonObject();
            jsonarray.addProperty("item", BuiltInRegistries.ITEM.getKey(this.ingredients.getItem()).toString());
            jsonarray.addProperty("count", this.ingredients.getCount());

            pJson.add("input", jsonarray);

            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result.getItem()).toString());
            jsonobject.addProperty("count", this.result.getCount());

            pJson.add("output", jsonobject);

            JsonArray jsonArray = new JsonArray();

            for(String s : this.map) {
                jsonArray.add(s);
            }

            pJson.add("pattern", jsonArray);
        }

        public RecipeSerializer<?> getType() {
            return TFSRecipes.CUTTING_SERIALIZER.get();
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}