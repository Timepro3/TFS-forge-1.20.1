package net.Traise.tfs.block;

import net.Traise.tfs.block.custom.*;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.tfs;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TFSBlocks {
   public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, tfs.MOD_ID); /* при нажатии на IRON_BLOCK колёсиком мыши, можно посмотреть параметры*/
   public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block",
           () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
   public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
   public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
           () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
   public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
           () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
   public static final RegistryObject<Block> CAST_IRON_BLOCK = registerBlock("cast_iron_block",
           () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
   public static final RegistryObject<Block> UNKNOWN_METAL_BLOCK = registerBlock("unknown_metal_block",
           () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

   public static final RegistryObject<Block> RICH_CUPRITE_ORE = registerBlock("rich_cuprite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> STONE_CUPRITE_ORE = registerBlock("stone_cuprite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> DEEP_CUPRITE_ORE = registerBlock("deep_cuprite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_STONE_CUPRITE_ORE = registerBlock("poor_stone_cuprite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_DEEP_CUPRITE_ORE = registerBlock("poor_deep_cuprite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> RICH_LIMONITE_ORE = registerBlock("rich_limonite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> STONE_LIMONITE_ORE = registerBlock("stone_limonite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> DEEP_LIMONITE_ORE = registerBlock("deep_limonite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_STONE_LIMONITE_ORE = registerBlock("poor_stone_limonite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_DEEP_LIMONITE_ORE = registerBlock("poor_deep_limonite_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> RICH_TIN_ORE = registerBlock("rich_tin_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> STONE_TIN_ORE = registerBlock("stone_tin_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> DEEP_TIN_ORE = registerBlock("deep_tin_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_STONE_TIN_ORE = registerBlock("poor_stone_tin_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_DEEP_TIN_ORE = registerBlock("poor_deep_tin_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> RICH_ZINC_ORE = registerBlock("rich_zinc_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> STONE_ZINC_ORE = registerBlock("stone_zinc_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> DEEP_ZINC_ORE = registerBlock("deep_zinc_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_STONE_ZINC_ORE = registerBlock("poor_stone_zinc_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_DEEP_ZINC_ORE = registerBlock("poor_deep_zinc_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> RICH_SILVER_ORE = registerBlock("rich_silver_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> STONE_SILVER_ORE = registerBlock("stone_silver_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> DEEP_SILVER_ORE = registerBlock("deep_silver_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_STONE_SILVER_ORE = registerBlock("poor_stone_silver_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_DEEP_SILVER_ORE = registerBlock("poor_deep_silver_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> RICH_GOLD_ORE = registerBlock("rich_gold_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> STONE_GOLD_ORE = registerBlock("stone_gold_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> DEEP_GOLD_ORE = registerBlock("deep_gold_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_STONE_GOLD_ORE = registerBlock("poor_stone_gold_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> POOR_DEEP_GOLD_ORE = registerBlock("poor_deep_gold_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(3,5)));
   public static final RegistryObject<Block> STONE_DIAMOND_ORE = registerBlock("stone_diamond_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(10,15)));
   public static final RegistryObject<Block> DEEP_DIAMOND_ORE = registerBlock("deep_diamond_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(10,15)));
   public static final RegistryObject<Block> STONE_EMERALD_ORE = registerBlock("stone_emerald_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(10,15)));
   public static final RegistryObject<Block> DEEP_EMERALD_ORE = registerBlock("deep_emerald_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(6,10)));
   public static final RegistryObject<Block> STONE_CINNABAR_ORE = registerBlock("stone_cinnabar_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(6,10)));
   public static final RegistryObject<Block> DEEP_CINNABAR_ORE = registerBlock("deep_cinnabar_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(6,10)));
   public static final RegistryObject<Block> STONE_LAZULI_ORE = registerBlock("stone_lazuli_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(6,10)));
   public static final RegistryObject<Block> DEEP_LAZULI_ORE = registerBlock("deep_lazuli_ore",
           () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                   .strength(5f,2f).requiresCorrectToolForDrops(), UniformInt.of(6,10)));

   public static final RegistryObject<Block> SAND_DIAMOND = registerBlock("sand_diamond",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_DIAMOND = registerBlock("gravel_diamond",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_EMERALD = registerBlock("sand_emerald",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_EMERALD = registerBlock("gravel_emerald",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_LAZULI = registerBlock("sand_lazuli",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_LAZULI = registerBlock("gravel_lazuli",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_CINNABAR = registerBlock("sand_cinnabar",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_CINNABAR = registerBlock("gravel_cinnabar",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_COAL = registerBlock("sand_coal",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_COAL = registerBlock("gravel_coal",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_CUPRITE = registerBlock("sand_cuprite",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_CUPRITE = registerBlock("gravel_cuprite",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_LIMONITE = registerBlock("sand_limonite",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_LIMONITE = registerBlock("gravel_limonite",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_TIN = registerBlock("sand_tin",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_TIN = registerBlock("gravel_tin",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_ZINC = registerBlock("sand_zinc",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_ZINC = registerBlock("gravel_zinc",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_GOLD = registerBlock("sand_gold",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_GOLD = registerBlock("gravel_gold",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
   public static final RegistryObject<Block> SAND_SILVER = registerBlock("sand_silver",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
   public static final RegistryObject<Block> GRAVEL_SILVER = registerBlock("gravel_silver",
           () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));

   public static final RegistryObject<Block> ROCK_OBJECT = registerBlock("rock_object",
           () -> new TFSRock(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE).pushReaction(PushReaction.DESTROY).strength(0.1f, 0.1f)));
   public static final RegistryObject<Block> TWIG_BLOCK = registerBlock("twig_block",
           () -> new TFStwig(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).strength(0.1f, 0.1f)));
   public static final RegistryObject<Block> FORGE = registerBlock("forge",
           () -> new TFSForge(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
   public static final RegistryObject<Block> FOUNDRY = registerBlock("foundry",
           () -> new TFSFoundry(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
   public static final RegistryObject<Block> MIMIC = registerBlock("mimic",
           () -> new TFSMimic(BlockBehaviour.Properties.copy(Blocks.DIORITE)));

   public static final RegistryObject<Block> DIAMOND_FRAGMENT_OBJECT = registerBlock("diamond_fragment_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> EMERALD_FRAGMENT_OBJECT = registerBlock("emerald_fragment_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> LAZULI_FRAGMENT_OBJECT = registerBlock("lazuli_fragment_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> CINNABAR_FRAGMENT_OBJECT = registerBlock("cinnabar_fragment_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> COAL_FRAGMENT_OBJECT = registerBlock("coal_fragment_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> POOR_CUPRITE_OBJECT = registerBlock("poor_cuprite_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> POOR_LIMONITE_OBJECT = registerBlock("poor_limonite_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> POOR_TIN_OBJECT = registerBlock("poor_tin_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> POOR_ZINC_OBJECT = registerBlock("poor_zinc_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> POOR_GOLD_OBJECT = registerBlock("poor_gold_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));
   public static final RegistryObject<Block> POOR_SILVER_OBJECT = registerBlock("poor_silver_object",
           () -> new TFSOrePlaced(BlockBehaviour.Properties.copy(Blocks.SAND).noOcclusion().sound(SoundType.STONE)));

   private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
      RegistryObject<T> toReturn = BLOCKS.register(name, block);
      registerBlockItem(name, toReturn);
      return toReturn;
   }

   private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
      return TFSItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
   }

   public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}