package dev.padjokej.world.gen;

import dev.padjokej.world.WorldPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class WorldGenerator {
    public static void generateShimmerPlants() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DEEP_DARK), GenerationStep.Decoration.UNDERGROUND_DECORATION,
                WorldPlacedFeatures.ZAUN_FLOWER_PATCH_PLACED_KEY);
    }
}
