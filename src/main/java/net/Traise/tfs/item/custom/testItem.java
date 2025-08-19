package net.Traise.tfs.item.custom;

import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.util.TFSTiers;
import net.Traise.tfs.util.TFSToolType;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;

public class testItem extends Item {
    private NonNullList<TFSTiers> tiers = NonNullList.create();
    public testItem(Properties pProperties) {
        super(pProperties);
        for (int i = 0; i < 5; i++) {
            tiers.add(TFSTiers.IRON);
        }

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);

    }
}
