package net.juyoh.wetrooms.camera;

import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.WetRoomsClient;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.world.dimension.ModDimensions;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Set;

public class StartingCutscene {
    public static void Start(PlayerEntity player) {
        player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), 1.5d, 35d, -5.5d, Set.of(), -90.0f, 90f);
        WetRoomsClient.CRT_ON();
        //player.getWorld().setBlockState(new BlockPos(1, 21, -6), ModBlocks.LOGO.getDefaultState());
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 99999999, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
    }
}
