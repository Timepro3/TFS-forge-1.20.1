package net.Traise.tfs.fluid;

import net.Traise.tfs.tfs;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class TFSFluids {
    // Создаем DeferredRegister для жидкостей
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, tfs.MOD_ID);



    // Метод для регистрации
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
