package net.Traise.tfs.util;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class AddMth {
    public static int wheelOfFortune(int countParts, double[] percent, int totalPercent) {
        int T = Mth.nextInt(RandomSource.create(), 0, totalPercent);
        double cumulative = 0;
        for (int i = 0; i < countParts; i++) {
            cumulative += percent[i];
            if (T < cumulative) { // Используйте `<` вместо `<=`
                return i; // Возвращаем индекс без смещения
            }
        }
        return countParts - 1; // На случай погрешности
    }
}
