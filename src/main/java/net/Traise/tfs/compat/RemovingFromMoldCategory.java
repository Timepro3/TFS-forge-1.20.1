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
import net.Traise.tfs.recipe.RemovingFromMoldRecipe;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.MoldType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class RemovingFromMoldCategory implements IRecipeCategory<RemovingFromMoldRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(tfs.MOD_ID, "removing_from_mold");
    public static final ResourceLocation TEXTURE = new ResourceLocation(tfs.MOD_ID,
            "textures/gui/form_jei_gui.png");

    public static final RecipeType<RemovingFromMoldRecipe> REMOVING_FROM_MOLD_TYPE =
            new RecipeType<>(UID, RemovingFromMoldRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public RemovingFromMoldCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 98, 54);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TFSItems.INGOT_FORM.get()));
    }

    @Override
    public RecipeType<RemovingFromMoldRecipe> getRecipeType() {
        return REMOVING_FROM_MOLD_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.tfs.removing_from_mold");
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
    public void setRecipe(IRecipeLayoutBuilder builder, RemovingFromMoldRecipe recipe, IFocusGroup focuses) {
        FluidStack fluidStack = recipe.getInputFluids().get(0).getFluidStack();
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 29).addFluidStack(fluidStack.getFluid(), fluidStack.getAmount()).addTooltipCallback(CreateRecipeCategory.addFluidTooltip(fluidStack.getAmount()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 73, 29).addItemStack(recipe.getResultItem(null));

        Item moldItem = moldType(recipe.getMoldType());


        builder.addSlot(RecipeIngredientRole.CATALYST, 41, 9).addItemStack(new ItemStack(moldItem));
    }

    @Override
    public void draw(RemovingFromMoldRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
    }

    public Item moldType(MoldType moldType) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation
                ("tfs:" + moldType.getName() + "_form"));
    }
}