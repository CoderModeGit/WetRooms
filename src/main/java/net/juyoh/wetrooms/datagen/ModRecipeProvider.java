package net.juyoh.wetrooms.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.juyoh.wetrooms.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLEACHED_TILES, ModBlocks.BLEACHED_TILES_SLAB);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_TILES, ModBlocks.OBSIDIAN_TILES_SLAB);

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.IRON_BLOCK, ModBlocks.SOLID_METAL);
    }
}
