package com.yk.mvpframe.Util;

/**
 * @FileName AsciiUtils
 * @Author alan
 * @Date 2019/7/12 9:55
 * @Describe TODO
 * @Mark
 **/
public class AsciiUtils {
    /**
     * string 转 Ascii串 乘2+3
     * @param value
     * @return
     */
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1) {
                sbu.append(((int)chars[i])*2+3).append(",");
            }
            else {
                sbu.append((int)chars[i]*2+3);
            }
        }
        return sbu.toString();
    }

    /**
     * Ascii串 转 string
     * @param value
     * @return
     */
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    public static String stringEncryption(String value)
    {
        return asciiToString(stringToAscii(value));
    }
}
