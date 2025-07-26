package net.Traise.tfs.potion;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.phys.Vec3;

public class VertigoEffect extends MobEffect {
    private static float y;
    private static float x;
    public VertigoEffect() {
        super(MobEffectCategory.NEUTRAL, -3355444);

    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        y = Mth.nextFloat(RandomSource.create(), -1, 1);
        x = Mth.nextFloat(RandomSource.create(), -0.3f, 0.3f);
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        float Y = amplifier * y;
        float X = amplifier * x;

        entity.setYRot(entity.getYRot() + Y);
        entity.setXRot(entity.getXRot() + X);
        entity.setYBodyRot(entity.getYRot());
        entity.setYHeadRot(entity.getYRot());
        entity.yRotO = entity.getYRot();
        entity.xRotO = entity.getXRot();
        entity.yBodyRotO = entity.getYRot();

    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
