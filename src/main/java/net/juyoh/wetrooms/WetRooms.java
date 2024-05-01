package net.juyoh.wetrooms;

import net.fabricmc.api.ModInitializer;

import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.item.ModItems;
import net.juyoh.wetrooms.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WetRooms implements ModInitializer {
	public static final String MOD_ID = "wetrooms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
	}
}