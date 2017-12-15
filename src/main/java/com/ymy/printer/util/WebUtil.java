package com.ymy.printer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Web工具类
 */
public final class WebUtil {
    public static String httpPost(String url, String param) {
        HttpURLConnection conn = null;
        OutputStream os = null;
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            //POST提交设置参数
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setUseCaches(false);  //不允许缓存
            conn.setConnectTimeout(60000);//连接超时 单位毫秒
            conn.setReadTimeout(60000);   //读取超时 单位毫秒
            //发送POST请求必须设置如下两行
            conn.setDoOutput(true);   //向httpUrlConnection输出
            conn.setDoInput(true);    //从httpUrlConnection读入

            //输出参数
            os = conn.getOutputStream();
            os.write(param.getBytes("utf-8"));
            os.flush();

            //获取接口返回数据
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String temp;
            sb = new StringBuilder();
            while (null != (temp = br.readLine())) {
                sb.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                br.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
