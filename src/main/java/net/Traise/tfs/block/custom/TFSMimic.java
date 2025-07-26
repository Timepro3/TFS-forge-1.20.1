package net.Traise.tfs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TFSMimic extends Block {
    public TFSMimic(Properties pProperties) {
        super(pProperties.randomTicks());
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        world.setBlock(BlockPos.containing(x, y, z), (world.getBlockState(BlockPos.containing(x, y - 1, z))), 3);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        updates(world, pos);
        world.setBlock(BlockPos.containing(x, y, z), (world.getBlockState(BlockPos.containing(x, y - 1, z))), 3);
    }

    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        updates(world, pos);
        world.setBlock(BlockPos.containing(x, y, z), (world.getBlockState(BlockPos.containing(x, y - 1, z))), 3);
    }

    public void updates(Level world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        for (int Y = -1; Y <= 1; Y++) {
            for (int X = -1; X <= 1; X++) {
                for (int Z = -1; Z <= 1; Z++) {
                    if (!(Y == 0 && X == 0 && Z == 0)) {
                        world.updateNeighborsAt(BlockPos.containing(x + X, y + Y, z + Z), world.getBlockState(BlockPos.containing(x + X, y + Y, z + Z)).getBlock());

                    }
                }
            }
        }
        world.setBlock(BlockPos.containing(x, y, z), (world.getBlockState(BlockPos.containing(x, y - 1, z))), 3);
    }
}
