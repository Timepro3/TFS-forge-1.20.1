package net.Traise.tfs.util;

import com.google.common.base.Enums;
import net.Traise.tfs.item.custom.TFSBaseItem;
import net.Traise.tfs.tfs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*public class WandJob {
    public final Player player;
    public final Level world;
    public final BlockHitResult rayTraceResult;
    public final WandOptions options;
    public final ItemStack wand;
    public final TFSBaseItem wandItem;

    private final IWandAction wandAction;
    private final IWandSupplier wandSupplier;

    private List<ISnapshot> placeSnapshots;

    public WandJob(Player player, Level world, BlockHitResult rayTraceResult, ItemStack wand) {
        this.player = player;
        this.world = world;
        this.rayTraceResult = rayTraceResult;
        this.placeSnapshots = new ArrayList<>();

        // Get wand
        this.wand = wand;
        this.wandItem = (TFSBaseItem) wand.getItem();
        options = new WandOptions(wand);

        // Select wand action and supplier based on options
        wandSupplier = options.random.get() ?
                new SupplierRandom(player, options) : new SupplierInventory(player, options);
        wandAction = options.cores.get().getWandAction();

        wandSupplier.getSupply(getTargetItem(world, rayTraceResult));
    }

    @Nullable
    private static BlockItem getTargetItem(Level world, BlockHitResult rayTraceResult) {
        // Get target item
        Item tgitem = world.getBlockState(rayTraceResult.getBlockPos()).getBlock().asItem();
        if (!(tgitem instanceof BlockItem)) return null;
        return (BlockItem) tgitem;
    }

    public void getSnapshots() {
        int limit;
        // Infinity wand gets enhanced limit in creative mode
        limit = Math.min(wandItem.getMaxDamage(wand) - wandItem.getDamage(wand), wandAction.getLimit(wand));

        if (rayTraceResult.getType() == HitResult.Type.BLOCK)
            placeSnapshots = wandAction.getSnapshots(world, player, rayTraceResult, wand, options, wandSupplier, limit);
        else
            placeSnapshots = wandAction.getSnapshotsFromAir(world, player, rayTraceResult, wand, options, wandSupplier, limit);
    }

    public Set<BlockPos> getBlockPositions() {
        return placeSnapshots.stream().map(ISnapshot::getPos).collect(Collectors.toSet());
    }

    public int blockCount() {
        return placeSnapshots.size();
    }

    public boolean doIt() {
        ArrayList<ISnapshot> executed = new ArrayList<>();

        for (ISnapshot snapshot : placeSnapshots) {
            if (wand.isEmpty() || wandItem.getMaxDamage(wand) == wandItem.getDamage(wand)) break;

            if (snapshot.execute(world, player, rayTraceResult)) {
                if (player.isCreative()) executed.add(snapshot);
                else {
                    // If the item cant be taken, undo the placement
                    if (wandSupplier.takeItemStack(snapshot.getRequiredItems()) == 0) {
                        executed.add(snapshot);
                        wand.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                    } else {
                        tfs.LOGGER.info("Item could not be taken. Remove block: " +
                                snapshot.getBlockState().getBlock().toString());
                        snapshot.forceRestore(world);
                    }
                }
                player.awardStat(ModStats.USE_WAND);
            }
        }
        placeSnapshots = executed;

        // Play place sound
        if (!placeSnapshots.isEmpty()) {
            SoundType sound = placeSnapshots.get(0).getBlockState().getSoundType();
            world.playSound(null, player.blockPosition(), sound.getPlaceSound(), SoundSource.BLOCKS, sound.volume, sound.pitch);

            // Add to job history for undo
            tfs.instance.undoHistory.add(player, world, placeSnapshots);
        }

        return !placeSnapshots.isEmpty();
    }

    public static class UndoHistory {
        private final HashMap<UUID, PlayerEntry> history;

        public UndoHistory() {
            history = new HashMap<>();
        }

        private PlayerEntry getEntryFromPlayer(Player player) {
            return history.computeIfAbsent(player.getUUID(), k -> new PlayerEntry());
        }

        public void add(Player player, Level world, List<ISnapshot> placeSnapshots) {
            LinkedList<HistoryEntry> list = getEntryFromPlayer(player).entries;
            list.add(new HistoryEntry(placeSnapshots, world));
            while (list.size() > ConfigServer.UNDO_HISTORY.get()) list.removeFirst();
        }

        public void removePlayer(Player player) {
            history.remove(player.getUUID());
        }

        public void updateClient(Player player, boolean ctrlDown) {
            Level world = player.level();
            if (world.isClientSide) return;

            // Set state of CTRL key
            PlayerEntry playerEntry = getEntryFromPlayer(player);
            playerEntry.undoActive = ctrlDown;

            LinkedList<HistoryEntry> historyEntries = playerEntry.entries;
            Set<BlockPos> positions;

            // Send block positions of most recent entry to client
            if (historyEntries.isEmpty()) positions = Collections.emptySet();
            else {
                HistoryEntry entry = historyEntries.getLast();

                if (entry == null || !entry.world.equals(world)) positions = Collections.emptySet();
                else positions = entry.getBlockPositions();
            }

            PacketUndoBlocks packet = new PacketUndoBlocks(positions);
            tfs.instance.HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), packet);
        }

        public boolean isUndoActive(Player player) {
            return getEntryFromPlayer(player).undoActive;
        }

        public boolean undo(Player player, Level world, BlockPos pos) {
            // If CTRL key is not pressed, return
            PlayerEntry playerEntry = getEntryFromPlayer(player);
            if (!playerEntry.undoActive) return false;

            // Get the most recent entry for undo
            LinkedList<HistoryEntry> historyEntries = playerEntry.entries;
            if (historyEntries.isEmpty()) return false;
            HistoryEntry entry = historyEntries.getLast();

            // Player has to be in the same world and near the blocks
            if (!entry.world.equals(world) || !entry.withinRange(pos)) return false;

            if (entry.undo(player)) {
                historyEntries.remove(entry);
                updateClient(player, true);
                return true;
            }
            return false;
        }

        private static class PlayerEntry {
            public final LinkedList<HistoryEntry> entries;
            public boolean undoActive;

            public PlayerEntry() {
                entries = new LinkedList<>();
                undoActive = false;
            }
        }

        private static class HistoryEntry {
            public final List<ISnapshot> placeSnapshots;
            public final Level world;

            public HistoryEntry(List<ISnapshot> placeSnapshots, Level world) {
                this.placeSnapshots = placeSnapshots;
                this.world = world;
            }

            public Set<BlockPos> getBlockPositions() {
                return placeSnapshots.stream().map(ISnapshot::getPos).collect(Collectors.toSet());
            }

            public boolean withinRange(BlockPos pos) {
                Set<BlockPos> positions = getBlockPositions();

                if (positions.contains(pos)) return true;

                for (BlockPos p : positions) {
                    if (pos.closerThan(p, 3)) return true;
                }
                return false;
            }

            public boolean undo(Player player) {
                // Check first if all snapshots can be restored
                for (ISnapshot snapshot : placeSnapshots) {
                    if (!snapshot.canRestore(world, player)) return false;
                }
                for (ISnapshot snapshot : placeSnapshots) {
                    if (snapshot.restore(world, player) && !player.isCreative()) {
                        ItemStack stack = snapshot.getRequiredItems();

                        if (!player.getInventory().add(stack)) {
                            player.drop(stack, false);
                        }
                    }
                }
                player.getInventory().setChanged();

                // Play teleport sound
                SoundEvent sound = SoundEvents.CHORUS_FRUIT_TELEPORT;
                world.playSound(null, player.blockPosition(), sound, SoundSource.PLAYERS, 1.0F, 1.0F);

                return true;
            }
        }
    }

    public interface ISnapshot {
        BlockPos getPos();

        BlockState getBlockState();

        ItemStack getRequiredItems();

        boolean execute(Level world, Player player, BlockHitResult rayTraceResult);

        boolean canRestore(Level world, Player player);

        boolean restore(Level world, Player player);

        void forceRestore(Level world);
    }

    public class WandOptions {
        public final CompoundTag tag;

        private static final String TAG_ROOT = "wand_options";

        public enum LOCK {
            HORIZONTAL,
            VERTICAL,
            NORTHSOUTH,
            EASTWEST,
            NOLOCK
        }

        public enum DIRECTION {
            TARGET,
            PLAYER
        }

        public enum MATCH {
            EXACT,
            SIMILAR,
            ANY
        }

        public final WandUpgradesSelectable<IWandCore> cores;

        public final OptionEnum<LOCK> lock;
        public final OptionEnum<DIRECTION> direction;
        public final OptionBoolean replace;
        public final OptionEnum<MATCH> match;
        public final OptionBoolean random;

        public final IOption<?>[] allOptions;

        public WandOptions(ItemStack wandStack) {
            tag = wandStack.getOrCreateTagElement(TAG_ROOT);

            cores = new WandUpgradesSelectable<>(tag, "cores", new CoreDefault());

            lock = new OptionEnum<>(tag, "lock", LOCK.class, LOCK.NOLOCK);
            direction = new OptionEnum<>(tag, "direction", DIRECTION.class, DIRECTION.TARGET);
            replace = new OptionBoolean(tag, "replace", true);
            match = new OptionEnum<>(tag, "match", MATCH.class, MATCH.SIMILAR);
            random = new OptionBoolean(tag, "random", false);

            allOptions = new IOption[]{cores, lock, direction, replace, match, random};
        }

        @Nullable
        public IOption<?> get(String key) {
            for (IOption<?> option : allOptions) {
                if (option.getKey().equals(key)) return option;
            }
            return null;
        }

        public boolean testLock(LOCK l) {
            if (lock.get() == LOCK.NOLOCK) return true;
            return lock.get() == l;
        }

        public boolean matchBlocks(Block b1, Block b2) {
            switch (match.get()) {
                case EXACT:
                    return b1 == b2;
                case SIMILAR:
                    return ReplacementRegistry.matchBlocks(b1, b2);
                case ANY:
                    return b1 != Blocks.AIR && b2 != Blocks.AIR;
            }
            return false;
        }

        public boolean hasUpgrade(IWandUpgrade upgrade) {
            if (upgrade instanceof IWandCore) return cores.hasUpgrade((IWandCore) upgrade);
            return false;
        }

        public boolean addUpgrade(IWandUpgrade upgrade) {
            if (upgrade instanceof IWandCore) return cores.addUpgrade((IWandCore) upgrade);
            return false;
        }
    }

    public class OptionEnum<E extends Enum<E>> implements IOption<E> {
        private final CompoundTag tag;
        private final String key;
        private final Class<E> enumClass;
        private final boolean enabled;
        private final E dval;
        private E value;

        public OptionEnum(CompoundTag tag, String key, Class<E> enumClass, E dval, boolean enabled) {
            this.tag = tag;
            this.key = key;
            this.enumClass = enumClass;
            this.enabled = enabled;
            this.dval = dval;

            value = Enums.getIfPresent(enumClass, tag.getString(key).toUpperCase()).or(dval);
        }

        public OptionEnum(CompoundTag tag, String key, Class<E> enumClass, E dval) {
            this(tag, key, enumClass, dval, true);
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValueString() {
            return value.name().toLowerCase();
        }

        @Override
        public void setValueString(String val) {
            set(Enums.getIfPresent(enumClass, val.toUpperCase()).or(dval));
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        @Override
        public void set(E val) {
            if (!enabled) return;
            value = val;
            tag.putString(key, getValueString());
        }

        @Override
        public E get() {
            return value;
        }

        @Override
        public E next(boolean dir) {
            E[] enumValues = enumClass.getEnumConstants();
            int i = value.ordinal() + (dir ? 1 : -1);
            if (i < 0) i += enumValues.length;
            set(enumValues[i % enumValues.length]);
            return value;
        }
    }

    public interface IOption<T> {
        String getKey();

        String getValueString();

        void setValueString(String val);

        default String getKeyTranslation() {
            return tfs.MOD_ID + ".option." + getKey();
        }

        default String getValueTranslation() {
            return tfs.MOD_ID + ".option." + getKey() + "." + getValueString();
        }

        default String getDescTranslation() {
            return tfs.MOD_ID + ".option." + getKey() + "." + getValueString() + ".desc";
        }

        boolean isEnabled();

        void set(T val);

        T get();

        T next(boolean dir);

        default T next() {
            return next(true);
        }
    }

    public class OptionBoolean implements IOption<Boolean> {
        private final CompoundTag tag;
        private final String key;
        private final boolean enabled;
        private boolean value;

        public OptionBoolean(CompoundTag tag, String key, boolean dval, boolean enabled) {
            this.tag = tag;
            this.key = key;
            this.enabled = enabled;

            if (tag.contains(key)) value = tag.getBoolean(key);
            else value = dval;
        }

        public OptionBoolean(CompoundTag tag, String key, boolean dval) {
            this(tag, key, dval, true);
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValueString() {
            return value ? "yes" : "no";
        }

        @Override
        public void setValueString(String val) {
            set(val.equals("yes"));
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        @Override
        public void set(Boolean val) {
            if (!enabled) return;
            value = val;
            tag.putBoolean(key, value);
        }

        @Override
        public Boolean get() {
            return value;
        }

        @Override
        public Boolean next(boolean dir) {
            set(!value);
            return value;
        }
    }

    public interface IWandCore extends IWandUpgrade {
        int getColor();

        IWandAction getWandAction();
    }

    public interface IWandAction {
        int getLimit(ItemStack wand);

        @Nonnull
        List<ISnapshot> getSnapshots(Level world, Player player, BlockHitResult rayTraceResult,
                                     ItemStack wand, WandOptions options, IWandSupplier supplier, int limit);

        @Nonnull
        List<ISnapshot> getSnapshotsFromAir(Level world, Player player, BlockHitResult rayTraceResult,
                                            ItemStack wand, WandOptions options, IWandSupplier supplier, int limit);
    }

    public interface IWandUpgrade {
        ResourceLocation getRegistryName();
    }

    public interface IWandSupplier {
        void getSupply(@Nullable BlockItem target);

        /**
         * Tries to create a new PlaceSnapshot at the specified position.
         * Returns null if there aren't any blocks available that can be placed
         * in that position.

        @Nullable
        PlaceSnapshot getPlaceSnapshot(Level world, BlockPos pos, BlockHitResult rayTraceResult,
                                       @Nullable BlockState supportingBlock);

        /**
         * Consumes an item stack if the placement was successful

        int takeItemStack(ItemStack stack);
    }

    public class PlaceSnapshot implements ISnapshot {
        private BlockState block;
        private final BlockPos pos;
        private final BlockItem item;
        private final BlockState supportingBlock;
        private final boolean targetMode;

        public PlaceSnapshot(BlockState block, BlockPos pos, BlockItem item, BlockState supportingBlock, boolean targetMode) {
            this.block = block;
            this.pos = pos;
            this.item = item;
            this.supportingBlock = supportingBlock;
            this.targetMode = targetMode;
        }

        public static PlaceSnapshot get(Level world, Player player, BlockHitResult rayTraceResult,
                                        BlockPos pos, BlockItem item,
                                        @Nullable BlockState supportingBlock, @Nullable WandOptions options) {
            boolean targetMode = options != null && supportingBlock != null && options.direction.get() == WandOptions.DIRECTION.TARGET;
            BlockState blockState = getPlaceBlockstate(world, player, rayTraceResult, pos, item, supportingBlock, targetMode);
            if (blockState == null) return null;

            return new PlaceSnapshot(blockState, pos, item, supportingBlock, targetMode);
        }

        @Override
        public BlockPos getPos() {
            return pos;
        }

        @Override
        public BlockState getBlockState() {
            return block;
        }

        @Override
        public ItemStack getRequiredItems() {
            return new ItemStack(item);
        }

        @Override
        public boolean execute(Level world, Player player, BlockHitResult rayTraceResult) {
            // Recalculate PlaceBlockState, because other blocks might be placed nearby
            // Not doing this may cause game crashes (StackOverflowException) when placing lots of blocks
            // with changing orientation like panes, iron bars or redstone.
            block = getPlaceBlockstate(world, player, rayTraceResult, pos, item, supportingBlock, targetMode);
            if (block == null) return false;
            return WandUtil.placeBlock(world, player, block, pos, item);
        }

        @Override
        public boolean canRestore(Level world, Player player) {
            return true;
        }

        @Override
        public boolean restore(Level world, Player player) {
            return WandUtil.removeBlock(world, player, block, pos);
        }

        @Override
        public void forceRestore(Level world) {
            world.removeBlock(pos, false);
        }

        /**
         * Tests if a certain block can be placed by the wand.
         * If it can, returns the blockstate to be placed.

        @SuppressWarnings({"rawtypes", "unchecked"})
        @Nullable
        private static BlockState getPlaceBlockstate(Level world, Player player, BlockHitResult rayTraceResult,
                                                     BlockPos pos, BlockItem item,
                                                     @Nullable BlockState supportingBlock, boolean targetMode) {
            // Is block at pos replaceable?
            BlockPlaceContext ctx = new WandItemUseContext(world, player, rayTraceResult, pos, item);
            if (!ctx.canPlace()) return null;

            // Can block be placed?
            BlockState blockState = item.getBlock().getStateForPlacement(ctx);
            if (blockState == null || !blockState.canSurvive(world, pos)) return null;

            // Forbidden Tile Entity?
            if (!WandUtil.isTEAllowed(blockState)) return null;

            // No entities colliding?
            if (WandUtil.entitiesCollidingWithBlock(world, blockState, pos)) return null;

            // Copy block properties from supporting block
            if (targetMode && supportingBlock != null) {
                // Block properties to be copied (alignment/rotation properties)

                for (Property property : new Property[]{
                        BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.FACING, BlockStateProperties.FACING_HOPPER,
                        BlockStateProperties.ROTATION_16, BlockStateProperties.AXIS, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE}) {
                    if (supportingBlock.hasProperty(property) && blockState.hasProperty(property)) {
                        blockState = blockState.setValue(property, supportingBlock.getValue(property));
                    }
                }

                // Dont dupe double slabs
                if (supportingBlock.hasProperty(BlockStateProperties.SLAB_TYPE) && blockState.hasProperty(BlockStateProperties.SLAB_TYPE)) {
                    SlabType slabType = supportingBlock.getValue(BlockStateProperties.SLAB_TYPE);
                    if (slabType != SlabType.DOUBLE)
                        blockState = blockState.setValue(BlockStateProperties.SLAB_TYPE, slabType);
                }
            }
            return blockState;
        }
    }

    public class WandItemUseContext extends BlockPlaceContext {
        public WandItemUseContext(Level world, Player player, BlockHitResult rayTraceResult, BlockPos pos, BlockItem item) {
            super(world, player, InteractionHand.MAIN_HAND, new ItemStack(item),
                    new BlockHitResult(getBlockHitVec(rayTraceResult, pos), rayTraceResult.getDirection(), pos, false));
        }

        private static Vec3 getBlockHitVec(BlockHitResult rayTraceResult, BlockPos pos) {
            Vec3 hitVec = rayTraceResult.getLocation(); // Absolute coords of hit target

            Vec3 blockDelta = WandUtil.blockPosVec(rayTraceResult.getBlockPos()).subtract(WandUtil.blockPosVec(pos)); // Vector between start and current block

            return blockDelta.add(hitVec); // Absolute coords of current block hit target
        }

        @Override
        public boolean canPlace() {
            return replaceClicked;
        }
    }

    public class WandUtil {
        public static boolean stackEquals(ItemStack stackA, ItemStack stackB) {
            return ItemStack.isSameItemSameTags(stackA, stackB);
        }

        public static boolean stackEquals(ItemStack stackA, Item item) {
            ItemStack stackB = new ItemStack(item);
            return stackEquals(stackA, stackB);
        }

        public static ItemStack holdingWand(Player player) {
            if (player.getItemInHand(InteractionHand.MAIN_HAND) != ItemStack.EMPTY && player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ItemWand) {
                return player.getItemInHand(InteractionHand.MAIN_HAND);
            } else if (player.getItemInHand(InteractionHand.OFF_HAND) != ItemStack.EMPTY && player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof ItemWand) {
                return player.getItemInHand(InteractionHand.OFF_HAND);
            }
            return null;
        }

        public static BlockPos posFromVec(Vec3 vec) {
            return new BlockPos(
                    (int) Math.round(vec.x), (int) Math.round(vec.y), (int) Math.round(vec.z));
        }

        public static Vec3 entityPositionVec(Entity entity) {
            return new Vec3(entity.getX(), entity.getY() - entity.getMyRidingOffset() + entity.getBbHeight() / 2, entity.getZ());
        }

        public static Vec3 blockPosVec(BlockPos pos) {
            return new Vec3(pos.getX(), pos.getY(), pos.getZ());
        }

        public static List<ItemStack> getHotbar(Player player) {
            return player.getInventory().items.subList(0, 9);
        }

        public static List<ItemStack> getHotbarWithOffhand(Player player) {
            ArrayList<ItemStack> inventory = new ArrayList<>(player.getInventory().items.subList(0, 9));
            inventory.addAll(player.getInventory().offhand);
            return inventory;
        }

        public static List<ItemStack> getMainInv(Player player) {
            return player.getInventory().items.subList(9, player.getInventory().items.size());
        }

        public static List<ItemStack> getFullInv(Player player) {
            ArrayList<ItemStack> inventory = new ArrayList<>(player.getInventory().offhand);
            inventory.addAll(player.getInventory().items);
            return inventory;
        }

        public static int blockDistance(BlockPos p1, BlockPos p2) {
            return Math.max(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getZ() - p2.getZ()));
        }

        public static boolean isTEAllowed(BlockState state) {
            if (!state.hasBlockEntity()) return true;

            ResourceLocation name = ForgeRegistries.BLOCKS.getKey(state.getBlock());
            if (name == null) return false;

            String fullId = name.toString();
            String modId = name.getNamespace();

            boolean inList = ConfigServer.TE_LIST.get().contains(fullId) || ConfigServer.TE_LIST.get().contains(modId);
            boolean isWhitelist = ConfigServer.TE_WHITELIST.get();

            return isWhitelist == inList;
        }

        public static boolean placeBlock(Level world, Player player, BlockState block, BlockPos pos, @Nullable BlockItem item) {
            if (!world.setBlockAndUpdate(pos, block)) {
                tfs.LOGGER.info("Block could not be placed");
                return false;
            }

            // Remove block if placeEvent is canceled
            BlockSnapshot snapshot = BlockSnapshot.create(world.dimension(), world, pos);
            BlockEvent.EntityPlaceEvent placeEvent = new BlockEvent.EntityPlaceEvent(snapshot, block, player);
            MinecraftForge.EVENT_BUS.post(placeEvent);
            if (placeEvent.isCanceled()) {
                world.removeBlock(pos, false);
                return false;
            }

            ItemStack stack;
            if (item == null) stack = new ItemStack(block.getBlock().asItem());
            else {
                stack = new ItemStack(item);
                player.awardStat(Stats.ITEM_USED.get(item));
            }

            // Call OnBlockPlaced method
            block.getBlock().setPlacedBy(world, pos, block, player, stack);

            return true;
        }

        public static boolean removeBlock(Level world, Player player, @Nullable BlockState block, BlockPos pos) {
            BlockState currentBlock = world.getBlockState(pos);

            if (!world.mayInteract(player, pos)) return false;

            if (!player.isCreative()) {
                if (currentBlock.getDestroySpeed(world, pos) <= -1 || world.getBlockEntity(pos) != null) return false;

                if (block != null)
                    if (!ReplacementRegistry.matchBlocks(currentBlock.getBlock(), block.getBlock())) return false;
            }

            BlockEvent.BreakEvent breakEvent = new BlockEvent.BreakEvent(world, pos, currentBlock, player);
            MinecraftForge.EVENT_BUS.post(breakEvent);
            if (breakEvent.isCanceled()) return false;

            world.removeBlock(pos, false);
            return true;
        }

        public static int countItem(Player player, Item item) {
            if (player.getInventory().items == null) return 0;
            if (player.isCreative()) return Integer.MAX_VALUE;

            int total = 0;
            ContainerManager containerManager = ConstructionWand.instance.containerManager;
            List<ItemStack> inventory = WandUtil.getFullInv(player);

            for (ItemStack stack : inventory) {
                if (stack == null || stack.isEmpty()) continue;

                if (WandUtil.stackEquals(stack, item)) {
                    total += stack.getCount();
                } else {
                    int amount = containerManager.countItems(player, new ItemStack(item), stack);
                    if (amount == Integer.MAX_VALUE) return Integer.MAX_VALUE;
                    total += amount;
                }
            }
            return total;
        }

        private static boolean isPositionModifiable(Level world, Player player, BlockPos pos) {
            // Is position out of world?
            if (!world.isInWorldBounds(pos)) return false;

            // Is block modifiable?
            if (!world.mayInteract(player, pos)) return false;

            ItemStack pStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            // Limit range
            if (ConfigServer.MAX_RANGE.get() > 0 &&
                    WandUtil.blockDistance(player.blockPosition(), pos) > ConfigServer.MAX_RANGE.get()) return false;

            return true;
        }

        /**
         * Tests if a wand can place a block at a certain position.
         * This check is independent of the used block.

        public boolean isPositionPlaceable(Level world, Player player, BlockPos pos, boolean replace) {
            if (!isPositionModifiable(world, player, pos)) return false;

            // If replace mode is off, target has to be air
            if (world.isEmptyBlock(pos)) return true;

            // Otherwise, check if the block can be replaced by a generic block
            return replace && world.getBlockState(pos).canBeReplaced(
                    new WandItemUseContext(world, player,
                            new BlockHitResult(new Vec3(0, 0, 0), Direction.DOWN, pos, false),
                            pos, (BlockItem) Items.STONE));
        }

        public static boolean isBlockRemovable(Level world, Player player, BlockPos pos) {
            if (!isPositionModifiable(world, player, pos)) return false;

            if (!player.isCreative()) {
                return !(world.getBlockState(pos).getDestroySpeed(world, pos) <= -1) && world.getBlockEntity(pos) == null;
            }
            return true;
        }

        public static boolean isBlockPermeable(Level world, BlockPos pos) {
            return world.isEmptyBlock(pos) || world.getBlockState(pos).getCollisionShape(world, pos).isEmpty();
        }

        public static boolean entitiesCollidingWithBlock(Level world, BlockState blockState, BlockPos pos) {
            VoxelShape shape = blockState.getCollisionShape(world, pos);
            if (!shape.isEmpty()) {
                AABB blockBB = shape.bounds().move(pos);
                return !world.getEntitiesOfClass(LivingEntity.class, blockBB, Predicate.not(Entity::isSpectator)).isEmpty();
            }
            return false;
        }

        public static Direction fromVector(Vec3 vector) {
            return Direction.getNearest(vector.x, vector.y, vector.z);
        }
    }
}*/
