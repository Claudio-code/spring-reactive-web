package com.reactive.reactive.learning.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SleepUtil {
    public static void sleepSeconds(int seconds) {
        try {
            final long milliseconds = seconds * 1000L;
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
