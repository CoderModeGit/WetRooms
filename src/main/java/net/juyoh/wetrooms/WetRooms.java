package net.juyoh.wetrooms;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.effect.WetShoesEffect;
import net.juyoh.wetrooms.entity.custom.PuddleEntity;
import net.juyoh.wetrooms.event.PlayerJoinCallback;
import net.juyoh.wetrooms.event.PlayerWalkCallback;
import net.juyoh.wetrooms.event.RightClickBlockHandler;
import net.juyoh.wetrooms.item.ModItems;
import net.juyoh.wetrooms.sound.ModSounds;
import net.juyoh.wetrooms.util.HomeData;
import net.juyoh.wetrooms.util.IEntityDataSaver;
import net.juyoh.wetrooms.world.biome.ModBiomes;
import net.juyoh.wetrooms.world.dimension.ModDimensions;
import net.juyoh.wetrooms.world.gen.ModWorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.JigsawBlock;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.block.enums.JigsawOrientation;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.PlaceCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.GameMode;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.structure.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.undo.CompoundEdit;
import java.util.Map;
import java.util.Set;

public class WetRooms implements ModInitializer {
	public static final String MOD_ID = "wetrooms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final StatusEffect WETSHOES = new WetShoesEffect();

	public void UpdateCRT(PlayerEntity player) {
		if (player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY) == player.getWorld()) {
			WetRoomsClient.CRT_ON();
		} else {
			WetRoomsClient.CRT_OFF();
		}
	}
	public void PlaceStructure(ServerWorld serverWorld) {
		RegistryEntry.Reference<Structure> structureRegistryEntry = serverWorld.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY).getRegistryManager().get(RegistryKeys.STRUCTURE).getEntry(RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(MOD_ID, "wetrooms_structure"))).get();
		Structure structure2 = structureRegistryEntry.value();
		ChunkGenerator chunkGenerator = serverWorld.getChunkManager().getChunkGenerator();
		StructureStart structureStart = structure2.createStructureStart(serverWorld.getRegistryManager(), chunkGenerator, chunkGenerator.getBiomeSource(), serverWorld.getChunkManager().getNoiseConfig(), serverWorld.getStructureTemplateManager(), serverWorld.getSeed(), new ChunkPos(new BlockPos(0, 0, 0)), 0, serverWorld, biome -> true);
		BlockBox blockBox = structureStart.getBoundingBox();

		ChunkPos chunkPos = new ChunkPos(ChunkSectionPos.getSectionCoord(blockBox.getMinX()), ChunkSectionPos.getSectionCoord(blockBox.getMinZ()));
		ChunkPos chunkPos2 = new ChunkPos(ChunkSectionPos.getSectionCoord(blockBox.getMaxX()), ChunkSectionPos.getSectionCoord(blockBox.getMaxZ()));
		ChunkPos.stream(chunkPos, chunkPos2).forEach(chunkPosx -> {
			structureStart.place(serverWorld, serverWorld.getStructureAccessor(), chunkGenerator, serverWorld.getRandom(), new BlockBox(chunkPosx.getStartX(),serverWorld.getBottomY(), chunkPosx.getStartZ(), chunkPosx.getEndX(), serverWorld.getTopY(), chunkPosx.getEndZ()), chunkPosx);
		});


	}

	public static final EntityType<PuddleEntity> PUDDLE = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(WetRooms.MOD_ID, "puddle"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, PuddleEntity::new).dimensions(EntityDimensions.fixed(1f, 0.2f)).build());

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

		FabricDefaultAttributeRegistry.register(PUDDLE, PuddleEntity.createPuddleAttributes());





		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "wet_shoes"), WETSHOES);

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
					player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), 6.5d, 28d, 1.5d, Set.of(), player.getYaw(), player.getPitch());
					UpdateCRT(player);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 99999999, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));

					if (player.getServer() != null && player.getWorld().getBlockState(new BlockPos(2, 26, -6)).getBlock() == Blocks.AIR){
						PlaceStructure(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY));
					}


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
				player.addVelocity(-5d, 0d, 0d);
				}

			} else if (player.getWorld().getBlockState(player.getBlockPos().east(1)).getBlock() == ModBlocks.QUARTZ_DOOR && player.getServer() != null) {
				if (player.getWorld() == player.getServer().getOverworld()){
					HomeData.addHomePositionData((IEntityDataSaver) player, player.getBlockPos().east(1));
					player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), -0.5d, 28d, -6.5d, Set.of(), player.getYaw(), player.getPitch());
					UpdateCRT(player);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 99999999, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));

					if (player.getServer() != null && player.getWorld().getBlockState(new BlockPos(2, 26, -6)).getBlock() == Blocks.AIR){
						PlaceStructure(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY));
					}





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
					player.addVelocity(-5d, 0d, 0d);

				}
			}

			if (player.getWorld().getBlockState(player.getBlockPos()).getBlock() == ModBlocks.TRIGGER_BLOCK) {
				//Trigger sound
				if (player.getWorld().getBlockState(player.getBlockPos()).get(Properties.LEVEL_1_8) == 1) {
					player.playSound(ModSounds.WETROOMS_RINSE, 1f ,1f);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 120, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));

					player.getWorld().addBlockBreakParticles(player.getBlockPos().up(1), Blocks.GRAY_WOOL.getDefaultState());
					player.getWorld().addBlockBreakParticles(player.getBlockPos().up(1).north(), Blocks.GRAY_WOOL.getDefaultState());
					player.getWorld().addBlockBreakParticles(player.getBlockPos().up(1).east(), Blocks.GRAY_WOOL.getDefaultState());
					player.getWorld().addBlockBreakParticles(player.getBlockPos().up(1).south(), Blocks.GRAY_WOOL.getDefaultState());
					player.getWorld().addBlockBreakParticles(player.getBlockPos().up(1).west(), Blocks.GRAY_WOOL.getDefaultState());
					if (player.getServer() != null) {
						PlaceStructure(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY));
						//RegistryEntry.Reference<Structure> structureRegistryEntry = player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY).getRegistryManager().get(RegistryKeys.STRUCTURE).getEntry(RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(MOD_ID, "wetrooms_structure"))).get();
						//ServerCommandSource commandSource = player.getServer().getCommandSource();
						//PlaceCommand.executePlaceStructure(commandSource,  structureRegistryEntry, new BlockPos(200, 200, 200));
					}

				}
				if (player.getWorld().getBlockState(player.getBlockPos()).get(Properties.LEVEL_1_8) == 2) {player.playSound(ModSounds.WETROOMS_TRAIN, 1f ,1f);}
				if (player.getWorld().getBlockState(player.getBlockPos()).get(Properties.LEVEL_1_8) == 3) {player.playSound(ModSounds.WETROOMS_BUILDUP, 1f ,1f);}
				if (player.getWorld().getBlockState(player.getBlockPos()).get(Properties.LEVEL_1_8) == 4) {player.playSound(ModSounds.WETROOMS_WAVE, 1f ,1f);}

			}
		
			if (player.getWorld().getBlockState(player.getBlockPos()).getBlock() == Blocks.WATER && player.getWorld().getBiome(player.getBlockPos()).getKey().get() == ModBiomes.WETROOMS_BIOME) {
				//Rinse effect
				player.playSound(ModSounds.LOUDER_WADING, 0.6f ,0.6f);
				player.addStatusEffect(new StatusEffectInstance(WETSHOES, 160, 1, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
			}

			if (player.hasStatusEffect(WETSHOES) && (player.getWorld().getBlockState(player.getBlockPos()).getBlock() != Blocks.WATER)) {
				PuddleEntity puddle = new PuddleEntity(WetRooms.PUDDLE, player.getWorld());
				puddle.setPos(player.getX(), player.getY(), player.getZ());
				player.getWorld().spawnEntity(puddle);
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