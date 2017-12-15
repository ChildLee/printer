package com.ymy.printer.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 密钥工具类
 */
public final class KeyUtil {
    /**
     * md5加密
     *
     * @param param 需要加密的字符串
     * @return 加密结果
     */
    public static String sign(String param) {
        byte[] bytes = null;
        try {
            bytes = param.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
