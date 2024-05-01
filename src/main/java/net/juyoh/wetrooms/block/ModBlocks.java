package net.juyoh.wetrooms.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.block.custom.BakedLightingBlock;
import net.juyoh.wetrooms.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BLEACHED_TILES = registerBlock("bleached_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block OBSIDIAN_TILES = registerBlock("obsidian_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block SOLID_METAL = registerBlock("solid_metal",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block UNNATURAL_LIGHT = registerBlock("unnatural_light",
            new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE).mapColor(MapColor.WHITE).luminance(16)));
    public static final Block BLEACHED_TILES_STAIRS = registerBlock("bleached_tiles_stairs",
            new StairsBlock(ModBlocks.BLEACHED_TILES.getDefaultState(), FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block OBSIDIAN_TILES_STAIRS = registerBlock("obsidian_tiles_stairs",
            new StairsBlock(ModBlocks.OBSIDIAN_TILES.getDefaultState(), FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block BLEACHED_TILES_SLAB = registerBlock("bleached_tiles_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block OBSIDIAN_TILES_SLAB = registerBlock("obsidian_tiles_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block QUARTZ_DOOR = registerBlock("quartz_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS), BlockSetType.OAK));
    public static final Block BLEACHED_DOOR = registerBlock("bleached_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS), BlockSetType.IRON));
    public static final Block METAL_RAILING = registerBlock("metal_railing",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(WetRooms.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(WetRooms.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks() {
        WetRooms.LOGGER.info("Registering mod blocks for " + WetRooms.MOD_ID);
    }
}
