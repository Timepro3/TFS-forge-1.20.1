package net.Traise.tfs.potion;

import net.Traise.tfs.tfs;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffect {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, tfs.MOD_ID);
    public static final RegistryObject<MobEffect> VERTIGO = REGISTRY.register("vertigo", () -> new VertigoEffect());
    public static final RegistryObject<MobEffect> BLACK_PLAGUE = REGISTRY.register("black_plague", () -> new BlackPlagueEffect());
    public static final RegistryObject<MobEffect> PINK_PLAGUE = REGISTRY.register("pink_plague", () -> new PinkPlagueEffect());


    public static void register(IEventBus modEventBus) {
        REGISTRY.register(modEventBus);
    }
}