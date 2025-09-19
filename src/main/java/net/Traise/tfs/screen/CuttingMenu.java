package net.Traise.tfs.screen;

import net.Traise.tfs.recipe.CuttingRecipe;
import net.Traise.tfs.recipe.FoundryRecipe;
import net.Traise.tfs.sound.ModSounds;
import net.Traise.tfs.util.BooleanContainer;
import net.Traise.tfs.util.TFSVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

public class CuttingMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final Level world;
    public final Player entity;
    private ItemStackHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private final int id;
    private boolean distroy = true;

    public int map_Height = 5;
    public int map_Width = 5;
    public NonNullList<Boolean> map = NonNullList.withSize(map_Height * map_Width, true);

    private InteractionHand usedHand;

    public CuttingMenu(int id, Inventory inv, FriendlyByteBuf extraData, InteractionHand pHand) {
        super(TFSMenuTypes.CUTTING_MENU.get(), id);
        this.id = id;
        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = new ItemStackHandler(1);

        usedHand = pHand;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 133, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player playerIn) {
                entity.closeContainer();
                return super.mayPickup(playerIn);
            }
        }));
    }

    public CuttingMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, extraData, InteractionHand.MAIN_HAND);
    }

    public NonNullList<Boolean> getMap() {
        return map;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean clickMenuButton(Player pPlayer, int pId) {
        this.map.set(pId, false);
        Optional<CuttingRecipe> recipe = getCurrentRecipe();
        if (distroy) {
            if (!entity.isCreative()) {
                this.entity.getItemInHand(usedHand).shrink(1);
            }
            distroy = false;
        }

        world.playLocalSound(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);

        if (recipe.isPresent()) {
            internal.setStackInSlot(0, recipe.get().getResultItem(null));
        } else internal.setStackInSlot(0, ItemStack.EMPTY);

        return super.clickMenuButton(pPlayer, pId);
    }

    private Optional<CuttingRecipe> getCurrentRecipe() {
        BooleanContainer inventory = new BooleanContainer(NonNullList.withSize(25, true));
        for(int i = 0; i < this.map.size(); i++) {
            inventory.setMapSlot(i, this.map.get(i));
        }

        return this.world.getRecipeManager().getRecipeFor(CuttingRecipe.Type.INSTANCE, inventory, world);
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 1;  // must be the number of slots you have!
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        if (playerIn instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getSlots(); ++j) {
                    playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
                }
            } else {
                for (int i = 0; i < internal.getSlots(); ++i) {
                    playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
                }
            }
        }
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            if (playerInventory.selected == i && usedHand == InteractionHand.MAIN_HAND) {
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142) {
                    @Override
                    public boolean mayPickup(Player pPlayer) {
                        return false;
                    }
                });
            } else {
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
            }
        }
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }
}