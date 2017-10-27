package org.arnoid.damoclus.controller.strings;

import org.arnoid.damoclus.R;

import java.util.Map;

public class StringRefUtil {

    private static final String PATTER_STRING_REFERENCE = "@string/";
    private static final String PATTER_IMAGE_REFERENCE = "@image/";

    public static boolean isStringReference(String stringToCheck) {
        return stringToCheck.startsWith(PATTER_STRING_REFERENCE);
    }

    public static boolean isImageReference(String stringToCheck) {
        return stringToCheck.startsWith(PATTER_IMAGE_REFERENCE);
    }

    public static String stringByRef(Map<String, String> map, String key) {
        if (key == null) {
            return key;
        } else {
            String extractedReference = key.replace(PATTER_STRING_REFERENCE, "");
            return map.get(extractedReference);
        }
    }

    public static String imageByRef(Map<String, String> map, String key) {
        String extractedReference = key.replace(PATTER_IMAGE_REFERENCE, "");
        return map.get(extractedReference);
    }
}
