package dev.padjokej.particles;

import dev.padjokej.registry.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SimpleModParticle extends SimpleAnimatedParticle {
    private SimpleModParticle(
            final ClientLevel level, final double x, final double y, final double z, final double xa, final double ya, final double za, final SpriteSet sprites
    ) {
        super(level, x, y, z, sprites, 0.0125F);
        this.xd = xa;
        this.yd = ya;
        this.zd = za;
        this.quadSize *= 8f;
        this.lifetime = 6;
        this.setFadeColor(15916745);
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void move(final double xa, final double ya, final double za) {
//        this.setBoundingBox(this.getBoundingBox().move(xa, ya, za));
//        this.setLocationFromBoundingbox();
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(final SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(
                final SimpleParticleType options,
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final double xAux,
                final double yAux,
                final double zAux,
                final RandomSource random
        ) {
            return new SimpleModParticle(level, x, y, z, xAux, yAux, zAux, this.sprites);
        }
    }
}
