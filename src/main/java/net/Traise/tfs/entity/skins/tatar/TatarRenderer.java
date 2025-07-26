package net.Traise.tfs.entity.skins.tatar;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.Traise.tfs.entity.model.ModModelLayers;
import net.Traise.tfs.tfs;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class TatarRenderer extends MobRenderer<TatarEntity, TatarModel<TatarEntity>> {
    public static final Map<TatarVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(TatarVariant.class), (p_114874_) -> {
                p_114874_.put(TatarVariant.TATAR,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar.png"));
                p_114874_.put(TatarVariant.SNOW,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow.png"));
                p_114874_.put(TatarVariant.DED,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/ded.png"));
                p_114874_.put(TatarVariant.DED_WHITE,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/ded_white.png"));
                p_114874_.put(TatarVariant.HEROBRINE,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/herobrine.png"));
                p_114874_.put(TatarVariant.SNOW_WHITE,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow_white.png"));
                p_114874_.put(TatarVariant.SNOW_BLACK_0,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow_black_0.png"));
                p_114874_.put(TatarVariant.SNOW_BLACK_1,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow_black_1.png"));
                p_114874_.put(TatarVariant.SNOW_BLACK_2,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow_black_2.png"));
                p_114874_.put(TatarVariant.SNOW_BLACK_3,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow_black_3.png"));
                p_114874_.put(TatarVariant.SNOW_BLACK_4,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow_black_4.png"));
                p_114874_.put(TatarVariant.SNOW_BLACK_5,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/snow_black_5.png"));
                p_114874_.put(TatarVariant.TATAR_WHITE,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar_white.png"));
                p_114874_.put(TatarVariant.TATAR_BLACK_0,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar_black_0.png"));
                p_114874_.put(TatarVariant.TATAR_BLACK_1,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar_black_1.png"));
                p_114874_.put(TatarVariant.TATAR_BLACK_2,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar_black_2.png"));
                p_114874_.put(TatarVariant.TATAR_BLACK_3,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar_black_3.png"));
                p_114874_.put(TatarVariant.TATAR_BLACK_4,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar_black_4.png"));
                p_114874_.put(TatarVariant.TATAR_BLACK_5,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/tatar_black_5.png"));
            });


    public TatarRenderer(EntityRendererProvider.Context context) {
        super(context, new TatarModel<>(context.bakeLayer(ModModelLayers.TATAR_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(TatarEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(TatarEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}

