package com.ymy.printer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrinterApplicationTests {

    @Test
    public void prin() throws IOException {

    }

    @Test
    public void http() throws IOException {
        String u = UUID.randomUUID().toString().toUpperCase();
        Date date = new Date();
        String time = String.valueOf(date.getTime() / 1000);
        String m = sign("1070784614" + time + "db8264dc3a54faa47ef85a896595f7bf");

        URL url = new URL("https://open-api.10ss.net/oauth/oauth");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //POST提交设置参数
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setUseCaches(false);  //不允许缓存
        conn.setConnectTimeout(10000);//连接超时 单位毫秒
        conn.setReadTimeout(10000);   //读取超时 单位毫秒
        //发送POST请求必须设置如下两行
        conn.setDoOutput(true);   //向httpUrlConnection输出
        conn.setDoInput(true);    //从httpUrlConnection读入

        Map<String, String> map = new HashMap<>();
        map.put("client_id", "1070784614");
        map.put("grant_type", "client_credentials");
        map.put("sign", m);
        map.put("scope", "all");
        map.put("timestamp", time);
        map.put("id", u);

        String param = createLinkString(map);
        //输出参数
        OutputStream os = conn.getOutputStream();
//        os.write(param.getBytes("utf-8"));
        os.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String temp;
        StringBuilder sb = new StringBuilder();
        while (null != (temp = br.readLine())) {
            sb.append(temp);
        }
        System.out.println(sb);
    }

    /**
     * md5加密
     *
     * @param linkText 需要签名的字符串
     * @return 签名结果
     */
    public static String sign(String linkText) {
        String text = linkText;
        byte[] bytes = new byte[0];
        try {
            bytes = text.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DigestUtils.md5DigestAsHex(bytes);
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
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

    /**
     * 获取一定长度的随机数字
     *
     * @param length 指定数字长度
     * @return 一定长度的数字
     */
    public static String getRandomNumber(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

    /**
     * 对参数按照key=value的格式，并按照参数名ASCII字典序排序
     *
     * @param params 参数集合
     * @return 拼接完返回字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        //微信要求按照参数名ASCII字典序排序
        Collections.sort(keys);
        String parstr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (null == value || "".equals(value)) {
                continue;
            }
            parstr += "&" + key + "=" + value;
        }
        return parstr.substring(1);
    }
}
