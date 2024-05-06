package net.juyoh.wetrooms.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.juyoh.wetrooms.block.ModBlocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BLEACHED_DOOR, doorDrops(ModBlocks.BLEACHED_DOOR));

        addDrop(ModBlocks.BLEACHED_TILES);
        addDrop(ModBlocks.BLEACHED_TILES_SLAB, slabDrops(ModBlocks.BLEACHED_TILES_SLAB));
        addDrop(ModBlocks.BLEACHED_TILES_STAIRS);
        addDrop(ModBlocks.OBSIDIAN_TILES);
        addDrop(ModBlocks.OBSIDIAN_TILES_SLAB, slabDrops(ModBlocks.OBSIDIAN_TILES_SLAB));
        addDrop(ModBlocks.OBSIDIAN_TILES_STAIRS);
        addDrop(ModBlocks.UNNATURAL_LIGHT);
        addDrop(ModBlocks.SOLID_METAL);
        addDrop(ModBlocks.EPSOMITE_ORE);

        addDrop(ModBlocks.RUBBER_LOG);
        addDrop(ModBlocks.STRIPPED_RUBBER_LOG);
        addDrop(ModBlocks.STRIPPED_RUBBER_WOOD);
        addDrop(ModBlocks.RUBBER_WOOD);
        addDrop(ModBlocks.RUBBER_PLANKS);
        addDrop(ModBlocks.RUBBER_SAPLING);

        addDrop(ModBlocks.RUBBER_LEAVES, leavesDrops(ModBlocks.RUBBER_LEAVES, ModBlocks.RUBBER_SAPLING, 0.025f));
    }
}
