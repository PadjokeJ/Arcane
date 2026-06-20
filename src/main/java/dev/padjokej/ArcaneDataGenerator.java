package dev.padjokej;

import dev.padjokej.datagen.ArcaneLootTableProvider;
import dev.padjokej.datagen.ArcaneModelGenerator;
import dev.padjokej.datagen.ArcaneRecipeProvider;
import dev.padjokej.datagen.ArcaneRegistryProvider;
import dev.padjokej.datagen.language.ArcaneEnglishProvider;
import dev.padjokej.datagen.language.ArcaneFrenchProvider;
import dev.padjokej.world.WorldConfiguredFeatures;
import dev.padjokej.world.WorldPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ArcaneDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ArcaneModelGenerator::new);
        pack.addProvider(ArcaneRegistryProvider::new);
        pack.addProvider(ArcaneLootTableProvider::new);
        pack.addProvider(ArcaneRecipeProvider::new);

        pack.addProvider(ArcaneEnglishProvider::new);
        pack.addProvider(ArcaneFrenchProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, WorldConfiguredFeatures::configure);
        registryBuilder.add(Registries.PLACED_FEATURE, WorldPlacedFeatures::configure);
    }
}
