package net.Traise.tfs.tools.toolItem;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.util.ModTags;
import net.Traise.tfs.worldgen.biome.ModBiomes;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EmptyMapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import javax.print.attribute.standard.Destination;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TFSGeologicalHammerTool extends TFSBaseItem {
    public TFSGeologicalHammerTool(Properties pProperties) {
        super(ModTags.Blocks.STONE, 0, 0, -2, ToolActions.DEFAULT_SWORD_ACTIONS, pProperties);
    }

    @Override
    public boolean canAction(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isDigger(ItemStack item) {
        return false;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Level pLevel = context.getLevel();
        Player player = context.getPlayer();
        BlockPos clickPos = context.getClickedPos();
        ItemStack offStack = player.getItemInHand(InteractionHand.OFF_HAND);

        if (context.getLevel() instanceof  ServerLevel serverLevel ) {
            if (offStack.getItem() instanceof EmptyMapItem) {
                BlockPos blockpos = findBiome(serverLevel, clickPos, 500 * (MaterialStorageHandler.getMineLevel(context.getItemInHand()) + 1));
                if (!player.isCreative()) context.getItemInHand().hurtAndBreak(4, player, (p_41007_) -> p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                if (blockpos != null) {
                    if (!player.isCreative()) offStack.shrink(1);
                    ItemStack map = MapItem.create(serverLevel, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
                    MapItem.renderBiomePreviewMap(serverLevel, map);
                    MapItemSavedData.addTargetDecoration(map, blockpos, "+", MapDecoration.Type.RED_X);
                    ItemHandlerHelper.giveItemToPlayer(player, map);
                    player.displayClientMessage(Component.translatable("geological.tfs.ore_recorder"), true);
                } else player.displayClientMessage(Component.translatable("geological.tfs.ore_not_found"), true);

            } else player.displayClientMessage(Component.translatable("geological.tfs.take_the_cart"), true);
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    public BlockPos findBiome(ServerLevel serverLevel, BlockPos startPos, int radius) {
        Registry<Biome> biomeRegistry = serverLevel.getServer().registryAccess().registryOrThrow(Registries.BIOME);
        HolderSet<Biome> biomesInTag = (HolderSet<Biome>) biomeRegistry.getTagOrEmpty(ModTags.Biomes.ORE);

        Holder<Biome> biome = biomesInTag.get(Mth.nextInt(RandomSource.create(), 0, biomesInTag.size() - 1));

        Pair<BlockPos, Holder<Biome>> pair = serverLevel.findClosestBiome3d(holder -> {
            return holder.equals(biome);

        }, startPos, radius, 32, 64);
        if (pair != null) {
            if (pair.getFirst() != null) {
                return pair.getFirst();
            }
        }
        return null;
    }



        /*if (entity.isShiftKeyDown()) {
            if (pLevel instanceof ServerLevel serverLevel) {
                Registry<Biome> biomeRegistry = serverLevel.getServer().registryAccess().registryOrThrow(Registries.BIOME);
                HolderSet<Biome> biomesInTag = (HolderSet<Biome>) biomeRegistry.getTagOrEmpty(ModTags.Biomes.ORE);
                //int distance = -1;
                //Pair<BlockPos, Holder<Biome>> closestPair = null;
                BlockPos startPos = blockpos;

                Pair<BlockPos, Holder<Biome>> pair = serverLevel.findClosestBiome3d(holder -> {

                    return holder.equals(biomesInTag);
                }, startPos, /*(MaterialStorageHandler.getMineLevel(pStack) + 1) * 250 6000, 32, 64);

                /*if (pair != null) {
                    int currentDist = (int) Math.sqrt(startPos.distSqr(pair.getFirst()));
                    if (distance == -1 || currentDist < distance) {
                        distance = currentDist;
                        closestPair = pair;
                    }
                }

                for (Holder<Biome> biomeHolder : biomesInTag) {

                    Pair<BlockPos, Holder<Biome>> pair = serverLevel.findClosestBiome3d(holder -> {

                        return holder.equals(biomeHolder);
                    }, startPos, (MaterialStorageHandler.getMineLevel(pStack) + 1) * 250, 32, 64);

                    if (pair != null) {
                        int currentDist = (int) Math.sqrt(startPos.distSqr(pair.getFirst()));
                        if (distance == -1 || currentDist < distance) {
                            distance = currentDist;
                            closestPair = pair;
                        }
                    }
                }

                if (pair != null) {
                    int g = (int) Math.sqrt(blockpos.distSqr(new BlockPos(pair.getFirst().getX(), blockpos.getY(), pair.getFirst().getZ())));
                    entity.displayClientMessage(Component.translatable("geological.tfs.ore_recorded"), true);
                    pStack.getOrCreateTag().putBoolean("coordinate", true);
                    pStack.getOrCreateTag().putInt("coordinateX", pair.getFirst().getX());
                    pStack.getOrCreateTag().putInt("coordinateZ", pair.getFirst().getZ());
                    pStack.getOrCreateTag().putInt("last_coordinate", 0);

                } else {
                    entity.displayClientMessage(Component.translatable("geological.tfs.no_ore_found"), true);
                    pStack.getOrCreateTag().putBoolean("coordinate", false);
                    pStack.getOrCreateTag().putInt("last_coordinate", 0);
                }

            }
        } else {
            if (pStack.getOrCreateTag().getBoolean("coordinate")) {
                int g = (int) Math.sqrt(blockpos.distSqr(new BlockPos(pStack.getOrCreateTag().getInt("coordinateX"), blockpos.getY(), pStack.getOrCreateTag().getInt("coordinateZ"))));

                if (pStack.getOrCreateTag().getInt("last_coordinate") == 0 && g > 10) {
                    if (g > 30) {
                        entity.displayClientMessage(Component.translatable("geological.tfs.cold"), true);
                    } else {
                        entity.displayClientMessage(Component.translatable("geological.tfs.warm"), true);
                    }
                    pStack.getOrCreateTag().putInt("last_coordinate", g);
                } else if (g <= 10) {
                    entity.displayClientMessage(Component.translatable("geological.tfs.ore_found"), true);
                    pStack.getOrCreateTag().putInt("last_coordinate", 0);
                } else if (g <= pStack.getOrCreateTag().getInt("last_coordinate")) {
                    entity.displayClientMessage(Component.translatable("geological.tfs.warmer"), true);
                    pStack.getOrCreateTag().putInt("last_coordinate", g);
                } else {
                    entity.displayClientMessage(Component.translatable("geological.tfs.colder"), true);
                }


            } else entity.displayClientMessage(Component.translatable("geological.tfs.ore_not_recorded"), true);
        }

        entity.getCooldowns().addCooldown(this, 30);
        if (!entity.isCreative()) {
            pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return InteractionResult.CONSUME;
    }*/
}