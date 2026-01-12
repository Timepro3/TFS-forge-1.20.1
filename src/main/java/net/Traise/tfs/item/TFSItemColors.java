package net.Traise.tfs.item;

import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.item.custom.TFSFormItem;
import net.Traise.tfs.tools.TFSPartItem;
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
                TFSItems.HAMMER.get(), TFSItems.SPADE.get(), TFSItems.SICKLE.get(), TFSItems.PAXEL.get(),
                TFSItems.TROWEL.get(), TFSItems.GEOLOGICAL_HAMMER.get(), TFSItems.BUILDER_WAND.get(),
                TFSItems.BOW.get(), TFSItems.HELMET.get(), TFSItems.CHESTPLATE.get(), TFSItems.LEGGINGS.get(),
                TFSItems.BOOTS.get());

        event.register((p_92681_, p_92682_) -> {
            return p_92682_ == 1 ? TFSFormItem.getColor(p_92681_, Minecraft.getInstance().level) : -1;
        }, TFSItems.INGOT_FORM.get(), TFSItems.AXE_FORM.get(), TFSItems.SWORD_FORM.get(),
                TFSItems.HOE_FORM.get(), TFSItems.PICKAXE_FORM.get(), TFSItems.SHOVEL_FORM.get(),
                TFSItems.KNIFE_FORM.get(), TFSItems.SPEAR_FORM.get(), TFSItems.HAMMER_FORM.get(),
                TFSItems.SPADE_FORM.get(), TFSItems.SICKLE_FORM.get(), TFSItems.TROWEL_FORM.get(),
                TFSItems.GEOLOGICAL_HAMMER_FORM.get(), TFSItems.BUILDER_WAND_FORM.get(),
                TFSItems.PAXEL_FORM.get(), TFSItems.STICK_FORM.get(), TFSItems.STRING_FORM.get(),
                TFSItems.BOW_FORM.get());

        event.register((p_92681_, p_92682_) ->  { return TFSPartItem.getColor(p_92681_); }, TFSItems.SWORD_PART.get(), TFSItems.STICK_PART.get(),
                TFSItems.AXE_PART.get(), TFSItems.HOE_PART.get(), TFSItems.STRING_PART.get(), TFSItems.BUILDER_WAND_PART.get(),
                TFSItems.PICKAXE_PART.get(), TFSItems.SHOVEL_PART.get(), TFSItems.KNIFE_PART.get(), TFSItems.SPEAR_PART.get(),
                TFSItems.HAMMER_PART.get(), TFSItems.SPADE_PART.get(), TFSItems.SICKLE_PART.get(), TFSItems.PAXEL_PART.get(),
                TFSItems.TROWEL_PART.get(), TFSItems.GEOLOGICAL_HAMMER_PART.get(), TFSItems.BOW_PART.get());
    }
}
