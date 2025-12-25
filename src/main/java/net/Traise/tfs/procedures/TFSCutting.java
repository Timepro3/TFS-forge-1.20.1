package net.Traise.tfs.procedures;

import io.netty.buffer.Unpooled;
import net.Traise.tfs.screen.CuttingMenu;
import net.Traise.tfs.screen.ModelingMenu;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber
public class TFSCutting {
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getHand() != event.getEntity().getUsedItemHand()) return;
        InteractionHand pUsedHand = event.getHand();
        Player player = event.getEntity();
        if (event.getItemStack().is(ModTags.Items.ROCK) && event.getItemStack().getCount() >= 2) {
            if (player instanceof ServerPlayer _ent) {
                BlockPos _bpos = BlockPos.containing(player.getX(), player.getY(), player.getZ());
                NetworkHooks.openScreen(_ent, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new CuttingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos), pUsedHand);
                    }
                }, _bpos);
            }
        } else if (event.getItemStack().is(Items.CLAY_BALL) && event.getItemStack().getCount() >= 5) {
            if (player instanceof ServerPlayer _ent) {
                BlockPos _bpos = BlockPos.containing(player.getX(), player.getY(), player.getZ());
                NetworkHooks.openScreen(_ent, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new ModelingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos), pUsedHand);
                    }
                }, _bpos);
            }
        }
    }
}
