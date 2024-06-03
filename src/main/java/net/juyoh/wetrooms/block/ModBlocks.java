package net.juyoh.wetrooms.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.block.custom.MarbleBlock;
import net.juyoh.wetrooms.block.custom.MarbleManBlock;
import net.juyoh.wetrooms.block.custom.VentBlock;
import net.juyoh.wetrooms.sound.ModSounds;
import net.juyoh.wetrooms.world.tree.RubberSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.core.util.Clock;

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
    public static final Block BLEACHED_TILES_WALL = registerBlock("bleached_tiles_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block OBSIDIAN_TILES_SLAB = registerBlock("obsidian_tiles_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block WASHED_STONE = registerBlock("washed_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block MARBLE_MAN = registerBlock("marble_man",
            new MarbleManBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(ModSounds.WETROOMS_TILE_SOUNDS)));
    public static final Block QUARTZ_DOOR = registerBlock("quartz_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS).dropsNothing().luminance(5), BlockSetType.OAK));
    public static final Block SIDEWAYS_DOOR = registerBlock("sideways_door", //TODO Make new custom class for sideways door to add rotation
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block BLEACHED_DOOR = registerBlock("bleached_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS), BlockSetType.IRON));
    public static final Block METAL_RAILING = registerBlock("metal_railing",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block METAL_LADDER = registerBlock("metal_ladder",
            new LadderBlock(FabricBlockSettings.copyOf(Blocks.LADDER)));
    public static final Block METAL_LADDER_TOP = registerBlock("metal_ladder_top",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).noCollision()));
    public static final Block VENT_BLOCK = registerBlock("vent",
            new VentBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).noCollision()));
    public static final Block CLOCK_BLOCK = registerBlock("clock",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).noCollision()));
    public static final Block EPSOMITE_ORE = registerBlock("epsomite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE)));
    public static final Block RUBBER_LOG = registerBlock("rubber_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));
    public static final Block RUBBER_WOOD = registerBlock("rubber_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(4f)));
    public static final Block STRIPPED_RUBBER_LOG = registerBlock("stripped_rubber_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG).strength(4f)));
    public static final Block STRIPPED_RUBBER_WOOD = registerBlock("stripped_rubber_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).strength(4f)));
    public static final Block RUBBER_PLANKS = registerBlock("rubber_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));
    public static final Block RUBBER_PLANKS_STAIRS = registerBlock("rubber_planks_stairs",
            new StairsBlock(ModBlocks.RUBBER_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block RUBBER_PLANKS_SLAB = registerBlock("rubber_planks_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block RUBBER_PLANKS_FENCE = registerBlock("rubber_planks_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block RUBBER_LEAVES = registerBlock("rubber_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block RUBBER_SAPLING = registerBlock("rubber_sapling",
            new SaplingBlock(new RubberSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block MARBLE_BLOCK = registerBlock("marble_block",
            new MarbleBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    //public static final Block PLASTIC_CHAIR = registerBlock("plastic_chair",
    //        new ChairBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));



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
