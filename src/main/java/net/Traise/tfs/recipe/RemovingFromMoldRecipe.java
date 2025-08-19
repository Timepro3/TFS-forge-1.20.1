package net.Traise.tfs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.Traise.tfs.fluid.util.TFSFluidStack;
import net.Traise.tfs.util.MoldType;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.FluidContainer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RemovingFromMoldRecipe implements Recipe<FluidContainer> {
    private final MoldType moldType;
    private final NonNullList<TFSFluidStack> inputFluids; // Входные предметы
    private final ItemStack output; // Выходная жидкость
    private final ResourceLocation id;

    public RemovingFromMoldRecipe(MoldType moldType, NonNullList<TFSFluidStack> inputFluids, ItemStack output, ResourceLocation id) {
        this.moldType = moldType;
        this.inputFluids = inputFluids;
        this.output = output;
        this.id = id;
    }

    public boolean matches(FluidContainer pContainer, Level pLevel) {
        return pContainer.getMoldType() == moldType && pContainer.getFluidInSlot(0).isFluidEqual(inputFluids.get(0));
    }

    @Override
    public ItemStack assemble(FluidContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true; // или по вашей логике
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    public NonNullList<TFSFluidStack> getInputFluids() {
        return inputFluids;
    }

    public MoldType getMoldType() {
        return moldType;
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

    public static class Type implements RecipeType<RemovingFromMoldRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "removing_from_mold";
    }

    public static class Serializer implements RecipeSerializer<RemovingFromMoldRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(tfs.MOD_ID, "removing_from_mold");

        @Override
        public RemovingFromMoldRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            MoldType pMoldType = MoldType.getMoldTypeInName(GsonHelper.getAsString(pSerializedRecipe, "mold_type"));

            JsonArray ingredientsArray = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<TFSFluidStack> inputs = NonNullList.create();

            for (int i = 0; i < ingredientsArray.size(); i++) {
                JsonObject ingredientObj = ingredientsArray.get(i).getAsJsonObject();
                TFSFluidStack inputFluid = getFluidStack(ingredientObj);
                inputs.add(inputFluid);
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            return new RemovingFromMoldRecipe(pMoldType, inputs, output, pRecipeId);
        }

        public static TFSFluidStack getFluidStack(JsonObject json) {
            String fluidName = GsonHelper.getAsString(json, "fluid");
            int amount = GsonHelper.getAsInt(json, "amount", 1000); // по умолчанию 1000 мб

            Fluid fluid = getFluid(fluidName, false); // или true в зависимости от логики
            return new TFSFluidStack(fluid, amount);
        }

        @Override
        public @Nullable RemovingFromMoldRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int size = pBuffer.readInt();
            MoldType pMoldType = pBuffer.readEnum(MoldType.class);

            NonNullList<TFSFluidStack> inputs = NonNullList.withSize(size, null);

            for (int i = 0; i < size; i++) {
                inputs.set(i, TFSFluidStack.readFromPacket(pBuffer));
            }
            ItemStack output = pBuffer.readItem();


            return new RemovingFromMoldRecipe(pMoldType, inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, RemovingFromMoldRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getInputFluids().size());
            for (TFSFluidStack fs : pRecipe.getInputFluids()) {
                pBuffer.writeFluidStack(fs.getFluidStack());
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}