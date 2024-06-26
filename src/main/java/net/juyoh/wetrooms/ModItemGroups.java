package net.juyoh.wetrooms;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup WETROOMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WetRooms.MOD_ID, "wetrooms"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.wetrooms"))
                    .icon(() -> new ItemStack(ModBlocks.BLEACHED_TILES)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.BLEACHED_TILES);
                        entries.add(ModBlocks.BLEACHED_TILES_STAIRS);
                        entries.add(ModBlocks.BLEACHED_TILES_SLAB);
                        entries.add(ModBlocks.BLEACHED_TILES_WALL);
                        entries.add(ModBlocks.BLEACHED_DOOR);
                        entries.add(ModBlocks.QUARTZ_DOOR);
                        entries.add(ModBlocks.SIDEWAYS_DOOR);

                        entries.add(ModBlocks.OBSIDIAN_TILES);
                        entries.add(ModBlocks.OBSIDIAN_TILES_STAIRS);
                        entries.add(ModBlocks.OBSIDIAN_TILES_SLAB);

                        entries.add(ModBlocks.WASHED_STONE);

                        entries.add(ModBlocks.UNNATURAL_LIGHT);
                        entries.add(ModBlocks.SOLID_METAL);
                        entries.add(ModBlocks.METAL_RAILING);
                        entries.add(ModBlocks.METAL_LADDER);
                        entries.add(ModBlocks.METAL_LADDER_TOP);
                        entries.add(ModBlocks.VENT_BLOCK);
                        ///entries.add(ModBlocks.PLASTIC_CHAIR);

                        entries.add(ModItems.RUBBERDUCK);

                        entries.add(ModBlocks.EPSOMITE_ORE);
                        entries.add(ModItems.EPSOM_SALT);

                        entries.add(ModBlocks.RUBBER_LOG);
                        entries.add(ModBlocks.RUBBER_WOOD);
                        entries.add(ModBlocks.RUBBER_PLANKS);
                        entries.add(ModBlocks.RUBBER_PLANKS_STAIRS);
                        entries.add(ModBlocks.RUBBER_PLANKS_SLAB);
                        entries.add(ModBlocks.RUBBER_PLANKS_FENCE);
                        entries.add(ModBlocks.STRIPPED_RUBBER_LOG);
                        entries.add(ModBlocks.STRIPPED_RUBBER_WOOD);
                        entries.add(ModBlocks.RUBBER_LEAVES);
                        entries.add(ModBlocks.RUBBER_SAPLING);
                        entries.add(ModItems.LATEX_BUCKET);
                        entries.add(ModBlocks.MARBLE_MAN);

                        entries.add(ModBlocks.TRIGGER_BLOCK);
                    }).build());
    public static void registerItemGroups() {
        WetRooms.LOGGER.info("Registering item tabs for " + WetRooms.MOD_ID);
    }
}
