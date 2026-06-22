package dev.padjokej.registry;

import dev.padjokej.Arcane;
import dev.padjokej.shimmer.items.Bomb;
import dev.padjokej.shimmer.items.ShimmerVial;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModItems {
    public static final Item SHIMMER_VIAL =
            register("shimmer_vial", ShimmerVial::new, new Item.Properties());
    public static final Item CHEMTECH_BOMB =
            register("chemtech_bomb", Bomb::new, new Item.Properties());

    public static final Map<ArmorType, Item> CHEMTECH_SUIT =
            registerArmor(Item::new, ModArmorMaterials.ChemtechSuit::new);

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Arcane.id(name));

        T item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static <T extends Item> Map<ArmorType, T> registerArmor(Function<Item.Properties, T> itemFactory,
                                                                   Supplier<ModArmorMaterials> materialSupplier) {
        ModArmorMaterials material = materialSupplier.get();

        return material.elements().stream().collect(Collectors.toMap(e -> e, e -> {
            Item.Properties properties = new Item.Properties()
                    .humanoidArmor(material.instance(), e)
                    .durability(e.getDurability(material.durability()));

            return register(material.pieceName(e), itemFactory, properties);
        }));
    }

    public static void registerAll() {
        Arcane.LOGGER.info("Registering items");
    }
}
