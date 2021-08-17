package com.burchard36.utils;

import com.burchard36.MyPlugin;

import static com.burchard36.MyPlugin.ofString;

public class Logger {

    private static final String prefix = "&6Saiyan&eGens&6Z ";


    public static void debug(final String message) {
        if (MyPlugin.DEBUG) {
            System.out.println(ofString(prefix + "&b:: &2DEBUG &b:: &a" + message));
        }
    }

    public static void log(final String message) {
        System.out.println(ofString(prefix + "&b:: &3INFO &b:: &b" + message));
    }

    public static void warn(final String message) {
        System.out.println(ofString(prefix + "&4:: &eWARN &4:: &c" + message));
    }

    public static void error(final String message) {
        System.out.println(ofString(prefix + "&4:: &cERROR &4:: &c" + message));
    }
}
