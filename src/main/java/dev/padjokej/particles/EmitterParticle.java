package dev.padjokej.particles;

import dev.padjokej.registry.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class EmitterParticle extends SimpleAnimatedParticle {
    private EmitterParticle(
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
    public void tick() {
        this.age++;
        if (this.age >= this.lifetime) {
            this.remove();
        }

        for (int i = 0; i < 6; i++) {
            double xx = this.x + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
            double yy = this.y + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
            double zz = this.z + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
            this.level.addParticle(ModParticles.SHIMMER_EXPLOSION, xx, yy, zz, (float)this.age / this.lifetime, 0.0, 0.0);
        }
    }

    @Override
    public void move(final double xa, final double ya, final double za) {
        this.setBoundingBox(this.getBoundingBox().move(xa, ya, za));
        this.setLocationFromBoundingbox();
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
            return new EmitterParticle(level, x, y, z, xAux, yAux, zAux, this.sprites);
        }
    }
}
