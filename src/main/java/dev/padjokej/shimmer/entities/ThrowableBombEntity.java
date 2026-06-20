package dev.padjokej.shimmer.entities;

import dev.padjokej.registry.ModEntities;
import dev.padjokej.registry.ModItems;
import dev.padjokej.registry.ModParticles;
import dev.padjokej.utils.LevelUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrowableBombEntity extends ThrowableItemProjectile {

    public static final byte PARTICLE_EVENT = (byte) 3;

    public ThrowableBombEntity(final EntityType<? extends ThrowableBombEntity> type, final Level level) {
        super(type, level);
    }

    public ThrowableBombEntity(final Level level, final LivingEntity mob, final ItemStack itemStack) {
        super(ModEntities.CHEMTECH_BOMB, mob, level, itemStack);
    }

    public ThrowableBombEntity(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        super(ModEntities.CHEMTECH_BOMB, x, y, z, level, itemStack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CHEMTECH_BOMB;
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();

        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 5f);
        explode((ServerLevel) entity.level());
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);

        if (level() instanceof ServerLevel serverLevel) {
            Entity owner = this.getOwner();

            if ((!(owner instanceof Mob) || serverLevel.getGameRules().get(GameRules.MOB_GRIEFING)) && serverLevel.getGameRules().get(GameRules.TNT_EXPLODES)) {

                explode(serverLevel);
            }
        }
    }

    private void explode(ServerLevel serverLevel) {
        LevelUtils.explode(serverLevel, this, Explosion.getDefaultDamageSource(serverLevel, this),
                null, this.position(), 2.5f, true, ModParticles.SHIMMER_EXPLOSION_EMITTER, SoundEvents.GENERIC_EXPLODE);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (level() instanceof ServerLevel serverLevel) {
            serverLevel.broadcastEntityEvent(this, PARTICLE_EVENT);

            this.discard();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == PARTICLE_EVENT) {
            level().addParticle(ModParticles.SHIMMER_EXPLOSION_EMITTER, getX(), getY(), getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}
