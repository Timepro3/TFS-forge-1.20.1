package net.Traise.tfs.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.Traise.tfs.datagen.ModItemModelProvider;
import net.Traise.tfs.entity.projectile.ThrownSpear;
import net.Traise.tfs.item.TFSItems;
import net.Traise.tfs.tools.MaterialStorageHandler;
import net.Traise.tfs.tools.TFSToolMaterial;
import net.Traise.tfs.tools.TFSToolMaterials;
import net.Traise.tfs.util.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Set.of;
import static net.minecraft.world.item.HoeItem.changeIntoState;

@Mod.EventBusSubscriber
public class TFSBaseItem extends Item implements Vanishable, ICustomItemModel {
    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    private final TFSToolTypes toolType;
    protected static final UUID BASE_BLOCK_RANGE_UUID = UUID.fromString("7f7dbdb2-0d0d-458a-aa40-ac7633691f66");
    protected static final UUID BASE_ENTITY_RANGE_UUID = UUID.fromString("7f6dbdb1-0d0d-438a-aa40-ac7622691f56");
    private static final String TAG_PLACING_SEED = "placing_seed";
    private static final String TAG_LAST_STACK = "last_stack";

    public TFSBaseItem(TFSToolTypes pToolType, Properties pProperties) {
        super(pProperties.durability(100));

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
        this.toolType = pToolType;
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
        }

        if (pLivingEntity instanceof Player player) {
            if (this.toolType.equals(TFSToolTypes.SPEAR)) {
                int i = this.getUseDuration(pStack) - pTimeCharged;
                if (i >= 10) {
                    if (!pLevel.isClientSide) {
                        pStack.hurtAndBreak(1, player, (p_43388_) -> {
                            p_43388_.broadcastBreakEvent(pLivingEntity.getUsedItemHand());
                        });

                        ThrownSpear thrownmodspearitem = new ThrownSpear(pLevel, player, pStack);
                        thrownmodspearitem.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 4 + (float) MaterialStorageHandler.getAllAttackSpeed(pStack), (MaterialStorageHandler.getAllMineSpeed(pStack) / (MaterialStorageHandler.getMineLevel(pStack) + 1)) - 1 < 0 ? 0 : (float) (MaterialStorageHandler.getAllMineSpeed(pStack) / (MaterialStorageHandler.getMineLevel(pStack) + 1)) - 1);
                        if (player.getAbilities().instabuild) {
                            thrownmodspearitem.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        pLevel.addFreshEntity(thrownmodspearitem);
                        pLevel.playSound(null, thrownmodspearitem, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                        if (!player.getAbilities().instabuild) {
                            player.getInventory().removeItem(pStack);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        }

        if (pEntity instanceof Player player) {
            if (player.getItemInHand(InteractionHand.OFF_HAND).equals(pStack)) {
                mat.set(0, TFSToolMaterials.WOOD.get());
                mat.set(1, TFSToolMaterials.NETHERITE.get());
                mat.set(2, TFSToolMaterials.LEATHER.get());
                MaterialStorageHandler.save(pStack, mat);

            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(itemStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).use(pLevel, pPlayer, pUsedHand);
        }

        if (this.toolType.equals(TFSToolTypes.SPEAR)) {

            if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
                return InteractionResultHolder.fail(itemStack);
            } else if (EnchantmentHelper.getRiptide(itemStack) > 0 && !pPlayer.isInWaterOrRain()) {
                return InteractionResultHolder.fail(itemStack);
            } else {
                pPlayer.startUsingItem(pUsedHand);
                return InteractionResultHolder.consume(itemStack);
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return pState.is(this.toolType.getBlockTag()) ? (float) (MaterialStorageHandler.getAllMineSpeed(pStack) <= 0 ? 5 : MaterialStorageHandler.getAllMineSpeed(pStack)) : 1.0F;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        if (this.toolType.equals(TFSToolTypes.SPEAR)) {
            return UseAnim.SPEAR;
        } else return UseAnim.NONE;
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(this.toolType.isWeapon() ? 1 : 2, pAttacker, (p_41007_) -> {
            p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).hurtEnemy(pStack, pTarget, pAttacker);
        }
        return true;
    }

    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(this.toolType.isWeapon() ? 2 : 1, entity, (p_40992_) -> {
                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).mineBlock(pStack, pLevel, pState, pPos, entity);
        }

        double x = pPos.getX();
        double y = pPos.getY();
        double z = pPos.getZ();
        if (this.toolType.equals(TFSToolTypes.HAMMER) || this.toolType.equals(TFSToolTypes.SPADE)) {
            if (!entity.isShiftKeyDown()) {
                if (pState.is(this.toolType.getBlockTag())) {
                    for (int XZ = -1; XZ <= 1; XZ++) {
                        for (int Y = -1; Y <= 1; Y++) {
                            if (!(XZ == 0 && Y == 0)) {
                                if (entity.pick(20.0D, 0.0F, false) instanceof BlockHitResult blockHitResult) {
                                    if (blockHitResult.getDirection().equals(Direction.EAST) || blockHitResult.getDirection().equals(Direction.WEST)) {
                                        if ((pLevel.getBlockState(BlockPos.containing(x, y + Y, z + XZ))).is(this.toolType.getBlockTag())) {
                                            BlockPos newPos = new BlockPos((int) x, (int) y + Y, (int) z + XZ);
                                            BlockState newState = pLevel.getBlockState(BlockPos.containing(x, y + Y, z + XZ));
                                            pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                            });
                                            pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                            pLevel.setBlock(BlockPos.containing(x, y + Y, z + XZ), Blocks.AIR.defaultBlockState(), 3);
                                            pLevel.playSound((Player) entity, x, y + Y, z + XZ, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                        }
                                    } else if (blockHitResult.getDirection().equals(Direction.NORTH) || blockHitResult.getDirection().equals(Direction.SOUTH)) {
                                        if ((pLevel.getBlockState(BlockPos.containing(x + XZ, y + Y, z))).is(this.toolType.getBlockTag())) {
                                            BlockPos newPos = new BlockPos((int) x + XZ, (int) y + Y, (int) z);
                                            BlockState newState = pLevel.getBlockState(BlockPos.containing(x + XZ, y + Y, z));
                                            pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                            });
                                            pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                            pLevel.setBlock(BlockPos.containing(x + XZ, y + Y, z), Blocks.AIR.defaultBlockState(), 3);
                                            pLevel.playSound((Player) entity, x + XZ, y + Y, z, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                        }
                                    } else if (blockHitResult.getDirection().equals(Direction.UP) || blockHitResult.getDirection().equals(Direction.DOWN)) {
                                        if ((pLevel.getBlockState(BlockPos.containing(x + XZ, y, z + Y))).is(this.toolType.getBlockTag())) {
                                            BlockPos newPos = new BlockPos((int) x + XZ, (int) y, (int) z + Y);
                                            BlockState newState = pLevel.getBlockState(BlockPos.containing(x + XZ, y, z + Y));
                                            pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                            });
                                            pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                            pLevel.setBlock(BlockPos.containing(x + XZ, y, z + Y), Blocks.AIR.defaultBlockState(), 3);
                                            pLevel.playSound((Player) entity, x + XZ, y, z + Y, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (this.toolType.equals(TFSToolTypes.SICKLE)) {
            if (!entity.isShiftKeyDown()) {
                if (pState.is(this.toolType.getBlockTag())) {
                    for (int Y = -2; Y <= 2; Y++) {
                        for (int X = -2; X <= 2; X++) {
                            for (int Z = -2; Z <= 2; Z++) {
                                if (!(X == 0 && Y == 0 && Z == 0)) {
                                    if ((pLevel.getBlockState(BlockPos.containing(x + X, y + Y, z + Z))).is(this.toolType.getBlockTag())) {
                                        BlockPos newPos = new BlockPos((int) x + X, (int) y + Y, (int) z + Z);
                                        BlockState newState = pLevel.getBlockState(BlockPos.containing(x + X, y + Y, z + Z));
                                        pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                            p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                        });
                                        pState.getBlock().playerDestroy(pLevel, (Player) entity, newPos, newState, null, pStack);
                                        pLevel.setBlock(BlockPos.containing(x + X, y + Y, z + Z), Blocks.AIR.defaultBlockState(), 3);
                                        pLevel.playSound((Player) entity, x + X, y + Y, z + Z, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (this.toolType.equals(TFSToolTypes.KNIFE)) {
            if (pState.getBlock() instanceof TallGrassBlock || pState.getBlock() instanceof DoublePlantBlock) {
                pLevel.addFreshEntity(new ItemEntity(pLevel, x + 0.5f, y + 0.5f, z + 0.5f, new ItemStack(TFSItems.GRASS_CUT.get())));
                pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                    p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
            }
        }

        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Level pLevel = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        InteractionHand hand = context.getHand();
        BlockState pState = pLevel.getBlockState(BlockPos.containing(x, y, z));
        Player entity = context.getPlayer();
        ItemStack pStack = context.getItemInHand();
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();

        NonNullList<TFSToolMaterial> mat = MaterialStorageHandler.load(pStack);
        for (int i = 0; i < mat.size(); i++) {
            mat.get(i).useOn(context);
        }

        if (this.toolType.equals(TFSToolTypes.SICKLE)) {
            if (!entity.isShiftKeyDown()) {
                if (pState.getBlock() instanceof CropBlock pCropBlock && pCropBlock.isMaxAge(pState)) {
                    for (int Y = -1; Y <= 1; Y++) {
                        for (int X = -2; X <= 2; X++) {
                            for (int Z = -2; Z <= 2; Z++) {
                                BlockState newState = pLevel.getBlockState(BlockPos.containing(x + X, y + Y, z + Z));

                                if (newState.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(newState)) {
                                    BlockPos newPos = new BlockPos((int) (x + X), (int) (y + Y), (int) (z + Z));

                                    pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                                        p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                    });

                                    pState.getBlock().playerDestroy(pLevel, entity, newPos, newState, null, pStack);
                                    pLevel.setBlock(newPos, cropBlock.getStateForAge(0), 2);
                                    pLevel.sendBlockUpdated(newPos, newState, newState, 3);
                                    pLevel.playSound(entity, x + X, y + Y, z + Z, pState.getSoundType().getBreakSound(), SoundSource.NEUTRAL, 2, 0);
                                }
                            }
                        }
                    }
                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                }
            }
            return InteractionResult.PASS;
        }
        else if (this.toolType.equals(TFSToolTypes.SHOVEL)) {
            if (context.getClickedFace() == Direction.DOWN) {
                return InteractionResult.PASS;
            } else {
                Player player = context.getPlayer();
                BlockState blockstate1 = pState.getToolModifiedState(context, ToolActions.SHOVEL_FLATTEN, false);
                BlockState blockstate2 = null;
                if (blockstate1 != null && pLevel.isEmptyBlock(blockpos.above())) {
                    pLevel.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    blockstate2 = blockstate1;
                } else if (pState.getBlock() instanceof CampfireBlock && pState.getValue(CampfireBlock.LIT)) {
                    if (!pLevel.isClientSide()) {
                        pLevel.levelEvent((Player)null, 1009, blockpos, 0);
                    }

                    CampfireBlock.dowse(context.getPlayer(), pLevel, blockpos, pState);
                    blockstate2 = pState.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
                }

                if (blockstate2 != null) {
                    if (!pLevel.isClientSide) {
                        pLevel.setBlock(blockpos, blockstate2, 11);
                        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
                        if (player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, (p_43122_) -> {
                                p_43122_.broadcastBreakEvent(context.getHand());
                            });
                        }
                    }

                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }
        else if (this.toolType.equals(TFSToolTypes.HOE)) {
            BlockState toolModifiedState = pLevel.getBlockState(blockpos).getToolModifiedState(context, ToolActions.HOE_TILL, false);
            Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null : Pair.of(ctx -> true, changeIntoState(toolModifiedState));
            if (pair == null) {
                return InteractionResult.PASS;
            } else {
                Predicate<UseOnContext> predicate = pair.getFirst();
                Consumer<UseOnContext> consumer = pair.getSecond();
                if (predicate.test(context)) {
                    Player player = context.getPlayer();
                    pLevel.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!pLevel.isClientSide) {
                        consumer.accept(context);
                        if (player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, (p_150845_) -> {
                                p_150845_.broadcastBreakEvent(context.getHand());
                            });
                        }
                    }

                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }
        else if (this.toolType.equals(TFSToolTypes.AXE)) {
            Optional<BlockState> optional = Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_STRIP, false));
            Optional<BlockState> optional1 = optional.isPresent() ? Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false));
            Optional<BlockState> optional2 = optional.isPresent() || optional1.isPresent() ? Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_WAX_OFF, false));
            ItemStack itemstack = context.getItemInHand();
            Optional<BlockState> optional3 = Optional.empty();
            if (optional.isPresent()) {
                pLevel.playSound(entity, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                optional3 = optional;
            } else if (optional1.isPresent()) {
                pLevel.playSound(entity, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.levelEvent(entity, 3005, blockpos, 0);
                optional3 = optional1;
            } else if (optional2.isPresent()) {
                pLevel.playSound(entity, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.levelEvent(entity, 3004, blockpos, 0);
                optional3 = optional2;
            }

            if (optional3.isPresent()) {
                if (entity instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)entity, blockpos, itemstack);
                }

                pLevel.setBlock(blockpos, optional3.get(), 11);
                pLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(entity, optional3.get()));
                if (entity != null) {
                    itemstack.hurtAndBreak(1, entity, (p_150686_) -> {
                        p_150686_.broadcastBreakEvent(context.getHand());
                    });
                }

                return InteractionResult.sidedSuccess(pLevel.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
        else if (this.toolType.equals(TFSToolTypes.PAXEL)) {
            Optional<BlockState> optional = Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_STRIP, false));
            Optional<BlockState> optional1 = optional.isPresent() ?
                    Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false));
            Optional<BlockState> optional2 = optional.isPresent() ||
                    optional1.isPresent() ? Optional.empty() : Optional.ofNullable(pState.getToolModifiedState(context, ToolActions.AXE_WAX_OFF, false));
            ItemStack itemstack = context.getItemInHand();
            Optional<BlockState> optional3 = Optional.empty();
            if (optional.isPresent()) {
                pLevel.playSound(entity, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                optional3 = optional;
            } else if (optional1.isPresent()) {
                pLevel.playSound(entity, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.levelEvent(entity, 3005, blockpos, 0);
                optional3 = optional1;
            } else if (optional2.isPresent()) {
                pLevel.playSound(entity, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.levelEvent(entity, 3004, blockpos, 0);
                optional3 = optional2;
            }

            if (optional3.isPresent()) {
                if (entity instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)entity, blockpos, itemstack);
                }

                pLevel.setBlock(blockpos, optional3.get(), 11);
                pLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(entity, optional3.get()));
                if (entity != null) {
                    itemstack.hurtAndBreak(1, entity, (p_150686_) -> {
                        p_150686_.broadcastBreakEvent(context.getHand());
                    });
                }

                return InteractionResult.sidedSuccess(pLevel.isClientSide);
            }

            if (context.getClickedFace() == Direction.DOWN) {
                return InteractionResult.PASS;
            } else {
                Player player = context.getPlayer();
                BlockState blockstate1 = pState.getToolModifiedState(context, ToolActions.SHOVEL_FLATTEN, false);
                BlockState blockstate2 = null;
                if (blockstate1 != null && pLevel.isEmptyBlock(blockpos.above())) {
                    pLevel.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    blockstate2 = blockstate1;
                } else if (pState.getBlock() instanceof CampfireBlock && pState.getValue(CampfireBlock.LIT)) {
                    if (!pLevel.isClientSide()) {
                        pLevel.levelEvent(null, 1009, blockpos, 0);
                    }

                    CampfireBlock.dowse(context.getPlayer(), pLevel, blockpos, pState);
                    blockstate2 = pState.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
                }

                if (blockstate2 != null) {
                    if (!pLevel.isClientSide) {
                        pLevel.setBlock(blockpos, blockstate2, 11);
                        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
                        if (player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, (p_43122_) -> {
                                p_43122_.broadcastBreakEvent(context.getHand());
                            });
                        }
                    }

                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }
        else if (this.toolType.equals(TFSToolTypes.TROWEL)) {
            List<ItemStack> targets = new ArrayList<>();
            for(int i = 0; i < Inventory.getSelectionSize(); i++) {
                ItemStack stack = entity.getInventory().getItem(i);
                if(isValidTarget(stack))
                    targets.add(stack);
            }

            ItemStack ourStack = entity.getItemInHand(hand);
            if(targets.isEmpty())
                return InteractionResult.PASS;

            long seed = ItemNBTHelper.getLong(ourStack, TAG_PLACING_SEED, 0);
            Random rand = new Random(seed);
            ItemNBTHelper.setLong(ourStack, TAG_PLACING_SEED, rand.nextLong());

            ItemStack target = targets.get(rand.nextInt(targets.size()));
            int count = target.getCount();
            InteractionResult result = placeBlock(target, context);
            if(entity.getAbilities().instabuild)
                target.setCount(count);

            if(result.consumesAction()) {
                CompoundTag cmp = target.serializeNBT();
                ItemNBTHelper.setCompound(ourStack, TAG_LAST_STACK, cmp);

                if(MaterialStorageHandler.getAllDurability(pStack) > 0)
                    MiscUtil.damageStack(entity, hand, context.getItemInHand(), 1);
            }

            return result;
        }
        else if (this.toolType.equals(TFSToolTypes.BUILDER_WAND)) {
            if (pLevel.isClientSide) {
                return InteractionResult.SUCCESS;
            }
            Block selectedBlock = pState.getBlock();

            int volume = (int)Math.pow(2, (MaterialStorageHandler.getMineLevel(pStack) + 2)); // радиус действия

            if (entity.isShiftKeyDown()) {
                pStack.getOrCreateTag().putInt("BuildMode", pStack.getOrCreateTag().getInt("BuildMode") + 1);
                if (pStack.getOrCreateTag().getInt("BuildMode") > 3) pStack.getOrCreateTag().putInt("BuildMode", 0);
                entity.displayClientMessage(
                        Component.literal("Режим: " + Component.translatable("gui.tfs." +BuildMode.getBuildModeInNumber(pStack.getOrCreateTag().getInt("BuildMode")).getName()).getString()), true);
            } else {
                pStack.getOrCreateTag().putInt("BlockCount", volume);
                placeBlock2(entity, pStack, blockpos, pLevel, selectedBlock, direction, context, BuildMode.getBuildModeInNumber(pStack.getOrCreateTag().getInt("BuildMode")));
            }

            return InteractionResult.CONSUME;
        }
        else if (this.toolType.equals(TFSToolTypes.GEOLOGICAL_HAMMER)) {
            if (entity.isShiftKeyDown()) {
                if (pLevel instanceof ServerLevel serverLevel) {
                    Registry<Biome> biomeRegistry = serverLevel.getServer().registryAccess().registryOrThrow(Registries.BIOME);
                    HolderSet<Biome> biomesInTag = (HolderSet<Biome>) biomeRegistry.getTagOrEmpty(ModTags.Biomes.ORE);
                    int distance = -1;
                    Pair<BlockPos, Holder<Biome>> closestPair = null;
                    BlockPos startPos = blockpos; // предполагаю, что blockpos — это начальная позиция игрока
                    for (Holder<Biome> biomeHolder : biomesInTag) {
                        // Найдём ближайший блок этого биома
                        Pair<BlockPos, Holder<Biome>> pair = serverLevel.findClosestBiome3d(holder -> {
                            // Проверка, что этого биома в теге
                            return holder.equals(biomeHolder);
                        }, startPos, (MaterialStorageHandler.getMineLevel(pStack) + 1) * 250, 32, 64);

                        if (pair != null) {
                            int currentDist = (int) Math.sqrt(startPos.distSqr(pair.getFirst()));
                            if (distance == -1 || currentDist < distance) {
                                distance = currentDist;
                                closestPair = pair;
                            }
                        }
                    }

                    if (closestPair != null) {
                        int g = (int) Math.sqrt(blockpos.distSqr(new BlockPos(closestPair.getFirst().getX(), blockpos.getY(), closestPair.getFirst().getZ())));
                        entity.displayClientMessage(Component.translatable("geological.tfs.ore_recorded"), true);
                        pStack.getOrCreateTag().putBoolean("coordinate", true);
                        pStack.getOrCreateTag().putInt("coordinateX", closestPair.getFirst().getX());
                        pStack.getOrCreateTag().putInt("coordinateZ", closestPair.getFirst().getZ());
                        pStack.getOrCreateTag().putInt("last_coordinate", 0);

                    } else {
                        entity.displayClientMessage(Component.translatable("geological.tfs.no_ore_found"), true);
                        pStack.getOrCreateTag().putBoolean("coordinate", false);
                        pStack.getOrCreateTag().putInt("last_coordinate", 0);
                    }

                }
            } else {
                if (pStack.getOrCreateTag().getBoolean("coordinate")) {
                    int g = (int) Math.sqrt(blockpos.distSqr(new BlockPos(pStack.getOrCreateTag().getInt("coordinateX"), blockpos.getY(), pStack.getOrCreateTag().getInt("coordinateZ"))));

                    if (pStack.getOrCreateTag().getInt("last_coordinate") == 0 && g > 10) {
                        if (g > 30) {
                            entity.displayClientMessage(Component.translatable("geological.tfs.cold"), true);
                        } else {
                            entity.displayClientMessage(Component.translatable("geological.tfs.warm"), true);
                        }
                        pStack.getOrCreateTag().putInt("last_coordinate", g);
                    } else if (g <= 10) {
                        entity.displayClientMessage(Component.translatable("geological.tfs.ore_found"), true);
                        pStack.getOrCreateTag().putInt("last_coordinate", 0);
                    } else if (g <= pStack.getOrCreateTag().getInt("last_coordinate")) {
                        entity.displayClientMessage(Component.translatable("geological.tfs.warmer"), true);
                        pStack.getOrCreateTag().putInt("last_coordinate", g);
                    } else {
                        entity.displayClientMessage(Component.translatable("geological.tfs.colder"), true);
                    }


                } else entity.displayClientMessage(Component.translatable("geological.tfs.ore_not_recorded"), true);
            }

            entity.getCooldowns().addCooldown(this, 30);
            if (!entity.isCreative()) {
                pStack.hurtAndBreak(1, entity, (p_40992_) -> {
                    p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
            }
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    private void placeBlock2(Player player, ItemStack pStack, BlockPos startPos, Level pLevel, Block pBlock, Direction startDirection, UseOnContext context, BuildMode buildMode) {
        int maxBlocks = pStack.getOrCreateTag().getInt("BlockCount");
        if (maxBlocks <= 0) return;

        boolean leftBlock = false;

        Block selectedBlock = pBlock;

        if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BlockItem blockItem && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
            leftBlock = true;
            selectedBlock = blockItem.getBlock();
        }

        BlockPos initialPos = startPos.relative(startDirection);
        BlockState initialState = pLevel.getBlockState(initialPos);

        if (!(initialState.isAir() || initialState.canBeReplaced()) ||
                !selectedBlock.getStateForPlacement(new BlockPlaceContext(context)).canSurvive(pLevel, initialPos)) {
            // Если блок не может выжить на стартовой позиции, прекращаем выполнение
            return;
        }

        Direction oppositeDirection = startDirection.getOpposite();

        Deque<BlockPos> queue = new LinkedList<>();
        queue.add(initialPos);

        while (!queue.isEmpty() && maxBlocks > 0) {
            BlockPos currentPos = queue.removeFirst();

            if (pStack.getDamageValue() >= pStack.getMaxDamage()) {
                break;
            }

            ItemStack InvItem = ItemStack.EMPTY;

            for (ItemStack invStack : player.getInventory().items) {
                if (invStack.getItem() instanceof BlockItem blockItem) {
                    if (blockItem.getBlock() == selectedBlock) {
                        InvItem = invStack;
                        break;
                    }
                }
            }

            if (isEntityAtPosition(pLevel, currentPos)) {
                continue;
            }

            if (!player.isCreative()) {
                if (InvItem.isEmpty()) {
                    if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BlockItem blockItem
                            && blockItem.getBlock() == selectedBlock) {
                        InvItem = player.getItemInHand(InteractionHand.OFF_HAND);
                    } else break;
                }
            }

            if ((!pLevel.getBlockState(currentPos).canBeReplaced() ||
                    pLevel.getBlockState(currentPos).getBlock() == selectedBlock) ||
                    !pLevel.getBlockState(currentPos).getBlock().canSurvive(pLevel.getBlockState(currentPos.relative(startDirection.getOpposite())), pLevel, currentPos) || (pLevel.getBlockState(currentPos.relative(startDirection.getOpposite())).getBlock() != selectedBlock && !leftBlock) || (leftBlock && pLevel.getBlockState(currentPos.relative(startDirection.getOpposite())).canBeReplaced())) {
                continue;
            }

            if (!player.isCreative()) {
                InvItem.shrink(1);
                pStack.hurtAndBreak(1, player, (p_40992_) -> {
                    p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
            }

            pLevel.setBlock(currentPos, selectedBlock.defaultBlockState(), 3);

            maxBlocks--;
            pStack.getOrCreateTag().putInt("BlockCount", maxBlocks);


            // Расширение в стороны (кроме стартовой и противоположной)
            for (Direction dir : Direction.values()) {
                if (buildMode.equals(BuildMode.PLACE)) {
                    if (dir != startDirection && dir != oppositeDirection) {
                        BlockPos neighborPos = currentPos.relative(dir);
                        BlockState neighborState = pLevel.getBlockState(neighborPos);
                        BlockState baseState = pLevel.getBlockState(currentPos);

                        if ((neighborState.canBeReplaced() && neighborState.getBlock() != selectedBlock && selectedBlock.canSurvive(baseState, pLevel, neighborPos)) && (baseState.getBlock() == selectedBlock || (leftBlock && !baseState.isAir()))) {
                            queue.add(neighborPos);
                        }
                    }
                } else {
                    if (dir.getAxis().equals(buildMode.axis) && dir != startDirection && dir != oppositeDirection) {
                        BlockPos neighborPos = currentPos.relative(dir);
                        BlockState neighborState = pLevel.getBlockState(neighborPos);
                        BlockState baseState = pLevel.getBlockState(currentPos);

                        if ((neighborState.canBeReplaced() && neighborState.getBlock() != selectedBlock && selectedBlock.canSurvive(baseState, pLevel, neighborPos)) && (baseState.getBlock() == selectedBlock || (leftBlock && !baseState.isAir()))) {
                            queue.add(neighborPos);
                        }
                    }
                }
            }
        }
    }

    private InteractionResult placeBlock(ItemStack itemstack, UseOnContext context) {
        if(isValidTarget(itemstack)) {
            Item item = itemstack.getItem();

            Player player = context.getPlayer();
            ItemStack restore = itemstack;
            if(player != null) {
                restore = player.getItemInHand(context.getHand());
                player.setItemInHand(context.getHand(), itemstack);
            }
            InteractionResult res = item.useOn(new TrowelBlockItemUseContext(context, itemstack));
            if(player != null) {
                player.setItemInHand(context.getHand(), restore);
            }
            return res;
        }

        return InteractionResult.PASS;
    }

    private boolean isEntityAtPosition(Level level, BlockPos pos) {
        List<Entity> entities = level.getEntities((Entity) null, new AABB(pos), entity -> {
            return !(entity instanceof ItemEntity);
        });
        return !entities.isEmpty();
    }

    private static String getElementName(Pair<BlockPos, ? extends Holder<?>> pResultWithPosition) {
        return pResultWithPosition.getSecond().unwrapKey().map((p_214498_) -> {
            return p_214498_.location().toString();
        }).orElse("[unregistered]");
    }

    private static boolean isValidTarget(ItemStack stack) {
        Item item = stack.getItem();
        return !stack.isEmpty() && (item instanceof BlockItem || item instanceof ITrowelable);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", (double)MaterialStorageHandler.getAllAttackDamage(stack), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)MaterialStorageHandler.getAllAttackSpeed(stack) - 4, AttributeModifier.Operation.ADDITION));

        if (this.toolType.equals(TFSToolTypes.SPEAR)) {
            builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", 1, AttributeModifier.Operation.ADDITION));
            builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", 1, AttributeModifier.Operation.ADDITION));
        } else if (this.toolType.equals(TFSToolTypes.KNIFE)) {
            builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Tool modifier", -1, AttributeModifier.Operation.ADDITION));
            builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_RANGE_UUID, "Tool modifier", -1, AttributeModifier.Operation.ADDITION));
        }

        this.defaultModifiers = builder.build();

        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return MaterialStorageHandler.getAllDurability(stack);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return MaterialStorageHandler.getAllEnchantmentValue(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (toolType.isWeapon() && !toolType.equals(TFSToolTypes.SPEAR) && enchantment.category.equals(EnchantmentCategory.WEAPON)) {
            return true;
        } else if (!toolType.isWeapon() && enchantment.category.equals(EnchantmentCategory.DIGGER)) {
            return true;
        } else if (toolType.equals(TFSToolTypes.SPEAR) && enchantment.category.equals(EnchantmentCategory.TRIDENT)) {
            return true;
        } else if (enchantment.equals(Enchantments.MENDING)) {
            return true;
        }

        return false;
    }

    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return false;
    }

    public static int getColor(ItemStack itemStack, int Index) {
        if (MaterialStorageHandler.getSize(itemStack) > Index) {
            return MaterialStorageHandler.load(itemStack).get(Index).color;
        } else {
            return -1;
        }
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        int i = MaterialStorageHandler.getMineLevel(stack);
        if (i < 7 && state.is(ModTags.Blocks.TFS_NEEDS_NETHERITE_TOOL)) {
            return false;
        } else if (i < 6 && state.is(ModTags.Blocks.TFS_NEEDS_DIAMOND_TOOL)) {
            return false;
        } else if (i < 5 && state.is(ModTags.Blocks.TFS_NEEDS_STEEL_TOOL)) {
            return false;
        } else if (i < 4 && state.is(ModTags.Blocks.TFS_NEEDS_IRON_TOOL)) {
            return false;
        } else if (i < 3 && state.is(ModTags.Blocks.TFS_NEEDS_BRONZE_TOOL)) {
            return false;
        } else if (i < 2 && state.is(ModTags.Blocks.TFS_NEEDS_COPPER_TOOL)) {
            return false;
        } else {
            return i < 1 && state.is(ModTags.Blocks.TFS_NEEDS_STONE_TOOL) ? false : state.is(this.toolType.getBlockTag());
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (this.toolType.canAction()) {
            return this.toolType.getActions().contains(toolAction);
        } return false;
    }

    @Override
    public void generateCustomItemModel(ModItemModelProvider generator, String name) {
        if (!this.toolType.equals(TFSToolTypes.SPEAR)) {
            generator.withExistingParent(name, "item/handheld")
                    .texture("layer0", generator.modLoc("item/tools/" + this.toolType.name + "_stick"))
                    .texture("layer1", generator.modLoc("item/tools/" + this.toolType.name + "_head"))
                    .texture("layer2", generator.modLoc("item/tools/" + this.toolType.name + "_rope"));

        }
    }

    public enum TFSToolTypes {
        NONE("none", ModTags.Blocks.STONE, false, false, 0, 0, 0, ToolActions.DEFAULT_PICKAXE_ACTIONS),
        SWORD("sword", BlockTags.SWORD_EFFICIENT, true, false, 0, 3, -2.4F, ToolActions.DEFAULT_SWORD_ACTIONS),
        AXE("axe", BlockTags.MINEABLE_WITH_AXE, false, false, 0, 6.0F, -3.2F, ToolActions.DEFAULT_AXE_ACTIONS),
        HOE("hoe", BlockTags.MINEABLE_WITH_HOE, false, false, 0, 0, -3.0F, ToolActions.DEFAULT_HOE_ACTIONS),
        PICKAXE("pickaxe", BlockTags.MINEABLE_WITH_PICKAXE, false, false, 0, 1, -2.8F, ToolActions.DEFAULT_PICKAXE_ACTIONS),
        SHOVEL("shovel", BlockTags.MINEABLE_WITH_SHOVEL, false, false, 0, 1.5F, -3.0F, ToolActions.DEFAULT_SHOVEL_ACTIONS),
        KNIFE("knife", BlockTags.SWORD_EFFICIENT, true, false, 0, 1, -1.8f, ToolActions.DEFAULT_SWORD_ACTIONS),
        SPEAR("spear", ModTags.Blocks.STONE, true, false, 0, 1, -2.9f, ToolActions.DEFAULT_SWORD_ACTIONS),
        HAMMER("hammer", BlockTags.MINEABLE_WITH_PICKAXE, false, true, -1, 9, -3.4f, ToolActions.DEFAULT_PICKAXE_ACTIONS),
        SPADE("spade", BlockTags.MINEABLE_WITH_SHOVEL, false, true, -1, 7, -3.3f, ToolActions.DEFAULT_SHOVEL_ACTIONS),
        SICKLE("sickle", ModTags.Blocks.PLANTS_HOE, false, true, -1, 2, -2.2f, ToolActions.DEFAULT_HOE_ACTIONS),
        TROWEL("trowel", ModTags.Blocks.STONE, false, false, -1, -1, -2.9f, ToolActions.DEFAULT_SWORD_ACTIONS),
        GEOLOGICAL_HAMMER("geological_hammer", ModTags.Blocks.STONE, false, false, -1, -1, -2.9f, ToolActions.DEFAULT_SWORD_ACTIONS),
        BUILDER_WAND("builder_wand", ModTags.Blocks.STONE, false, false, -1, -1, -2.9f, ToolActions.DEFAULT_SWORD_ACTIONS),
        PAXEL("paxel", ModTags.Blocks.MINEABLE_WITH_MULTI_TOOL, false, true, -1, 5.5F, -3.1F,
                of(ToolActions.AXE_DIG, ToolActions.AXE_STRIP, ToolActions.AXE_SCRAPE, ToolActions.AXE_WAX_OFF, ToolActions.SHOVEL_DIG, ToolActions.SHOVEL_FLATTEN, ToolActions.PICKAXE_DIG));

        private final String name;
        private final TagKey<Block> blockTag;
        private final boolean weapon;
        private final boolean bigDurability;
        private final float mineSpeed;
        private final float attackDamage;
        private final float attackSpeed;
        private final Set<ToolAction> actions;

        TFSToolTypes(String name, TagKey<Block> blockTag, boolean weapon, boolean bigDurability, float mineSpeed, float attackDamage, float attackSpeed, Set<ToolAction> actions) {
            this.name = name;
            this.blockTag = blockTag;
            this.weapon = weapon;
            this.bigDurability = bigDurability;
            this.mineSpeed = mineSpeed;
            this.attackDamage = attackDamage;
            this.attackSpeed = attackSpeed;
            this.actions = actions;
        }

        public String getName() {
            return this.name;
        }

        public boolean canAction() {
            return this != TFSToolTypes.SPEAR && this != TFSToolTypes.KNIFE;
        }

        public static TFSToolTypes getToolTypeInName(String name) {
            return switch (name) {
                case "sword" -> TFSToolTypes.SWORD;
                case "axe" -> TFSToolTypes.AXE;
                case "hoe" -> TFSToolTypes.HOE;
                case "pickaxe" -> TFSToolTypes.PICKAXE;
                case "shovel" -> TFSToolTypes.SHOVEL;
                case "knife" -> TFSToolTypes.KNIFE;
                case "spear" -> TFSToolTypes.SPEAR;
                case "hammer" -> TFSToolTypes.HAMMER;
                case "spade" -> TFSToolTypes.SPADE;
                case "sickle" -> TFSToolTypes.SICKLE;
                case "paxel" -> TFSToolTypes.PAXEL;
                default -> TFSToolTypes.NONE;
            };
        }

        public TagKey<Block> getBlockTag() {
            return blockTag;
        }

        public boolean isWeapon() {
            return weapon;
        }

        public boolean isBigDurability() {
            return bigDurability;
        }

        public float getMineSpeed() {
            return mineSpeed;
        }

        public float getAttackDamage() {
            return attackDamage;
        }

        public float getAttackSpeed() {
            return attackSpeed;
        }

        public Set<ToolAction> getActions() {
            return actions;
        }
    }

    public enum Material {
        NONE("none", 0xffffff,0, 1, 1, 1, 1, 0),
        WOOD("wood", 0x896727,0, 120, 1f, 0.1f, 1.5f, 4),
        STONE("stone", 0x787777,1, 200, 3f, 0.5f, 0.1f, 1),
        COPPER("copper", 0xc15a36,2, 420, 3.6f, 0.8f, 1.2f, 5),
        TIN("tin", 0xc3cccd,2, 180, 5.5f, 0.6f, 1.5f, 5),
        ZINC("zinc", 0xb9cec0,2, 310, 3.3f, 0.7f, 1f, 2),
        GOLD("gold", 0xfad64a,2, 50, 11f, 0.1f, 2f, 7),
        BRONZE("bronze", 0xc4904c,3, 880, 4.5f, 1f, 1.5f, 3),
        BRASS("brass", 0xfbcc68,3, 770, 4f, 0.9f, 1.7f, 4),
        CAST_IRON("cast_iron", 0xb6a195,4, 910, 5.4f, 4.5f, -0.2f, 4),
        IRON("iron", 0xc7c7c7,4, 1380, 5f, 1.7f, 1.1f, 4),
        SILVER("silver", 0xc0b2c0,4, 950, 4f, 1f, 1.8f, 6),
        STEEL("steel", 0xaba5a1,5, 2150, 6f, 2f, 1.2f, 2),
        DIAMOND("diamond", 0x2dd8c8,6, 3500, 7f, 3f, 1.2f, 3),
        NETHERITE("netherite", 0x4d494d,7, 4120, 8f, 3.8f, 1.5f, 4),
        BONE("bone", 0xfcfbed,0, 180, 2f, 0.2f, 0.2f, 5),
        STRING("string", 0xf7f7f7,0, 150, 0f, 0f, 0.3f, 1),
        GRASS_ROPE("grass_rope", 0x57783a,0, 60, 0f, 0f, 0.1f, 2),
        LEATHER("leather", 0x72482e,0, 210, 0f, 0f, 0.4f, 4),
        BLAZE_ROD("blaze_rod", 0xfff32d,0, 2100, 1.5f, 0f, 0.9f, 5),
        SLIME_BALL("slime_ball", 0x76be6d,0, 600, 0f, 0f, 0.5f, 1),
        HONEY_BOTTLE("honey_bottle", 0xff9116,0, 350, 0f, 0f, 0.3f, 0),
        LAPIS_LAZULI("lapis_lazuli", 0x345ec3,3, 360, 0.5f, -0.5f, 1.1f, 9),
        CINNABAR("cinnabar", 0xc83b3b,2, 480, 0.5f, 0.8f, 0.5f, 3),
        EMERALD("emerald", 0x009529,4, 1520, 1.5f, 0.8f, 0.3f, 2),
        SCUTE("scute", 0x3fa442,0, 310, 0.5f, 0f, 0f, 2),
        ECHO_SHARD("echo_shard", 0x034150,0, -200, 8f, -0.5f, 0.3f, 6),
        AMETHYST_SHARD("amethyst_shard", 0xb38ef3,0, 110, 0.5f, 0.5f, 0.3f, 6),
        TEST("test", 0xea1a1a,1, 100, 1f, 1f, 1f, 0),
        ;

        private final String name;
        private final int color;
        private final int mineLevel;
        private final int durability;
        private final float mineSpeed;
        private final float attackDamage;
        private final float attackSpeed;
        private final int enchantmentValue;

        Material(String name, int color, int mineLevel, int durability, float mineSpeed, float attackDamage, float attackSpeed, int enchantmentValue) {
            this.name = name;
            this.color = color;
            this.mineLevel = mineLevel;
            this.durability = durability;
            this.mineSpeed = mineSpeed;
            this.attackDamage = attackDamage;
            this.attackSpeed = attackSpeed;
            this.enchantmentValue = enchantmentValue;

        }

        public static Material getRandomMaterial() {
            return getMaterialInNumber(Mth.nextInt(RandomSource.create(), 0, 28));
        }

        public String getName() {
            return this.name;
        }

        public static Material getMaterialInName(String name) {
            return switch (name) {
                case "wood" -> WOOD;
                case "stone" -> STONE;
                case "copper" -> COPPER;
                case "tin" -> TIN;
                case "zinc" -> ZINC;
                case "gold" -> GOLD;
                case "bronze" -> BRONZE;
                case "brass" -> BRASS;
                case "cast_iron" -> CAST_IRON;
                case "iron" -> IRON;
                case "silver" -> SILVER;
                case "steel" -> STEEL;
                case "diamond" -> DIAMOND;
                case "netherite" -> NETHERITE;
                case "bone" -> BONE;
                case "string" -> STRING;
                case "grass_rope" -> GRASS_ROPE;
                case "leather" -> LEATHER;
                case "blaze_rod" -> BLAZE_ROD;
                case "slime_ball" -> SLIME_BALL;
                case "honey_bottle" -> HONEY_BOTTLE;
                case "lapis_lazuli" -> LAPIS_LAZULI;
                case "cinnabar" -> CINNABAR;
                case "emerald" -> EMERALD;
                case "scute" -> SCUTE;
                case "echo_shard" -> ECHO_SHARD;
                case "amethyst_shard" -> AMETHYST_SHARD;
                case "test" -> TEST;
                default -> Material.NONE;
            };
        }

        public static Material getMaterialInNumber(int number) {
            return switch (number) {
                case 1 -> WOOD;
                case 2 -> STONE;
                case 3 -> COPPER;
                case 4 -> TIN;
                case 5 -> ZINC;
                case 6 -> GOLD;
                case 7 -> BRONZE;
                case 8 -> BRASS;
                case 9 -> CAST_IRON;
                case 10 -> IRON;
                case 11 -> SILVER;
                case 12 -> STEEL;
                case 13 -> DIAMOND;
                case 14 -> NETHERITE;
                case 15 -> BONE;
                case 16 -> STRING;
                case 17 -> GRASS_ROPE;
                case 18 -> LEATHER;
                case 19 -> BLAZE_ROD;
                case 20 -> SLIME_BALL;
                case 21 -> HONEY_BOTTLE;
                case 22 -> LAPIS_LAZULI;
                case 23 -> CINNABAR;
                case 24 -> EMERALD;
                case 25 -> SCUTE;
                case 26 -> ECHO_SHARD;
                case 27 -> AMETHYST_SHARD;
                case 28 -> TEST;
                default -> NONE;
            };
        }

        public int getColor() {
            return color;
        }

        public int getMineLevel() {
            return mineLevel;
        }

        public int getDurability() {
            return durability;
        }

        public float getMineSpeed() {
            return mineSpeed;
        }

        public float getAttackDamage() {
            return attackDamage;
        }

        public float getAttackSpeed() {
            return attackSpeed;
        }

        public int getEnchantmentValue() {
            return enchantmentValue;
        }
    }

    class TrowelBlockItemUseContext extends BlockPlaceContext {

        public TrowelBlockItemUseContext(UseOnContext context, ItemStack stack) {
            super(context.getLevel(), context.getPlayer(), context.getHand(), stack,
                    new BlockHitResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside()));
        }

    }

    public enum BuildMode {
        PLACE("place", Direction.Axis.X),
        X_LINE("x_line", Direction.Axis.X),
        Y_LINE("y_line", Direction.Axis.Y),
        Z_LINE("z_line", Direction.Axis.Z);

        private final String name;
        private final Direction.Axis axis;

        BuildMode(String pName, Direction.Axis pAxis) {
            name = pName;
            axis = pAxis;
        }

        public String getName() {
            return name;
        }

        public Direction.Axis getAxis() {
            return axis;
        }

        public static BuildMode getBuildModeInNumber(int u) {
            return switch (u) {
                case 1 -> X_LINE;
                case 2 -> Y_LINE;
                case 3 -> Z_LINE;
                default -> PLACE;
            };
        }
    }
}