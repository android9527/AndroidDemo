package com.rey.material.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


public class Toaster {

    public static boolean isShowToast = true;

    public static void l(Context context, String string) {

        if (isShowToast) {
            Toast.makeText(context, string, Toast.LENGTH_LONG).show();
        }
    }

    public static void s(Context context, String string) {
        if (isShowToast) {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 在UI线程运行弹出
     */
    public static void showToastOnUiThread(final Activity ctx, final String text) {
        if (ctx != null) {
            ctx.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    l(ctx, text);
                }
            });
        }
    }

}