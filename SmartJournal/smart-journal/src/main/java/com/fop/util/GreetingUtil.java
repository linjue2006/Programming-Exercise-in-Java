package com.fop.util;

import java.time.LocalTime;

public class GreetingUtil {
    public static String greeting() {
        int h = LocalTime.now().getHour();
        if (h < 12) return "Good Morning";
        if (h < 18) return "Good Afternoon";
        return "Good Evening";
    }
}

