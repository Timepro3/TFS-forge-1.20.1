package net.Traise.tfs.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Traise.tfs.entity.model.ModModelLayers;
import net.Traise.tfs.entity.model.SilverBulletModel;
import net.Traise.tfs.entity.projectile.ThrownSilverBullet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SilverBulletRenderer extends EntityRenderer<ThrownSilverBullet> {
    private final SilverBulletModel bone;

    public SilverBulletRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.bone = new SilverBulletModel(pContext.bakeLayer(ModModelLayers.SILVER_BULLET_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownSilverBullet pEntity) {
        return new ResourceLocation("tfs:textures/entity/silver_bullet.png");
    }

    @Override
    public void render(ThrownSilverBullet pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
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
