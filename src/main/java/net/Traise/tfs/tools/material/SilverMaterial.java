package net.Traise.tfs.tools.material;

import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.tools.TFSToolMaterial;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;

public class SilverMaterial extends TFSToolMaterial {
    public SilverMaterial(int color, int mineLevel, int durability, float mineSpeed, float attackDamage, float attackSpeed, int enchantmentValue) {
        super(color, mineLevel, durability, mineSpeed, attackDamage, attackSpeed, enchantmentValue);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pTarget.getMobType().equals(MobType.UNDEAD)) {
            pTarget.setSecondsOnFire((int) pTarget.getMaxHealth());
            pTarget.hurt(pAttacker.damageSources().magic(), (float) Math.min(MaterialStorageHandler.getAllAttackDamage(pStack) * 2, pTarget.getMaxHealth() * 0.10f));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
