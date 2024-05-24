package net.juyoh.wetrooms.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.juyoh.wetrooms.WetRooms;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerWalkCallback {

    Event<PlayerWalkCallback> EVENT = EventFactory.createArrayBacked(PlayerWalkCallback.class,
            (listeners) -> (player) -> {
                for (PlayerWalkCallback listener : listeners) {
                    ActionResult result = listener.interact(player);
                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(PlayerEntity player);


}