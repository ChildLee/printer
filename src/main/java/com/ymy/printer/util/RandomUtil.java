package com.ymy.printer.util;

import java.util.Random;

/**
 * 随机数工具类
 */
public final class RandomUtil {
    /**
     * 获取一定长度的随机数字
     *
     * @param length 指定数字长度
     * @return 一定长度的数字
     */
    public static String getRandomNumber(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }
}
