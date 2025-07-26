package net.Traise.tfs.item.custom;

import com.simibubi.create.AllItems;
import net.Traise.tfs.item.TFSItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class TFSOldFormItem extends TFSItemTexts {
    public TFSOldFormItem(int number, Properties pProperties) {
        super(number, pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

        if (itemstack.getOrCreateTag().getDouble("QUN") >= 100) {
            int x = itemstack.getOrCreateTag().getInt("CustomModelData");
            ItemStack stack = TFSItems.UNKNOWN_METAL_INGOT.get().getDefaultInstance();
            if (x == 2) {
                stack = Items.COPPER_INGOT.getDefaultInstance();
            } else if (x == 3) {
                stack = Items.IRON_INGOT.getDefaultInstance();
            } else if (x == 4) {
                stack = TFSItems.TIN_INGOT.get().getDefaultInstance();
            } else if (x == 5) {
                stack = AllItems.ZINC_INGOT.get().getDefaultInstance();
            } else if (x == 6) {
                stack = Items.GOLD_INGOT.getDefaultInstance();
            } else if (x == 7) {
                stack = TFSItems.SILVER_INGOT.get().getDefaultInstance();
            } else if (x == 8) {
                stack = TFSItems.BRONZE_INGOT.get().getDefaultInstance();
            } else if (x == 9) {
                stack = AllItems.BRASS_INGOT.get().getDefaultInstance();
            } else if (x == 10) {
                stack = TFSItems.STEEL_INGOT.get().getDefaultInstance();
            } else if (x == 11) {
                stack = TFSItems.CAST_IRON_INGOT.get().getDefaultInstance();
            }

            ItemStack sta = TFSItems.INGOT_FORM.get().getDefaultInstance();


            itemstack.shrink(1);
            ItemHandlerHelper.giveItemToPlayer(pPlayer, stack);
            if (Mth.nextInt(RandomSource.create(), 0, 1) == 1) {
                for (int i = 1; i <= 7; i++){
                    sta.getOrCreateTag().putInt("T" + i, 0);
                }
                sta.getOrCreateTag().putDouble("QUN", 0);
                sta.getOrCreateTag().putInt("CustomModelData", 0);
                sta.getOrCreateTag().putString("componenT", "");

                ItemHandlerHelper.giveItemToPlayer(pPlayer, sta);
            }

        }


        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }

    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        myTick(pStack);
    }

    public static void myTick(ItemStack pStack) {
        double x = 0;
        for (int i = 1;i <= 7; i++) {
            x = x + pStack.getOrCreateTag().getDouble("I" + i);
        }
        for (int i = 1;i <= 7; i++) {
            if (pStack.getOrCreateTag().getDouble("I" + i) < 0) {
                pStack.getOrCreateTag().putDouble("I" + i, 0);
            }
        }
        pStack.getOrCreateTag().putDouble("QUN", x);
        if (5 < (10 * (pStack.getOrCreateTag().getDouble("QUN") - ((int) pStack.getOrCreateTag().getDouble("QUN"))))) {
            pStack.getOrCreateTag().putDouble("QUN", ((int) pStack.getOrCreateTag().getDouble("QUN")) + 1);
        }
        interest(pStack);
        material(pStack);
    }

    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        if (itemstack.getOrCreateTag().getDouble("QUN") != 0) {
            list.add(Component.literal("§2" + itemstack.getOrCreateTag().getString("componenT")));
            list.add(Component.literal("§7" + (int)(itemstack.getOrCreateTag().getDouble("QUN")) + "/100мб"));
        }
        if (Screen.hasShiftDown()) {
            if (itemstack.getOrCreateTag().getInt("T1") != 0) {
                list.add(Component.literal("§7Медь: " + ((float) itemstack.getOrCreateTag().getInt("T1") / 100) + "%"));
            }
            if (itemstack.getOrCreateTag().getInt("T2") != 0) {
                list.add(Component.literal("§7Железо: " + ((float) itemstack.getOrCreateTag().getInt("T2") / 100) + "%"));
            }
            if (itemstack.getOrCreateTag().getInt("T3") != 0) {
                list.add(Component.literal("§7Олово: " + ((float) itemstack.getOrCreateTag().getInt("T3") / 100) + "%"));
            }
            if (itemstack.getOrCreateTag().getInt("T4") != 0) {
                list.add(Component.literal("§7Цинк: " + ((float) itemstack.getOrCreateTag().getInt("T4") / 100) + "%"));
            }
            if (itemstack.getOrCreateTag().getInt("T5") != 0) {
                list.add(Component.literal("§7Золото: " + ((float) itemstack.getOrCreateTag().getInt("T5") / 100) + "%"));
            }
            if (itemstack.getOrCreateTag().getInt("T6") != 0) {
                list.add(Component.literal("§7Серебро: " + ((float) itemstack.getOrCreateTag().getInt("T6") / 100) + "%"));
            }
            if (itemstack.getOrCreateTag().getInt("T7") != 0) {
                list.add(Component.literal("§7Углерод: " + ((float) itemstack.getOrCreateTag().getInt("T7") / 100) + "%"));
            }
        } else if (itemstack.getOrCreateTag().getDouble("QUN") > 0) {
            list.add(Component.literal("§7<§eSHIFT§7>"));
        }
    }

    private static void interest(ItemStack itemstack) {
        if (itemstack.getOrCreateTag().getDouble("QUN") > 0) {
            int u1 = 0;if (((itemstack.getOrCreateTag().getDouble("I1") * 100000) / itemstack.getOrCreateTag().getDouble("QUN")) % 10 > 5) {u1 = 1;}
            int u2 = 0;if (((itemstack.getOrCreateTag().getDouble("I2") * 100000) / itemstack.getOrCreateTag().getDouble("QUN")) % 10 > 5) {u2 = 1;}
            int u3 = 0;if (((itemstack.getOrCreateTag().getDouble("I3") * 100000) / itemstack.getOrCreateTag().getDouble("QUN")) % 10 > 5) {u3 = 1;}
            int u4 = 0;if (((itemstack.getOrCreateTag().getDouble("I4") * 100000) / itemstack.getOrCreateTag().getDouble("QUN")) % 10 > 5) {u4 = 1;}
            int u5 = 0;if (((itemstack.getOrCreateTag().getDouble("I5") * 100000) / itemstack.getOrCreateTag().getDouble("QUN")) % 10 > 5) {u5 = 1;}
            int u6 = 0;if (((itemstack.getOrCreateTag().getDouble("I6") * 100000) / itemstack.getOrCreateTag().getDouble("QUN")) % 10 > 5) {u6 = 1;}
            int u7 = 0;if (((itemstack.getOrCreateTag().getDouble("I7") * 100000) / itemstack.getOrCreateTag().getDouble("QUN")) % 10 > 5) {u7 = 1;}
            itemstack.getOrCreateTag().putInt("T1", (int) ((itemstack.getOrCreateTag().getDouble("I1") * 10000) / itemstack.getOrCreateTag().getDouble("QUN")) + u1);
            itemstack.getOrCreateTag().putInt("T2", (int) ((itemstack.getOrCreateTag().getDouble("I2") * 10000) / itemstack.getOrCreateTag().getDouble("QUN")) + u2);
            itemstack.getOrCreateTag().putInt("T3", (int) ((itemstack.getOrCreateTag().getDouble("I3") * 10000) / itemstack.getOrCreateTag().getDouble("QUN")) + u3);
            itemstack.getOrCreateTag().putInt("T4", (int) ((itemstack.getOrCreateTag().getDouble("I4") * 10000) / itemstack.getOrCreateTag().getDouble("QUN")) + u4);
            itemstack.getOrCreateTag().putInt("T5", (int) ((itemstack.getOrCreateTag().getDouble("I5") * 10000) / itemstack.getOrCreateTag().getDouble("QUN")) + u5);
            itemstack.getOrCreateTag().putInt("T6", (int) ((itemstack.getOrCreateTag().getDouble("I6") * 10000) / itemstack.getOrCreateTag().getDouble("QUN")) + u6);
            itemstack.getOrCreateTag().putInt("T7", (int) ((itemstack.getOrCreateTag().getDouble("I7") * 10000) / itemstack.getOrCreateTag().getDouble("QUN")) + u7);

        }
        else {
            itemstack.getOrCreateTag().putInt("T1", 0);
            itemstack.getOrCreateTag().putInt("T2", 0);
            itemstack.getOrCreateTag().putInt("T3", 0);
            itemstack.getOrCreateTag().putInt("T4", 0);
            itemstack.getOrCreateTag().putInt("T5", 0);
            itemstack.getOrCreateTag().putInt("T6", 0);
            itemstack.getOrCreateTag().putInt("T7", 0);
        }
    }

    private static void material(ItemStack itemstack) {
        itemstack.getOrCreateTag().putString("componenT", "Неизвестный металл");
        itemstack.getOrCreateTag().putInt("CustomModelData", 1);
        if (itemstack.getOrCreateTag().getDouble("QUN") <= 0) {
            itemstack.getOrCreateTag().putString("componenT", "");
            itemstack.getOrCreateTag().putInt("CustomModelData", 0);
        }
        if (itemstack.getOrCreateTag().getInt("T1") >= 9500) {
            itemstack.getOrCreateTag().putString("componenT", "Медь");
            itemstack.getOrCreateTag().putInt("CustomModelData", 2);
        } if (itemstack.getOrCreateTag().getInt("T2") >= 9500) {
            itemstack.getOrCreateTag().putString("componenT", "Железо");
            itemstack.getOrCreateTag().putInt("CustomModelData", 3);
        } if (itemstack.getOrCreateTag().getInt("T3") >= 9500) {
            itemstack.getOrCreateTag().putString("componenT", "Олово");
            itemstack.getOrCreateTag().putInt("CustomModelData", 4);
        } if (itemstack.getOrCreateTag().getInt("T4") >= 9500) {
            itemstack.getOrCreateTag().putString("componenT", "Цинк");
            itemstack.getOrCreateTag().putInt("CustomModelData", 5);
        } if (itemstack.getOrCreateTag().getInt("T5") >= 9500) {
            itemstack.getOrCreateTag().putString("componenT", "Золото");
            itemstack.getOrCreateTag().putInt("CustomModelData", 6);
        } if (itemstack.getOrCreateTag().getInt("T6") >= 9500) {
            itemstack.getOrCreateTag().putString("componenT", "Серебро");
            itemstack.getOrCreateTag().putInt("CustomModelData", 7);
        } if (itemstack.getOrCreateTag().getInt("T1") >= 8500 && itemstack.getOrCreateTag().getInt("T3") >= 500) {
            itemstack.getOrCreateTag().putString("componenT", "Бронза");
            itemstack.getOrCreateTag().putInt("CustomModelData", 8);
        } if (itemstack.getOrCreateTag().getInt("T1") >= 6500 && itemstack.getOrCreateTag().getInt("T4") >= 2500) {
            itemstack.getOrCreateTag().putString("componenT", "Латунь");
            itemstack.getOrCreateTag().putInt("CustomModelData", 9);
        } if (itemstack.getOrCreateTag().getInt("T2") >= 8500 && itemstack.getOrCreateTag().getInt("T7") > 500) {
            itemstack.getOrCreateTag().putString("componenT", "Сталь");
            itemstack.getOrCreateTag().putInt("CustomModelData", 10);
        } if (itemstack.getOrCreateTag().getInt("T2") >= 7500 && itemstack.getOrCreateTag().getInt("T7") > 1500) {
            itemstack.getOrCreateTag().putString("componenT", "Чугун");
            itemstack.getOrCreateTag().putInt("CustomModelData", 11);
        }
    }
}