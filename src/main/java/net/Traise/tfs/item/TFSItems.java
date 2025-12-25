package net.Traise.tfs.item;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.entity.ModEntities;
import net.Traise.tfs.fluid.TFSFluids;
import net.Traise.tfs.item.custom.*;
import net.Traise.tfs.tfs;
import net.Traise.tfs.tools.TFSPartItem;
import net.Traise.tfs.util.MoldType;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TFSItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, tfs.MOD_ID);

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OLD_TIN = ITEMS.register( "old_tin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BUCKET_OF_IRON = ITEMS.register("bucket_of_iron",
            () -> new BucketItem(TFSFluids.IRON,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_COPPER = ITEMS.register("bucket_of_copper",
            () -> new BucketItem(TFSFluids.COPPER,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_ZINC = ITEMS.register("bucket_of_zinc",
            () -> new BucketItem(TFSFluids.ZINC,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_TIN = ITEMS.register("bucket_of_tin",
            () -> new BucketItem(TFSFluids.TIN,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_GOLD = ITEMS.register("bucket_of_gold",
            () -> new BucketItem(TFSFluids.GOLD,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_SILVER = ITEMS.register("bucket_of_silver",
            () -> new BucketItem(TFSFluids.SILVER,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_BRONZE = ITEMS.register("bucket_of_bronze",
            () -> new BucketItem(TFSFluids.BRONZE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_BRASS = ITEMS.register("bucket_of_brass",
            () -> new BucketItem(TFSFluids.BRASS,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_STEEL = ITEMS.register("bucket_of_steel",
            () -> new BucketItem(TFSFluids.STEEL,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_CAST_IRON = ITEMS.register("bucket_of_cast_iron",
            () -> new BucketItem(TFSFluids.CAST_IRON,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_CARBON = ITEMS.register("bucket_of_carbon",
            () -> new BucketItem(TFSFluids.FLOWING_CARBON,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_OF_UNKNOWN_METAL = ITEMS.register("bucket_of_unknown_metal",
            () -> new BucketItem(TFSFluids.UNKNOWN_METAL,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> ROCK = ITEMS.register( "rock",
            () -> new TFSItemNameBlockItem(1, TFSBlocks.ROCK_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> TWIG = ITEMS.register( "twig",
            () -> new TFSItemNameBlockItem(1, TFSBlocks.TWIG_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> PIK_ROCK_AXE = ITEMS.register( "pik_rock_axe",
            () -> new TFSItemTexts(1, new Item.Properties()));
    public static final RegistryObject<Item> PIK_ROCK_HOE = ITEMS.register( "pik_rock_hoe",
            () -> new TFSItemTexts(2, new Item.Properties()));
    public static final RegistryObject<Item> PIK_ROCK_PICK = ITEMS.register( "pik_rock_pick",
            () -> new TFSItemTexts(3, new Item.Properties()));
    public static final RegistryObject<Item> PIK_ROCK_SHOVEL = ITEMS.register( "pik_rock_shovel",
            () -> new TFSItemTexts(4, new Item.Properties()));
    public static final RegistryObject<Item> PIK_ROCK_SPEAR = ITEMS.register( "pik_rock_spear",
            () -> new TFSKnifeItem(TFSToolTiers.ROCK, new Item.Properties().durability(15)));
    public static final RegistryObject<Item> GRASS_CUT = ITEMS.register( "grass_cut",
            () -> new TFSItemTexts(6, new Item.Properties()));
    public static final RegistryObject<Item> GRASS_ROPE = ITEMS.register( "grass_rope",
            () -> new TFSItemTexts(7, new Item.Properties()));
    public static final RegistryObject<Item> DISTORTED_TWIG = ITEMS.register( "distorted_twig",
            () -> new TFSItemTexts(34, new Item.Properties()));

    public static final RegistryObject<Item> RAW_DIAMOND = ITEMS.register( "raw_diamond",
            () -> new TFSItemTexts(14, new Item.Properties()));
    public static final RegistryObject<Item> RAW_EMERALD = ITEMS.register( "raw_emerald",
            () -> new TFSItemTexts(15, new Item.Properties()));
    public static final RegistryObject<Item> CINNABAR = ITEMS.register( "cinnabar",
            () -> new TFSItemTexts(16, new Item.Properties()));
    public static final RegistryObject<Item> RAW_LAZULI= ITEMS.register( "raw_lazuli",
            () -> new TFSItemTexts(17, new Item.Properties()));

    public static final RegistryObject<Item> RICH_CUPRITE = ITEMS.register( "rich_cuprite",
            () -> new TFSItemTexts(8, new Item.Properties()));
    public static final RegistryObject<Item> CUPRITE = ITEMS.register( "cuprite",
            () -> new TFSItemTexts(9, new Item.Properties()));
    public static final RegistryObject<Item> POOR_CUPRITE = ITEMS.register( "poor_cuprite",
            () -> new TFSItemNameBlockItem(10, TFSBlocks.POOR_CUPRITE_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> RICH_LIMONITE = ITEMS.register( "rich_limonite",
            () -> new TFSItemTexts(11, new Item.Properties()));
    public static final RegistryObject<Item> LIMONITE = ITEMS.register( "limonite",
            () -> new TFSItemTexts(12, new Item.Properties()));
    public static final RegistryObject<Item> POOR_LIMONITE = ITEMS.register( "poor_limonite",
            () -> new TFSItemNameBlockItem(13, TFSBlocks.POOR_LIMONITE_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> RICH_TIN = ITEMS.register( "rich_tin",
            () -> new TFSItemTexts(18, new Item.Properties()));
    public static final RegistryObject<Item> TIN = ITEMS.register( "tin",
            () -> new TFSItemTexts(19, new Item.Properties()));
    public static final RegistryObject<Item> POOR_TIN = ITEMS.register( "poor_tin",
            () -> new TFSItemNameBlockItem(20, TFSBlocks.POOR_TIN_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> RICH_ZINC = ITEMS.register( "rich_zinc",
            () -> new TFSItemTexts(21, new Item.Properties()));
    public static final RegistryObject<Item> ZINC = ITEMS.register( "zinc",
            () -> new TFSItemTexts(22, new Item.Properties()));
    public static final RegistryObject<Item> POOR_ZINC = ITEMS.register( "poor_zinc",
            () -> new TFSItemNameBlockItem(23, TFSBlocks.POOR_ZINC_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> RICH_SILVER = ITEMS.register( "rich_silver",
            () -> new TFSItemTexts(24, new Item.Properties()));
    public static final RegistryObject<Item> SILVER = ITEMS.register( "silver",
            () -> new TFSItemTexts(25, new Item.Properties()));
    public static final RegistryObject<Item> POOR_SILVER = ITEMS.register( "poor_silver",
            () -> new TFSItemNameBlockItem(26, TFSBlocks.POOR_SILVER_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> RICH_GOLD = ITEMS.register( "rich_gold",
            () -> new TFSItemTexts(27, new Item.Properties()));
    public static final RegistryObject<Item> GOLD = ITEMS.register( "gold",
            () -> new TFSItemTexts(28, new Item.Properties()));
    public static final RegistryObject<Item> POOR_GOLD = ITEMS.register( "poor_gold",
            () -> new TFSItemNameBlockItem(29, TFSBlocks.POOR_GOLD_OBJECT.get(), new Item.Properties()));

    public static final RegistryObject<Item> RAW_INGOT_FORM = ITEMS.register( "raw_ingot_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_AXE_FORM = ITEMS.register("raw_axe_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SWORD_FORM = ITEMS.register("raw_sword_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_HOE_FORM = ITEMS.register("raw_hoe_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PICKAXE_FORM = ITEMS.register("raw_pickaxe_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SHOVEL_FORM = ITEMS.register("raw_shovel_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_KNIFE_FORM = ITEMS.register("raw_knife_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SPEAR_FORM = ITEMS.register("raw_spear_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_HAMMER_FORM = ITEMS.register("raw_hammer_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SPADE_FORM = ITEMS.register("raw_spade_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SICKLE_FORM = ITEMS.register("raw_sickle_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TROWEL_FORM = ITEMS.register("raw_trowel_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GEOLOGICAL_HAMMER_FORM = ITEMS.register("raw_geological_hammer_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BUILDER_WAND_FORM = ITEMS.register("raw_builder_wand_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PAXEL_FORM = ITEMS.register("raw_paxel_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_STICK_FORM = ITEMS.register("raw_stick_form",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_STRING_FORM = ITEMS.register("raw_string_form",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_FORM = ITEMS.register( "ingot_form",
            () -> new TFSFormItem(MoldType.INGOT, new Item.Properties()));
    public static final RegistryObject<Item> AXE_FORM = ITEMS.register( "axe_form",
            () -> new TFSFormItem(MoldType.AXE, new Item.Properties()));
    public static final RegistryObject<Item> SWORD_FORM = ITEMS.register("sword_form",
            () -> new TFSFormItem(MoldType.SWORD, new Item.Properties()));
    public static final RegistryObject<Item> HOE_FORM = ITEMS.register("hoe_form",
            () -> new TFSFormItem(MoldType.HOE, new Item.Properties()));
    public static final RegistryObject<Item> PICKAXE_FORM = ITEMS.register("pickaxe_form",
            () -> new TFSFormItem(MoldType.PICKAXE, new Item.Properties()));
    public static final RegistryObject<Item> SHOVEL_FORM = ITEMS.register("shovel_form",
            () -> new TFSFormItem(MoldType.SHOVEL, new Item.Properties()));
    public static final RegistryObject<Item> KNIFE_FORM = ITEMS.register("knife_form",
            () -> new TFSFormItem(MoldType.KNIFE, new Item.Properties()));
    public static final RegistryObject<Item> SPEAR_FORM = ITEMS.register("spear_form",
            () -> new TFSFormItem(MoldType.SPEAR, new Item.Properties()));
    public static final RegistryObject<Item> HAMMER_FORM = ITEMS.register("hammer_form",
            () -> new TFSFormItem(MoldType.HAMMER, new Item.Properties()));
    public static final RegistryObject<Item> SPADE_FORM = ITEMS.register("spade_form",
            () -> new TFSFormItem(MoldType.SPADE, new Item.Properties()));
    public static final RegistryObject<Item> SICKLE_FORM = ITEMS.register("sickle_form",
            () -> new TFSFormItem(MoldType.SICKLE, new Item.Properties()));
    public static final RegistryObject<Item> TROWEL_FORM = ITEMS.register("trowel_form",
            () -> new TFSFormItem(MoldType.TROWEL, new Item.Properties()));
    public static final RegistryObject<Item> GEOLOGICAL_HAMMER_FORM = ITEMS.register("geological_hammer_form",
            () -> new TFSFormItem(MoldType.GEOLOGICAL_HAMMER, new Item.Properties()));
    public static final RegistryObject<Item> BUILDER_WAND_FORM = ITEMS.register("builder_wand_form",
            () -> new TFSFormItem(MoldType.BUILDER_WAND, new Item.Properties()));
    public static final RegistryObject<Item> PAXEL_FORM = ITEMS.register("paxel_form",
            () -> new TFSFormItem(MoldType.PAXEL, new Item.Properties()));
    public static final RegistryObject<Item> STICK_FORM = ITEMS.register("stick_form",
            () -> new TFSFormItem(MoldType.STICK, new Item.Properties()));
    public static final RegistryObject<Item> STRING_FORM = ITEMS.register("string_form",
            () -> new TFSFormItem(MoldType.STRING, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT= ITEMS.register( "steel_ingot",
            () -> new TFSItemTexts(36, new Item.Properties()));
    public static final RegistryObject<Item> CAST_IRON_INGOT= ITEMS.register( "cast_iron_ingot",
            () -> new TFSItemTexts(37, new Item.Properties()));
    public static final RegistryObject<Item> UNKNOWN_METAL_INGOT = ITEMS.register( "unknown_metal_ingot",
            () -> new TFSItemTexts(38, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register( "bronze_ingot",
            () -> new TFSItemTexts(39, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register( "silver_ingot",
            () -> new TFSItemTexts(40, new Item.Properties()));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register( "tin_ingot",
            () -> new TFSItemTexts(41, new Item.Properties()));

    public static final RegistryObject<Item> ZINC_SHEET = ITEMS.register( "zinc_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SHEET = ITEMS.register( "silver_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> TIN_SHEET = ITEMS.register( "tin_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SHEET = ITEMS.register( "bronze_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHEET = ITEMS.register( "steel_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> CAST_IRON_SHEET = ITEMS.register( "cast_iron_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> UNKNOWN_METAL_SHEET = ITEMS.register( "unknown_metal_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SHEET = ITEMS.register( "netherite_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> ANDEZITE_ALLOY_SHEET = ITEMS.register( "andesite_alloy_sheet",
            () -> new TFSItemTexts(-1, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register( "silver_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register( "tin_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register( "bronze_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register( "steel_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> CAST_IRON_NUGGET = ITEMS.register( "cast_iron_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> UNKNOWN_METAL_NUGGET = ITEMS.register( "unknown_metal_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_NUGGET = ITEMS.register( "netherite_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> ANDEZITE_ALLOY_NUGGET = ITEMS.register( "andesite_alloy_nugget",
            () -> new TFSItemTexts(-1, new Item.Properties()));

    public static final RegistryObject<Item> EMERALD_DUST = ITEMS.register( "emerald_dust",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_DUST = ITEMS.register( "diamond_dust",
            () -> new TFSItemTexts(-1, new Item.Properties()));
    public static final RegistryObject<Item> LAZULI_DUST = ITEMS.register( "lazuli_dust",
            () -> new TFSItemTexts(-1, new Item.Properties()));

    public static final RegistryObject<Item> EMERALD_FRAGMENT = ITEMS.register( "emerald_fragment",
            () -> new TFSItemNameBlockItem(-1, TFSBlocks.EMERALD_FRAGMENT_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_FRAGMENT = ITEMS.register( "diamond_fragment",
            () -> new TFSItemNameBlockItem(-1, TFSBlocks.DIAMOND_FRAGMENT_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> LAZULI_FRAGMENT = ITEMS.register( "lazuli_fragment",
            () -> new TFSItemNameBlockItem(-1, TFSBlocks.LAZULI_FRAGMENT_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> CINNABAR_FRAGMENT = ITEMS.register( "cinnabar_fragment",
            () -> new TFSItemNameBlockItem(-1, TFSBlocks.CINNABAR_FRAGMENT_OBJECT.get(), new Item.Properties()));
    public static final RegistryObject<Item> COAL_FRAGMENT = ITEMS.register( "coal_fragment",
            () -> new TFSItemNameBlockItem(-1, TFSBlocks.COAL_FRAGMENT_OBJECT.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register( "blueberry",
            () -> new Item(new Item.Properties().food(TFSFood.BLUEBERRY)));
    public static final RegistryObject<Item> BLU = ITEMS.register( "blu",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE = ITEMS.register( "blue",
            () -> new Item( new Item.Properties()));
    public static final RegistryObject<Item> ALISE_SPAWN_EGG = ITEMS.register("alise_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ALISE, -1, -1, new Item.Properties()));
    public static final RegistryObject<Item> TATAR_SPAWN_EGG = ITEMS.register("tatar_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TATAR, -1, -1, new Item.Properties()));

    public static final RegistryObject<Item> SWORD = ITEMS.register( "sword",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.SWORD, new Item.Properties()));
    public static final RegistryObject<Item> AXE = ITEMS.register( "axe",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.AXE, new Item.Properties()));
    public static final RegistryObject<Item> HOE = ITEMS.register( "hoe",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.HOE, new Item.Properties()));
    public static final RegistryObject<Item> PICKAXE = ITEMS.register( "pickaxe",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.PICKAXE, new Item.Properties()));
    public static final RegistryObject<Item> SHOVEL = ITEMS.register( "shovel",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.SHOVEL, new Item.Properties()));
    public static final RegistryObject<Item> KNIFE = ITEMS.register( "knife",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.KNIFE, new Item.Properties()));
    public static final RegistryObject<Item> SPEAR = ITEMS.register( "spear",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.SPEAR, new Item.Properties()));
    public static final RegistryObject<Item> HAMMER = ITEMS.register( "hammer",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.HAMMER, new Item.Properties()));
    public static final RegistryObject<Item> SPADE = ITEMS.register( "spade",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.SPADE, new Item.Properties()));
    public static final RegistryObject<Item> SICKLE = ITEMS.register( "sickle",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.SICKLE, new Item.Properties()));
    public static final RegistryObject<Item> TROWEL = ITEMS.register( "trowel",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.TROWEL, new Item.Properties()));
    public static final RegistryObject<Item> PAXEL = ITEMS.register( "paxel",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.PAXEL, new Item.Properties()));
    public static final RegistryObject<Item> GEOLOGICAL_HAMMER = ITEMS.register( "geological_hammer",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.GEOLOGICAL_HAMMER, new Item.Properties()));
    public static final RegistryObject<Item> BUILDER_WAND = ITEMS.register( "builder_wand",
            () -> new TFSBaseItem(TFSBaseItem.TFSToolTypes.BUILDER_WAND, new Item.Properties()));

    public static final RegistryObject<Item> STICK_PART = ITEMS.register( "stick_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> STRING_PART = ITEMS.register( "string_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> SWORD_PART = ITEMS.register("sword_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> AXE_PART = ITEMS.register("axe_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> HOE_PART = ITEMS.register("hoe_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> PICKAXE_PART = ITEMS.register("pickaxe_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> SHOVEL_PART = ITEMS.register("shovel_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> KNIFE_PART = ITEMS.register("knife_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> SPEAR_PART = ITEMS.register("spear_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> HAMMER_PART = ITEMS.register("hammer_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> SPADE_PART = ITEMS.register("spade_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> SICKLE_PART = ITEMS.register("sickle_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> TROWEL_PART = ITEMS.register("trowel_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> GEOLOGICAL_HAMMER_PART = ITEMS.register("geological_hammer_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> BUILDER_WAND_PART = ITEMS.register("builder_wand_part",
            () -> new TFSPartItem(new Item.Properties()));
    public static final RegistryObject<Item> PAXEL_PART = ITEMS.register("paxel_part",
            () -> new TFSPartItem(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet",
            () -> new ArmorItem(TFSArmorMaterials.SILVER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate",
            () -> new ArmorItem(TFSArmorMaterials.SILVER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_LEGGINGS = ITEMS.register("silver_leggings",
            () -> new ArmorItem(TFSArmorMaterials.SILVER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots",
            () -> new ArmorItem(TFSArmorMaterials.SILVER, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HELMET = ITEMS.register("bronze_helmet",
            () -> new ArmorItem(TFSArmorMaterials.BRONZE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
            () -> new ArmorItem(TFSArmorMaterials.BRONZE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
            () -> new ArmorItem(TFSArmorMaterials.BRONZE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BOOTS = ITEMS.register("bronze_boots",
            () -> new ArmorItem(TFSArmorMaterials.BRONZE, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(TFSArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(TFSArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(TFSArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(TFSArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> TIN_SWORD = ITEMS.register("tin_sword",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> CUDGEL = ITEMS.register( "cudgel",
            () -> new SwordItem(Tiers.WOOD, 5,-3, new Item.Properties()));
    public static final RegistryObject<Item> POLE = ITEMS.register( "pole",
            () -> new SwordItem(Tiers.WOOD, 0,-2.5f, new Item.Properties().durability(45)));
    public static final RegistryObject<Item> WOODEN_SPEAR = ITEMS.register( "wooden_spear",
            () -> new TFSSpearItem(Tiers.WOOD, new Item.Properties(), "wooden"));
    public static final RegistryObject<Item> ROCK_AXE = ITEMS.register( "rock_axe",
            () -> new AxeItem(TFSToolTiers.ROCK, 7, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> ROCK_HOE = ITEMS.register( "rock_hoe",
            () -> new HoeItem(TFSToolTiers.ROCK,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ROCK_PICKAXE = ITEMS.register( "rock_pickaxe",
            () -> new PickaxeItem(TFSToolTiers.ROCK,3, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> ROCK_SHOVEL = ITEMS.register( "rock_shovel",
            () -> new ShovelItem(TFSToolTiers.ROCK,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> ROCK_KNIFE = ITEMS.register( "rock_knife",
            () -> new TFSKnifeItem(TFSToolTiers.ROCK, new Item.Properties()));
    public static final RegistryObject<Item> ROCK_SPEAR = ITEMS.register("rock_spear",
            () -> new TFSSpearItem(TFSToolTiers.ROCK, new Item.Properties(), "rock"));
    public static final RegistryObject<Item> IRON_SPEAR = ITEMS.register("iron_spear",
            () -> new TFSSpearItem(Tiers.IRON, new Item.Properties(), "iron"));
    public static final RegistryObject<Item> IRON_KNIFE = ITEMS.register("iron_knife",
            () -> new TFSKnifeItem(Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new TFSHammerItem(Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SPADE = ITEMS.register("iron_spade",
            () -> new TFSSpadeItem(Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SICKLE = ITEMS.register("iron_sickle",
            () -> new TFSSickleItem(Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_SPEAR = ITEMS.register("gold_spear",
            () -> new TFSSpearItem(Tiers.GOLD, new Item.Properties(), "gold"));
    public static final RegistryObject<Item> GOLD_KNIFE = ITEMS.register("gold_knife",
            () -> new TFSKnifeItem(Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_HAMMER = ITEMS.register("gold_hammer",
            () -> new TFSHammerItem(Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_SPADE = ITEMS.register("gold_spade",
            () -> new TFSSpadeItem(Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_SICKLE = ITEMS.register("gold_sickle",
            () -> new TFSSickleItem(Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register( "silver_sword",
            () -> new SwordItem(TFSToolTiers.SILVER,3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register( "silver_shovel",
            () -> new ShovelItem(TFSToolTiers.SILVER,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register( "silver_pickaxe",
            () -> new PickaxeItem(TFSToolTiers.SILVER,3, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register( "silver_axe",
            () -> new AxeItem(TFSToolTiers.SILVER,6.5f, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register( "silver_hoe",
            () -> new HoeItem(TFSToolTiers.SILVER,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SPEAR = ITEMS.register("silver_spear",
            () -> new TFSSpearItem(TFSToolTiers.SILVER, new Item.Properties(), "silver"));
    public static final RegistryObject<Item> SILVER_KNIFE = ITEMS.register("silver_knife",
            () -> new TFSKnifeItem(TFSToolTiers.SILVER, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HAMMER = ITEMS.register("silver_hammer",
            () -> new TFSHammerItem(TFSToolTiers.SILVER, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SPADE = ITEMS.register("silver_spade",
            () -> new TFSSpadeItem(TFSToolTiers.SILVER, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SICKLE = ITEMS.register("silver_sickle",
            () -> new TFSSickleItem(TFSToolTiers.SILVER, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register( "bronze_sword",
            () -> new SwordItem(TFSToolTiers.BRONZE,3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register( "bronze_shovel",
            () -> new ShovelItem(TFSToolTiers.BRONZE,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PICKAXE = ITEMS.register( "bronze_pickaxe",
            () -> new PickaxeItem(TFSToolTiers.BRONZE,3, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register( "bronze_axe",
            () -> new AxeItem(TFSToolTiers.BRONZE,7, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HOE = ITEMS.register( "bronze_hoe",
            () -> new HoeItem(TFSToolTiers.BRONZE,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SPEAR = ITEMS.register("bronze_spear",
            () -> new TFSSpearItem(TFSToolTiers.BRONZE, new Item.Properties(), "bronze"));
    public static final RegistryObject<Item> BRONZE_KNIFE = ITEMS.register("bronze_knife",
            () -> new TFSKnifeItem(TFSToolTiers.BRONZE, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HAMMER = ITEMS.register("bronze_hammer",
            () -> new TFSHammerItem(TFSToolTiers.BRONZE, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SPADE = ITEMS.register("bronze_spade",
            () -> new TFSSpadeItem(TFSToolTiers.BRONZE, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SICKLE = ITEMS.register("bronze_sickle",
            () -> new TFSSickleItem(TFSToolTiers.BRONZE, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register( "steel_sword",
            () -> new SwordItem(TFSToolTiers.STEEL,3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register( "steel_shovel",
            () -> new ShovelItem(TFSToolTiers.STEEL,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register( "steel_pickaxe",
            () -> new PickaxeItem(TFSToolTiers.STEEL,3, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register( "steel_axe",
            () -> new AxeItem(TFSToolTiers.STEEL,5.5f, -3.1f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register( "steel_hoe",
            () -> new HoeItem(TFSToolTiers.STEEL,3, -3f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SPEAR = ITEMS.register("steel_spear",
            () -> new TFSSpearItem(TFSToolTiers.STEEL, new Item.Properties(), "steel"));
    public static final RegistryObject<Item> STEEL_KNIFE = ITEMS.register("steel_knife",
            () -> new TFSKnifeItem(TFSToolTiers.STEEL, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            () -> new TFSHammerItem(TFSToolTiers.STEEL, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SPADE = ITEMS.register("steel_spade",
            () -> new TFSSpadeItem(TFSToolTiers.STEEL, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SICKLE = ITEMS.register("steel_sickle",
            () -> new TFSSickleItem(TFSToolTiers.STEEL, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SPEAR = ITEMS.register("diamond_spear",
            () -> new TFSSpearItem(Tiers.DIAMOND, new Item.Properties(), "diamond"));
    public static final RegistryObject<Item> DIAMOND_KNIFE = ITEMS.register("diamond_knife",
            () -> new TFSKnifeItem(Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            () -> new TFSHammerItem(Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SPADE = ITEMS.register("diamond_spade",
            () -> new TFSSpadeItem(Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SICKLE = ITEMS.register("diamond_sickle",
            () -> new TFSSickleItem(Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SPEAR = ITEMS.register("netherite_spear",
            () -> new TFSSpearItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), "netherite"));
    public static final RegistryObject<Item> NETHERITE_KNIFE = ITEMS.register("netherite_knife",
            () -> new TFSKnifeItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new TFSHammerItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPADE = ITEMS.register("netherite_spade",
            () -> new TFSSpadeItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SICKLE = ITEMS.register("netherite_sickle",
            () -> new TFSSickleItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> REVOLVER = ITEMS.register("revolver",
            () -> new TFSRevolverItem(33, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SLEEVE = ITEMS.register( "sleeve",
            () -> new TFSItemTexts(30, new Item.Properties()));
    public static final RegistryObject<Item> CARTRIDGE_STEEL = ITEMS.register( "cartridge_steel",
            () -> new TFSItemTexts(31, new Item.Properties()));
    public static final RegistryObject<Item> CARTRIDGE_SILVER = ITEMS.register( "cartridge_silver",
            () -> new TFSItemTexts(32, new Item.Properties()));

    public static final RegistryObject<Item> REVOLVER_N = ITEMS.register("revolver_n",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SLEEVE_N = ITEMS.register( "sleeve_n",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CARTRIDGE_STEEL_N = ITEMS.register( "cartridge_steel_n",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CARTRIDGE_SILVER_N = ITEMS.register( "cartridge_silver_n",
            () -> new Item(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
