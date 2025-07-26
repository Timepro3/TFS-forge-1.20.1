package net.Traise.tfs.entity.skins.alise;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.Traise.tfs.entity.model.ModModelLayers;
import net.Traise.tfs.tfs;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.lang.reflect.Method;
import java.util.Map;

public class AliseRenderer extends MobRenderer<AliseEntity, AliseModel<AliseEntity>> {
    public static final Map<AliseVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(AliseVariant.class), (p_114874_) -> {
                p_114874_.put(AliseVariant.ALISE,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/alice.png"));
                p_114874_.put(AliseVariant.ALISE_WHITE,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/alice_white.png"));
                p_114874_.put(AliseVariant.ALISE_WHITE_0,
                        new ResourceLocation(tfs.MOD_ID, "textures/entity/skins/alice_white_0.png"));
            });


    public AliseRenderer(EntityRendererProvider.Context context) {
        super(context, new AliseModel<>(context.bakeLayer(ModModelLayers.ALISE_LAYER)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new ElytraLayer<>(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(AliseEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(AliseEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}

