package com.ymy.printer.util;

/**
 * 字符工具类
 */
public final class StringUtil {
    /**
     * 判断是不是空值
     *
     * @param value 需要判断的值
     * @return 空值为true, 否则false
     */
    public static Boolean isNull(String value) {
        if (null == value || value.isEmpty()) {
            return true;
        }
        return false;
    }
}
