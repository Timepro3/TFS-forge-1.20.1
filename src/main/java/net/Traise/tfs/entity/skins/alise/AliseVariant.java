package net.Traise.tfs.entity.skins.alise;

import java.util.Arrays;
import java.util.Comparator;

public enum AliseVariant {
    ALISE(0),
    ALISE_WHITE(1),
    ALISE_WHITE_0(2);

    private static final AliseVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(AliseVariant::getId)).toArray(AliseVariant[]::new);
    private final int id;

    AliseVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static AliseVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
