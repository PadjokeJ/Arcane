package dev.padjokej.world;

import dev.padjokej.Arcane;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class WorldPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ZAUN_FLOWER_PATCH_PLACED_KEY = registerResourceKey("zaun_flower_patch_placed");


    public static ResourceKey<PlacedFeature> registerResourceKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Arcane.id(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> context,
                                                                                          ResourceKey<PlacedFeature> key,
                                                                                          Holder<ConfiguredFeature<?, ?>> config,
                                                                                          PlacementModifier... modifiers) {
        register(context, key, config, List.of(modifiers));
    }

    public static void configure(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ZAUN_FLOWER_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(WorldConfiguredFeatures.ZAUN_FLOWER_PATCH),
                List.of(
                        CountPlacement.of(4),
                        BiomeFilter.biome(),
                        RandomOffsetPlacement.ofTriangle(4, 3),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(60))),
                        BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                ));
    }
}
