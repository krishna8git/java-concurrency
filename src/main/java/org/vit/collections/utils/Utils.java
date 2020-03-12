package org.vit.collections.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {


    public static double getElapsedMillisFromNanos(long start, long end) {
        return getElapsedMicrosFromNanos(start, end) / 1000.0;
    }

    public static double getElapsedMicrosFromNanos(long start, long end) {
        return (end - start) / 1000.0;
    }

}
