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

public class ArcaneEnglishProvider extends FabricLanguageProvider {

    Map<ArmorType, String> armorTranslationMap = Map.of(
            ArmorType.HELMET, "Helmet",
            ArmorType.CHESTPLATE, "Chestplate",
            ArmorType.LEGGINGS, "Leggings",
            ArmorType.BOOTS, "Boots"
    );

    public ArcaneEnglishProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CHEMTECH_BOMB, "Chemtech Bomb");
        translationBuilder.add(ModItems.SHIMMER_VIAL, "Shimmer Vial");
        translationBuilder.add(ModBlocks.ZAUN_FLOWER_BLOCK, "Zaun Flower");
        translationBuilder.add(ModEffects.SHIMMER.value(), "Shimmer");

        ModItems.CHEMTECH_SUIT.forEach((a, i) -> {
            StringJoiner sj = new StringJoiner(" ");
            sj.add("Chemtech");
            sj.add(armorTranslationMap.get(a));

            translationBuilder.add(i, sj.toString());
        });
    }
}
