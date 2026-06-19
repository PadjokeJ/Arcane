package dev.padjokej.shimmer.entities;

import dev.padjokej.registry.ModEntities;
import dev.padjokej.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrowableShimmer extends ThrowableItemProjectile {
    public ThrowableShimmer(final EntityType<? extends ThrowableShimmer> type, final Level level) {
        super(type, level);
    }

    public ThrowableShimmer(final Level level, final LivingEntity mob, final ItemStack itemStack) {
        super(ModEntities.SHIMMER, mob, level, itemStack);
    }

    public ThrowableShimmer(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        super(ModEntities.SHIMMER, x, y, z, level, itemStack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SHIMMER_VIAL;
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();

        entity.setRemainingFireTicks(50);
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);

        if (level() instanceof ServerLevel serverLevel) {
            Entity owner = this.getOwner();

            if (!(owner instanceof Mob) || serverLevel.getGameRules().get(GameRules.MOB_GRIEFING)) {

                BlockPos pos = hitResult.getBlockPos().relative(hitResult.getDirection());

                if (serverLevel.isEmptyBlock(pos))
                    serverLevel.setBlockAndUpdate(pos, BaseFireBlock.getState(serverLevel, pos));
            }
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        
        if (level() instanceof ServerLevel) {
            this.discard();
        }
    }
}
