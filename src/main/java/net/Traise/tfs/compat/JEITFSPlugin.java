package net.Traise.tfs.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.recipe.FoundryRecipe;
import net.Traise.tfs.screen.FoundryScreen;
import net.Traise.tfs.tfs;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

@JeiPlugin
public class JEITFSPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(tfs.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
       registration.addRecipeCategories(new FoundryCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<FoundryRecipe> meltingRecipes = recipeManager.getAllRecipesFor(FoundryRecipe.Type.INSTANCE);
        registration.addRecipes(FoundryCategory.FOUNDRY_TYPE, meltingRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(TFSBlocks.FOUNDRY.get()), FoundryCategory.FOUNDRY_TYPE);
    }

    /* @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FoundryScreen.class, 60, 30, 20, 30,
                FoundryCategory.FOUNDRY_TYPE);
    }*/
}
