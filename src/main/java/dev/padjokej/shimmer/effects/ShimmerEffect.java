package dev.padjokej.shimmer.effects;

import dev.padjokej.Arcane;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.function.Function;

public class ShimmerEffect extends MobEffect {
    Function<Integer, Float> effectTickrateStrength = x -> 10f * (1f - (float) Math.exp(-0.5 * x));

    public ShimmerEffect(MobEffectCategory category, int color) {
        super(category, color);

        addAttributeModifier(Attributes.MOVEMENT_SPEED, Arcane.id("shimmer_speed"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, Arcane.id("shimmer_fastmine"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, Arcane.id("shimmer_damage"), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }


    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        applySlowMotion(entity, amplifier, true);
    }

    @Override
    public void onMobRemoved(ServerLevel level, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        applySlowMotion(entity, amplifier, false);
    }

    @Override
    public void onEffectRemoved(MobEffectInstance effectInstance, LivingEntity entity) {
        int amplifier = effectInstance.getAmplifier();

        applySlowMotion(entity, amplifier, false);
    }

    private void applySlowMotion(LivingEntity entity, int amplifier, boolean apply) {
        if (!(entity instanceof Player))
            return;

        Level level = entity.level();
        if (!(level instanceof ServerLevel serverLevel))
            return;

        if (serverLevel.players().size() != 1) // makeshift .isSinglePlayer
            return;

        TickRateManager trm = serverLevel.tickRateManager();
        float tr = trm.tickrate();

        float shimmerTickrate = (apply ? -1 : 1) * effectTickrateStrength.apply(amplifier);

        trm.setTickRate(Math.min(tr + shimmerTickrate, 20)); // clamp the value to avoid funnies
    }
}
