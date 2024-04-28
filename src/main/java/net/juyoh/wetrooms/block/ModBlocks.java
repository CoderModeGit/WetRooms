package net.juyoh.wetrooms.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.block.custom.BakedLightingBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BLEACHED_TILES = registerBlock("bleached_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
    public static final Block OBSIDIAN_TILES = registerBlock("obsidian_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN)));
    public static final Block UNNATURAL_LIGHT = registerBlock("unnatural_light",
            new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE).mapColor(MapColor.WHITE).luminance(0).postProcess(Blocks::always)));
    public static final Block EMMISION_LINEAR = registerBlock("emmision_linear",
            new BakedLightingBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.SHROOMLIGHT).noCollision().nonOpaque().replaceable()));
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
