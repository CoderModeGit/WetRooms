package net.juyoh.wetrooms.mixin;

import net.juyoh.wetrooms.event.GenerateWorldCallback;
import net.juyoh.wetrooms.event.PlayerWalkCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class GenerateWorldMixin {
    @Inject(method = "loadWorld", at = @At("HEAD"))
    private void onWorldGenerate(CallbackInfo ci) {
        ActionResult result = GenerateWorldCallback.EVENT.invoker().interact((MinecraftServer)(Object)this);
        if (result == ActionResult.FAIL) {
            // Cancel the player's movement
            // ...
        }
    }

}