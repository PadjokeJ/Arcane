package dev.padjokej.datagen;

import dev.padjokej.registry.ModBlocks;
import dev.padjokej.registry.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ArcaneModelGenerator extends FabricModelProvider {
    public ArcaneModelGenerator(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createCrossBlock(ModBlocks.ZAUN_FLOWER_BLOCK,
                BlockModelGenerators.PlantType.EMISSIVE_NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.SHIMMER_VIAL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CHEMTECH_BOMB, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "Arcane models generator";
    }
}
