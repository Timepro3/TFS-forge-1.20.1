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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class AlloyRecipeBuilder extends CraftingRecipeBuilder implements RecipeBuilder {
    private FluidStack result;
    private final List<FluidStack> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public AlloyRecipeBuilder(FluidStack pResult) {
        this.result = pResult;
    }

    public static AlloyRecipeBuilder alloy(Fluid pResult) {
        return new AlloyRecipeBuilder(new FluidStack(pResult, 1));
    }

    public AlloyRecipeBuilder requires(Fluid fluid, float percent) {
        this.ingredients.add(new FluidStack(fluid, (int) (percent * 100)));

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
        return ItemStack.EMPTY.getItem();
    }

    public FluidStack getFluidResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new AlloyRecipeBuilder.Result(pRecipeId, CraftingBookCategory.MISC, this.result, this.ingredients, this.advancement, pRecipeId.withPrefix("recipes/alloying/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result extends CraftingRecipeBuilder.CraftingResult {
        private final ResourceLocation id;
        private final FluidStack result;
        private final List<FluidStack> ingredients;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, CraftingBookCategory category, FluidStack pResult, List<FluidStack> pIngredients, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            super(category);
            this.id = pId;
            this.result = pResult;
            this.ingredients = pIngredients;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonarray = new JsonArray();

            for(int i = 0; i < ingredients.size(); i++) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("fluid", BuiltInRegistries.FLUID.getKey(this.ingredients.get(i).getFluid()).toString());
                jsonobject.addProperty("amount", this.ingredients.get(i).getAmount());

                jsonarray.add(jsonobject);
            }

            pJson.add("ingredients", jsonarray);

            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("fluid", BuiltInRegistries.FLUID.getKey(this.result.getFluid()).toString());
            jsonobject.addProperty("amount", this.result.getAmount());


            pJson.add("output", jsonobject);
        }

        public RecipeSerializer<?> getType() {
            return TFSRecipes.ALLOYING_SERIALIZER.get();
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
