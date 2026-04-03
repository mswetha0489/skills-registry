package com.metabyte.skillregistry.util;

public class NormalizationUtil {

    public static String normalize(String input) {
        return input.trim().replaceAll("\\s+", " ").toLowerCase();
    }
}
