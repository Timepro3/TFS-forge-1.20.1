package net.Traise.tfs.datagen;

import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.tfs;
import net.Traise.tfs.util.ICustomItemModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, tfs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(RegistryObject<Item> itemObject : TFSItems.ITEMS.getEntries()) {
            Item item = itemObject.get();
            String name = ForgeRegistries.ITEMS.getKey(item).getPath();

            if(item instanceof ICustomItemModel)
                ((ICustomItemModel) item).generateCustomItemModel(this, name);
        }

        {
            simpleItem(TFSItems.OLD_TIN);
            simpleItem(TFSItems.RAW_TIN);

            simpleItem(TFSItems.BUCKET_OF_IRON);
            simpleItem(TFSItems.BUCKET_OF_COPPER);
            simpleItem(TFSItems.BUCKET_OF_ZINC);
            simpleItem(TFSItems.BUCKET_OF_TIN);
            simpleItem(TFSItems.BUCKET_OF_GOLD);
            simpleItem(TFSItems.BUCKET_OF_SILVER);
            simpleItem(TFSItems.BUCKET_OF_BRONZE);
            simpleItem(TFSItems.BUCKET_OF_BRASS);
            simpleItem(TFSItems.BUCKET_OF_STEEL);
            simpleItem(TFSItems.BUCKET_OF_CAST_IRON);
            simpleItem(TFSItems.BUCKET_OF_CARBON);
            simpleItem(TFSItems.BUCKET_OF_UNKNOWN_METAL);

            simpleItem(TFSItems.GRASS_CUT);
            simpleItem(TFSItems.GRASS_ROPE);
            simpleItem(TFSItems.ROCK);
            handheldItem(TFSItems.TWIG);
            handheldItem(TFSItems.DISTORTED_TWIG);
            simpleItem(TFSItems.PIK_ROCK_AXE);
            simpleItem(TFSItems.PIK_ROCK_HOE);
            simpleItem(TFSItems.PIK_ROCK_PICK);
            simpleItem(TFSItems.PIK_ROCK_SHOVEL);
            simpleItem(TFSItems.PIK_ROCK_SPEAR);

            simpleItem(TFSItems.TIN_INGOT);
            simpleItem(TFSItems.SILVER_INGOT);
            simpleItem(TFSItems.BRONZE_INGOT);
            simpleItem(TFSItems.UNKNOWN_METAL_INGOT);
            simpleItem(TFSItems.STEEL_INGOT);
            simpleItem(TFSItems.CAST_IRON_INGOT);

            simpleItem(TFSItems.ZINC_SHEET);
            simpleItem(TFSItems.SILVER_SHEET);
            simpleItem(TFSItems.TIN_SHEET);
            simpleItem(TFSItems.BRONZE_SHEET);
            simpleItem(TFSItems.STEEL_SHEET);
            simpleItem(TFSItems.CAST_IRON_SHEET);
            simpleItem(TFSItems.UNKNOWN_METAL_SHEET);
            simpleItem(TFSItems.NETHERITE_SHEET);
            simpleItem(TFSItems.ANDEZITE_ALLOY_SHEET);

            simpleItem(TFSItems.SILVER_NUGGET);
            simpleItem(TFSItems.TIN_NUGGET);
            simpleItem(TFSItems.BRONZE_NUGGET);
            simpleItem(TFSItems.STEEL_NUGGET);
            simpleItem(TFSItems.CAST_IRON_NUGGET);
            simpleItem(TFSItems.UNKNOWN_METAL_NUGGET);
            simpleItem(TFSItems.NETHERITE_NUGGET);
            simpleItem(TFSItems.ANDEZITE_ALLOY_NUGGET);

            simpleItem(TFSItems.EMERALD_DUST);
            simpleItem(TFSItems.DIAMOND_DUST);
            simpleItem(TFSItems.LAZULI_DUST);
            simpleItem(TFSItems.EMERALD_FRAGMENT);
            simpleItem(TFSItems.DIAMOND_FRAGMENT);
            simpleItem(TFSItems.LAZULI_FRAGMENT);
            simpleItem(TFSItems.CINNABAR_FRAGMENT);
            simpleItem(TFSItems.COAL_FRAGMENT);

            simpleItem(TFSItems.STICK_PART);
            simpleItem(TFSItems.STRING_PART);
            simpleItem(TFSItems.SWORD_PART);
            simpleItem(TFSItems.AXE_PART);
            simpleItem(TFSItems.HOE_PART);
            simpleItem(TFSItems.PICKAXE_PART);
            simpleItem(TFSItems.SHOVEL_PART);
            simpleItem(TFSItems.KNIFE_PART);
            simpleItem(TFSItems.SPEAR_PART);
            simpleItem(TFSItems.HAMMER_PART);
            simpleItem(TFSItems.SPADE_PART);
            simpleItem(TFSItems.SICKLE_PART);
            simpleItem(TFSItems.TROWEL_PART);
            simpleItem(TFSItems.GEOLOGICAL_HAMMER_PART);
            simpleItem(TFSItems.BUILDER_WAND_PART);
            simpleItem(TFSItems.PAXEL_PART);
            simpleItem(TFSItems.BOW_PART);

            simpleItem(TFSItems.RAW_INGOT_FORM);
            simpleItem(TFSItems.RAW_AXE_FORM);
            simpleItem(TFSItems.RAW_SWORD_FORM);
            simpleItem(TFSItems.RAW_HOE_FORM);
            simpleItem(TFSItems.RAW_PICKAXE_FORM);
            simpleItem(TFSItems.RAW_SHOVEL_FORM);
            simpleItem(TFSItems.RAW_KNIFE_FORM);
            simpleItem(TFSItems.RAW_SPEAR_FORM);
            simpleItem(TFSItems.RAW_HAMMER_FORM);
            simpleItem(TFSItems.RAW_SPADE_FORM);
            simpleItem(TFSItems.RAW_SICKLE_FORM);
            simpleItem(TFSItems.RAW_TROWEL_FORM);
            simpleItem(TFSItems.RAW_GEOLOGICAL_HAMMER_FORM);
            simpleItem(TFSItems.RAW_BUILDER_WAND_FORM);
            simpleItem(TFSItems.RAW_PAXEL_FORM);
            simpleItem(TFSItems.RAW_STICK_FORM);
            simpleItem(TFSItems.RAW_STRING_FORM);
            simpleItem(TFSItems.RAW_BOW_FORM);

            simpleItem(TFSItems.RICH_CUPRITE);
            simpleItem(TFSItems.CUPRITE);
            simpleItem(TFSItems.POOR_CUPRITE);
            simpleItem(TFSItems.RICH_LIMONITE);
            simpleItem(TFSItems.LIMONITE);
            simpleItem(TFSItems.POOR_LIMONITE);
            simpleItem(TFSItems.RICH_TIN);
            simpleItem(TFSItems.TIN);
            simpleItem(TFSItems.POOR_TIN);
            simpleItem(TFSItems.RICH_ZINC);
            simpleItem(TFSItems.ZINC);
            simpleItem(TFSItems.POOR_ZINC);
            simpleItem(TFSItems.RICH_SILVER);
            simpleItem(TFSItems.SILVER);
            simpleItem(TFSItems.POOR_SILVER);
            simpleItem(TFSItems.RICH_GOLD);
            simpleItem(TFSItems.GOLD);
            simpleItem(TFSItems.POOR_GOLD);
            simpleItem(TFSItems.RAW_DIAMOND);
            simpleItem(TFSItems.RAW_EMERALD);
            simpleItem(TFSItems.CINNABAR);
            simpleItem(TFSItems.RAW_LAZULI);
        }

        {simpleItem(TFSItems.BLUEBERRY);
        simpleItem(TFSItems.BLU);
            simpleItem(TFSItems.BLUE);
        withExistingParent(TFSItems.ALISE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(TFSItems.TATAR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        simpleItem(TFSItems.SLEEVE);
        simpleItem(TFSItems.CARTRIDGE_STEEL);
        simpleItem(TFSItems.CARTRIDGE_SILVER);

        simpleItem(TFSItems.REVOLVER_N);
        simpleItem(TFSItems.SLEEVE_N);
        simpleItem(TFSItems.CARTRIDGE_STEEL_N);
        simpleItem(TFSItems.CARTRIDGE_SILVER_N);}

        {handheldItem(TFSItems.TIN_SWORD);
        handheldItem(TFSItems.CUDGEL);
        handheldItem(TFSItems.POLE);
        handheldItem(TFSItems.ROCK_SHOVEL);
        handheldItem(TFSItems.ROCK_PICKAXE);
        handheldItem(TFSItems.ROCK_AXE);
        handheldItem(TFSItems.ROCK_HOE);
        handheldItem(TFSItems.ROCK_KNIFE);
        handheldItem(TFSItems.IRON_KNIFE);
        handheldItem(TFSItems.IRON_HAMMER);
        handheldItem(TFSItems.IRON_SPADE);
        handheldItem(TFSItems.IRON_SICKLE);
        handheldItem(TFSItems.GOLD_KNIFE);
        handheldItem(TFSItems.GOLD_HAMMER);
        handheldItem(TFSItems.GOLD_SPADE);
        handheldItem(TFSItems.GOLD_SICKLE);
        handheldItem(TFSItems.SILVER_SWORD);
        handheldItem(TFSItems.SILVER_SHOVEL);
        handheldItem(TFSItems.SILVER_PICKAXE);
        handheldItem(TFSItems.SILVER_AXE);
        handheldItem(TFSItems.SILVER_HOE);
        handheldItem(TFSItems.SILVER_KNIFE);
        handheldItem(TFSItems.SILVER_HAMMER);
        handheldItem(TFSItems.SILVER_SPADE);
        handheldItem(TFSItems.SILVER_SICKLE);
        handheldItem(TFSItems.BRONZE_SWORD);
        handheldItem(TFSItems.BRONZE_SHOVEL);
        handheldItem(TFSItems.BRONZE_PICKAXE);
        handheldItem(TFSItems.BRONZE_AXE);
        handheldItem(TFSItems.BRONZE_HOE);
        handheldItem(TFSItems.BRONZE_KNIFE);
        handheldItem(TFSItems.BRONZE_HAMMER);
        handheldItem(TFSItems.BRONZE_SPADE);
        handheldItem(TFSItems.BRONZE_SICKLE);
        handheldItem(TFSItems.STEEL_SWORD);
        handheldItem(TFSItems.STEEL_SHOVEL);
        handheldItem(TFSItems.STEEL_PICKAXE);
        handheldItem(TFSItems.STEEL_AXE);
        handheldItem(TFSItems.STEEL_HOE);
        handheldItem(TFSItems.STEEL_KNIFE);
        handheldItem(TFSItems.STEEL_HAMMER);
        handheldItem(TFSItems.STEEL_SPADE);
        handheldItem(TFSItems.STEEL_SICKLE);
        handheldItem(TFSItems.DIAMOND_KNIFE);
        handheldItem(TFSItems.DIAMOND_HAMMER);
        handheldItem(TFSItems.DIAMOND_SPADE);
        handheldItem(TFSItems.DIAMOND_SICKLE);
        handheldItem(TFSItems.NETHERITE_KNIFE);
        handheldItem(TFSItems.NETHERITE_HAMMER);
        handheldItem(TFSItems.NETHERITE_SPADE);
        handheldItem(TFSItems.NETHERITE_SICKLE);}

        {
            trimmedArmorItem(TFSItems.SILVER_HELMET);
            trimmedArmorItem(TFSItems.SILVER_CHESTPLATE);
            trimmedArmorItem(TFSItems.SILVER_LEGGINGS);
            trimmedArmorItem(TFSItems.SILVER_BOOTS);
            trimmedArmorItem(TFSItems.BRONZE_HELMET);
            trimmedArmorItem(TFSItems.BRONZE_CHESTPLATE);
            trimmedArmorItem(TFSItems.BRONZE_LEGGINGS);
            trimmedArmorItem(TFSItems.BRONZE_BOOTS);
            trimmedArmorItem(TFSItems.STEEL_HELMET);
            trimmedArmorItem(TFSItems.STEEL_CHESTPLATE);
            trimmedArmorItem(TFSItems.STEEL_LEGGINGS);
            trimmedArmorItem(TFSItems.STEEL_BOOTS);
        }

        toolTrimmedArmorItem(TFSItems.BOOTS);
        toolTrimmedArmorItem(TFSItems.LEGGINGS);
        toolTrimmedArmorItem(TFSItems.CHESTPLATE);
        toolTrimmedArmorItem(TFSItems.HELMET);
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = tfs.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private void toolTrimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = tfs.MOD_ID; // Change this to your mod id

        if (itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", new ResourceLocation(tfs.MOD_ID, "item/tools/" + armorType + "_r_part"))
                        .texture("layer1", new ResourceLocation(tfs.MOD_ID, "item/tools/" + armorType + "_main_part"))
                        .texture("layer2", new ResourceLocation(tfs.MOD_ID, "item/tools/" + armorType + "_l_part"))
                        .texture("layer3", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0", new ResourceLocation(tfs.MOD_ID, "item/tools/" + armorType + "_r_part"))
                        .texture("layer1", new ResourceLocation(tfs.MOD_ID, "item/tools/" + armorType + "_main_part"))
                        .texture("layer2", new ResourceLocation(tfs.MOD_ID, "item/tools/" + armorType + "_l_part"));
            });
        }
    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(tfs.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(tfs.MOD_ID, "item/" + item.getId().getPath()));
    }
}
