package dev.padjokej.datagen.language;

import dev.padjokej.registry.ModBlocks;
import dev.padjokej.registry.ModEffects;
import dev.padjokej.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;

public class ArcaneFrenchProvider extends FabricLanguageProvider {

    Map<ArmorType, String> armorTranslationMap = Map.of(
            ArmorType.HELMET, "Casque",
            ArmorType.CHESTPLATE, "Plastron",
            ArmorType.LEGGINGS, "Jambières",
            ArmorType.BOOTS, "Bottes"
    );

    public ArcaneFrenchProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "fr_fr", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CHEMTECH_BOMB, "Bombe Chemtech");
        translationBuilder.add(ModItems.SHIMMER_VIAL, "Fiole de Shimmer");
        translationBuilder.add(ModBlocks.ZAUN_FLOWER_BLOCK, "Fleure de Zaun");
        translationBuilder.add(ModEffects.SHIMMER.value(), "Shimmer");

        ModItems.CHEMTECH_SUIT.forEach((a, i) -> {
            StringJoiner sj = new StringJoiner(" ");
            sj.add(armorTranslationMap.get(a));
            sj.add("Chemtech");

            translationBuilder.add(i, sj.toString());
        });
    }
}
