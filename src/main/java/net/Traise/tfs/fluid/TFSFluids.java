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

public class TFSFluids{
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, tfs.MOD_ID);

    public static final RegistryObject<FlowingFluid> IRON = FLUIDS.register("iron",
            () -> new ForgeFlowingFluid.Source(TFSFluids.IRON_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_IRON = FLUIDS.register("flowing_iron",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.IRON_PROPERTIES));

    public static final RegistryObject<FlowingFluid> COPPER = FLUIDS.register("copper",
            () -> new ForgeFlowingFluid.Source(TFSFluids.COPPER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_COPPER = FLUIDS.register("flowing_copper",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.COPPER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> ZINC = FLUIDS.register("zinc",
            () -> new ForgeFlowingFluid.Source(TFSFluids.ZINC_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_ZINC = FLUIDS.register("flowing_zinc",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.ZINC_PROPERTIES));

    public static final RegistryObject<FlowingFluid> TIN = FLUIDS.register("tin",
            () -> new ForgeFlowingFluid.Source(TFSFluids.TIN_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_TIN = FLUIDS.register("flowing_tin",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.TIN_PROPERTIES));

    public static final RegistryObject<FlowingFluid> GOLD = FLUIDS.register("gold",
            () -> new ForgeFlowingFluid.Source(TFSFluids.GOLD_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_GOLD = FLUIDS.register("flowing_gold",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.GOLD_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SILVER = FLUIDS.register("silver",
            () -> new ForgeFlowingFluid.Source(TFSFluids.SILVER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SILVER = FLUIDS.register("flowing_silver",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.SILVER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> BRONZE = FLUIDS.register("bronze",
            () -> new ForgeFlowingFluid.Source(TFSFluids.BRONZE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BRONZE = FLUIDS.register("flowing_bronze",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.BRONZE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> BRASS = FLUIDS.register("brass",
            () -> new ForgeFlowingFluid.Source(TFSFluids.BRASS_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BRASS = FLUIDS.register("flowing_brass",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.BRASS_PROPERTIES));

    public static final RegistryObject<FlowingFluid> STEEL = FLUIDS.register("steel",
            () -> new ForgeFlowingFluid.Source(TFSFluids.STEEL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_STEEL = FLUIDS.register("flowing_steel",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.STEEL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CAST_IRON = FLUIDS.register("cast_iron",
            () -> new ForgeFlowingFluid.Source(TFSFluids.CAST_IRON_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CAST_IRON = FLUIDS.register("flowing_cast_iron",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.CAST_IRON_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CARBON = FLUIDS.register("carbon",
            () -> new ForgeFlowingFluid.Source(TFSFluids.CARBON_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CARBON = FLUIDS.register("flowing_carbon",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.CARBON_PROPERTIES));

    public static final RegistryObject<FlowingFluid> UNKNOWN_METAL = FLUIDS.register("unknown_metal",
            () -> new ForgeFlowingFluid.Source(TFSFluids.UNKNOWN_METAL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_UNKNOWN_METAL = FLUIDS.register("flowing_unknown_metal",
            () -> new ForgeFlowingFluid.Flowing(TFSFluids.UNKNOWN_METAL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties IRON_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.IRON_TYPE, IRON, FLOWING_IRON)
            .slopeFindDistance(2).levelDecreasePerBlock(1).tickRate(35).block(TFSBlocks.FLUID_IRON_BLOCK)
            .bucket(TFSItems.BUCKET_OF_IRON);

    public static final ForgeFlowingFluid.Properties COPPER_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.COPPER_TYPE, COPPER, FLOWING_COPPER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(40).block(TFSBlocks.FLUID_COPPER_BLOCK)
            .bucket(TFSItems.BUCKET_OF_COPPER);

    public static final ForgeFlowingFluid.Properties ZINC_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.ZINC_TYPE, ZINC, FLOWING_ZINC)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(30).block(TFSBlocks.FLUID_ZINC_BLOCK)
            .bucket(TFSItems.BUCKET_OF_ZINC);

    public static final ForgeFlowingFluid.Properties TIN_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.TIN_TYPE, TIN, FLOWING_TIN)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(35).block(TFSBlocks.FLUID_TIN_BLOCK)
            .bucket(TFSItems.BUCKET_OF_TIN);

    public static final ForgeFlowingFluid.Properties GOLD_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.GOLD_TYPE, GOLD, FLOWING_GOLD)
            .slopeFindDistance(2).levelDecreasePerBlock(3).tickRate(85).block(TFSBlocks.FLUID_GOLD_BLOCK)
            .bucket(TFSItems.BUCKET_OF_GOLD);

    public static final ForgeFlowingFluid.Properties SILVER_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.SILVER_TYPE, SILVER, FLOWING_SILVER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(45).block(TFSBlocks.FLUID_SILVER_BLOCK)
            .bucket(TFSItems.BUCKET_OF_SILVER);

    public static final ForgeFlowingFluid.Properties BRONZE_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.BRONZE_TYPE, BRONZE, FLOWING_BRONZE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(45).block(TFSBlocks.FLUID_BRONZE_BLOCK)
            .bucket(TFSItems.BUCKET_OF_BRONZE);

    public static final ForgeFlowingFluid.Properties BRASS_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.BRASS_TYPE, BRASS, FLOWING_BRASS)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(43).block(TFSBlocks.FLUID_BRASS_BLOCK)
            .bucket(TFSItems.BUCKET_OF_BRASS);

    public static final ForgeFlowingFluid.Properties STEEL_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.STEEL_TYPE, STEEL, FLOWING_STEEL)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(37).block(TFSBlocks.FLUID_STEEL_BLOCK)
            .bucket(TFSItems.BUCKET_OF_STEEL);

    public static final ForgeFlowingFluid.Properties CAST_IRON_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.CAST_IRON_TYPE, CAST_IRON, FLOWING_CAST_IRON)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(35).block(TFSBlocks.FLUID_CAST_IRON_BLOCK)
            .bucket(TFSItems.BUCKET_OF_CAST_IRON);

    public static final ForgeFlowingFluid.Properties CARBON_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.CARBON_TYPE, CARBON, FLOWING_CARBON)
            .slopeFindDistance(2).levelDecreasePerBlock(1).tickRate(1).block(TFSBlocks.FLUID_CARBON_BLOCK)
            .bucket(TFSItems.BUCKET_OF_CARBON);

    public static final ForgeFlowingFluid.Properties UNKNOWN_METAL_PROPERTIES = new ForgeFlowingFluid.Properties(
            TFSFluidTypes.UNKNOWN_METAL_TYPE, UNKNOWN_METAL, FLOWING_UNKNOWN_METAL)
            .slopeFindDistance(2).levelDecreasePerBlock(1).tickRate(5).block(TFSBlocks.FLUID_UNKNOWN_METAL_BLOCK)
            .bucket(TFSItems.BUCKET_OF_UNKNOWN_METAL);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
