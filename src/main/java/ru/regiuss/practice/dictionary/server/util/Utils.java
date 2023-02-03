package ru.regiuss.practice.dictionary.server.util;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static <T> Map<Integer, T> arrayToMap(T[] values) {
        Map<Integer, T> map = new HashMap<>(values.length<<1);
        for (int i = 0; i < values.length; i++) map.put(i+1, values[i]);
        return map;
    }
}
