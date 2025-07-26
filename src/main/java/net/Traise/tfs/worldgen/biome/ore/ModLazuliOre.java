package net.Traise.tfs.worldgen.biome.ore;

import com.mojang.datafixers.util.Pair;
import net.Traise.tfs.worldgen.biome.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils.*;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class ModLazuliOre extends Region {
    public ModLazuliOre(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterPointListBuilder()
                .temperature(Temperature.WARM)
                .humidity(Humidity.FULL_RANGE)
                .continentalness(Continentalness.FULL_RANGE)
                .erosion(Erosion.EROSION_6)
                .depth(Climate.Parameter.span(0.2F, 0.9F))
                .weirdness(Climate.Parameter.point(0.0F))
                .build().forEach(point -> builder.add(point, ModBiomes.LAZULI_DEPOSIT));
        builder.build().forEach(mapper::accept);
    }

}
