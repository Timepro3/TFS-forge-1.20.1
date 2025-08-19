package net.Traise.tfs.fluid;

import com.jozufozu.flywheel.api.Material;
import com.simibubi.create.AllFluids;
import net.Traise.tfs.tfs;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

import java.awt.*;

public class TFSFluidTypes {
    public static final ResourceLocation METAL_STILL_RL = new ResourceLocation(tfs.MOD_ID,"block/metal_still");
    public static final ResourceLocation METAL_FLOWING_RL = new ResourceLocation(tfs.MOD_ID,"block/metal_flow");
    public static final ResourceLocation IRON_OVERLAY_RL = new ResourceLocation(tfs.MOD_ID, "block/iron_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, tfs.MOD_ID);

    public static final RegistryObject<FluidType> IRON_TYPE = FLUID_TYPES.register("iron_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xfff71f00,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> COPPER_TYPE = FLUID_TYPES.register("copper_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xffff6700,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> ZINC_TYPE = FLUID_TYPES.register("zinc_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xff78b796,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> TIN_TYPE = FLUID_TYPES.register("tin_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xffc6f5ff,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> GOLD_TYPE = FLUID_TYPES.register("gold_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xffffe026,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> SILVER_TYPE = FLUID_TYPES.register("silver_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xfff8e2ff,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> BRONZE_TYPE = FLUID_TYPES.register("bronze_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xffc5860e,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> BRASS_TYPE = FLUID_TYPES.register("brass_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xffffbb00,
                    new Vector3f(255f / 255f, 255f / 255f, 187f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> STEEL_TYPE = FLUID_TYPES.register("steel_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xff9f9f9f,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> CAST_IRON_TYPE = FLUID_TYPES.register("cast_iron_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xffd7b386,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static final RegistryObject<FluidType> CARBON_TYPE = FLUID_TYPES.register("carbon_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xff111111,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1).sound(SoundActions.BUCKET_EMPTY, SoundEvents.FIRE_EXTINGUISH)));

    public static final RegistryObject<FluidType> UNKNOWN_METAL_TYPE = FLUID_TYPES.register("unknown_metal_type",
            () -> new BaseFluidType(METAL_STILL_RL, METAL_FLOWING_RL, IRON_OVERLAY_RL, 0xff443932,
                    new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(8).viscosity(1)));

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}