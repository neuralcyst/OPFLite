package ru.ncedu.opflite.util;

public class StringUtils {

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
