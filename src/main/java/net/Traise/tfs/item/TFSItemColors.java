package net.Traise.tfs.item;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.item.custom.TFSFormItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.Traise.tfs.tfs.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TFSItemColors extends ItemColors {

    @SubscribeEvent
    public static void createColor(RegisterColorHandlersEvent.Item event) {
        event.register(TFSBaseItem::getColor, TFSItems.SWORD.get(), TFSItems.AXE.get(), TFSItems.HOE.get(),
                TFSItems.PICKAXE.get(), TFSItems.SHOVEL.get(), TFSItems.KNIFE.get(), TFSItems.SPEAR.get(),
                TFSItems.HAMMER.get(), TFSItems.SPADE.get(), TFSItems.SICKLE.get(), TFSItems.TROWEL.get(),
                TFSItems.GEOLOGICAL_HAMMER.get(), TFSItems.BUILDER_WAND.get());

        event.register((p_92681_, p_92682_) -> {
            return p_92682_ == 1 ? TFSFormItem.getColor(p_92681_, Minecraft.getInstance().level) : -1;
        }, TFSItems.INGOT_FORM.get());
    }
}
