package net.Traise.tfs.tools.toolItem;

import com.mojang.datafixers.util.Pair;
import net.Traise.tfs.item.custom.TFSBaseItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.minecraft.world.item.HoeItem.changeIntoState;

public class TFSPickaxeTool extends TFSBaseItem {
    public TFSPickaxeTool(Properties pProperties) {
        super(BlockTags.MINEABLE_WITH_PICKAXE, 2, 1, -1, ToolActions.DEFAULT_PICKAXE_ACTIONS, pProperties);
    }
}