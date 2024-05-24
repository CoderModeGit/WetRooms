package net.juyoh.wetrooms.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;

public interface GenerateWorldCallback {

    Event<GenerateWorldCallback> EVENT = EventFactory.createArrayBacked(GenerateWorldCallback.class,
            (listeners) -> (server) -> {
                for (GenerateWorldCallback listener : listeners) {
                    ActionResult result = listener.interact(server);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(MinecraftServer server);


}