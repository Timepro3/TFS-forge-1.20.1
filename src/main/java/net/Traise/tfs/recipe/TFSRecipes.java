package net.Traise.tfs.recipe;

import net.Traise.tfs.tfs;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TFSRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, tfs.MOD_ID);

    public static final RegistryObject<RecipeSerializer<FoundryRecipe>> FOUNDRY_SERIALIZER =
            SERIALIZERS.register("foundry", () -> FoundryRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<AlloyRecipe>> ALLOYING_SERIALIZER =
            SERIALIZERS.register("alloying", () -> AlloyRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<RemovingFromMoldRecipe>> REMOVING_FROM_MOLD_SERIALIZER =
            SERIALIZERS.register("removing_from_mold", () -> RemovingFromMoldRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
