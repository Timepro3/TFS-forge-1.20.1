package net.Traise.tfs.util;

import net.minecraft.world.inventory.ContainerData;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;

public class StringContainerData implements ContainerData {
    private String data;

    public StringContainerData(String data) {
        this.data = data;
    }

    public String get() {
        return data;
    }

    public void set(String data) {
        this.data = data;
    }

    // Метод для сериализации строки в массив int
    public int[] encode() {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        int[] ints = new int[(bytes.length + 3) / 4]; // 4 байта на int
        for (int i = 0; i < ints.length; i++) {
            int value = 0;
            for (int j = 0; j < 4; j++) {
                int index = i * 4 + j;
                if (index < bytes.length) {
                    value |= (bytes[index] & 0xFF) << (j * 8);
                }
            }
            ints[i] = value;
        }
        return ints;
    }

    // Метод для декодирования массива int обратно в строку
    public static String decode(int[] ints) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int value : ints) {
            for (int j = 0; j < 4; j++) {
                byte b = (byte) ((value >> (j * 8)) & 0xFF);
                if (b != 0) { // Можно добавить условие для окончания
                    baos.write(b);
                }
            }
        }
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }

    @Override
    public int get(int pIndex) {
        return 0;
    }

    @Override
    public void set(int pIndex, int pValue) {

    }

    @Override
    public int getCount() {
        return 0;
    }
}

