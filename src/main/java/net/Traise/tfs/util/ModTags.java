package net.Traise.tfs.util;

import net.Traise.tfs.tfs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;


public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEED_1 = tag("need_1");
        public static final TagKey<Block> NEED_2 = tag("need_2");
        public static final TagKey<Block> NEED_3 = tag("need_3");

        public static final TagKey<Block> AGE_3 = tag("age_3");
        public static final TagKey<Block> PLANTS_HOE = tag("plants_hoe");

        public static final TagKey<Block> MINEABLE_WITH_MULTI_TOOL = tag("mineable/multi_tool");

        public static final TagKey<Block> STONE = tag("stone");
        public static final TagKey<Block> GRAVEL = tag("gravel");

        public static final TagKey<Block> REMOVE = tag("remove");

        public static final TagKey<Block> TFS_NEEDS_STONE_TOOL = tag("tfs_needs_stone_tool");
        public static final TagKey<Block> TFS_NEEDS_COPPER_TOOL = tag("tfs_needs_copper_tool");
        public static final TagKey<Block> TFS_NEEDS_BRONZE_TOOL = tag("tfs_needs_bronze_tool");
        public static final TagKey<Block> TFS_NEEDS_IRON_TOOL = tag("tfs_needs_iron_tool");
        public static final TagKey<Block> TFS_NEEDS_STEEL_TOOL = tag("tfs_needs_steel_tool");
        public static final TagKey<Block> TFS_NEEDS_DIAMOND_TOOL = tag("tfs_needs_diamond_tool");
        public static final TagKey<Block> TFS_NEEDS_NETHERITE_TOOL = tag("tfs_needs_netherite_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(tfs.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> KNIFE = tag("knife");
        public static final TagKey<Item> METALS = tag("metals");
        public static final TagKey<Item> FORMS = tag("forms");
        public static final TagKey<Item> BULLET = tag("bullet");
        public static final TagKey<Item> SILVER = tag("silver");

        public static final TagKey<Item> ROCK = tag("rock");

        public static final TagKey<Item> METAL_COPPER = tag("metal_copper");
        public static final TagKey<Item> METAL_IRON = tag("metal_iron");
        public static final TagKey<Item> METAL_TIN = tag("metal_tin");
        public static final TagKey<Item> METAL_ZINC = tag("metal_zinc");
        public static final TagKey<Item> METAL_GOLD = tag("metal_gold");
        public static final TagKey<Item> METAL_SILVER = tag("metal_silver");
        public static final TagKey<Item> METAL_BRONZE = tag("metal_bronze");
        public static final TagKey<Item> METAL_BRASS = tag("metal_brass");
        public static final TagKey<Item> METAL_STEEL = tag("metal_steel");
        public static final TagKey<Item> METAL_CAST_IRON = tag("metal_cast_iron");

        public static final TagKey<Item> METAL_15 = tag("metal_15");
        public static final TagKey<Item> METAL_20 = tag("metal_20");
        public static final TagKey<Item> METAL_35 = tag("metal_35");
        public static final TagKey<Item> METAL_100 = tag("metal_100");

        public static final TagKey<Item> TOOL_PART = tag("tool_part");
        public static final TagKey<Item> TOOL_HEAD = tag("tool_head");
        public static final TagKey<Item> TOOL_STICK = tag("tool_stick");
        public static final TagKey<Item> TOOL_STRING = tag("tool_string");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(tfs.MOD_ID, name));
        }
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> UNDEAD = tag("undead");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(tfs.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> OWER = tag("ower");

        public static final TagKey<Biome> ORE = tag("ore");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(tfs.MOD_ID, name));
        }
    }

    public static class PlacedFeatur {
        public static final TagKey<PlacedFeature> OWER_ORE = tag("ower_ore");

        private static TagKey<PlacedFeature> tag(String name) {
            return TagKey.create(Registries.PLACED_FEATURE, new ResourceLocation(name));
        }
    }
}
