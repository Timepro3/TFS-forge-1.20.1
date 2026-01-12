package net.Traise.tfs.recipe;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.Traise.tfs.fluid.util.TFSFluidStack;
import net.Traise.tfs.tfs;
import net.Traise.tfs.tools.*;
import net.Traise.tfs.util.ModTags;
import net.Traise.tfs.util.ToolIngredient;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class ToolAssemblyRecipe extends CustomRecipe {
    private final NonNullList<Ingredient> inputItems; // Входные предметы
    private final ItemStack output;

    public ToolAssemblyRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);

        this.inputItems = inputItems;
        this.output = output;
    }

    @Override
    public boolean matches(CraftingContainer pInv, Level pLevel) {
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for(int j = 0; j < pInv.getContainerSize(); ++j) {
            ItemStack itemstack = pInv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.inputItems.size() && net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.inputItems) != null;
    }

    @Override
    public ItemStack assemble(CraftingContainer pInv, RegistryAccess pRegistryAccess) {
        NonNullList<TFSToolMaterial> mat = NonNullList.withSize(inputItems.size(), TFSToolMaterials.NONE.get());
        boolean[] N = new boolean[pInv.getContainerSize()];
        Arrays.fill(N, true);

        for(int j = 0; j < inputItems.size(); ++j) {
            boolean mar = false;
            for (int i = 0; i < pInv.getContainerSize(); ++i) {
                if (inputItems.get(j).test(pInv.getItem(i)) && N[i]) {
                    N[i] = false;
                    mar = true;
                    if (pInv.getItem(i).getItem() instanceof TFSPartItem) {
                        mat.set(j, TFSPartItem.getMaterial(pInv.getItem(i)));
                    } else if (pInv.getItem(i).is(Items.STICK)) {
                        mat.set(j, TFSToolMaterials.WOOD.get());
                    } else  mat.set(j, TFSRegistries.TOOL_MATERIAL.get().getValue(new ResourceLocation(tfs.MOD_ID, pInv.getItem(i).getItem().toString())));
                    break;
                }
            }

            if (!mar) {
                return ItemStack.EMPTY;
            }
        }
        MaterialStorageHandler.save(output, mat);
        return output;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    public static class Type implements RecipeType<ToolAssemblyRecipe> {
        public static final ToolAssemblyRecipe.Type INSTANCE = new ToolAssemblyRecipe.Type();
        public static final String ID = "crafting_special_toolassembly";
    }

    public static class Serializer implements RecipeSerializer<ToolAssemblyRecipe> {
        public static final ToolAssemblyRecipe.Serializer INSTANCE = new ToolAssemblyRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(tfs.MOD_ID, "crafting_special_toolassembly");

        @Override
        public ToolAssemblyRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            CraftingBookCategory craftingbookcategory = CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(pSerializedRecipe, "category", (String)null), CraftingBookCategory.MISC);

            Map<String, Ingredient> map = keyFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "key"));
            NonNullList<Ingredient> inputs = NonNullList.withSize(map.size() - 1, Ingredient.EMPTY);
            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, map.get("part_" + i));
            }

            return new ToolAssemblyRecipe(inputs, output, pRecipeId, craftingbookcategory);
        }

        @Override
        public @Nullable ToolAssemblyRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new ToolAssemblyRecipe(inputs, output, pRecipeId, CraftingBookCategory.EQUIPMENT);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ToolAssemblyRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }

        static Map<String, Ingredient> keyFromJson(JsonObject pKeyEntry) {
            Map<String, Ingredient> map = Maps.newHashMap();

            for(Map.Entry<String, JsonElement> entry : pKeyEntry.entrySet()) {
                map.put(entry.getKey(), Ingredient.fromJson(entry.getValue(), false));
            }

            map.put(" ", Ingredient.EMPTY);
            return map;
        }
    }
}
