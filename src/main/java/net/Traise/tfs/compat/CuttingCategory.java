package net.Traise.tfs.compat;

import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.recipe.CuttingRecipe;
import net.Traise.tfs.tfs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CuttingCategory implements IRecipeCategory<CuttingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(tfs.MOD_ID, "cutting");
    public static final ResourceLocation TEXTURE = new ResourceLocation(tfs.MOD_ID,
            "textures/gui/cutting_jei_gui.png");

    public static final RecipeType<CuttingRecipe> CUTTING_TYPE =
            new RecipeType<>(UID, CuttingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CuttingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 160, 86);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TFSItems.ROCK.get()));
    }

    @Override
    public RecipeType<CuttingRecipe> getRecipeType() {
        return CUTTING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.tfs.cutting");
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
    public void setRecipe(IRecipeLayoutBuilder builder, CuttingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 133, 16).addItemStack(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 133, 55).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(CuttingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        for (int Y = 0; Y < recipe.getHeight(); Y++) {
            for (int X = 0; X < recipe.getWidth(); X++) {
                if (recipe.getMap().get(X + (recipe.getWidth() * Y))) {
                    guiGraphics.blit(TEXTURE, 14 + (15 * X), 5 + (15 * Y), 160, 0, 15, 15);
                }
            }
        }
    }
}
