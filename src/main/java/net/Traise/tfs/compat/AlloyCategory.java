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
import net.Traise.tfs.recipe.AlloyRecipe;
import net.Traise.tfs.tfs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class AlloyCategory implements IRecipeCategory<AlloyRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(tfs.MOD_ID, "alloying");
    public static final ResourceLocation TEXTURE = new ResourceLocation(tfs.MOD_ID,
            "textures/gui/alloy_jei_gui.png");

    public static final RecipeType<AlloyRecipe> ALLOY_TYPE =
            new RecipeType<>(UID, AlloyRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AlloyCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 174, 78);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TFSBlocks.FOUNDRY.get()));
    }

    @Override
    public RecipeType<AlloyRecipe> getRecipeType() {
        return ALLOY_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.tfs.alloy");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AlloyRecipe recipe, IFocusGroup focuses) {
        int Y = getY(recipe.getInputFluids().size());
        int r = 0;
        for (int i = 0; i < recipe.getInputFluids().size(); i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 5 + 60 * r,  (int) (Y + 19 * Math.ceil(((float)(i + 1) / 2) - 1)))
                    .addFluidStack(recipe.getInputFluids().get(i).getFluid(), 1000);

            if (r == 0) {
                r = 1;
            } else r = 0;
        }

        FluidStack resultFluid = recipe.getResultFluid(null);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 149, 31)
                .addFluidStack(resultFluid.getFluid(), 1000);
    }

    @Override
    public void draw(AlloyRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Minecraft minecraft = Minecraft.getInstance();
        int Y = getY(recipe.getInputFluids().size()) - 1;
        int r = 0;
        for (int i = 0; i < recipe.getInputFluids().size(); i++) {
            FluidStack fluidStack = recipe.getInputFluids().get(i);

            guiGraphics.drawString(minecraft.font, Component.literal(((fluidStack.getAmount() - 300) / 100) + "-" + ((fluidStack.getAmount() + 300) / 100) + "%"), 23 + 60 * r,  (int) (Y + 5 + 19 * Math.ceil(((float)(i + 1) / 2) - 1)), -12829636, false);
            guiGraphics.blit(TEXTURE, 4 + 60 * r, (int) (Y + 19 * Math.ceil(((float)(i + 1) / 2) - 1)), 148, 30, 18, 18);

            if (r == 0) {
                r = 1;
            } else r = 0;
        }
    }

    public int getY(int count) {
        if (count < 3) {
            return 31;
        } else if (count < 5) {
            return 22;
        } else if (count < 7) {
            return 13;
        } else {
            return 3;
        }
    }
}
