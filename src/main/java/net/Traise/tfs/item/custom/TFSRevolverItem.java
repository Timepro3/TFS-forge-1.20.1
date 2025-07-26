package net.Traise.tfs.item.custom;

import io.netty.buffer.Unpooled;
import net.Traise.tfs.entity.projectile.ThrownSilverBullet;
import net.Traise.tfs.entity.projectile.ThrownSteelBullet;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.item.inventory.RevolverInventory;
import net.Traise.tfs.screen.RevolverMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicReference;

public class TFSRevolverItem extends TFSItemTexts {
    public static final ItemStackHandler itemHandler = new ItemStackHandler(8);

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    private int batton = 0;

    public TFSRevolverItem(int number, Properties pProperties) {
        super(number, pProperties);
  

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> TFSRevolverItem.this.batton;
                    default -> 0;

                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> TFSRevolverItem.this.batton = pValue;

                }

            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    @Nonnull
    @Override
    public UseAnim getUseAnimation(@Nonnull ItemStack stack)
    {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        double x = pPlayer.getX();
        double y = pPlayer.getY();
        double z = pPlayer.getZ();



        ItemStack revolver = pPlayer.getItemInHand(pHand);
        if(pPlayer.isShiftKeyDown()) {
            if (pPlayer instanceof ServerPlayer serverPlayer) {
                NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Барабан");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                        packetBuffer.writeBlockPos(pPlayer.blockPosition());
                        packetBuffer.writeByte(pHand == InteractionHand.MAIN_HAND ? 0 : 1);
                        return new RevolverMenu(id, inventory, packetBuffer);
                    }
                }, buf -> {
                    buf.writeBlockPos(pPlayer.blockPosition());
                    buf.writeByte(pHand == InteractionHand.MAIN_HAND ? 0 : 1);
                });
            }

            return new InteractionResultHolder<>(InteractionResult.SUCCESS, revolver);
        } else {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            if ((new Object() {
                public ItemStack getItemStack(int sltid, ItemStack _isc) {
                    AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                    _isc.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                        _retval.set(capability.getStackInSlot(sltid).copy());
                    });
                    return _retval.get();
                }
            }.getItemStack(0, itemstack)).is(ItemTags.create(new ResourceLocation("tfs:bullet")))) {
                if ((new Object() {
                    public ItemStack getItemStack(int sltid, ItemStack _isc) {
                        AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
                        _isc.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                            _retval.set(capability.getStackInSlot(sltid).copy());
                        });
                        return _retval.get();
                    }
                }.getItemStack(0, itemstack)).getItem() == TFSItems.CARTRIDGE_STEEL.get()) {
                    if (pLevel instanceof ServerLevel projectileLevel) {
                        Projectile _entityToSpawn = new Object() {
                            public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                ThrownSteelBullet steelBullet= new ThrownSteelBullet(level, pPlayer);
                                steelBullet.setOwner(shooter);
                                steelBullet.setSilent(true);
                                return steelBullet;
                                //скорость урон
                            }
                        }.getArrow(projectileLevel, pPlayer, 8, 0);
                        _entityToSpawn.setPos(x, (y + 1.6f), z);
                        _entityToSpawn.shoot((pPlayer.getLookAngle().x), (pPlayer.getLookAngle().y), (pPlayer.getLookAngle().z), 4f, 0.6f);
                        projectileLevel.addFreshEntity(_entityToSpawn);
                    }

                } else {
                    if (pLevel instanceof ServerLevel projectileLevel) {
                        Projectile _entityToSpawn = new Object() {
                            public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                ThrownSilverBullet silverBullet= new ThrownSilverBullet(level, pPlayer);
                                silverBullet.setOwner(shooter);
                                silverBullet.setSilent(true);
                                return silverBullet;
                            }
                        }.getArrow(projectileLevel, pPlayer, 5, 0);
                        _entityToSpawn.setPos(x, (y + 1.6f), z);
                        _entityToSpawn.shoot((pPlayer.getLookAngle().x), (pPlayer.getLookAngle().y), (pPlayer.getLookAngle().z), 4f, 0.3f);
                        projectileLevel.addFreshEntity(_entityToSpawn);
                    }
                }

                AtomicReference<ItemStack> slot0 = new AtomicReference<>(TFSItems.SLEEVE.get().getDefaultInstance());
                pPlayer.getItemInHand(pHand).getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                    if (capability instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(0, slot0.get());
                    }
                });

                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5f, 1f);

            } else {
                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.PLAYERS, 1f, 1f);

            }

            AtomicReference<ItemStack> slot1 = new AtomicReference<>(ItemStack.EMPTY);
            pPlayer.getItemInHand(pHand).getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                        slot1.set(capability.getStackInSlot(0).copy());

            });

            AtomicReference<ItemStack> slots = new AtomicReference<>(ItemStack.EMPTY);
            for (int i = 1; i <= 7;) {
                int finalI = i;
                pPlayer.getItemInHand(pHand).getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                    slots.set(capability.getStackInSlot(finalI).copy());

                });

                pPlayer.getItemInHand(pHand).getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                    if (capability instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(finalI - 1, slots.get());
                    }
                });

                i = i + 1;
            }

            pPlayer.getItemInHand(pHand).getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                if (capability instanceof IItemHandlerModifiable itemHandlerModifiable) {
                    itemHandlerModifiable.setStackInSlot(7, slot1.get());
                }
            });

            pPlayer.getCooldowns().addCooldown(itemstack.getItem(), 10);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, revolver);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag compound) {
        return new RevolverInventory();
    }

    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> nbt.put("Inventory", ((ItemStackHandler) capability).serializeNBT()));
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        super.readShareTag(stack, nbt);
        if (nbt != null)
            stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> ((ItemStackHandler) capability).deserializeNBT((CompoundTag) nbt.get("Inventory")));
    }
}
