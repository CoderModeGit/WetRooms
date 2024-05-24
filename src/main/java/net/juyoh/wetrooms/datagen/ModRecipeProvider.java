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
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLEACHED_TILES_SLAB, ModBlocks.BLEACHED_TILES);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_TILES_SLAB, ModBlocks.OBSIDIAN_TILES);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBBER_PLANKS_SLAB, ModBlocks.RUBBER_PLANKS);


        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOLID_METAL, Blocks.IRON_BLOCK);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBBER_WOOD, ModBlocks.RUBBER_LOG);

        offerShapelessRecipe(exporter, ModBlocks.RUBBER_PLANKS, ModBlocks.RUBBER_LOG, "rubber", 4);
        offerShapelessRecipe(exporter, ModBlocks.RUBBER_PLANKS, ModBlocks.RUBBER_WOOD, "rubber", 4);

        //offerSmelting(exporter, );
        offerSmelting(exporter, List.of(ModBlocks.EPSOMITE_ORE), RecipeCategory.MISC, ModItems.EPSOM_SALT, 0.7f, 200, "epsomsalt");

        offerBlasting(exporter, List.of(ModBlocks.EPSOMITE_ORE), RecipeCategory.MISC, ModItems.EPSOM_SALT, 0.7f, 100, "epsomsalt");

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_TILES, Blocks.OBSIDIAN);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_TILES_SLAB, Blocks.OBSIDIAN);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_TILES_STAIRS, Blocks.OBSIDIAN);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLEACHED_TILES_WALL, ModBlocks.BLEACHED_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLEACHED_TILES_SLAB, ModBlocks.BLEACHED_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLEACHED_TILES_STAIRS, ModBlocks.BLEACHED_TILES);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLEACHED_TILES).pattern("sos").pattern("oso").pattern("sos")
                .input('s', ModItems.EPSOM_SALT)
                .input('o', Blocks.OBSIDIAN)
                .criterion(FabricRecipeProvider.hasItem(ModItems.EPSOM_SALT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.EPSOM_SALT))
                .criterion(FabricRecipeProvider.hasItem(Blocks.OBSIDIAN),
                        FabricRecipeProvider.conditionsFromItem(Blocks.OBSIDIAN))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBBER_PLANKS_FENCE).pattern("psp").pattern("psp")
                .input('s', Items.STICK)
                .input('p', ModBlocks.RUBBER_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.RUBBER_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(ModItems.EPSOM_SALT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.METAL_RAILING).pattern("bib").pattern("bib")
                .input('i', Items.IRON_INGOT)
                .input('b', ModBlocks.SOLID_METAL)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.SOLID_METAL),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.SOLID_METAL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLEACHED_TILES_STAIRS).pattern("b  ").pattern("bb ").pattern("bbb")
                .input('b', ModBlocks.BLEACHED_TILES)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.BLEACHED_TILES),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.BLEACHED_TILES))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_TILES_STAIRS).pattern("o  ").pattern("oo ").pattern("ooo")
                .input('o', ModBlocks.OBSIDIAN_TILES)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.OBSIDIAN_TILES),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.OBSIDIAN_TILES))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBBER_PLANKS_STAIRS).pattern("p  ").pattern("pp ").pattern("ppp")
                .input('p', ModBlocks.RUBBER_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.RUBBER_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.RUBBER_PLANKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WASHED_STONE).pattern("sss").pattern("ses").pattern("sss")
                .input('s', Blocks.STONE)
                .input('e', ModItems.EPSOM_SALT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.EPSOM_SALT),
                        FabricRecipeProvider.conditionsFromItem(ModItems.EPSOM_SALT))
                .offerTo(exporter);
    }
}
