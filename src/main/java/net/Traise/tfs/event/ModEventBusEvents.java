package net.Traise.tfs.event;

import net.Traise.tfs.entity.ModEntities;
import net.Traise.tfs.entity.model.*;
import net.Traise.tfs.entity.skins.alise.AliseEntity;
import net.Traise.tfs.entity.skins.tatar.TatarEntity;
import net.Traise.tfs.tfs;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = tfs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ALISE.get(), AliseEntity.createAttributes().build());
        event.put(ModEntities.TATAR.get(), TatarEntity.createAttributes().build());
    }
}
