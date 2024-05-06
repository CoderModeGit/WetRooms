package net.juyoh.wetrooms;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.juyoh.wetrooms.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;


public class WetRoomsClient implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        WetRooms.LOGGER.info("client init");
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBBER_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBBER_SAPLING, RenderLayer.getCutout());
    }
}
