package net.juyoh.wetrooms.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.juyoh.wetrooms.WetRooms;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item RUBBERDUCK = registerItem("rubberduck", new Item(new FabricItemSettings()));
    public static final Item LATEX_BUCKET = registerItem("latex_bucket", new Item(new FabricItemSettings()));
    public static final Item EPSOM_SALT = registerItem("epsom_salt", new Item(new FabricItemSettings()));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WetRooms.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WetRooms.LOGGER.info("Registering mod items for " + WetRooms.MOD_ID);
    }
}
