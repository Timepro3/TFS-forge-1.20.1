package net.Traise.tfs.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

/*public class TFSForgeRegistries extends ForgeRegistries {
    public static final IForgeRegistry<TFSToolType> TOOL_TYPES = RegistryManager.ACTIVE.getRegistry((ResourceKey<? extends Registry<TFSToolType>>) Keys.TOOL_TYPES);

    public static final class Keys {
        public static final ResourceKey<Registry<Block>> TOOL_TYPES  = key("tool_types");

        private static <T> ResourceKey<Registry<T>> key(String name)
        {
            return ResourceKey.createRegistryKey(new ResourceLocation(name));
        }
        private static void init() {}
    }
}*/
