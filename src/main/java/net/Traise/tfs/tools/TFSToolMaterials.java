package net.Traise.tfs.tools;

import net.Traise.tfs.tfs;
import net.Traise.tfs.tools.material.SilverMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TFSToolMaterials {
    public static final DeferredRegister<TFSToolMaterial> MATERIAL =
            DeferredRegister.create(TFSRegistries.DEFERRED_TOOL_MATERIAL.getRegistryName(), tfs.MOD_ID);

    public static final RegistryObject<TFSToolMaterial> NONE = MATERIAL.register("none",
            () -> new TFSToolMaterial(0xffffff,0, 1, 1, 1, 0.5f, 0));
    public static final RegistryObject<TFSToolMaterial> WOOD = MATERIAL.register("wood",
            () -> new TFSToolMaterial(0x896727,0, 120, 1f, 0.1f, 1.5f, 4));
    public static final RegistryObject<TFSToolMaterial> STONE = MATERIAL.register("stone",
            () -> new TFSToolMaterial(0x787777,1, 200, 3f, 0.5f, 0.1f, 1));
    public static final RegistryObject<TFSToolMaterial> COPPER = MATERIAL.register("copper",
            () -> new TFSToolMaterial(0xc15a36,2, 420, 3.6f, 0.8f, 1.2f, 5));
    public static final RegistryObject<TFSToolMaterial> TIN = MATERIAL.register("tin",
            () -> new TFSToolMaterial(0xffc6f5ff,2, 180, 5.5f, 0.6f, 1.5f, 5));
    public static final RegistryObject<TFSToolMaterial> ZINC = MATERIAL.register("zinc",
            () -> new TFSToolMaterial(0xb9cec0,2, 310, 3.3f, 0.7f, 1f, 2));
    public static final RegistryObject<TFSToolMaterial> GOLD = MATERIAL.register("gold",
            () -> new TFSToolMaterial(0xfad64a,2, 50, 11f, 0.1f, 2f, 7));
    public static final RegistryObject<TFSToolMaterial> BRONZE = MATERIAL.register("bronze",
            () -> new TFSToolMaterial(0xc4904c,3, 880, 4.5f, 1f, 1.5f, 3));
    public static final RegistryObject<TFSToolMaterial> BRASS = MATERIAL.register("brass",
            () -> new TFSToolMaterial(0xfbcc68,3, 770, 4f, 0.9f, 1.7f, 4));
    public static final RegistryObject<TFSToolMaterial> CAST_IRON = MATERIAL.register("cast_iron",
            () -> new TFSToolMaterial(0xb6a195,4, 910, 5.4f, 4.5f, -0.2f, 4));
    public static final RegistryObject<TFSToolMaterial> IRON = MATERIAL.register("iron",
            () -> new TFSToolMaterial(0xc7c7c7,4, 1380, 5f, 1.7f, 1.1f, 4));
    public static final RegistryObject<TFSToolMaterial> SILVER = MATERIAL.register("silver",
            () -> new SilverMaterial(0xc0b2c0,4, 950, 4f, 1f, 1.8f, 6));
    public static final RegistryObject<TFSToolMaterial> STEEL = MATERIAL.register("steel",
            () -> new TFSToolMaterial(0xaba5a1,5, 2150, 6f, 2f, 1.2f, 2));
    public static final RegistryObject<TFSToolMaterial> DIAMOND = MATERIAL.register("diamond",
            () -> new TFSToolMaterial(0x2dd8c8,6, 3500, 7f, 3f, 1.2f, 3));
    public static final RegistryObject<TFSToolMaterial> NETHERITE = MATERIAL.register("netherite",
            () -> new TFSToolMaterial(0x4d494d,7, 4120, 8f, 3.8f, 1.5f, 4));
    public static final RegistryObject<TFSToolMaterial> BONE = MATERIAL.register("bone",
            () -> new TFSToolMaterial(0xfcfbed,0, 180, 2f, 0.2f, 0.2f, 5));
    public static final RegistryObject<TFSToolMaterial> STRING = MATERIAL.register("string",
            () -> new TFSToolMaterial(0xf7f7f7,0, 150, 0f, 0f, 0.3f, 1));
    public static final RegistryObject<TFSToolMaterial> GRASS_ROPE = MATERIAL.register("grass_rope",
            () -> new TFSToolMaterial(0x57783a,0, 60, 0f, 0f, 0.1f, 2));
    public static final RegistryObject<TFSToolMaterial> LEATHER = MATERIAL.register("leather",
            () -> new TFSToolMaterial(0x72482e,0, 210, 0f, 0f, 0.4f, 4));
    public static final RegistryObject<TFSToolMaterial> BLAZE_ROD = MATERIAL.register("blaze_rod",
            () -> new TFSToolMaterial(0xfff32d,0, 2100, 1.5f, 0f, 0.9f, 5));
    public static final RegistryObject<TFSToolMaterial> SLIME_BALL = MATERIAL.register("slime_ball",
            () -> new TFSToolMaterial(0x76be6d,0, 600, 0f, 0f, 0.5f, 1));
    public static final RegistryObject<TFSToolMaterial> HONEY_BOTTLE = MATERIAL.register("honey_bottle",
            () -> new TFSToolMaterial(0xff9116,0, 350, 0f, 0f, 0.3f, 0));
    public static final RegistryObject<TFSToolMaterial> LAPIS_LAZULI = MATERIAL.register("lapis_lazuli",
            () -> new TFSToolMaterial(0x345ec3,3, 360, 0.5f, -0.5f, 1.1f, 9));
    public static final RegistryObject<TFSToolMaterial> CINNABAR = MATERIAL.register("cinnabar",
            () -> new TFSToolMaterial(0xc83b3b,2, 480, 0.5f, 0.8f, 0.5f, 3));
    public static final RegistryObject<TFSToolMaterial> EMERALD = MATERIAL.register("emerald",
            () -> new TFSToolMaterial(0x009529,4, 1520, 1.5f, 0.8f, 0.3f, 2));
    public static final RegistryObject<TFSToolMaterial> SCUTE = MATERIAL.register("scute",
            () -> new TFSToolMaterial(0x3fa442,0, 310, 0.5f, 0f, 0f, 2));
    public static final RegistryObject<TFSToolMaterial> ECHO_SHARD = MATERIAL.register("echo_shard",
            () -> new TFSToolMaterial(0x034150,0, -200, 8f, -0.5f, 0.3f, 6));
    public static final RegistryObject<TFSToolMaterial> AMETHYST_SHARD = MATERIAL.register("amethyst_shard",
            () -> new TFSToolMaterial(0xb38ef3,0, 110, 0.5f, 0.5f, 0.3f, 6));


    public static void register(IEventBus eventBus) {
        MATERIAL.register(eventBus);
    }
}
