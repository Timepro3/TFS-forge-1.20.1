package net.Traise.tfs.entity;

import net.Traise.tfs.entity.projectile.*;
import net.Traise.tfs.entity.skins.alise.AliseEntity;
import net.Traise.tfs.entity.skins.tatar.TatarEntity;
import net.Traise.tfs.tfs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, tfs.MOD_ID);

    public static final RegistryObject<EntityType<ThrownSpear>> SPEAR =
            ENTITY_TYPES.register("spear", () -> EntityType.Builder.<ThrownSpear>of(ThrownSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("spear"));

    public static final RegistryObject<EntityType<ThrownWoodSpear>> WOOD_SPEAR =
            ENTITY_TYPES.register("wood_spear", () -> EntityType.Builder.<ThrownWoodSpear>of(ThrownWoodSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("wood_spear"));

    public static final RegistryObject<EntityType<ThrownRockSpear>> ROCK_SPEAR =
            ENTITY_TYPES.register("rock_spear", () -> EntityType.Builder.<ThrownRockSpear>of(ThrownRockSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("rock_spear"));

    public static final RegistryObject<EntityType<ThrownIronSpear>> IRON_SPEAR =
            ENTITY_TYPES.register("iron_spear", () -> EntityType.Builder.<ThrownIronSpear>of(ThrownIronSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("iron_spear"));

    public static final RegistryObject<EntityType<ThrownSilverSpear>> SILVER_SPEAR =
            ENTITY_TYPES.register("silver_spear", () -> EntityType.Builder.<ThrownSilverSpear>of(ThrownSilverSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("silver_spear"));

    public static final RegistryObject<EntityType<ThrownGoldSpear>> GOLD_SPEAR =
            ENTITY_TYPES.register("gold_spear", () -> EntityType.Builder.<ThrownGoldSpear>of(ThrownGoldSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("gold_spear"));

    public static final RegistryObject<EntityType<ThrownBronzeSpear>> BRONZE_SPEAR =
            ENTITY_TYPES.register("bronze_spear", () -> EntityType.Builder.<ThrownBronzeSpear>of(ThrownBronzeSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("bronze_spear"));

    public static final RegistryObject<EntityType<ThrownSteelSpear>> STEEL_SPEAR =
            ENTITY_TYPES.register("steel_spear", () -> EntityType.Builder.<ThrownSteelSpear>of(ThrownSteelSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("steel_spear"));

    public static final RegistryObject<EntityType<ThrownDiamondSpear>> DIAMOND_SPEAR =
            ENTITY_TYPES.register("diamond_spear", () -> EntityType.Builder.<ThrownDiamondSpear>of(ThrownDiamondSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("diamond_spear"));

    public static final RegistryObject<EntityType<ThrownNetheriteSpear>> NETHERITE_SPEAR =
            ENTITY_TYPES.register("netherite_spear", () -> EntityType.Builder.<ThrownNetheriteSpear>of(ThrownNetheriteSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("netherite_spear"));

    public static final RegistryObject<EntityType<ThrownSilverBullet>> SILVER_BULLET =
            ENTITY_TYPES.register("silver_bullet", () -> EntityType.Builder.<ThrownSilverBullet>of(ThrownSilverBullet::new, MobCategory.MISC)
                    .sized(0.2f, 0.2f).build("silver_bullet"));

    public static final RegistryObject<EntityType<ThrownSteelBullet>> STEEL_BULLET =
            ENTITY_TYPES.register("steel_bullet", () -> EntityType.Builder.<ThrownSteelBullet>of(ThrownSteelBullet::new, MobCategory.MISC)
                    .sized(0.2f, 0.2f).build("steel_bullet"));

    public static final RegistryObject<EntityType<AliseEntity>> ALISE =
            ENTITY_TYPES.register("alise", () -> EntityType.Builder.of(AliseEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("alise"));

    public static final RegistryObject<EntityType<TatarEntity>> TATAR =
            ENTITY_TYPES.register("tatar", () -> EntityType.Builder.of(TatarEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("tatar"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

