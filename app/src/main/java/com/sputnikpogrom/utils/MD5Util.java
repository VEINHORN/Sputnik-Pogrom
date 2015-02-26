package com.sputnikpogrom.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by veinhorn on 26.2.15.
 */

public class MD5Util {
    private static final String TAG = MD5Util.class.getName();

    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    public static String md5Hex (String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, "No such algorithm", e);
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, "Unsupported encoding", e);
        }
        return null;
    }
}