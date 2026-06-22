package dev.padjokej.registry;

import dev.padjokej.Arcane;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public interface ModArmorMaterials {
    String name();
    int durability();
    List<ArmorType> elements();
    ArmorMaterial instance();
    String pieceName(ArmorType type);

    public static class ChemtechSuit implements ModArmorMaterials {
        public static final String NAME = "chemtech_suit";
        public static final int BASE_DURABILITY = 12;

        public static final ResourceKey<EquipmentAsset> MATERIAL_KEY =
                ResourceKey.create(EquipmentAssets.ROOT_ID, Arcane.id(NAME));

        public static final TagKey<Item> REPAIR_INGREDIENT =
                TagKey.create(BuiltInRegistries.ITEM.key(), Arcane.id("repairs_" + NAME));

        public static final ArmorMaterial INSTANCE = new ArmorMaterial(
                BASE_DURABILITY,
                Map.of(),
                5,
                SoundEvents.ARMOR_EQUIP_COPPER,
                0F,
                2F,
                REPAIR_INGREDIENT,
                MATERIAL_KEY
        );

        @Override
        public String name() {
            return NAME;
        }

        @Override
        public int durability() {
            return BASE_DURABILITY;
        }

        @Override
        public List<ArmorType> elements() {
            return List.of(ArmorType.HELMET, ArmorType.CHESTPLATE, ArmorType.LEGGINGS, ArmorType.BOOTS);
        }

        @Override
        public ArmorMaterial instance() {
            return INSTANCE;
        }

        @Override
        public String pieceName(ArmorType type) {
            StringJoiner stringJoiner = new StringJoiner("_");
            stringJoiner
                    .add(name())
                    .add(type.toString().toLowerCase());
            return stringJoiner.toString();
        }
    }
}
