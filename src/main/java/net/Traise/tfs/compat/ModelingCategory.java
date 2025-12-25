package net.Traise.tfs.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.Traise.tfs.recipe.ModelingRecipe;
import net.Traise.tfs.tfs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModelingCategory implements IRecipeCategory<ModelingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(tfs.MOD_ID, "modeling");
    public static final ResourceLocation TEXTURE = new ResourceLocation(tfs.MOD_ID,
            "textures/gui/modeling_jei_gui.png");

    public static final RecipeType<ModelingRecipe> MODELING_TYPE =
            new RecipeType<>(UID, ModelingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public ModelingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 160, 86);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.CLAY_BALL));
    }

    @Override
    public RecipeType<ModelingRecipe> getRecipeType() {
        return MODELING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.tfs.modeling");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ModelingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 133, 16).addItemStack(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 133, 55).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(ModelingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        int x = 0, y = 0;
        if (recipe.getWidth() <= 3) {
            x = 15;
        }
        if (recipe.getHeight() <= 3) {
            y = 15;
        }

        for (int Y = 0; Y < recipe.getHeight(); Y++) {
            for (int X = 0; X < recipe.getWidth(); X++) {
                if (recipe.getMap().get(X + (recipe.getWidth() * Y))) {
                    guiGraphics.blit(TEXTURE, 14 + x + (15 * X), 5 + y + (15 * Y), 160, 0, 15, 15);
                }
            }
        }

    }
}
