package dev.padjokej.datagen.language;

import dev.padjokej.registry.ModBlocks;
import dev.padjokej.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ArcaneEnglishProvider extends FabricLanguageProvider {

    public ArcaneEnglishProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.SHIMMER_VIAL, "Shimmer Vial");
        translationBuilder.add(ModBlocks.ZAUN_FLOWER_BLOCK, "Zaun Flower");
    }
}
