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
                        entries.add(ModBlocks.OBSIDIAN_TILES);
                        entries.add(ModBlocks.UNNATURAL_LIGHT);
                        entries.add(ModItems.RUBBERDUCK);
                    }).build());
    public static void registerItemGroups() {
        WetRooms.LOGGER.info("Registering item tabs for " + WetRooms.MOD_ID);
    }
}
