package net.Traise.tfs.item.custom;

import com.google.common.collect.ImmutableMultimap;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.item.TFSToolTiers;
import net.Traise.tfs.util.MoldType;
import net.Traise.tfs.util.TFSTiers;
import net.Traise.tfs.util.TFSToolType;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;

public class TFSToolItem extends DiggerItem {
    private final Material material;

    public TFSToolItem(Tier pTier, Properties pProperties) {
        super(7, -3.3f, pTier, BlockTags.MINEABLE_WITH_SHOVEL,  pProperties.durability(100));
        material = Material.NONE;
    }

    public TFSToolItem(Material pMaterial, Tier pTier, Properties pProperties) {
        super(7, -3.3f, pTier, BlockTags.MINEABLE_WITH_SHOVEL,  pProperties.durability(100));
        material = pMaterial;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        RegistryObject<Item> TEST = TFSItems.BLU;

        ItemHandlerHelper.giveItemToPlayer(pPlayer, TEST.get().getDefaultInstance());

        save(pPlayer.getItemInHand(pUsedHand));

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void save(ItemStack itemStack) {
        itemStack.getOrCreateTag().putString("Material", material.getName());
    }

    public void load(ItemStack itemStack) {
        //material = Material.getMaterialInName(itemStack.getOrCreateTag().getString("Material"));
    }

    public enum Material {
        NONE("none"),
        ROCK("rock"),
        IRON("iron");

        private final String name;

        Material(String pName) {
            name = pName;
        }

        public String getName() {
            return this.name;
        }

        public static Material getMaterialInName(String name) {
            return switch (name) {
                case "none" -> Material.NONE;
                case "rock" -> Material.ROCK;
                case "iron" -> Material.IRON;
                default -> Material.NONE;
            };
        }
    }
}
