package net.Traise.tfs.util;

import net.Traise.tfs.tfs;

public enum MoldType {
    INGOT("ingot", 0, 100),
    STRING("string", 1, 100),
    AXE("axe", 2, 100),
    SWORD("sword", 3, 100),
    HOE("hoe", 4, 100),
    PICKAXE("pickaxe", 5, 100),
    SHOVEL("shovel", 6, 100),
    KNIFE("knife", 7, 100),
    SPEAR("spear", 8, 100),
    HAMMER("hammer", 9, 900),
    SPADE("spade", 10, 900),
    SICKLE("sickle", 11, 900),
    TROWEL("trowel", 12, 100),
    GEOLOGICAL_HAMMER("geological_hammer", 13, 100),
    BUILDER_WAND("builder_wand", 14, 100),
    PAXEL("paxel", 15, 900),
    STICK("stick", 16, 100),
    BOW("bow", 17, 100);

    private final String name;
    private final int number;
    private final int amount;

    MoldType(String pName, int pNumber, int pAmount) {
        this.name = pName;
        this.number = pNumber;
        this.amount = pAmount;
    }

    public static MoldType getMoldTypeInName(String name) {
        return switch (name) {
            case "ingot" -> INGOT;
            case "axe" -> AXE;
            case "sword" -> SWORD;
            case "hoe" -> HOE;
            case "pickaxe" -> PICKAXE;
            case "shovel" -> SHOVEL;
            case "knife" -> KNIFE;
            case "spear" -> SPEAR;
            case "hammer" -> HAMMER;
            case "spade" -> SPADE;
            case "sickle" -> SICKLE;
            case "trowel" -> TROWEL;
            case "geological_hammer" -> GEOLOGICAL_HAMMER;
            case "builder_wand" -> BUILDER_WAND;
            case "paxel" -> PAXEL;
            case "stick" -> STICK;
            case "string" -> STRING;
            default -> INGOT;
        };
    }

    public static MoldType getMoldTypeInNumber(int Number) {
        return switch (Number) {
            case 0 -> INGOT;
            case 1 -> STRING;
            case 2 -> AXE;
            case 3 -> SWORD;
            case 4 -> HOE;
            case 5 -> PICKAXE;
            case 6 -> SHOVEL;
            case 7 -> KNIFE;
            case 8 -> SPEAR;
            case 9 -> HAMMER;
            case 10 -> SPADE;
            case 11 -> SICKLE;
            case 12 -> TROWEL;
            case 13 -> GEOLOGICAL_HAMMER;
            case 14 -> BUILDER_WAND;
            case 15 -> PAXEL;
            case 16 -> STICK;
            default -> INGOT;
        };
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return number;
    }

    public int getAmount() {
        return amount;
    }
}