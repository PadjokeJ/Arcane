package dev.padjokej.world;

import dev.padjokej.Arcane;
import dev.padjokej.registry.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class WorldConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZAUN_FLOWER_PATCH = registerResourceKey("zaun_flower_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZAUN_FLOWER_SINGLE = registerResourceKey("zaun_flower_single");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerResourceKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Arcane.id(name));
    }

    public static void configure(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ZAUN_FLOWER_SINGLE, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ZAUN_FLOWER_BLOCK)));

        register(context, ZAUN_FLOWER_PATCH, Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(BlockTags.BASE_STONE_OVERWORLD,
                        BlockStateProvider.simple(Blocks.SCULK),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ZAUN_FLOWER_SINGLE)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.1F,
                        5,
                        0.4F,
                        UniformInt.of(4, 7),
                        0.3F));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> k, F feature, FC config) {
        context.register(k, new ConfiguredFeature<>(feature, config));
    }
}
