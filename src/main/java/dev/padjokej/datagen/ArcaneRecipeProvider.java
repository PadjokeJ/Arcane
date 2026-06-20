package dev.padjokej.datagen;

import dev.padjokej.registry.ModBlocks;
import dev.padjokej.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ArcaneRecipeProvider extends FabricRecipeProvider {
    public ArcaneRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                shapeless(RecipeCategory.MISC, ModItems.SHIMMER_VIAL)
                        .requires(ModBlocks.ZAUN_FLOWER_BLOCK)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(ModBlocks.ZAUN_FLOWER_BLOCK), has(ModBlocks.ZAUN_FLOWER_BLOCK))
                        .save(output);

                shapeless(RecipeCategory.COMBAT, ModItems.CHEMTECH_BOMB)
                        .requires(ModBlocks.ZAUN_FLOWER_BLOCK)
                        .requires(Items.GUNPOWDER)
                        .requires(Items.IRON_INGOT)
                        .unlockedBy(getHasName(ModBlocks.ZAUN_FLOWER_BLOCK), has(ModBlocks.ZAUN_FLOWER_BLOCK))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "Arcane recipe provider";
    }
}
