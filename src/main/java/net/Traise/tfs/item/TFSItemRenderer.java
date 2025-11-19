package net.Traise.tfs.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Traise.tfs.entity.model.SpearModel;
import net.Traise.tfs.entity.renderer.SpearRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TFSItemRenderer extends BlockEntityWithoutLevelRenderer {
    private SpearModel spearModel;
    private final EntityModelSet entityModelSet = Minecraft.getInstance().getEntityModels();

    public TFSItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.spearModel = new SpearModel(this.entityModelSet.bakeLayer(SpearModel.SPEAR));
    }

    public void m_6213_(@NotNull ResourceManager resourceManager) {
        this.spearModel = new SpearModel(this.entityModelSet.bakeLayer(SpearModel.SPEAR));
    }

    public void m_108829_(ItemStack stack, @NotNull ItemDisplayContext context, @NotNull PoseStack pose, @NotNull MultiBufferSource source, int light, int overlay) {
        pose.popPose();
        pose.scale(1.0F, -1.0F, -1.0F);
        ResourceLocation layerLocation = SpearRenderer.SPEAR;
        VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(source, RenderType.entityCutout(layerLocation), false, stack.hasFoil());
        this.spearModel.renderToBuffer(pose, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pose.pushPose();
    }
}
