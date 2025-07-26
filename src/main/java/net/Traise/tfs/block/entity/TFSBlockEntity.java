package net.Traise.tfs.block.entity;

import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.tfs;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TFSBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, tfs.MOD_ID);

    public static final RegistryObject<BlockEntityType<ForgeBlockEntity>> FORGE_BE =
            BLOCK_ENTITIES.register("forge_be", () ->
                    BlockEntityType.Builder.of(ForgeBlockEntity::new,
                            TFSBlocks.FORGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<FoundryBlockEntity>> FOUNDRY_BE =
            BLOCK_ENTITIES.register("foundry_be", () ->
                    BlockEntityType.Builder.of(FoundryBlockEntity::new,
                            TFSBlocks.FOUNDRY.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}