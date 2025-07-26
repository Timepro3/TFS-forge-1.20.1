package net.Traise.tfs.util;

import net.Traise.tfs.tfs;

public enum MoldType {
    INGOT("ingot", 0),
    PICK("pick", 1);

    private final String name;
    private final int number;

    MoldType(String pName, int pNumber) {
        name = pName;
        number = pNumber;
    }

    public static MoldType getMoldTypeInName(String name) {
        return switch (name) {
            case "ingot" -> MoldType.INGOT;
            case "pick" -> MoldType.PICK;
            default -> MoldType.INGOT;
        };
    }

    public static MoldType getMoldTypeInNumber(int Number) {
        return switch (Number) {
            case 0 -> MoldType.INGOT;
            case 1 -> MoldType.PICK;
            default -> MoldType.INGOT;
        };
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return number;
    }
}
