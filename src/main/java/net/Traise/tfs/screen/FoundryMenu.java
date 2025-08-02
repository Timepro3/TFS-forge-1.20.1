package net.Traise.tfs.screen;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.block.entity.FoundryBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FoundryMenu extends AbstractContainerMenu {
    public FoundryBlockEntity blockEntity;
    private static Inventory inventory;
    private static int containerId;
    private static FriendlyByteBuf extraDat;
    private final Level level;
    private final ContainerData data;

    public FoundryMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, (FoundryBlockEntity) inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
        extraDat = extraData;
    }

    public FoundryMenu(int pContainerId, Inventory inv, FoundryBlockEntity entity, ContainerData data) {
        super(TFSMenuTypes.FOUNDRY_MENU.get(), pContainerId);
        checkContainerSize(inv, 2);
        containerId = pContainerId;
        inventory = inv;
        blockEntity = entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 34, 43));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 34, 61));
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 34, 79));
            this.addSlot(new SlotItemHandler(iItemHandler, 3, 130, 61));

        });

        addDataSlots(data);
    }

    public static FoundryBlockEntity entity;

    public static void tickUpdate(FoundryBlockEntity blockEntity, int id) {
        if (containerId == id) {
            entity = blockEntity;
        }
    }

    public int getSize() {
        return entity.getSize();
    }

    public int getFluidAmount(int Index) {
        return entity.getFluidAmount(Index);
    }
    
    public float getPercent(int Index) {
        int T = (entity.getFluidAmount(Index) * 10000) / entity.sumAllFluidSlots();

        return (float) T / 100;
    }

    public boolean hawFluid(int Index) {
        return entity.hawFluid(Index);
    }

    public String getFluidStackInSlot(int Index) {
        return entity.getFluidName(Index);
    }

    public String getAlloyName() {
        return entity.getAlloyName();
    }

    public int getAlloyCount() {
        return entity.sumAllFluidSlots();
    }

    public boolean burned() {
        return entity.isBurned();
    }

    public int progress(int i) {
        int progress = entity.getSlotProgress(i);
        int maxProgress = entity.getMaxProgress();
        int progressArrowSize = 16; // This is the height in pixels of your arrow
        int T = 0;
        if (progress > 0 && progress < maxProgress) {T = 1;}

        return maxProgress != 0 && progress != 0 ? (progress * progressArrowSize / maxProgress) + T : 0;
    }

    public int fullness() {
        int progress = this.data.get(1);
        int maxProgress = this.data.get(0);
        int progressArrowSize = 29; // This is the height in pixels of your arrow
        int T = 0;
        if (progress > 0) {T = 1;}

        return maxProgress != 0 && progress != 0 ? (progress * progressArrowSize / maxProgress) + T : 0;
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
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!
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
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, TFSBlocks.FOUNDRY.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 102 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 160));
        }
    }
}