package dev.padjokej.registry;

import dev.padjokej.Arcane;
import dev.padjokej.shimmer.entities.ThrowableShimmer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<ThrowableShimmer> SHIMMER = register(
            "shimmer", EntityType.Builder.<ThrowableShimmer>of(ThrowableShimmer::new, MobCategory.MISC).noLootTable().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
    );

    private static <T extends Entity> EntityType<T> register(String name, final EntityType.Builder<T> builder) {
        var id = ResourceKey.create(Registries.ENTITY_TYPE, Arcane.id(name));

        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, builder.build(id));
    }

    public static void registerAll() {
        Arcane.LOGGER.info("Registering entities");

        EntityRendererRegistry.register(SHIMMER, ThrownItemRenderer::new);
    }
}
