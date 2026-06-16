package dev.padjokej.datagen;

import dev.padjokej.registry.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.concurrent.CompletableFuture;

public class LootTableProvider extends FabricBlockLootSubProvider {
    protected LootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
//        this.add(ModBlocks.ZAUN_FLOWER_BLOCK, b -> {
//            this.applyExplosionDecay(b, LootTable.lootTable()
//                    .pool(LootPool.lootPool().add(ItemEntry)))
//        });
    }
}
