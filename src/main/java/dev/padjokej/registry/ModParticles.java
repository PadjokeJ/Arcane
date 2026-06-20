package dev.padjokej.registry;

import dev.padjokej.Arcane;
import dev.padjokej.particles.EmitterParticle;
import dev.padjokej.particles.SimpleModParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModParticles {
    public static final SimpleParticleType SHIMMER_EXPLOSION = register("shimmer_explosion", FabricParticleTypes.simple(), SimpleModParticle.Provider::new);
    public static final SimpleParticleType SHIMMER_EXPLOSION_EMITTER = register("shimmer_explosion_emitter", FabricParticleTypes.simple(), EmitterParticle.Provider::new);

    public static SimpleParticleType register(String name, SimpleParticleType particleType, ParticleProviderRegistry.PendingParticleProvider<SimpleParticleType> provider) {
        ParticleProviderRegistry.getInstance().register(particleType, provider);

        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Arcane.id(name), particleType);
    }

    public static void registerAll() {
        Arcane.LOGGER.info("Registering particles");
    }
}
