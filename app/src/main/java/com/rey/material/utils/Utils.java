package com.rey.material.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Created by chenfeiyue on 15/7/28.
 */
public class Utils {

    private static String sID = "";
    private static final String UNIQUEID = "UNIQUEID";

//    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyyMMdd");

    public static String getAppVersion(Context context) {
        String appVersion = "1.0";
        try {
            appVersion = context
                    .getPackageManager()
                    .getPackageInfo(
                            context.getPackageName(), 0).versionName;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appVersion;
    }


    public static void onCopy2Clipboar(Context context, String text) {

        if (android.os.Build.VERSION.SDK_INT > 11) {
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setPrimaryClip(ClipData.newPlainText("text", text));
        } else {
            android.text.ClipboardManager cmb = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(text);
        }
    }

    public static String getDeviceId(Context context) {
        if (context == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = "";

        try {
            deviceId = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(deviceId)) {
            try {
                deviceId = telephonyManager.getSimSerialNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(deviceId)) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            String macAddress = wifiManager.getConnectionInfo().getMacAddress();
            deviceId = md5(macAddress);
        }
        if (TextUtils.isEmpty(deviceId)) {
            try {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = md5(uniqueId(context));
        }
        return deviceId;
    }

    private static String md5(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
//            System.out.println("result: " + buf.toString());//32位的加密
//            System.out.println("result: " + buf.toString().substring(8,24));//16位的加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public synchronized static String uniqueId(Context context) {
        if (sID == null) {
            File uniqueid = new File(context.getFilesDir(), UNIQUEID);
            try {
                if (!uniqueid.exists())
                    writeUniqueidFile(uniqueid);
                sID = readUniqueidFile(uniqueid);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }


    private static String readUniqueidFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeUniqueidFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }

    /**
     * 获取屏幕宽高
     *
     * @param activity
     * @return width height
     */
    public static int[] getScreenPixels(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pixels[] = new int[2];
        pixels[0] = metrics.widthPixels;
        pixels[1] = metrics.heightPixels;
        return pixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbar;
    }

    public static boolean isCanConnectionNetWork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    public static String getDotNumber(String numberString) {

        if (TextUtils.isEmpty(numberString)) {
            return "0";
        }
        long number = Long.parseLong(numberString);
        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
            number *= -1;
            numberString = String.valueOf(number);
        }
        if (numberString.length() > 2) {
            String brforeString = numberString.substring(0, numberString.length() - 2);

            String afterString = numberString.substring(numberString.length() - 2, numberString.length());

            numberString = brforeString + "." + afterString;
        } else if (numberString.length() == 2) {
            numberString = "0." + numberString;
        } else if (numberString.length() == 1) {
            numberString = "0.0" + numberString;
        }

        return isNegative ? "-" + numberString : numberString;
    }

    public static Bitmap zoomBitmap(Bitmap icon, int h) {
        // ����ͼƬ
        Matrix m = new Matrix();
        float sx = (float) 2 * h / icon.getWidth();
        float sy = (float) 2 * h / icon.getHeight();
        m.setScale(sx, sy);
        // ���¹���һ��2h*2h��ͼƬ
        return Bitmap.createBitmap(icon, 0, 0, icon.getWidth(), icon.getHeight(), m, false);
    }

    /**
     * 关闭Dialog
     *
     * @param dialog
     */
    public static void safeDismissDialog(Dialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long timeStamp = 0;

    /**
     * return server time
     *
     * @return
     */
    public static String getTime() {
        long time = System.currentTimeMillis() - timeStamp;
        return formatter.format(time);
    }

    public static String getDateTime() {
        long time = System.currentTimeMillis() - timeStamp;
        return dateFormat.format(time);
    }

    public static void setTime(String timestamp) {
        try {
            //记住时间差
            Date date = formatter.parse(timestamp);
            timeStamp = System.currentTimeMillis() - date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static Date parseDate(String date) throws ParseException {
        return formatter.parse(date);
    }

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatTime(String date) {
        if (TextUtils.isEmpty(date)) {
            return date;
        }
        try {
            Date d = Utils.parseDate(date);
            return format.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
