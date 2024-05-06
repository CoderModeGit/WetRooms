package net.juyoh.wetrooms;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.event.RightClickBlockHandler;
import net.juyoh.wetrooms.item.ModItems;
import net.juyoh.wetrooms.sound.ModSounds;
import net.juyoh.wetrooms.world.gen.ModWorldGeneration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
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

		ModWorldGeneration.generateModWorlGen();

		StrippableBlockRegistry.register(ModBlocks.RUBBER_LOG, ModBlocks.STRIPPED_RUBBER_LOG);
		StrippableBlockRegistry.register(ModBlocks.RUBBER_WOOD, ModBlocks.STRIPPED_RUBBER_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RUBBER_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_LEAVES, 30, 60);

		RightClickBlockHandler.EVENT.register(new RightClickBlockHandler());




	}
}