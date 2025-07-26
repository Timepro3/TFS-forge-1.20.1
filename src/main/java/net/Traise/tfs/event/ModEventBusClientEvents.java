package net.Traise.tfs.event;

import net.Traise.tfs.entity.model.*;
import net.Traise.tfs.entity.skins.alise.AliseModel;
import net.Traise.tfs.entity.skins.tatar.TatarModel;
import net.Traise.tfs.tfs;
import net.minecraft.client.model.HumanoidModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = tfs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ROCK_SPEAR_LAYER, RockSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WOOD_SPEAR_LAYER, WoodSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.IRON_SPEAR_LAYER, IronSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SILVER_SPEAR_LAYER, SilverSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GOLD_SPEAR_LAYER, GoldSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BRONZE_SPEAR_LAYER, BronzeSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.STEEL_SPEAR_LAYER, SteelSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DIAMOND_SPEAR_LAYER, DiamondSpearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.NETHERITE_SPEAR_LAYER, NetheriteSpearModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.SILVER_BULLET_LAYER, SilverBulletModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.STEEL_BULLET_LAYER, SteelBulletModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.ALISE_LAYER, AliseModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TATAR_LAYER, TatarModel::createBodyLayer);
    }

}