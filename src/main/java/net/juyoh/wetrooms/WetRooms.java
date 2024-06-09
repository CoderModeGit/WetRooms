package net.juyoh.wetrooms;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.event.PlayerJoinCallback;
import net.juyoh.wetrooms.event.PlayerWalkCallback;
import net.juyoh.wetrooms.event.RightClickBlockHandler;
import net.juyoh.wetrooms.item.ModItems;
import net.juyoh.wetrooms.sound.ModSounds;
import net.juyoh.wetrooms.util.HomeData;
import net.juyoh.wetrooms.util.IEntityDataSaver;
import net.juyoh.wetrooms.world.dimension.ModDimensions;
import net.juyoh.wetrooms.world.gen.ModWorldGeneration;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class WetRooms implements ModInitializer {
	public static final String MOD_ID = "wetrooms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void UpdateCRT(PlayerEntity player) {
		if (player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY) == player.getWorld()) {
			WetRoomsClient.CRT_ON();
		} else {
			WetRoomsClient.CRT_OFF();
		}
	}

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

		PlayerWalkCallback.EVENT.register((player) -> {
			ServerPlayerEntity player_server = null;
			if (player.getServer() != null) {
				player_server = player.getServer().getPlayerManager().getPlayer(player.getUuid());
			}
			if (player.getWorld().getBlockState(player.getBlockPos()).getBlock() == ModBlocks.QUARTZ_DOOR && player.getServer() != null) {

				//player.sendMessage(Text.literal(player.getWorld().toString()));
				if (player.getWorld() == player.getServer().getOverworld()){
					HomeData.addHomePositionData((IEntityDataSaver) player, player.getBlockPos());
					player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), -5.5d, 26d, 0.5d, Set.of(), player.getYaw(), player.getPitch());
					UpdateCRT(player);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 99999999, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
				} else {
					//player.moveToWorld(player.getServer().getOverworld());
					BlockPos HomeBlockPos = HomeData.getHomePositionData(((IEntityDataSaver) player));
					//player.sendMessage(Text.literal(HomeBlockPos.toString()));

					player.getServer().getOverworld().breakBlock(HomeBlockPos, false);
					player.teleport(player.getServer().getOverworld(), HomeBlockPos.getX() + 0.5, HomeBlockPos.getY() + 0.5, HomeBlockPos.getZ() + 0.5, Set.of(), player.getYaw(), player.getPitch());
					//player.teleport(0, 80, 0);

					//When player leaves
					UpdateCRT(player);
					player.removeStatusEffect(StatusEffects.JUMP_BOOST);
					if (!player.isCreative() && !player.isSpectator()) {
                        assert player_server != null;
                        player_server.changeGameMode(GameMode.SURVIVAL);}
				}

			} else if (player.getWorld().getBlockState(player.getBlockPos().east(1)).getBlock() == ModBlocks.QUARTZ_DOOR && player.getServer() != null) {
				if (player.getWorld() == player.getServer().getOverworld()){
					HomeData.addHomePositionData((IEntityDataSaver) player, player.getBlockPos().east(1));
					player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), -5.5d, 26d, 0.5d, Set.of(), player.getYaw(), player.getPitch());
					UpdateCRT(player);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 99999999, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));



				} else {
					//player.moveToWorld(player.getServer().getOverworld());
					BlockPos HomeBlockPos = HomeData.getHomePositionData(((IEntityDataSaver) player));
					//player.sendMessage(Text.literal(HomeBlockPos.toString()));
					player.getServer().getOverworld().breakBlock(HomeBlockPos, false);
					player.teleport(player.getServer().getOverworld(), HomeBlockPos.getX() + 0.5, HomeBlockPos.getY() + 0.5, HomeBlockPos.getZ() + 0.5, Set.of(), player.getYaw(), player.getPitch());
					//player.teleport(0, 80, 0);

					//When player leaves
					UpdateCRT(player);
					player.removeStatusEffect(StatusEffects.JUMP_BOOST);
					if (!player.isCreative() && !player.isSpectator()) {
						assert player_server != null;
						player_server.changeGameMode(GameMode.SURVIVAL);}

				}
			}

			if (player.getWorld().getBlockState(player.getBlockPos()).getBlock() == ModBlocks.TRIGGER_BLOCK) {
				//Rinse effect
				player.playSound(ModSounds.WETROOMS_RINSE, 1f ,1f);
				player.sendMessage(Text.literal("ham bor ger"));
			}
			if (player.getWorld().getBlockState(player.getBlockPos()).getBlock() == Blocks.WATER) {
				//Rinse effect
				player.playSound(ModSounds.LOUDER_WADING, 0.6f ,0.6f);
			}




			return ActionResult.PASS;
		});


		PlayerJoinCallback.EVENT.register((server) -> {
			server.sendMessage(Text.literal("poop"));

			return ActionResult.PASS;
		});


		ServerPlayConnectionEvents.JOIN.register(((handler, sender, server) -> {
			UpdateCRT(handler.getPlayer());
		}));


	}

}