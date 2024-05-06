package net.juyoh.wetrooms.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
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
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBBER_WOOD, ModBlocks.RUBBER_LOG);

        offerShapelessRecipe(exporter, ModBlocks.RUBBER_PLANKS, ModBlocks.RUBBER_LOG, "rubber", 4);
        offerShapelessRecipe(exporter, ModBlocks.RUBBER_PLANKS, ModBlocks.RUBBER_WOOD, "rubber", 4);

        //offerSmelting(exporter, );
        offerSmelting(exporter, List.of(ModBlocks.EPSOMITE_ORE), RecipeCategory.MISC, ModItems.EPSOM_SALT, 0.7f, 200, "epsomsalt");

        offerBlasting(exporter, List.of(ModBlocks.EPSOMITE_ORE), RecipeCategory.MISC, ModItems.EPSOM_SALT, 0.7f, 100, "epsomsalt");

        
    }
}
