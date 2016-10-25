package com.rey.material.utils;

import android.util.Log;

public class T {

    static String AppName = "TzPay";

    public static Boolean isTesting = true;

    public static void i(String string) {
        if (isTesting) {
            Log.i(AppName, string);
        }
    }

    public static void i(String tag, String string) {
        if (isTesting) {
            Log.i(tag, string);
        }
    }

    public static void w(String string) {
        if (isTesting) {
            Log.w(AppName, string);
        }
    }

    public static void w(String tag, String string) {
        if (isTesting) {
            Log.w(tag, string);
        }
    }

    public static void e(String string) {
        if (isTesting) {
            Log.e(AppName, string);
        }
    }

    public static void e(String tag, String string) {
        if (isTesting) {
            Log.e(tag, string);
        }
    }

    public static void d(String string) {
        if (isTesting) {
            Log.d(AppName, string);
        }
    }

    public static void d(String tag, String string) {
        if (isTesting) {
            Log.d(tag, string);
        }
    }

    public static void a(int num) {
        if (isTesting) {
            Log.d(AppName, Integer.toString(num));
        }
    }

    public static void a(String tag, int num) {
        if (isTesting) {
            Log.d(tag, Integer.toString(num));
        }
    }

}