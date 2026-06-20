package dev.padjokej.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class LevelUtils {
    private static final WeightedList<ExplosionParticleInfo> BLOCK_PARTICLES = WeightedList.<ExplosionParticleInfo>builder()
            .add(new ExplosionParticleInfo(ParticleTypes.POOF, 0.5F, 1.0F))
            .add(new ExplosionParticleInfo(ParticleTypes.SMOKE, 1.0F, 1.0F))
            .build();

    public static void explode(ServerLevel serverLevel,
                               final @Nullable Entity source,
                               final @Nullable DamageSource damageSource,
                               final @Nullable ExplosionDamageCalculator damageCalculator,
                               final Vec3 pos,
                               final float r,
                               final boolean fire,
                               final ParticleOptions particles,
                               final Holder<SoundEvent> explosionSound) {

        Explosion.BlockInteraction blockInteraction = Explosion.BlockInteraction.DESTROY;

        ServerExplosion explosion = new ServerExplosion(serverLevel, source, damageSource, damageCalculator, pos, r, fire, blockInteraction);
        int blockCount = explosion.explode();

        for (ServerPlayer player : serverLevel.players()) {
            if (player.distanceToSqr(pos) < 4096.0) {
                Optional<Vec3> playerKnockback = Optional.ofNullable(explosion.getHitPlayers().get(player));
                player.connection.send(new ClientboundExplodePacket(pos, r, blockCount, playerKnockback, particles, explosionSound, BLOCK_PARTICLES));
            }
        }
    }
}
