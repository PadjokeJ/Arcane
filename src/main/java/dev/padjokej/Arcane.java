package dev.padjokej;

import com.jcraft.jorbis.Block;
import dev.padjokej.registry.ModBlocks;
import dev.padjokej.registry.ModItems;
import dev.padjokej.shimmer.worldgen.ShimmerWorldGenerator;
import dev.padjokej.shimmer.worldgen.ShimmerWorldPlacedFeatures;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Arcane implements ModInitializer {
	public static final String MOD_ID = "arcane";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String id) {
		return Identifier.fromNamespaceAndPath(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Initialize " + MOD_ID);

		ModBlocks.registerAll();
		ModItems.registerAll();
		
		ShimmerWorldGenerator.generateShimmerPlants();
	}
}