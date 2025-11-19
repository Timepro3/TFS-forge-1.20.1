package net.Traise.tfs.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Traise.tfs.entity.model.ModModelLayers;
import net.Traise.tfs.entity.model.SpearModel;
import net.Traise.tfs.entity.model.WoodSpearModel;
import net.Traise.tfs.entity.projectile.ThrownSpear;
import net.Traise.tfs.entity.projectile.ThrownWoodSpear;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import org.w3c.dom.css.RGBColor;

public class SpearRenderer extends EntityRenderer<ThrownSpear> {
    public static final ResourceLocation SPEAR = new ResourceLocation("tfs:textures/entity/wood_spear.png");
    private final SpearModel bone;

    public SpearRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.bone = new SpearModel(pContext.bakeLayer(ModModelLayers.SPEAR_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownSpear pEntity) {
        return SPEAR;
    }

    @Override
    public void render(ThrownSpear pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        VertexConsumer vb = pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pEntity)));
        pMatrixStack.pushPose();
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90));
        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));
        bone.renderToBuffer(pMatrixStack, vb, pPackedLight, OverlayTexture.NO_OVERLAY, 91, 86, 217, 0.8f);
        pMatrixStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
