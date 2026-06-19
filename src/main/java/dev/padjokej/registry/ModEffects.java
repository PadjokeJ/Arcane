package dev.padjokej.registry;

import dev.padjokej.Arcane;
import dev.padjokej.shimmer.effects.ShimmerEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModEffects {
    public static final Holder<MobEffect> SHIMMER =
            register("shimmer", new ShimmerEffect(MobEffectCategory.BENEFICIAL, 0xc312f4));

    public static final Holder.Reference<Potion> SHIMMER_POTION =
            register("shimmer", new Potion("shimmer", new MobEffectInstance(SHIMMER, 3600, 2)));

    private static Holder<MobEffect> register(final String name, final MobEffect mobEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Arcane.id(name), mobEffect);
    }

    private static Holder.Reference<Potion> register(final String name, final Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Arcane.id(name), potion);
    }

    public static void registerAll() {
        Arcane.LOGGER.info("Registering effects");
    }
}
