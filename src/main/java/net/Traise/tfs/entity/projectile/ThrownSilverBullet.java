package net.Traise.tfs.entity.projectile;

import net.Traise.tfs.entity.ModEntities;
import net.Traise.tfs.item.TFSItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownSilverBullet extends AbstractArrow {
    public ThrownSilverBullet(EntityType<? extends ThrownSilverBullet> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownSilverBullet(Level pLevel, Player player) {
        this(ModEntities.SILVER_BULLET.get(), pLevel);
        this.setOwner(player);
        this.setPos(player.getX() - (double)(player.getBbWidth() + 1.0F) * 0.5D * (double)Mth.sin(player.yBodyRot * ((float)Math.PI / 180F)), player.getEyeY() - (double)0.1F, player.getZ() + (double)(player.getBbWidth() + 1.0F) * 0.5D * (double)Mth.cos(player.yBodyRot * ((float)Math.PI / 180F)));
    }

    @Override
    public void tick() {
        super.tick();
        setBaseDamage(1.25D);
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
            this.onHit(hitresult);

        if (this.inGround)
            this.discard();
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (pResult.getEntity().getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("tfs:undead")))) {
            pResult.getEntity().setSecondsOnFire(60);
        }

        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity livingentity) {
            pResult.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), 5.0F);
        }


    }

    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level().isClientSide) {
            this.discard();
        }

    }

    @Override
    protected ItemStack getPickupItem() {
        return TFSItems.ROCK.get().getDefaultInstance();
    }

    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
    }

    protected float getWaterInertia() {
        return 0.6F;
    }

    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }
}
