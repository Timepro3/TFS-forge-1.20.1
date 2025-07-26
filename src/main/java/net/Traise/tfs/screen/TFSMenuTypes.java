package net.Traise.tfs.screen;

import net.Traise.tfs.tfs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TFSMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, tfs.MOD_ID);

    public static final RegistryObject<MenuType<ForgeMenu>> FORGE_MENU =
            registerMenuType("forge_menu", ForgeMenu::new);

    public static final RegistryObject<MenuType<RevolverMenu>> REVOLVER_MENU =
            registerMenuType("revolver_menu", RevolverMenu::new);

    public static final RegistryObject<MenuType<FoundryMenu>> FOUNDRY_MENU =
            registerMenuType("foundry_menu", FoundryMenu::new);



    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
