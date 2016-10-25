package com.rey.material.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;


/**
 * 屏幕工具类
 *
 * @author notreami
 */
public class ScreenUtils {
    public static float maxWidthDpi;
    public static float maxHeightDpi;

    private ScreenUtils() {

    }

    /**
     * 获取屏幕分辨率
     */
    public static void getDisplayMetrics(Activity mActivity) {
        DisplayMetrics dMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
        maxWidthDpi = px2dip(mActivity, dMetrics.widthPixels);
        maxHeightDpi = px2dip(mActivity, dMetrics.heightPixels);
        System.out.println("分辨率宽度：" + dMetrics.widthPixels + "~ 分辨率高度：" + dMetrics.heightPixels + "~密度:" + dMetrics.density + "~密度Dpi:" + dMetrics.densityDpi + "~Dip宽度：" + maxWidthDpi + "~Dip高度:" + maxHeightDpi);
    }

    /* 根据手机的分辨率从 px(像素) 的单位 转成为 dip */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round(pxValue / scale);
    }

    /* 将px值转换为sp值，保证文字大小不变 */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return Math.round(pxValue / fontScale);
    }

    /* 根据手机的分辨率从 dip 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round(dipValue * scale);
    }

	/* 根据手机的分辨率从 dip 的单位 转成为 px(像素) */
    // public static int dip2pxSys(float dipValue) {
    // float pxValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
    // dipValue, AppInit.getContext().getResources().getDisplayMetrics());
    // return Math.round(pxValue);
    // }

	/* 根据手机的分辨率从 pt 的单位 转成为 px(像素) */
    // public static int pt2pxSys(float ptValue) {
    // float pxValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT,
    // ptValue, AppInit.getContext().getResources().getDisplayMetrics());
    // return Math.round(pxValue);
    // }

    /* 将sp值转换为px值，保证文字大小不变 */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return Math.round(spValue * fontScale);
    }

	/* 将sp值转换为px值，保证文字大小不变 */
    // public static int sp2pxSys(float spValue) {
    // float pxValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
    // spValue, AppInit.getContext().getResources().getDisplayMetrics());
    // return Math.round(pxValue);
    // }
}
