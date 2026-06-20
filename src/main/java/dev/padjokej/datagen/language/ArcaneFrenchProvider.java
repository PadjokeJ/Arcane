package dev.padjokej.datagen.language;

import dev.padjokej.registry.ModBlocks;
import dev.padjokej.registry.ModEffects;
import dev.padjokej.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ArcaneFrenchProvider extends FabricLanguageProvider {

    public ArcaneFrenchProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "fr_fr", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CHEMTECH_BOMB, "Bombe chemtech");
        translationBuilder.add(ModItems.SHIMMER_VIAL, "Fiole de shimmer");
        translationBuilder.add(ModBlocks.ZAUN_FLOWER_BLOCK, "Fleure de Zaun");
        translationBuilder.add(ModEffects.SHIMMER.value(), "Shimmer");
    }
}
