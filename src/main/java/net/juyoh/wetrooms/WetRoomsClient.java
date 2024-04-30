package net.juyoh.wetrooms;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.juyoh.wetrooms.block.ModBlocks;


public class WetRoomsClient implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        WetRooms.LOGGER.info("client init");
    }
}
