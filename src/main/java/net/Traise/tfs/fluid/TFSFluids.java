package net.Traise.tfs.fluid;

import com.simibubi.create.AllFluids;
import net.Traise.tfs.block.TFSBlocks;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.tfs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class TFSFluids extends AllFluids{
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, tfs.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_IRON = FLUIDS.register("iron",
            () -> new ForgeFlowingFluid.Source(TFSFluids.IRON_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_IRON = FLUIDS.register("flowing_iron",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.IRON_PROPERTIES));

    public static final ForgeFlowingFluid.Properties IRON_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.IRON_TYPE, SOURCE_IRON, FLOWING_IRON)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(TFSBlocks.FLUID_IRON_BLOCK)
            .bucket(TFSItems.BUCKET_OF_IRON);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
