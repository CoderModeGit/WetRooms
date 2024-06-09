package net.juyoh.wetrooms.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;

public interface PlayerJoinCallback {

    Event<PlayerJoinCallback> EVENT = EventFactory.createArrayBacked(PlayerJoinCallback.class,
            (listeners) -> (server) -> {
                for (PlayerJoinCallback listener : listeners) {
                    ActionResult result = listener.interact(server);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(MinecraftServer server);


}