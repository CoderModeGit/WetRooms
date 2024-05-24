package net.juyoh.wetrooms.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.juyoh.wetrooms.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        //getOrCreateTagBuilder(BlockTags.DOORS)
        //        .add(ModBlocks.BLEACHED_DOOR)
        //        .add(ModBlocks.QUARTZ_DOOR);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.OBSIDIAN_TILES_SLAB)
                .add(ModBlocks.OBSIDIAN_TILES_STAIRS)
                .add(ModBlocks.OBSIDIAN_TILES)
                .add(ModBlocks.BLEACHED_TILES_SLAB)
                .add(ModBlocks.BLEACHED_TILES_STAIRS)
                .add(ModBlocks.BLEACHED_TILES)
                .add(ModBlocks.BLEACHED_DOOR)
                .add(ModBlocks.EPSOMITE_ORE)
                .add(ModBlocks.SOLID_METAL);


        getOrCreateTagBuilder(BlockTags.IMPERMEABLE)
                .add(ModBlocks.QUARTZ_DOOR);
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.METAL_RAILING);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.RUBBER_PLANKS_FENCE)
                .add(ModBlocks.METAL_RAILING);


        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.RUBBER_LOG)
                .add(ModBlocks.STRIPPED_RUBBER_LOG)
                .add(ModBlocks.RUBBER_WOOD)
                .add(ModBlocks.STRIPPED_RUBBER_WOOD);
        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(ModBlocks.METAL_LADDER);

    }
}
