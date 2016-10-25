package com.rey.material.utils;

/**
 * Created by chenfeiyue on 16/4/7.
 */
public class TzCryptTool {

    /**
     * 登录验证mac
     * @param deviceId
     * @param userId
     * @param userPwd
     * @return
     */
    public static String md5ForLogin(String deviceId, String userId, String userPwd) {
        StringBuilder sb = new StringBuilder();
        sb.append("DEVICEID=").append(deviceId.toUpperCase());
        sb.append("&USERID=").append(userId.toUpperCase());
        sb.append("&USERPWD=").append(userPwd.toUpperCase());
        String mac = "";
        try {
            mac = CryptTool.md5Hex(getContentBytes(sb.toString(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    /**
     * 上传交易数据验证
     * @param deviceId
     * @param merchantId
     * @param orderNo
     * @param shopId
     * @param funcId
     * @param orgKey
     * @return
     */
    public static String md5ForEPay(String deviceId, String merchantId, String orderNo, String shopId, String funcId, String orgKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("DEVICEID=").append(deviceId.toUpperCase());
        sb.append("&MERCHANTID=").append(merchantId.toUpperCase());
        sb.append("&ORDERNO=").append(orderNo.toUpperCase());
        sb.append("&SHOPID=").append(shopId.toUpperCase());
        sb.append("&FUNCID=").append(funcId.toUpperCase());
        sb.append("&KEY=").append(orgKey);
        String mac = "";
        try {
            mac = CryptTool.md5Hex(getContentBytes(sb.toString(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    /**
     * 查询交易数据验证mac
     * @param deviceId
     * @param shopId
     * @param orgKey
     * @return
     */
    public static String md5ForEPayQuery(String deviceId, String shopId, String orgKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("DEVICEID=").append(deviceId.toUpperCase());
        sb.append("&SHOPID=").append(shopId.toUpperCase());
        sb.append("&KEY=").append(orgKey);
        String mac = "";
        try {
            mac = CryptTool.md5Hex(getContentBytes(sb.toString(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset))
            return content.getBytes();
        try {
            return content.getBytes(charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }
}
