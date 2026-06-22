package dev.padjokej.shimmer.items;

import dev.padjokej.registry.ModEffects;
import dev.padjokej.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.Nullable;

public class ChemtechArmor extends Item {
    public ChemtechArmor(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (itemStack.getItem() == ModItems.CHEMTECH_SUIT.get(ArmorType.HELMET)
                && slot != null
                && slot.isArmor()
                && owner instanceof Player player
                && level.getGameTime() % 100 == 0) {
            player.addEffect(new MobEffectInstance(ModEffects.SHIMMER, 120, 4), player);

            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
        }
    }
}
