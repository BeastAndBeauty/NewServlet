package com.paopao.newservlet.utils;

import java.security.MessageDigest;

/**
 * 作者：paopao on 2018/12/18 21:20
 * <p>
 * 作用:md5加密工具类
 */
public class MD5Util {
    public static String encode(String string) throws Exception {
        byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
