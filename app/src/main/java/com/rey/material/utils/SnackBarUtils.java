package com.rey.material.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * 底部提示
 * <p/>
 */
public class SnackBarUtils {
    public static void showLongSnackBar(View view, String msg, String actionTitle) {

        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(actionTitle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    public static void showShortSnackBar(View view, String msg, String actionTitle) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setAction(actionTitle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    public static void showClickSnackBar(View view, String msg, String actionTitle, View.OnClickListener clickListener) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(actionTitle, clickListener).show();
    }

    public static void showClickSnackBarAlways(View view, String msg, String actionTitle, View.OnClickListener clickListener) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(actionTitle, clickListener).show();
    }

    public static void showShortSnackBar(View view, String msg) {

        showShortSnackBar(view, msg, "我知道了");
    }

    public static void showLongSnackBar(View view, String msg) {

        showLongSnackBar(view, msg, "我知道了");
    }
}
