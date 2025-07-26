package net.Traise.tfs.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Traise.tfs.entity.model.BronzeSpearModel;
import net.Traise.tfs.entity.model.ModModelLayers;
import net.Traise.tfs.entity.projectile.ThrownBronzeSpear;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BronzeSpearRenderer extends EntityRenderer<ThrownBronzeSpear> {
    private final BronzeSpearModel bone;

    public BronzeSpearRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.bone = new BronzeSpearModel(pContext.bakeLayer(ModModelLayers.BRONZE_SPEAR_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownBronzeSpear pEntity) {
        return new ResourceLocation("tfs:textures/entity/bronze_spear.png");
    }

    @Override
    public void render(ThrownBronzeSpear pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        VertexConsumer vb = pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pEntity)));
        pMatrixStack.pushPose();
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90));
        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));
        bone.renderToBuffer(pMatrixStack, vb, pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        pMatrixStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
