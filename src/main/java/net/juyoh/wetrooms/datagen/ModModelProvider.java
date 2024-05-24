package net.juyoh.wetrooms.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.item.ModItems;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.UNNATURAL_LIGHT);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EPSOMITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WASHED_STONE);
        BlockStateModelGenerator.BlockTexturePool bleached_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLEACHED_TILES);
        BlockStateModelGenerator.BlockTexturePool obsidian_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OBSIDIAN_TILES);
        BlockStateModelGenerator.BlockTexturePool rubber_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBBER_PLANKS);
        BlockStateModelGenerator.BlockTexturePool metal_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SOLID_METAL);

        bleached_pool.slab(ModBlocks.BLEACHED_TILES_SLAB);
        bleached_pool.wall(ModBlocks.BLEACHED_TILES_WALL);
        obsidian_pool.slab(ModBlocks.OBSIDIAN_TILES_SLAB);
        
        bleached_pool.stairs(ModBlocks.BLEACHED_TILES_STAIRS);
        obsidian_pool.stairs(ModBlocks.OBSIDIAN_TILES_STAIRS);

        metal_pool.fence(ModBlocks.METAL_RAILING);

        rubber_pool.fence(ModBlocks.RUBBER_PLANKS_FENCE);
        rubber_pool.stairs(ModBlocks.RUBBER_PLANKS_STAIRS);
        rubber_pool.slab(ModBlocks.RUBBER_PLANKS_SLAB);

        blockStateModelGenerator.registerDoor(ModBlocks.BLEACHED_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.QUARTZ_DOOR);

        blockStateModelGenerator.registerLog(ModBlocks.RUBBER_LOG).log(ModBlocks.RUBBER_LOG).wood(ModBlocks.RUBBER_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_RUBBER_LOG).log(ModBlocks.STRIPPED_RUBBER_LOG).wood(ModBlocks.STRIPPED_RUBBER_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBBER_LEAVES);
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBBER_PLANKS);
        blockStateModelGenerator.registerTintableCross(ModBlocks.RUBBER_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(ModItems.LATEX_BUCKET, Models.GENERATED);
            itemModelGenerator.register(ModItems.EPSOM_SALT, Models.GENERATED);
    }
}
