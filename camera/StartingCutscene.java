package net.juyoh.wetrooms.camera;

import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.WetRoomsClient;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.util.AOData;
import net.juyoh.wetrooms.util.IEntityDataSaver;
import net.juyoh.wetrooms.world.dimension.ModDimensions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Set;

public class StartingCutscene {
    public static void Start(PlayerEntity player) {
        player.teleport(player.getServer().getWorld(ModDimensions.WETROOMS_LEVEL_KEY), 1.5d, 35d, -5.5d, Set.of(), -90.0f, 90f);
        if (MinecraftClient.getInstance().options.getAo().getValue()) { //If the player HAS AO and Smooth Lighting
            AOData.addAOData((IEntityDataSaver) player, Boolean.TRUE); //Make a note that they did
            //MinecraftClient.getInstance().options.getAo().setValue(Boolean.FALSE); //Turn it off
        } else { //If the player DOES NOT have AO and Smooth Lighting (Weird)
            AOData.addAOData((IEntityDataSaver) player, Boolean.FALSE); //Make a note that they didn't
        }


        //player.getWorld().setBlockState(new BlockPos(1, 21, -6), ModBlocks.LOGO.getDefaultState());
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100, 128, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));

    }
}
