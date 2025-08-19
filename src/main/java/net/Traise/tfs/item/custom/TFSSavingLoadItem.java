package net.Traise.tfs.item.custom;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.level.LevelEvent.*;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber
public class TFSSavingLoadItem extends Item {
    public TFSSavingLoadItem(Properties pProperties) {
        super(pProperties);
    }

    @SubscribeEvent
    public static void onWorldLoad(Load event) {

    }

    @SubscribeEvent
    public static void onWorldUnload(Unload event) {

    }

    public void load(ItemStack itemStack) {
        for (int i = 0; i < 300; i++) {
            System.out.println("Привет");
        }
    }

    public void save(ItemStack itemStack) {
        for (int i = 0; i < 300; i++) {
            System.out.println("Пока");
        }
    }

}
