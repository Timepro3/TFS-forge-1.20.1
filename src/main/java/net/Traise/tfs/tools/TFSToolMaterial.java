package net.Traise.tfs.tools;

import net.Traise.tfs.item.TFSArmorMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryBuilder;

public class TFSToolMaterial {
    public final int color;
    public final int mineLevel;
    public final int durability;
    public final float mineSpeed;
    public final float attackDamage;
    public final float attackSpeed;
    public final int enchantmentValue;

    public TFSToolMaterial(int color, int mineLevel, int durability, float mineSpeed, float attackDamage, float attackSpeed, int enchantmentValue) {
        this.color = color;
        this.mineLevel = mineLevel;
        this.durability = durability;
        this.mineSpeed = mineSpeed;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.enchantmentValue = enchantmentValue;
    }

    public static void save(CompoundTag pCompoundTag, TFSToolMaterial material) {
        pCompoundTag.putString("mat", TFSRegistries.TOOL_MATERIAL.get().getKey(material).toString());
    }

    public static TFSToolMaterial load(CompoundTag pCompoundTag) {
        if (pCompoundTag != null) {
            TFSToolMaterial material = TFSRegistries.TOOL_MATERIAL.get().getValue(new ResourceLocation(pCompoundTag.getString("mat")));
            if (material != null) {
                return material;
            }
        }
        return TFSToolMaterials.NONE.get();
    }

    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        return true;
    }

    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        return true;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        return InteractionResultHolder.consume(itemStack);
    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {

    }

    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.CONSUME;
    }
}
