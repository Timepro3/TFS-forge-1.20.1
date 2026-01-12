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
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.CraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ToolAssemblyRecipeBuilder extends CraftingRecipeBuilder implements RecipeBuilder {
    private ItemStack result;
    private NonNullList<Ingredient> ingredients = NonNullList.create();
    private final CraftingBookCategory category;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public ToolAssemblyRecipeBuilder(ItemStack pResult, CraftingBookCategory category) {
        this.result = pResult;
        this.category = category;
    }

    public static ToolAssemblyRecipeBuilder assembly(Item pResult, CraftingBookCategory category) {
        return new ToolAssemblyRecipeBuilder(new ItemStack(pResult), category);
    }

    public ToolAssemblyRecipeBuilder ingredient(ItemLike ingredient, int Number) {
        ingredients.add(Number, Ingredient.of(ingredient));
        return this;
    }

    public ToolAssemblyRecipeBuilder ingredient(TagKey<Item> ingredient, int Number) {
        ingredients.add(Number, Ingredient.of(ingredient));
        return this;
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

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new ToolAssemblyRecipeBuilder.Result(pRecipeId, this.category, this.result, this.ingredients, this.advancement, pRecipeId.withPrefix("recipes/alloying/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result extends CraftingResult {
        private final ResourceLocation id;
        private final CraftingBookCategory category;
        private final ItemStack result;
        private final NonNullList<Ingredient> ingredients;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, CraftingBookCategory category, ItemStack pResult, NonNullList<Ingredient> pIngredients, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            super(category);
            this.id = pId;
            this.category = category;
            this.result = pResult;
            this.ingredients = pIngredients;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.addProperty("category", this.category.getSerializedName());

            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result.getItem()).toString());
            jsonobject.addProperty("count", this.result.getCount());

            pJson.add("output", jsonobject);

            JsonObject jsonarray = new JsonObject();
            int i = 0;
            for (Ingredient ingredient : this.ingredients) {
                JsonArray jsonar = new JsonArray();
                jsonar.add(ingredient.toJson());
                jsonarray.add("part_" + i, jsonar);
                i++;
            }

            pJson.add("key", jsonarray);

        }

        public RecipeSerializer<?> getType() {
            return TFSRecipes.TOOL_ASSEMBLY_SERIALIZER.get();
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