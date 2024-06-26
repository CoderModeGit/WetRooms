package net.juyoh.wetrooms.entity.client;

import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.entity.custom.PuddleEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;

public class PuddleRenderer extends MobEntityRenderer<PuddleEntity, PuddleModel> {
    private static final Identifier TEXTURE = new Identifier(WetRooms.MOD_ID, "textures/entity/puddle/puddle.png");
    public PuddleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PuddleModel(ctx.getPart(ModModelLayers.PUDDLE)), 0f);
    }

    public void render(PuddleEntity puddleEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.scale(1f, 1f, 1f);

        super.render(puddleEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(PuddleEntity entity) {
        return TEXTURE;
    }
}
