package net.Traise.tfs;

import net.Traise.tfs.entity.renderer.SilverBulletRenderer;
import net.Traise.tfs.entity.renderer.SteelBulletRenderer;
import net.Traise.tfs.entity.skins.alise.AliseRenderer;
import net.Traise.tfs.entity.skins.tatar.TatarRenderer;
import net.Traise.tfs.fluid.TFSFluidTypes;
import net.Traise.tfs.fluid.TFSFluids;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.potion.ModMobEffect;
import net.Traise.tfs.recipe.TFSRecipes;
import net.Traise.tfs.screen.*;
import net.Traise.tfs.util.TFSAttributes;
import net.Traise.tfs.worldgen.biome.ModTerrablender;
import net.Traise.tfs.worldgen.biome.surface.ModSurfaceRules;
import net.Traise.tfs.entity.renderer.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import com.mojang.logging.LogUtils;
import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.block.entity.TFSBlockEntity;
import net.Traise.tfs.entity.ModEntities;
import net.Traise.tfs.item.TFSCreativeModTabs;
import net.Traise.tfs.sound.ModSounds;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import org.slf4j.Logger;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.SurfaceRuleManager;


import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.antlr.runtime.debug.DebugEventListener.PROTOCOL_VERSION;

@Mod(tfs.MOD_ID)
public class tfs {
    public static tfs instance;
    public static final String MOD_ID = "tfs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public tfs(FMLJavaModLoadingContext context) {
        instance = this;

        IEventBus modEventBus = context.getModEventBus();

        TFSCreativeModTabs.register(modEventBus);
        TFSMenuTypes.register(modEventBus);

        TFSItems.register(modEventBus);
        TFSBlocks.register(modEventBus);

        TFSFluids.register(modEventBus);
        TFSFluidTypes.register(modEventBus);

        TFSBlockEntity.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModMobEffect.register(modEventBus);
        ModTerrablender.registerBiomes();

        TFSRecipes.register(modEventBus);
        TFSAttributes.register(modEventBus);

        //modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

   // private void commonSetup(final FMLCommonSetupEvent event) {
    //    event.enqueueWork(() -> {
    //        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
     //   });
    //}

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(TFSItems.GRASS_CUT);
            event.accept(TFSItems.GRASS_ROPE);
        }

    }

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.SPEAR.get(), SpearRenderer::new);
            EntityRenderers.register(ModEntities.WOOD_SPEAR.get(), WoodSpearRenderer::new);
            EntityRenderers.register(ModEntities.ROCK_SPEAR.get(), RockSpearRenderer::new);
            EntityRenderers.register(ModEntities.IRON_SPEAR.get(), IronSpearRenderer::new);
            EntityRenderers.register(ModEntities.SILVER_SPEAR.get(), SilverSpearRenderer::new);
            EntityRenderers.register(ModEntities.GOLD_SPEAR.get(), GoldSpearRenderer::new);
            EntityRenderers.register(ModEntities.BRONZE_SPEAR.get(), BronzeSpearRenderer::new);
            EntityRenderers.register(ModEntities.STEEL_SPEAR.get(), SteelSpearRenderer::new);
            EntityRenderers.register(ModEntities.DIAMOND_SPEAR.get(), DiamondSpearRenderer::new);
            EntityRenderers.register(ModEntities.NETHERITE_SPEAR.get(), NetheriteSpearRenderer::new);

            EntityRenderers.register(ModEntities.SILVER_BULLET.get(), SilverBulletRenderer::new);
            EntityRenderers.register(ModEntities.STEEL_BULLET.get(), SteelBulletRenderer::new);

            EntityRenderers.register(ModEntities.ALISE.get(), AliseRenderer::new);
            EntityRenderers.register(ModEntities.TATAR.get(), TatarRenderer::new);

            MenuScreens.register(TFSMenuTypes.CUTTING_MENU.get(), CuttingScreen::new);
            MenuScreens.register(TFSMenuTypes.FOUNDRY_MENU.get(), FoundryScreen::new);
            MenuScreens.register(TFSMenuTypes.REVOLVER_MENU.get(), RevolverScreen::new);
        }
    }
}
