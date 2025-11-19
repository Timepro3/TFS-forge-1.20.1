package net.Traise.tfs.commands;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.Traise.tfs.tfs;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.FillCommand;
import net.minecraft.server.commands.PlaySoundCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.IOException;

@Mod.EventBusSubscriber
public class TestCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("tfs")
                .then(Commands.literal("yy").executes(arguments -> {
                    Level world = arguments.getSource().getUnsidedLevel();

                    double x = arguments.getSource().getPosition().x();
                    double y = arguments.getSource().getPosition().y();
                    double z = arguments.getSource().getPosition().z();

                    Entity entity = arguments.getSource().getEntity();
                    if (entity == null && world instanceof ServerLevel _servLevel)
                        entity = FakePlayerFactory.getMinecraft(_servLevel);

                    Direction direction = Direction.DOWN;
                    if (entity != null)
                        direction = entity.getDirection();

                    File folder = new File("rue");

                    File[] files = folder.listFiles();

                    for (File file : files) {
                        if (file.isFile()) {
                            System.out.println(file.getName());
                            arguments.getSource().sendFailure(Component.literal("Ау"));
                        }
                    }


                    return 0;
                })));

    }

    private static LiteralArgumentBuilder<CommandSourceStack> source(SoundSource pCategory) {
        return Commands.literal(pCategory.getName());
    }
}
