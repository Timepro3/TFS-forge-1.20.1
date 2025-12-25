package net.Traise.tfs.tools;

import net.Traise.tfs.tfs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class TFSRegistries {
    public static final DeferredRegister<TFSToolMaterial> DEFERRED_TOOL_MATERIAL =
            DeferredRegister.create(new ResourceLocation(tfs.MOD_ID, "tool_material"), tfs.MOD_ID);

    public static final Supplier<IForgeRegistry<TFSToolMaterial>> TOOL_MATERIAL = DEFERRED_TOOL_MATERIAL.makeRegistry(RegistryBuilder::new);

    // Метод для регистрации, вызываемый в основном классе при старте
    public static void register(IEventBus eventBus) {
        //DEFERRED_TOOL_MATERIAL.makeRegistry(RegistryBuilder::new);
        DEFERRED_TOOL_MATERIAL.register(eventBus);
        eventBus.register(TOOL_MATERIAL);
    }

    /*@SubscribeEvent
    public void onNewRegistry(NewRegistryEvent event){
        RegistryBuilder<TFSToolMaterial> registryBuilder = new RegistryBuilder<>();
        registryBuilder.setName(new ResourceLocation(tfs.MOD_ID, "tool_material"));
        registry = event.create(registryBuilder).get();
    }*/
}
