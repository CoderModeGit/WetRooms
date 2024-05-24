package net.juyoh.wetrooms.event;

import com.mojang.brigadier.ParseResults;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.item.ModItems;
import net.juyoh.wetrooms.world.biome.ModBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.FillBiomeCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.BuiltinBiomes;

import java.util.Objects;

public class RightClickBlockHandler implements UseBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if(!player.isSpectator() && !world.isClient()){
            HitResult lookingat = MinecraftClient.getInstance().crosshairTarget;
            if (lookingat != null) {
                BlockPos pos = ((BlockHitResult) lookingat).getBlockPos();
                BlockPos pos_up = pos.up(1);
                BlockPos pos_left = pos_up.north(3).east(3);
                BlockPos pos_right = pos_up.north(-3).east(-3);
                //player.sendMessage(Text.literal(world.getBlockState(pos).toString()));
                if (world.getBlockState(pos).getBlock() == ModBlocks.RUBBER_LOG && player.getStackInHand(hand).isOf(Items.BUCKET) ) {
                    player.setStackInHand(hand, new ItemStack(ModItems.LATEX_BUCKET));
                }

                //WetRooms.LOGGER.info(world.getBiome(pos_up).getKey().get().toString());
                //WetRooms.LOGGER.info(ModBiomes.WETROOMS_BIOME.toString());
                //if (Text.literal(ModBiomes.WETROOMS_BIOME.toString()) == Text.literal(world.getBiome(pos_up).getKey().get().toString()) ) {
                //    player.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1f, 1f);
                //    player.sendMessage(Text.literal("yes"));
                //}
                //player.sendMessage(Text.literal(ModBiomes.WETROOMS_BIOME.toString()));
                //player.sendMessage(Text.literal(world.getBiome(pos_up).getKey().get().toString()));
                if ((world.getBlockState(pos_up).getBlock() == Blocks.WATER) && player.getStackInHand(hand).isOf(ModItems.EPSOM_SALT) && (world.getBiome(pos_up).getKey().get().toString() != ModBiomes.WETROOMS_BIOME.toString() && !player.isSneaking())) {
                    if (!player.isCreative()) {
                        //Take away one EPSOM_SALT
                    }
                    //WetRooms.LOGGER.info("fillbiome " + pos_left.getX() + " " + pos_left.getY() + " " + pos_left.getX() + " " + pos_right.getZ() + " " + pos_right.getY() + " " + pos_right.getZ() + " wetrooms:wetrooms_biome");
                    //FillBiomeCommand. pos_left.getX() pos_left.getY() pos_left.getZ() pos_right.getX()  pos_right.getY() pos_right.getZ();
                    world.playSound(null, pos_up, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1f, 1f);

                }
            }
        }return ActionResult.PASS;


    }
}
