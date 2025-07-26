package net.Traise.tfs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.FluidContainer;
import net.Traise.tfs.util.FluidRequirement;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix.GSON;

public class AlloyRecipe implements Recipe<FluidContainer> {
    private final NonNullList<FluidStack> inputFluids; // Входные предметы
    private final FluidStack output; // Выходная жидкость
    private final ResourceLocation id;

    public AlloyRecipe(NonNullList<FluidStack> inputFluids, FluidStack output, ResourceLocation id) {
        this.inputFluids = inputFluids;
        this.output = output;
        this.id = id;
    }

    public boolean matches(FluidContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        int T = 0;

        for (int i = 0; i < inputFluids.size(); i++) {
            boolean FluidSame = true;

            for (int slot = 0; slot < pContainer.getContainerSize(); slot++) {
                if (inputFluids.get(i).getFluid().isSame(pContainer.getFluidInSlot(slot).getFluid())) {
                    FluidStack fluidStack = new FluidStack(pContainer.getFluidInSlot(slot).getFluid(), (int)(((double) pContainer.getFluidInSlot(slot).getAmount() / pContainer.getSumAllFluid()) * 10000));
                    if (fluidStack.getAmount() >= inputFluids.get(i).getAmount() - 300 && fluidStack.getAmount() <= inputFluids.get(i).getAmount() + 300 ) {
                        FluidSame = false;
                        T += 1;
                    }

                }
            }

            if (FluidSame) {
                return false;
            }
        }

        return T == pContainer.getContainerSize() - 1;
    }

    @Override
    public ItemStack assemble(FluidContainer pContainer, RegistryAccess pRegistryAccess) {
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

    public NonNullList<FluidStack> getInputFluids() {
        return inputFluids;
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

    public static class Type implements RecipeType<AlloyRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "alloying";
    }

    public static class Serializer implements RecipeSerializer<AlloyRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(tfs.MOD_ID, "alloying");

        @Override
        public AlloyRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonArray ingredientsArray = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<FluidStack> inputs = NonNullList.create();

            for (int i = 0; i < ingredientsArray.size(); i++) {
                JsonObject ingredientObj = ingredientsArray.get(i).getAsJsonObject();
                FluidStack inputFluid = getFluidStack(ingredientObj);
                inputs.add(inputFluid);
            }

            JsonObject outputObj = GsonHelper.getAsJsonObject(pSerializedRecipe, "output");
            FluidStack output = getFluidStack(outputObj);

            return new AlloyRecipe(inputs, output, pRecipeId);
        }

        public static FluidStack getFluidStack(JsonObject json) {
            String fluidName = GsonHelper.getAsString(json, "fluid");
            int amount = GsonHelper.getAsInt(json, "amount", 1000); // по умолчанию 1000 мб

            Fluid fluid = getFluid(fluidName, false); // или true в зависимости от логики
            return new FluidStack(fluid, amount);
        }

        @Override
        public @Nullable AlloyRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int size = pBuffer.readInt();
            NonNullList<FluidStack> inputs = NonNullList.withSize(size, null);

            for (int i = 0; i < size; i++) {
                inputs.set(i, pBuffer.readFluidStack());
            }
            FluidStack output = pBuffer.readFluidStack();

            return new AlloyRecipe(inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, AlloyRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getInputFluids().size());
            for (FluidStack fs : pRecipe.getInputFluids()) {
                pBuffer.writeFluidStack(fs);
            }
            pBuffer.writeFluidStack(pRecipe.getResultFluid(null));
        }
    }
}