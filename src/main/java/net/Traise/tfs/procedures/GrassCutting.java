package net.Traise.tfs.procedures;

import net.Traise.tfs.item.TFSItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class GrassCutting {
    public static void execute(LevelAccessor world, double x, double y, double z, ItemStack pStack) {
        if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FERN || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.GRASS
                || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.TALL_GRASS || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.LARGE_FERN) {
            if (world instanceof ServerLevel _level) {
                ItemEntity entityToSpawn = new ItemEntity(_level, x + 0.5f, y + 0.5f, z + 0.5f, new ItemStack(TFSItems.GRASS_CUT.get()));
                entityToSpawn.setPickUpDelay(0);
                _level.addFreshEntity(entityToSpawn);
            }
            {
                ItemStack _ist = pStack;
                if (_ist.hurt(1, RandomSource.create(), null)) {
                    _ist.shrink(1);
                    _ist.setDamageValue(0);
                }
            }

        }
    }
}
