package dev.padjokej.shimmer.items;

import dev.padjokej.shimmer.entities.ThrowableBombEntity;
import dev.padjokej.shimmer.entities.ThrowableShimmer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Bomb extends Item {
    public Bomb(Properties properties) {
        properties.stacksTo(16);
        properties.fireResistant();
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileFromRotation(ThrowableBombEntity::new, serverLevel, stack, player, 0.0F, 0.8F, 1.5F);
        }

        stack.consume(1, player);

        return InteractionResult.SUCCESS;
    }
}
