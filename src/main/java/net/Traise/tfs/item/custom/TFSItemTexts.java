package net.Traise.tfs.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TFSItemTexts extends Item {
    public int Num;
    public int Ml;

    public TFSItemTexts(int number, Properties pProperties) {
        super(pProperties);
        Num = number;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        Ml = itemstack.getCount();
        if (Screen.hasShiftDown()) {
            if (Num == 0) {
                list.add(Component.literal("§7Тестовая надпись 1"));
            } else if (Num == 1) {
                list.add(Component.literal("§7Грубо обработанный камень, по форме напоминающий головку топора"));
            } else if (Num == 2) {
                list.add(Component.literal("§7Грубо обработанный камень, по форме напоминающий головку мотыги"));
            } else if (Num == 3) {
                list.add(Component.literal("§7Грубо обработанный камень, по форме напоминающий наконечник кирки"));
            } else if (Num == 4) {
                list.add(Component.literal("§7Грубо обработанный плоский камень, по форме напоминающий головку лопаты"));
            } else if (Num == 6) {
                list.add(Component.literal("§7Свеже срезанная трава"));
            } else if (Num == 7) {
                list.add(Component.literal("§7Грубая примитивная верёвка"));
            } else if (Num == 8) {
                list.add(Component.literal("§7При переплавке вы получите §cМедь§7, в этой горстке её §9" + 35 * Ml + "§7мл"));
            } else if (Num == 9) {
                list.add(Component.literal("§7При переплавке вы получите §cМедь§7, в этой горстке её §9" + 20 * Ml + "§7мл"));
            } else if (Num == 10) {
                list.add(Component.literal("§7При переплавке вы получите §cМедь§7, в этой горстке её §9" + 15 * Ml + "§7мл"));
            } else if (Num == 11) {
                list.add(Component.literal("§7При переплавке вы получите §cЖелезо§7, в этой горстке его §9" + 35 * Ml + "§7мл"));
            } else if (Num == 12) {
                list.add(Component.literal("§7При переплавке вы получите §cЖелезо§7, в этой горстке его §9" + 20 * Ml + "§7мл"));
            } else if (Num == 13) {
                list.add(Component.literal("§7При переплавке вы получите §cЖелезо§7, в этой горстке его §9" + 15 * Ml + "§7мл"));
            } else if (Num == 14) {
                list.add(Component.literal("§7Необработанный алмаз, отполируйте перед использованием"));
            } else if (Num == 15) {
                list.add(Component.literal("§7Необработанный изумруд, отполируйте перед использованием"));
            } else if (Num == 16) {
                list.add(Component.literal("§7Красивый красный камень, от которого веет энергией"));
            } else if (Num == 17) {
                list.add(Component.literal("§7Необработанный лазурит, отполируйте перед использованием"));
            } else if (Num == 18) {
                list.add(Component.literal("§7При переплавке вы получите §cОлово§7, в этой горстке его §9" + 35 * Ml + "§7мл"));
            } else if (Num == 19) {
                list.add(Component.literal("§7При переплавке вы получите §cОлово§7, в этой горстке его §9" + 20 * Ml + "§7мл"));
            } else if (Num == 20) {
                list.add(Component.literal("§7При переплавке вы получите §cОлово§7, в этой горстке его §9" + 15 * Ml + "§7мл"));
            } else if (Num == 21) {
                list.add(Component.literal("§7При переплавке вы получите §cЦинк§7, в этой горстке его §9" + 35 * Ml + "§7мл"));
            } else if (Num == 22) {
                list.add(Component.literal("§7При переплавке вы получите §cЦинк§7, в этой горстке его §9" + 20 * Ml + "§7мл"));
            } else if (Num == 23) {
                list.add(Component.literal("§7При переплавке вы получите §cЦинк§7, в этой горстке его §9" + 15 * Ml + "§7мл"));
            } else if (Num == 24) {
                list.add(Component.literal("§7При переплавке вы получите §cСеребро§7, в этой горстке его §9" + 35 * Ml + "§7мл"));
            } else if (Num == 25) {
                list.add(Component.literal("§7При переплавке вы получите §cСеребро§7, в этой горстке его §9" + 20 * Ml + "§7мл"));
            } else if (Num == 26) {
                list.add(Component.literal("§7При переплавке вы получите §cСеребро§7, в этой горстке его §9" + 15 * Ml + "§7мл"));
            } else if (Num == 27) {
                list.add(Component.literal("§7При переплавке вы получите §cЗолото§7, в этой горстке его §9" + 35 * Ml + "§7мл"));
            } else if (Num == 28) {
                list.add(Component.literal("§7При переплавке вы получите §cЗолото§7, в этой горстке его §9" + 20 * Ml + "§7мл"));
            } else if (Num == 29) {
                list.add(Component.literal("§7При переплавке вы получите §cЗолото§7, в этой горстке его §9" + 15 * Ml + "§7мл"));
            } else if (Num == 30) {
                list.add(Component.literal("§7Обычная бронзовая гильза, такие часто выбрасывают"));
            } else if (Num == 31) {
                list.add(Component.literal("§7Вблизи наносит урон в§9 8§7хп, за каждые§c 4§7 блока урон увеличивается на§9 1§7хп, вплоть до§9 20§7хп"));
            } else if (Num == 32) {
                list.add(Component.literal("§7Вблизи наносит урон в§9 8§7хп, начиная с§c 2§7 блоков урон падает до§9 5§7хп, поджигает неживых существ"));
            } else if (Num == 33) {
                list.add(Component.literal("§7Лучшее дальнее оружие для дуэлей, гений инженерной мысли"));
            } else if (Num == 34) {
                list.add(Component.literal("§7Держа её в руках вы ощущаете как холодок пробегает по вашему телу, используется для #####"));
            } else if (Num == 35) {
                list.add(Component.literal("§7Положите в сетку крафта для извлечения содержимого"));
            } else if (Num == 36) {
                list.add(Component.literal("§7Серьёзный металл"));
            } else if (Num == 37) {
                list.add(Component.literal("§7Хороший в хозяйстве металл"));
            } else if (Num == 38) {
                list.add(Component.literal("§7Что это?"));
            } else if (Num == 39) {
                list.add(Component.literal("§7Первый сплав"));
            } else if (Num == 40) {
                list.add(Component.literal("§7Довольно дорогая вещь, губительна для не совсем живых"));
            } else if (Num == 41) {
                list.add(Component.literal("§7Сам по себе, бесполезная вещь"));
            }
        } else if (Num > -1){
            list.add(Component.literal("§7<§eSHIFT§7>"));
        }



    }
}
