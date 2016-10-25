package com.rey.material.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by chenfeiyue on 15/7/6.
 */
public class SPUtil {
    private volatile static SPUtil saver;
    private final static int MODE = Context.MODE_WORLD_READABLE
            | Context.MODE_WORLD_WRITEABLE | Context.MODE_MULTI_PROCESS;
    private static SharedPreferences sp;

    public static SPUtil getInstance(Context context) {
        if (saver == null) {
            synchronized (SPUtil.class) {
                if (saver == null) {
                    saver = new SPUtil();
                    sp = context.getApplicationContext().getSharedPreferences("shared", MODE);
                }
            }
        }
        return saver;
    }


    public void save(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void save(String key, long value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void save(String key, int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void save(String key, float value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void save(String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void remove(String key){
        SharedPreferences.Editor editor = sp.edit();
        if (sp.contains(key)) {
            editor.remove(key);
            editor.apply();
        }
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return sp.getFloat(key, defValue);
    }

    public static final class SPKey{
        public static final String NEW_MESSAGE_NUMBER = "new_message_number";
        public static String IS_PAY_SUCCESS = "is_pay_success";
        public static String WECHAT_PAY_TYPE = "wechat_pay_type";
    }

}
