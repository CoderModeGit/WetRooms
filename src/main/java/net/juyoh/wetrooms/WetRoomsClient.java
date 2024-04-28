package net.juyoh.wetrooms;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.juyoh.wetrooms.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class WetRoomsClient implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMMISION_LINEAR, RenderLayer.getTranslucent());
    }
}
