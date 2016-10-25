package com.rey.material.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;


/**
 * 获取对应的app信息
 *
 * @author notreami
 */
public class AppUtils {

    /* 获取本应用的版本号 */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager pManager = context.getPackageManager();
            PackageInfo pInfo = pManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = pInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /* 获取本应用的版本名 */
    public static String getAppVersion(Context context) {
        String versionName = null;
        try {
            PackageManager pManager = context.getPackageManager();
            PackageInfo pInfo = pManager.getPackageInfo(context.getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /* 获取本应用的包名 */
    public static String getAppPackageName(Context context) {
        String packageName = null;
        try {
            PackageManager pManager = context.getPackageManager();
            PackageInfo pInfo = pManager.getPackageInfo(context.getPackageName(), 0);
            packageName = pInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }

    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
