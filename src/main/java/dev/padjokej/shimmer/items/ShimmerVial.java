package dev.padjokej.shimmer.items;

import dev.padjokej.registry.ModEffects;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumables;

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
}
