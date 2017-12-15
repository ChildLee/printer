package com.ymy.printer.util;

import java.util.*;

import static com.ymy.printer.util.StringUtil.isNull;

/**
 * 转换工具类
 */
public final class ConvertUtil {
    /**
     * 除去Map集合中的空值
     *
     * @param params 参数集合
     */
    public static void mapFilter(Map<String, String> params) {
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (isNull(entry.getKey()) || isNull(entry.getValue())) {
                iterator.remove();
            }
        }
    }

    /**
     * 对参数按照key=value的格式，并按照参数名ASCII字典序排序
     *
     * @param params 参数集合
     * @return 拼接完返回字符串
     */
    public static String spliceParamsToString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        //微信要求按照参数名ASCII字典序排序
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params.get(key);
            if (isNull(value)) {
                continue;
            }
            sb.append("&" + key + "=" + value);
        }
        return sb.substring(1);
    }
}
