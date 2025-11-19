package net.Traise.tfs.util;

import net.Traise.tfs.tfs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.jar.Attributes;

public class TFSAttributes extends Attributes{
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, tfs.MOD_ID);

    public static final RegistryObject<Attribute> PLAYER_RANGE = ATTRIBUTES.register("player_range",
            () -> new RangedAttribute("attribute.tfs.player_range", 5D, 0D, 1200D));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
