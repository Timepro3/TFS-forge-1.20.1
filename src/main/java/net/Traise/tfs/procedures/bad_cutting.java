package net.Traise.tfs.procedures;

import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class bad_cutting {
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        execute(null,  world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TFSItems.ROCK.get()) {
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.ROCK.get()) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(TFSItems.PIK_ROCK_AXE.get()).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            } else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.PIK_ROCK_AXE.get()) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(TFSItems.PIK_ROCK_PICK.get()).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            }else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.PIK_ROCK_PICK.get()) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(TFSItems.PIK_ROCK_SPEAR.get()).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            }else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.PIK_ROCK_SPEAR.get()) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(TFSItems.PIK_ROCK_SHOVEL.get()).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            }else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.PIK_ROCK_SHOVEL.get()) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(TFSItems.PIK_ROCK_HOE.get()).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            }else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.PIK_ROCK_HOE.get()) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(Items.GRAVEL).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            }
        }else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("tfs:knife")))) {
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.STICK) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(TFSItems.POLE.get()).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                {
                    ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
                    if (_ist.hurt(1, RandomSource.create(), null)) {
                        _ist.shrink(1);
                        _ist.setDamageValue(0);
                    }
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            }else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.TWIG.get()) {
                (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(Items.STICK).copy();
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                {
                    ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
                    if (_ist.hurt(1, RandomSource.create(), null)) {
                        _ist.shrink(1);
                        _ist.setDamageValue(0);
                    }
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                    }
                }
            }
        }else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TFSItems.GRASS_CUT.get()) {
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TFSItems.GRASS_CUT.get()) {
                if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount() >= 2) {
                    (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(2);
                    (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
                    if (entity instanceof Player _player) {
                        ItemStack _setstack = new ItemStack(TFSItems.GRASS_ROPE.get()).copy();
                        _setstack.setCount(1);
                        ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                    }
                } else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount() >= 2) {
                    (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                    (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(2);
                    if (entity instanceof Player _player) {
                        ItemStack _setstack = new ItemStack(TFSItems.GRASS_ROPE.get()).copy();
                        _setstack.setCount(1);
                        ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                    }
                }
                if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount() + (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount() != 2) {
                    if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                            _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 1);
                        } else {
                            _level.playLocalSound(x, y, z, ModSounds.ROCK.get(), SoundSource.NEUTRAL, 2, 0, false);
                        }
                    }
                }
            }
        }
    }
}
