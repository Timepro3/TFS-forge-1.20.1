package net.Traise.tfs.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.SimpleContainer;

public class BooleanContainer extends SimpleContainer {
    private NonNullList<Boolean> map;

    public BooleanContainer(NonNullList<Boolean> pMap) {
        this.map = pMap;
    }

    public void setMapSlot(int Index, boolean newSlot) {
        this.map.set(Index, newSlot);
    }

    public boolean getMapSlot(int Index) {
        return this.map.get(Index);
    }

    public boolean getMap(int Index) {
        return map.get(Index);
    }
}
