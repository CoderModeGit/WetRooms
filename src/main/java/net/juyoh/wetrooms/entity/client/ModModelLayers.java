package net.juyoh.wetrooms.entity.client;

import net.juyoh.wetrooms.WetRooms;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static EntityModelLayer PUDDLE = new EntityModelLayer(new Identifier(WetRooms.MOD_ID, "puddle"), "main");
}
