package net.Traise.tfs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.Traise.tfs.tfs;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix.GSON;

public class FoundryRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems; // Входные предметы
    private final FluidStack output; // Выходная жидкость
    private final ResourceLocation id;

    public FoundryRecipe(NonNullList<Ingredient> inputItems, FluidStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    private static int Index = 0;

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return inputItems.get(0).test(pContainer.getItem(Index));
    }

    public static void setIndex(int index) {
        Index = index;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true; // или по вашей логике
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    public FluidStack getResultFluid(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    public NonNullList<Ingredient> getIngredient() {
        return inputItems;
    }

    public Ingredient getInputItem() {
        return inputItems.get(0);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static FluidStack getFluidStack(JsonObject json, boolean readNBT, boolean disallowsAirInRecipe)
    {
        String fluidName = GsonHelper.getAsString(json, "fluid");
        Fluid fluid = getFluid(fluidName, disallowsAirInRecipe);
        if (readNBT && json.has("nbt"))
        {
            CompoundTag nbt = getNBT(json.get("nbt"));
            CompoundTag tmp = new CompoundTag();
            if (nbt.contains("ForgeCaps"))
            {
                tmp.put("ForgeCaps", nbt.get("ForgeCaps"));
                nbt.remove("ForgeCaps");
            }

            tmp.put("tag", nbt);
            tmp.putString("id", fluidName);
            tmp.putInt("Amount", GsonHelper.getAsInt(json, "amount", 1));

            return FluidStack.loadFluidStackFromNBT(tmp);
        }

        return new FluidStack(fluid, GsonHelper.getAsInt(json, "amount", 1));
    }

    public static Fluid getFluid(String fluidName, boolean disallowsAirInRecipe)
    {
        ResourceLocation fluidKey = new ResourceLocation(fluidName);
        if (!ForgeRegistries.FLUIDS.containsKey(fluidKey))
            throw new JsonSyntaxException("Unknown fluid '" + fluidName + "'");

        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidKey);
        if (disallowsAirInRecipe && fluid == Fluids.EMPTY)
            throw new JsonSyntaxException("Invalid fluid: " + fluidName);
        return Objects.requireNonNull(fluid);
    }

    public static CompoundTag getNBT(JsonElement element)
    {
        try
        {
            if (element.isJsonObject())
                return TagParser.parseTag(GSON.toJson(element));
            else
                return TagParser.parseTag(GsonHelper.convertToString(element, "nbt"));
        }
        catch (CommandSyntaxException e)
        {
            throw new JsonSyntaxException("Invalid NBT Entry: " + e);
        }
    }

    public static FluidStack fluidStackFromJson(JsonObject pStackObject) {
        return getFluidStack(pStackObject, true, true);
    }

    public static class Type implements RecipeType<FoundryRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "foundry";
    }

    public static class Serializer implements RecipeSerializer<FoundryRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(tfs.MOD_ID, "foundry");

        @Override
        public FoundryRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            FluidStack output = fluidStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new FoundryRecipe(inputs, output, pRecipeId);
        }

        @Override
        public @Nullable FoundryRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            FluidStack output = pBuffer.readFluidStack();
            return new FoundryRecipe(inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FoundryRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}