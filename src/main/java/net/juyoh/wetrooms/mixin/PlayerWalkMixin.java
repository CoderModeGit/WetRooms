package net.juyoh.wetrooms.mixin;

import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.event.PlayerWalkCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerWalkMixin {
    @Inject(method = "playStepSound", at = @At("HEAD"))
    private void playStepSound(CallbackInfo ci) {
        ActionResult result = PlayerWalkCallback.EVENT.invoker().interact((PlayerEntity)(Object)this);
        if (result == ActionResult.FAIL) {
            // Cancel the player's movement
            // ...
        }
    }

}