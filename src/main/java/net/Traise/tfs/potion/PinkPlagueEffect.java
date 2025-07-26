package net.Traise.tfs.potion;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class PinkPlagueEffect extends MobEffect {
    public PinkPlagueEffect() {
        super(MobEffectCategory.NEUTRAL, -16777216);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if ((entity.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? entity.getEffect(ModMobEffect.PINK_PLAGUE.get()).getAmplifier() : 0) == 1) {
            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 1));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 0));
            }
        }
        else if ((entity.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? entity.getEffect(ModMobEffect.PINK_PLAGUE.get()).getAmplifier() : 0) == 2) {
            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 2));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 1));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.setTicksFrozen(100);
            }
        }
        else if ((entity.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? entity.getEffect(ModMobEffect.PINK_PLAGUE.get()).getAmplifier() : 0) >= 3) {
            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 3));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 2));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 2));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.setTicksFrozen(100);
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 600, 0));
            }

            if (Mth.nextInt(RandomSource.create(), 1, 2400) == 1) {
                if (!entity.level().isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 0));
            }
        }
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
