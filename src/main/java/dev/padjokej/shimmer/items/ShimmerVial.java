package dev.padjokej.shimmer.items;

import dev.padjokej.registry.ModEffects;
import dev.padjokej.shimmer.entities.ThrowableShimmer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.Level;

import static net.minecraft.world.item.Items.GLASS_BOTTLE;

public class ShimmerVial extends Item {
    public ShimmerVial(Properties properties) {
        properties.stacksTo(16)
                .component(DataComponents.POTION_CONTENTS, new PotionContents(ModEffects.SHIMMER_POTION))
                .component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK)
                .usingConvertsTo(GLASS_BOTTLE);
        super(properties);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (player.isCrouching()) {
            ItemStack stack = player.getItemInHand(hand);

            if (level instanceof ServerLevel serverLevel) {
                Projectile.spawnProjectileFromRotation(ThrowableShimmer::new, serverLevel, stack, player, 0.0F, 1.5F, 1.0F);
            }

            stack.consume(1, player);

            return InteractionResult.SUCCESS;
        }

        return super.use(level, player, hand);
    }
}
