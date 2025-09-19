package net.Traise.tfs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.Traise.tfs.fluid.util.TFSFluidStack;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.BooleanContainer;
import net.Traise.tfs.util.FluidContainer;
import net.Traise.tfs.util.MoldType;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CuttingRecipe implements Recipe<BooleanContainer> {
    private static int MAX_WIDTH = 5; // ширина текущего материала
    private static int MAX_HEIGHT = 5; // высота текущего материала

    public static void setCraftingSize(int width, int height) { // изменения размера
        if (MAX_WIDTH < width) MAX_WIDTH = width;
        if (MAX_HEIGHT < height) MAX_HEIGHT = height;
    }

    private final int width;
    private final int height;

    private final NonNullList<Boolean> map; // материал // Измени на обычный лист
    private final ItemStack input; // Входные предметы
    private final ItemStack output; // Выходные предметы
    private final ResourceLocation id;

    public CuttingRecipe(ItemStack pInput, ItemStack output, NonNullList<Boolean> pMap, int pWidth, int pHeight, ResourceLocation id) {
        this.map = pMap;
        this.input = pInput;
        this.output = output;
        this.width = pWidth;
        this.height = pHeight;
        this.id = id;
    }

    public boolean matches(BooleanContainer pInv, Level pLevel) {
        for(int i = 0; i <= 5 - this.width; ++i) {
            for(int j = 0; j <= 5 - this.height; ++j) {
                if (this.matches(pInv, i, j, true)) {
                    return true;
                }

                if (this.matches(pInv, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean matches(BooleanContainer pCraftingInventory, int pWidth, int pHeight, boolean pMirrored) {
        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                int k = i - pWidth;
                int l = j - pHeight;
                Boolean ingredient = false;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    if (pMirrored) {
                        ingredient = this.map.get(this.width - k - 1 + l * this.width);
                    } else {
                        ingredient = this.map.get(k + l * this.width);
                    }
                }

                if (!ingredient.equals(pCraftingInventory.getMapSlot(i + j * 5))) {
                    return false;
                }
            }
        }

        return true;
    }

    /*public boolean matches(BooleanContainer pContainer, Level pLevel) {
        if (matches(pContainer, pLevel, false)) {
            return true;
        }

        if (matches(pContainer, pLevel, true)) {
            return true;
        }

        return false;
    }

    private boolean matches(BooleanContainer pContainer, Level pLevel, boolean pMirrored) {
        if (!pMirrored) {
            for (int i = 0; i < 25; i++) {
                if (map.get(i) != pContainer.getMap(i)) {
                    return false;
                }
            }
        } else {
            for (int Y = 0; Y < MAX_HEIGHT; Y++) {
                for (int X = 0; X < MAX_WIDTH; X++) {
                    if (map.get(Y + (X * 5)) != pContainer.getMap(4 - Y + (X * 5))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }*/

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public ItemStack assemble(BooleanContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true; // или по вашей логике
    }

    public ItemStack getInput() {
        return input;
    }

    public NonNullList<Boolean> getMap() {
        return map;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CuttingRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return CuttingRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<CuttingRecipe> {
        public static final CuttingRecipe.Type INSTANCE = new CuttingRecipe.Type();
        public static final String ID = "cutting";
    }

    public static class Serializer implements RecipeSerializer<CuttingRecipe> {
        public static final CuttingRecipe.Serializer INSTANCE = new CuttingRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(tfs.MOD_ID, "cutting");

        @Override
        public CuttingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack inputs = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "input"));

            JsonArray array = pSerializedRecipe.getAsJsonArray("pattern");

            int wight = GsonHelper.convertToString(array.get(0), "pattern[" + 0 + "]").length();
            int height = array.size();

            NonNullList<Boolean> map = NonNullList.withSize(wight * height, true);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < wight; x++) {
                    String row = GsonHelper.convertToString(array.get(y), "pattern[" + y + "]");
                    map.set(x + (y * wight), row.charAt(x) != ' ');
                }
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            return new CuttingRecipe(inputs, output, map, wight, height, pRecipeId);
        }

        @Override
        public @Nullable CuttingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            ItemStack input = pBuffer.readItem();
            NonNullList<Boolean> map = NonNullList.withSize(25, true);
            ItemStack output = pBuffer.readItem();

            return new CuttingRecipe(input, output, map, 1, 1, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, CuttingRecipe pRecipe) {
            pBuffer.writeItemStack(pRecipe.getInput(), false);
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
