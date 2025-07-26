package net.Traise.tfs.entity.skins.tatar;

import java.util.Arrays;
import java.util.Comparator;

public enum TatarVariant {
    TATAR(0),
    SNOW(1),
    DED(2),
    DED_WHITE(3),
    HEROBRINE(4),
    SNOW_WHITE(5),
    SNOW_BLACK_0(6),
    SNOW_BLACK_1(7),
    SNOW_BLACK_2(8),
    SNOW_BLACK_3(9),
    SNOW_BLACK_4(10),
    SNOW_BLACK_5(11),
    TATAR_WHITE(12),
    TATAR_BLACK_0(13),
    TATAR_BLACK_1(14),
    TATAR_BLACK_2(15),
    TATAR_BLACK_3(16),
    TATAR_BLACK_4(17),
    TATAR_BLACK_5(18);

    private static final TatarVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(TatarVariant::getId)).toArray(TatarVariant[]::new);
    private final int id;

    TatarVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static TatarVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
