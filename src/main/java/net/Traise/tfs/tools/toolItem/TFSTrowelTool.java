package net.Traise.tfs.tools.toolItem;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.util.ITrowelable;
import net.Traise.tfs.util.ItemNBTHelper;
import net.Traise.tfs.util.MiscUtil;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TFSTrowelTool extends TFSBaseItem {
    private static final String TAG_PLACING_SEED = "placing_seed";
    private static final String TAG_LAST_STACK = "last_stack";

    public TFSTrowelTool(Properties pProperties) {
        super(ModTags.Blocks.STONE, 0, -1, -2, ToolActions.DEFAULT_SWORD_ACTIONS, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        InteractionHand hand = context.getHand();
        Player entity = context.getPlayer();
        ItemStack pStack = context.getItemInHand();

        List<ItemStack> targets = new ArrayList<>();
        for(int i = 0; i < Inventory.getSelectionSize(); i++) {
            ItemStack stack = entity.getInventory().getItem(i);
            if(isValidTarget(stack))
                targets.add(stack);
        }

        ItemStack ourStack = entity.getItemInHand(hand);
        if(targets.isEmpty())
            return InteractionResult.PASS;

        long seed = ItemNBTHelper.getLong(ourStack, TAG_PLACING_SEED, 0);
        Random rand = new Random(seed);
        ItemNBTHelper.setLong(ourStack, TAG_PLACING_SEED, rand.nextLong());

        ItemStack target = targets.get(rand.nextInt(targets.size()));
        int count = target.getCount();
        InteractionResult result = placeBlock(target, context);
        if(entity.getAbilities().instabuild)
            target.setCount(count);

        if(result.consumesAction()) {
            CompoundTag cmp = target.serializeNBT();
            ItemNBTHelper.setCompound(ourStack, TAG_LAST_STACK, cmp);

            if(MaterialStorageHandler.getAllDurability(pStack) > 0)
                MiscUtil.damageStack(entity, hand, context.getItemInHand(), 1);
        }

        return result;
    }

    @Override
    public boolean canAction(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isDigger(ItemStack item) {
        return false;
    }

    private InteractionResult placeBlock(ItemStack itemstack, UseOnContext context) {
        if(isValidTarget(itemstack)) {
            Item item = itemstack.getItem();

            Player player = context.getPlayer();
            ItemStack restore = itemstack;
            if(player != null) {
                restore = player.getItemInHand(context.getHand());
                player.setItemInHand(context.getHand(), itemstack);
            }
            InteractionResult res = item.useOn(new TrowelBlockItemUseContext(context, itemstack));
            if(player != null) {
                player.setItemInHand(context.getHand(), restore);
            }
            return res;
        }

        return InteractionResult.PASS;
    }

    private static boolean isValidTarget(ItemStack stack) {
        Item item = stack.getItem();
        return !stack.isEmpty() && (item instanceof BlockItem || item instanceof ITrowelable);
    }

    class TrowelBlockItemUseContext extends BlockPlaceContext {

        public TrowelBlockItemUseContext(UseOnContext context, ItemStack stack) {
            super(context.getLevel(), context.getPlayer(), context.getHand(), stack,
                    new BlockHitResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside()));
        }

    }
}