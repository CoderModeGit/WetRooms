package net.juyoh.wetrooms.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.UNNATURAL_LIGHT);
        BlockStateModelGenerator.BlockTexturePool bleached_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLEACHED_TILES);
        BlockStateModelGenerator.BlockTexturePool obsidian_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OBSIDIAN_TILES);
        BlockStateModelGenerator.BlockTexturePool metal_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SOLID_METAL);

        bleached_pool.slab(ModBlocks.BLEACHED_TILES_SLAB);
        obsidian_pool.slab(ModBlocks.OBSIDIAN_TILES_SLAB);
        
        bleached_pool.stairs(ModBlocks.BLEACHED_TILES_STAIRS);
        obsidian_pool.stairs(ModBlocks.OBSIDIAN_TILES_STAIRS);

        metal_pool.fence(ModBlocks.METAL_RAILING);

        blockStateModelGenerator.registerDoor(ModBlocks.BLEACHED_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.QUARTZ_DOOR);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(ModItems.LATEX_BUCKET, Models.GENERATED);
    }
}
