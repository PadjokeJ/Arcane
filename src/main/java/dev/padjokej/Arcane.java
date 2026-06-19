package dev.padjokej;

import dev.padjokej.registry.ModBlocks;
import dev.padjokej.registry.ModEffects;
import dev.padjokej.registry.ModItems;
import dev.padjokej.world.gen.WorldGenerator;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;
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
		ModEffects.registerAll();

		WorldGenerator.generateShimmerPlants();
	}
}