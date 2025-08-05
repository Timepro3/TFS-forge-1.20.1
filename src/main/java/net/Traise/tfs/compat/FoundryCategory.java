package net.Traise.tfs.compat;

import com.mojang.blaze3d.vertex.PoseStack;
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
import net.Traise.tfs.recipe.FoundryRecipe;
import net.Traise.tfs.tfs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collections;
import java.util.List;

public class FoundryCategory implements IRecipeCategory<FoundryRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(tfs.MOD_ID, "foundry");
    public static final ResourceLocation TEXTURE = new ResourceLocation(tfs.MOD_ID,
            "textures/gui/foundry_jei_gui.png");

    public static final RecipeType<FoundryRecipe> FOUNDRY_TYPE =
            new RecipeType<>(UID, FoundryRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public FoundryCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 142, 52);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TFSBlocks.FOUNDRY.get()));
    }

    @Override
    public RecipeType<FoundryRecipe> getRecipeType() {
        return FOUNDRY_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.tfs.foundry");
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
    public void setRecipe(IRecipeLayoutBuilder builder, FoundryRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 19).addIngredients(recipe.getInputItem());

        // Проверка результата жидкости
        FluidStack resultFluid = recipe.getResultFluid(null);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 117, 19)
                .addFluidStack(resultFluid.getFluid(), resultFluid.getAmount()).addTooltipCallback(CreateRecipeCategory.addFluidTooltip(resultFluid.getAmount()));

    }

    @Override
    public void draw(FoundryRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
    }
}
