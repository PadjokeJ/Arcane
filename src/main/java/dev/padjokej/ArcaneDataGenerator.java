package dev.padjokej;

import dev.padjokej.datagen.ModelGenerator;
import dev.padjokej.datagen.RegistryProvider;
import dev.padjokej.shimmer.worldgen.ShimmerWorldConfiguredFeatures;
import dev.padjokej.shimmer.worldgen.ShimmerWorldPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ArcaneDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModelGenerator::new);
        pack.addProvider(RegistryProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ShimmerWorldConfiguredFeatures::configure);
        registryBuilder.add(Registries.PLACED_FEATURE, ShimmerWorldPlacedFeatures::configure);
    }
}
