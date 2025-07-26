package net.Traise.tfs.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber
public class AddPlague {
    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        execute(event, event.getEntity());
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("black_5")
                && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getAmplifier() : 0) < 5
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getDuration() : 0) <= 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.BLACK_PLAGUE.get(), 12200, 5));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("black_4") && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getAmplifier() : 0) < 4
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getDuration() : 0) <= 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.BLACK_PLAGUE.get(), 12200, 4));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("black_3") && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getAmplifier() : 0) < 3
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.BLACK_PLAGUE.get(), 12200, 3));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("black_2")
                && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getAmplifier() : 0) < 2
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.BLACK_PLAGUE.get(), 12200, 2));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("black_1")
                && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getAmplifier() : 0) < 1
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.BLACK_PLAGUE.get(), 12200, 1));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("black_0")
                && (!(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(ModMobEffect.BLACK_PLAGUE.get()))
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.BLACK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.BLACK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.BLACK_PLAGUE.get(), 12200, 0));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("pink_3") && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.PINK_PLAGUE.get()).getAmplifier() : 0) < 3
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.PINK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.PINK_PLAGUE.get(), 12200, 3));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("pink_2") && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.PINK_PLAGUE.get()).getAmplifier() : 0) < 2
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.PINK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.PINK_PLAGUE.get(), 12200, 2));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("pink_1") && ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.PINK_PLAGUE.get()).getAmplifier() : 0) < 1
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.PINK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.PINK_PLAGUE.get(), 12200, 1));
        }
        else if ((entity instanceof LivingEntity _teamEnt && _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()) != null
                ? _teamEnt.level().getScoreboard().getPlayersTeam(_teamEnt instanceof Player _pl ? _pl.getGameProfile().getName() : _teamEnt.getStringUUID()).getName()
                : "").equals("pink_0")
                && (!(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(ModMobEffect.PINK_PLAGUE.get()))
                || (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffect.PINK_PLAGUE.get()) ? _livEnt.getEffect(ModMobEffect.PINK_PLAGUE.get()).getDuration() : 0) < 12000)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffect.PINK_PLAGUE.get(), 12200, 0));
        }
    }
}
