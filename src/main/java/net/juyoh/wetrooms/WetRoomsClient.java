package net.juyoh.wetrooms;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.juyoh.wetrooms.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.network.ClientConnection;
import net.minecraft.util.Identifier;


public class WetRoomsClient implements ClientModInitializer{

        public static final ManagedShaderEffect CRT_SHADER = ShaderEffectManager.getInstance()
                .manage(new Identifier("minecraft", "shaders/post/scan_pincushion.json"));
        private static boolean enabled = false;  // can be disabled whenever you want

        public static void CRT_ON() {
            enabled = true;
        }
        public static void CRT_OFF() {
            enabled = false;
        }
        @Override
        public void onInitializeClient() {
            // the render method of the shader will be called after the game
            // has drawn the world on the main framebuffer, when it renders
            // vanilla post process shaders
            ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
                if (enabled) {
                    CRT_SHADER.render(tickDelta);
                }
            });
            WetRooms.LOGGER.info("client init");
            BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBBER_LEAVES, RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBBER_SAPLING, RenderLayer.getCutout());
        }


}
