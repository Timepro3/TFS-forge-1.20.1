package net.Traise.tfs.util;

public enum TFSToolType {
    NONE(),
    SWORD(),
    AXE(),
    HOE(),
    PICKAXE(),
    SHOVEL(),
    KNIFE(),
    SPEAR(),
    HAMMER(),
    SPADE(),
    SICKLE();

    TFSToolType() {
    }

    public boolean isSame(TFSToolType same) {
        return this == same;
    }
}
