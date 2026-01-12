package net.Traise.tfs.util;

import net.minecraft.world.item.crafting.Ingredient;

import java.util.stream.Stream;

import static net.minecraftforge.common.crafting.CraftingHelper.getIngredient;

public class ToolIngredient extends Ingredient {
    public int Number;

    public ToolIngredient(Stream<? extends Value> pValues, int Number) {
        super(pValues);
        this.Number = Number;
    }

    public ToolIngredient setNumber(int nun) {
        Number = nun;
        return this;
    }
}
