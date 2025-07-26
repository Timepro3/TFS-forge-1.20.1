package net.Traise.tfs.block.entity;

import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.item.custom.TFSOldFormItem;
import net.Traise.tfs.screen.ForgeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForgeBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT2 = 1;
    private static final int INPUT_SLOT3 = 2;
    private static final int OUTPUT_SLOT = 3;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int temp1 = 0;
    private int temp2 = 0;
    private int temp3 = 0;
    private int temperature = 0;
    private int maxTemperature = 0;
    private int QUN = 0;
    private double QUN2 = 0;
    private int fillIndicator = 0;
    private int MAXQ = 1000;

    private double I1 = 0;
    private double I2 = 0;
    private double I3 = 0;
    private double I4 = 0;
    private double I5 = 0;
    private double I6 = 0;
    private double I7 = 0;

    private int f1 = 0;
    private int f2 = 0;
    private int f3 = 0;
    private int f4 = 0;
    private int f5 = 0;
    private int f6 = 0;
    private int f7 = 0;

    private int T1 = 0;
    private int T2 = 0;
    private int T4 = 0;

    public ForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(TFSBlockEntity.FORGE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> ForgeBlockEntity.this.temp1;
                    case 1 -> ForgeBlockEntity.this.temp2;
                    case 2 -> ForgeBlockEntity.this.temp3;
                    case 3 -> ForgeBlockEntity.this.temperature;
                    case 4 -> ForgeBlockEntity.this.QUN;
                    case 5 -> ForgeBlockEntity.this.MAXQ;
                    case 6 -> ForgeBlockEntity.this.fillIndicator;
                    case 7 -> ForgeBlockEntity.this.maxTemperature;
                    case 12 -> ForgeBlockEntity.this.f1;
                    case 13 -> ForgeBlockEntity.this.f2;
                    case 14 -> ForgeBlockEntity.this.f3;
                    case 15 -> ForgeBlockEntity.this.f4;
                    case 16 -> ForgeBlockEntity.this.f5;
                    case 17 -> ForgeBlockEntity.this.f6;
                    case 18 -> ForgeBlockEntity.this.T4;
                    case 20 -> ForgeBlockEntity.this.f7;
                    default -> 0;

                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> ForgeBlockEntity.this.temp1 = pValue;
                    case 1 -> ForgeBlockEntity.this.temp2 = pValue;
                    case 2 -> ForgeBlockEntity.this.temp3 = pValue;
                    case 3 -> ForgeBlockEntity.this.temperature = pValue;
                    case 4 -> ForgeBlockEntity.this.QUN = pValue;
                    case 5 -> ForgeBlockEntity.this.MAXQ = pValue;
                    case 6 -> ForgeBlockEntity.this.fillIndicator = pValue;
                    case 7 -> ForgeBlockEntity.this.maxTemperature = pValue;
                    case 12 -> ForgeBlockEntity.this.f1 = pValue;
                    case 13 -> ForgeBlockEntity.this.f2 = pValue;
                    case 14 -> ForgeBlockEntity.this.f3 = pValue;
                    case 15 -> ForgeBlockEntity.this.f4 = pValue;
                    case 16 -> ForgeBlockEntity.this.f5 = pValue;
                    case 17 -> ForgeBlockEntity.this.f6 = pValue;
                    case 18 -> ForgeBlockEntity.this.T4 = pValue;
                    case 20 -> ForgeBlockEntity.this.f7 = pValue;

                }

            }

            @Override
            public int getCount() {
                return 21;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("литейная кузня");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new ForgeMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("forge.temp1", temp1);
        pTag.putInt("forge.temp2", temp2);
        pTag.putInt("forge.temp3", temp3);
        pTag.putInt("forge.temperature", temperature);
        pTag.putInt("forge.QUN", QUN);
        pTag.putDouble("forge.QUN2", QUN2);
        pTag.putInt("forge.fillIndicator", fillIndicator);
        pTag.putInt("forge.maxTemperature", maxTemperature);
        pTag.putDouble("forge.I1", I1);
        pTag.putDouble("forge.I2", I2);
        pTag.putDouble("forge.I3", I3);
        pTag.putDouble("forge.I4", I4);
        pTag.putDouble("forge.I5", I5);
        pTag.putDouble("forge.I6", I6);
        pTag.putDouble("forge.I7", I7);
        pTag.putInt("forge.f1", f1);
        pTag.putInt("forge.f2", f2);
        pTag.putInt("forge.f3", f3);
        pTag.putInt("forge.f4", f4);
        pTag.putInt("forge.f5", f5);
        pTag.putInt("forge.f6", f6);
        pTag.putInt("forge.f7", f7);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        temp1 = pTag.getInt("forge.temp1");
        temp2 = pTag.getInt("forge.temp2");
        temp3 = pTag.getInt("forge.temp3");
        temperature = pTag.getInt("forge.temperature");
        QUN = pTag.getInt("forge.QUN");
        QUN2 = pTag.getDouble("forge.QUN2");
        fillIndicator = pTag.getInt("forge.fillIndicator");
        maxTemperature = pTag.getInt("forge.maxTemperature");
        I1 = pTag.getDouble("forge.I1");
        I2 = pTag.getDouble("forge.I2");
        I3 = pTag.getDouble("forge.I3");
        I4 = pTag.getDouble("forge.I4");
        I5 = pTag.getDouble("forge.I5");
        I6 = pTag.getDouble("forge.I6");
        I7 = pTag.getDouble("forge.I7");
        f1 = pTag.getInt("forge.f1");
        f2 = pTag.getInt("forge.f2");
        f3 = pTag.getInt("forge.f3");
        f4 = pTag.getInt("forge.f4");
        f5 = pTag.getInt("forge.f5");
        f6 = pTag.getInt("forge.f6");
        f7 = pTag.getInt("forge.f7");
    }

    public void execute(ServerLevel pLevel, double x, double y, double z) {
        if ((pLevel.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.LAVA) {
            T4 = -1;
        } else {
            T4 = 0;
        }
    }

    private boolean Lav() {
        return T4 == -1;
    }

    public void tick(ServerLevel pLevel, BlockPos pPos, BlockState pState) {
        int x = pPos.getX();
        int y = pPos.getY();
        int z = pPos.getZ();
        execute(pLevel, x, y, z);
        temperature();
        removeTheCons();

        for (int i = 0; i <= 2; i++) {
            int o = temp1;
            int u = 5;

            if (i == 1) {
                o = temp2;
            } else if (i == 2) {
                o = temp3;
            }

            setChanged(pLevel, pPos, pState);
            if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metals")))) {
                if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_copper"))) && hasContainerFull(full(i))) {
                    if (o >= 500) {
                        I1 = I1 + full(i);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_iron"))) && hasContainerFull(full(i))) {
                    if (o >= 900) {
                        I2 = I2 + full(i);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_tin"))) && hasContainerFull(full(i))) {
                    if (o >= 250) {
                        I3 = I3 + full(i);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_zinc"))) && hasContainerFull(full(i))) {
                    if (o >= 400) {
                        I4 = I4 + full(i);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_gold"))) && hasContainerFull(full(i))) {
                    if (o >= 500) {
                        I5 = I5 + full(i);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_silver"))) && hasContainerFull(full(i))) {
                    if (o >= 450) {
                        I6 = I6 + full(i);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_bronze"))) && hasContainerFull(full(i))) {
                    if (o >= 450) {
                        I1 = I1 + (full(i) * 0.9f);
                        I3 = I3 + (full(i) * 0.1f);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_brass"))) && hasContainerFull(full(i))) {
                    if (o >= 420) {
                        I1 = I1 + (full(i) * 0.7f);
                        I4 = I4 + (full(i) * 0.3f);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_steel"))) && hasContainerFull(full(i))) {
                    if (o >= 1000) {
                        I2 = I2 + (full(i) * 0.9f);
                        I7 = I7 + (full(i) * 0.1f);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (this.itemHandler.getStackInSlot(i).is(ItemTags.create(new ResourceLocation("tfs:metal_cast_iron"))) && hasContainerFull(full(i))) {
                    if (o >= 850) {
                        I2 = I2 + (full(i) * 0.8f);
                        I7 = I7 + (full(i) * 0.2f);
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (QUN2 + 20 <= MAXQ && this.itemHandler.getStackInSlot(i).getItem() == Items.COAL || this.itemHandler.getStackInSlot(i).getItem() == Items.CHARCOAL) {
                    if (o >= 100) {
                        I7 = I7 + 20;
                        this.itemHandler.extractItem(i, 1, false);
                    } else if (o < maxTemperature) {
                        o = o + u;
                    }
                } else if (QUN2 + 1 <= MAXQ && this.itemHandler.getStackInSlot(i).getItem() == TFSItems.COPPER_INGOT_FORM.get()) {
                    TFSOldFormItem.myTick(this.itemHandler.getStackInSlot(i));
                    if (this.itemHandler.getStackInSlot(i).getOrCreateTag().getDouble("QUN") > 0) {
                        o = u;
                        outPouring(i);
                    } else if (this.itemHandler.getStackInSlot(i).getItem() == TFSItems.COPPER_INGOT_FORM.get()){
                        this.itemHandler.extractItem(i, 1, false);
                        ItemStack sta = TFSItems.COPPER_INGOT_FORM.get().getDefaultInstance();
                        for (int k = 1; k <= 7; k++){
                            sta.getOrCreateTag().putInt("T" + k, 0);
                        }
                        sta.getOrCreateTag().putDouble("QUN", 0);
                        sta.getOrCreateTag().putInt("CustomModelData", 0);
                        sta.getOrCreateTag().putString("componenT", "");

                        this.itemHandler.setStackInSlot(i, sta);
                    }
                }
            } else {o = 0;}

            if (i == 0) {
                temp1 = o;
            } else if (i == 1) {
                temp2 = o;
            } else if (i == 2) {
                temp3 = o;
            }

            QUN2 = I1 + I2 + I3 + I4 + I5 + I6 + I7;
            if ( 5 < (10 * (QUN2 - ((int) QUN2)))) {
                QUN2 = ((int) QUN2) + 1;
            }
            QUN = (int) QUN2;
            Amount();
        }

        if(hasRecipe2()) {
            fillIndicator = 1;
            setChanged(pLevel, pPos, pState);
            Amount();
            double t3 = QUN2; //-1
            pouring();

            I1 = I1 - (I1 / t3);
            I2 = I2 - (I2 / t3);
            I3 = I3 - (I3 / t3);
            I4 = I4 - (I4 / t3);
            I5 = I5 - (I5 / t3);
            I6 = I6 - (I6 / t3);
            I7 = I7 - (I7 / t3);

            removeTheCons();

            QUN2 = I1 + I2 + I3 + I4 + I5 + I6 + I7;
            if ( 5 < (10 * (QUN2 - ((int) QUN2)))) {
                QUN2 = ((int) QUN2) + 1;
            }
            QUN = (int) QUN2;
            Amount();
        }else {fillIndicator = 0;}
    }

    public void pouring() {
        this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putDouble("I1", this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getDouble("I1") + (I1 / QUN2));
        this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putDouble("I2", this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getDouble("I2") + (I2 / QUN2));
        this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putDouble("I3", this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getDouble("I3") + (I3 / QUN2));
        this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putDouble("I4", this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getDouble("I4") + (I4 / QUN2));
        this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putDouble("I5", this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getDouble("I5") + (I5 / QUN2));
        this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putDouble("I6", this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getDouble("I6") + (I6 / QUN2));
        this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putDouble("I7", this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getDouble("I7") + (I7 / QUN2));
        TFSOldFormItem.myTick(this.itemHandler.getStackInSlot(OUTPUT_SLOT));
    }

    public void outPouring(int h) {
        double u1 = (this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I1") / this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("QUN"));
        double u2 = (this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I2") / this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("QUN"));
        double u3 = (this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I3") / this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("QUN"));
        double u4 = (this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I4") / this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("QUN"));
        double u5 = (this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I5") / this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("QUN"));
        double u6 = (this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I6") / this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("QUN"));
        double u7 = (this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I7") / this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("QUN"));

        I1 = I1 + u1;
        I2 = I2 + u2;
        I3 = I3 + u3;
        I4 = I4 + u4;
        I5 = I5 + u5;
        I6 = I6 + u6;
        I7 = I7 + u7;

        this.itemHandler.getStackInSlot(h).getOrCreateTag().putDouble("I1", this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I1") - u1);
        this.itemHandler.getStackInSlot(h).getOrCreateTag().putDouble("I2", this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I2") - u2);
        this.itemHandler.getStackInSlot(h).getOrCreateTag().putDouble("I3", this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I3") - u3);
        this.itemHandler.getStackInSlot(h).getOrCreateTag().putDouble("I4", this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I4") - u4);
        this.itemHandler.getStackInSlot(h).getOrCreateTag().putDouble("I5", this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I5") - u5);
        this.itemHandler.getStackInSlot(h).getOrCreateTag().putDouble("I6", this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I6") - u6);
        this.itemHandler.getStackInSlot(h).getOrCreateTag().putDouble("I7", this.itemHandler.getStackInSlot(h).getOrCreateTag().getDouble("I7") - u7);
        TFSOldFormItem.myTick(this.itemHandler.getStackInSlot(h));
    }

    private void removeTheCons() {
        if (I1 < 0) {
            I1 = 0;
        } else if (I2 < 0) {
            I2 = 0;
        } else if (I3 < 0) {
            I3 = 0;
        } else if (I4 < 0) {
            I4 = 0;
        } else if (I5 < 0) {
            I5 = 0;
        } else if (I6 < 0) {
            I6 = 0;
        } else if (I7 < 0) {
            I7 = 0;
        }
    }

    private float full(int h) {
        int x = 0;
        if (this.itemHandler.getStackInSlot(h).is(ItemTags.create(new ResourceLocation("tfs:metal_15")))) {
            x = 15;
        } else if (this.itemHandler.getStackInSlot(h).is(ItemTags.create(new ResourceLocation("tfs:metal_20")))) {
            x = 20;
        } else if (this.itemHandler.getStackInSlot(h).is(ItemTags.create(new ResourceLocation("tfs:metal_35")))) {
            x = 35;
        } else if (this.itemHandler.getStackInSlot(h).is(ItemTags.create(new ResourceLocation("tfs:metal_100")))) {
            x = 100;
        }
        return x;
    }

    private void Amount() {
        if (QUN2 > 0) {
            int u1 = 0;if (((I1 * 100000) / QUN2) % 10 > 5) {u1 = 1;}
            int u2 = 0;if (((I2 * 100000) / QUN2) % 10 > 5) {u2 = 1;}
            int u3 = 0;if (((I3 * 100000) / QUN2) % 10 > 5) {u3 = 1;}
            int u4 = 0;if (((I4 * 100000) / QUN2) % 10 > 5) {u4 = 1;}
            int u5 = 0;if (((I5 * 100000) / QUN2) % 10 > 5) {u5 = 1;}
            int u6 = 0;if (((I6 * 100000) / QUN2) % 10 > 5) {u6 = 1;}
            int u7 = 0;if (((I7 * 100000) / QUN2) % 10 > 5) {u7 = 1;}
            f1 = (int) ((I1 * 10000) / QUN2) + u1;
            f2 = (int) ((I2 * 10000) / QUN2) + u2;
            f3 = (int) ((I3 * 10000) / QUN2) + u3;
            f4 = (int) ((I4 * 10000) / QUN2) + u4;
            f5 = (int) ((I5 * 10000) / QUN2) + u5;
            f6 = (int) ((I6 * 10000) / QUN2) + u6;
            f7 = (int) ((I7 * 10000) / QUN2) + u7;
        } else {
            f1 = 0;
            f2 = 0;
            f3 = 0;
            f4 = 0;
            f5 = 0;
            f6 = 0;
            f7 = 0;
        }
    }

    private void temperature() {
        if (T4 == -1) {
            if (maxTemperature < 1000) {
                maxTemperature = maxTemperature + 5;
            } else if (maxTemperature != 1000) {
                maxTemperature = maxTemperature - 1;
            }
        } else {
            if (maxTemperature < (int) T4) {
                maxTemperature = maxTemperature + 5;
            } else if (maxTemperature != (int) T4) {
                maxTemperature = maxTemperature - 1;
            }
        }
    }

    private boolean hasRecipe2() {
        boolean hasCrafting = QUN2 > 0;

        return hasCrafting && hasEmptyForm() && Lav();
    }

    private boolean hasEmptyForm() {
        boolean M = this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(ItemTags.create(new ResourceLocation("tfs:forms")));
        TFSOldFormItem.myTick(this.itemHandler.getStackInSlot(OUTPUT_SLOT));
        boolean M2 = this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().getFloat("QUN") < 100;

        return M && M2;
    }

    private boolean hasContainerFull(float l) {
        return QUN2 + l <= MAXQ;
    }


}
