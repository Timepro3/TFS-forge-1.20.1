package net.Traise.tfs.util;

/*public class TFSToolType {
    public static final DeferredRegister<TFSToolType> TOOL =
            DeferredRegister.create(TFSForgeRegistries.TOOL_TYPES, tfs.MOD_ID);

    TFSToolType(String name) {
        TOOL.register(name, TFSToolType::new);
    }

    TFSToolType() {
    }

    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        return true;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        return new InteractionResultHolder<>(InteractionResult.PASS, itemStack);
    }

    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        return true;
    }

    public boolean canAction() {
        return false;
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

    public float multipleDurability() {
        return 1;
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

    public static TFSToolType loadToolFromNBT(CompoundTag nbt) {
        ResourceLocation toolName = new ResourceLocation(nbt.getString("ToolName"));
        TFSToolType toolType = TFSForgeRegistries.TOOL_TYPES.getValue(toolName);
        return toolType;
    }

    public CompoundTag writeToNBT(CompoundTag nbt, TFSToolType tfsToolType) {
        nbt.putString("ToolName", TFSForgeRegistries.TOOL_TYPES.getKey(tfsToolType).toString());
        return nbt;
    }
}*/
