package net.Traise.tfs.util;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.Traise.tfs.tfs;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

import com.mojang.blaze3d.platform.TextureUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.texture.DynamicTexture;

import javax.imageio.ImageIO;

@Mod(tfs.MOD_ID)
public class PlayerSkinRestorer {

    private static final Map<String, ResourceLocation> loadedSkinLocations = new HashMap<>();

    // Хранит загруженные скины (имя файла -> DynamicTexture)
    private static final Map<String, DynamicTexture> loadedSkins = new HashMap<>();

    // Хранит скин, выбранный для каждого игрока по имени
    private static final Map<String, DynamicTexture> playerSkins = new HashMap<>();

    public PlayerSkinRestorer() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("setplayskin")
                .then(Commands.argument("skinname", StringArgumentType.word())
                        .executes(context -> {
                            String skinName = StringArgumentType.getString(context, "skinname");
                            ServerPlayer player = context.getSource().getPlayerOrException();

                            if (!loadSkin(skinName)) {
                                context.getSource().sendFailure(
                                        net.minecraft.network.chat.Component.literal("Скин " + skinName + " не найден!")
                                );
                                return 0;
                            }

                            playerSkins.put(player.getName().getString(), loadedSkins.get(skinName));

                            context.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal("Скин установлен: " + skinName), true);
                            return 1;
                        })));
    }

    private boolean loadSkin(String skinName) {
        if (loadedSkinLocations.containsKey(skinName)) {
            return true;
        }
        try {
            Path configDir = FMLPaths.CONFIGDIR.get();
            File skinFile = configDir.resolve("player_skins").resolve(skinName + ".png").toFile();
            if (!skinFile.exists()) {
                return false;
            }
            try (InputStream inputStream = new FileInputStream(skinFile)) {
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                NativeImage nativeImage = toNativeImage(bufferedImage);
                DynamicTexture texture = new DynamicTexture(nativeImage);
                ResourceLocation location = new ResourceLocation("tfs", "customskin/" + skinName);
                Minecraft.getInstance().getTextureManager().register(location, texture);
                loadedSkins.put(skinName, texture);
                loadedSkinLocations.put(skinName, location);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Примерный метод для замены скина при рендере (только клиент)
    @Mod.EventBusSubscriber(modid = "tfs", value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
            Minecraft mc = Minecraft.getInstance();
            String playerName = event.getEntity().getName().getString();

            DynamicTexture skinTexture = playerSkins.get(playerName);
            if (skinTexture != null) {
                // Получаем ResourceLocation, если уже находится в текстуре, иначе регистрируем
                String skinName = playerSkins.get(playerName).toString();
                ResourceLocation location = loadedSkinLocations.get(skinName);

                if (location == null) {
                    location = mc.getTextureManager().register("customskin/" + playerName, skinTexture);
                }

                Minecraft.getInstance().getTextureManager().getTexture(location);

                // Альтернативно можно попробовать заменить текстуру напрямую у рендерера
                // Но для Forge больше изучения...
            }
        }
    }

    private NativeImage toNativeImage(BufferedImage img) {
        BufferedImage convertedImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = convertedImg.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        int width = convertedImg.getWidth();
        int height = convertedImg.getHeight();
        NativeImage nativeImage = new NativeImage(width, height, false);

        int[] pixels = ((DataBufferInt) convertedImg.getRaster().getDataBuffer()).getData();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = pixels[y * width + x];

                // В Minecraft NativeImage формат — ABGR, а BufferedImage в ARGB
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int gC = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                int abgr = (a << 24) | (b << 16) | (gC << 8) | r;
                nativeImage.setPixelRGBA(x, y, abgr);
            }
        }

        return nativeImage;
    }
}
