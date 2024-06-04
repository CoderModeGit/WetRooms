package net.juyoh.wetrooms;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.event.GenerateWorldCallback;
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
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.FillBiomeCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static net.juyoh.wetrooms.WetRoomsClient.CRT_SHADER;

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

			if (player.getWorld().getBlockState(player.getBlockPos()).getBlock() == ModBlocks.QUARTZ_DOOR && player.getServer() != null) {
				//player.sendMessage(Text.literal(player.getWorld().toString()));
				if (player.getWorld() == player.getServer().getOverworld()){
					player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY).setBlockState(new BlockPos(0, 100, 0), Blocks.OBSIDIAN.getDefaultState());
					HomeData.addHomePositionData((IEntityDataSaver) player, player.getBlockPos());
					player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), 0.5d, 110d, 0.5d, Set.of(), player.getYaw(), player.getPitch());
					UpdateCRT(player);
				} else {
					//player.moveToWorld(player.getServer().getOverworld());
					BlockPos HomeBlockPos = HomeData.getHomePositionData(((IEntityDataSaver) player));
					//player.sendMessage(Text.literal(HomeBlockPos.toString()));

					player.getServer().getOverworld().breakBlock(HomeBlockPos, false);
					player.teleport(player.getServer().getOverworld(), HomeBlockPos.getX() + 0.5, HomeBlockPos.getY() + 0.5, HomeBlockPos.getZ() + 0.5, Set.of(), player.getYaw(), player.getPitch());
					//player.teleport(0, 80, 0);
					UpdateCRT(player);
				}

			} else if (player.getWorld().getBlockState(player.getBlockPos().east(1)).getBlock() == ModBlocks.QUARTZ_DOOR && player.getServer() != null) {
				if (player.getWorld() == player.getServer().getOverworld()){
					player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY).setBlockState(new BlockPos(0, 100, 0), Blocks.OBSIDIAN.getDefaultState());
					HomeData.addHomePositionData((IEntityDataSaver) player, player.getBlockPos().east(1));
					player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), 0.5d, 110d, 0.5d, Set.of(), player.getYaw(), player.getPitch());
					UpdateCRT(player);
				} else {
					//player.moveToWorld(player.getServer().getOverworld());
					BlockPos HomeBlockPos = HomeData.getHomePositionData(((IEntityDataSaver) player));
					//player.sendMessage(Text.literal(HomeBlockPos.toString()));
					player.getServer().getOverworld().breakBlock(HomeBlockPos, false);
					player.teleport(player.getServer().getOverworld(), HomeBlockPos.getX() + 0.5, HomeBlockPos.getY() + 0.5, HomeBlockPos.getZ() + 0.5, Set.of(), player.getYaw(), player.getPitch());
					//player.teleport(0, 80, 0);
					UpdateCRT(player);

				}
			}
			return ActionResult.PASS;
		});


		GenerateWorldCallback.EVENT.register((server) -> {
			server.sendMessage(Text.literal("poop"));

			return ActionResult.PASS;
		});


	}

}