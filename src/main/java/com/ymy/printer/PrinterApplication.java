package com.ymy.printer;

import com.ymy.printer.util.ConvertUtil;
import com.ymy.printer.util.KeyUtil;
import com.ymy.printer.util.WebUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class PrinterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrinterApplication.class, args);
//        getToken();
    }

    /**
     * 获取Token
     */
    public static void getToken() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                String uuid = UUID.randomUUID().toString().toUpperCase();
                String time = String.valueOf(System.currentTimeMillis() / 1000);
                String md5 = KeyUtil.getMD5("1070784614" + time + "db8264dc3a54faa47ef85a896595f7bf");

                Map<String, String> map = new HashMap<>();
                map.put("client_id", "1070784614");
                map.put("grant_type", "client_credentials");
                map.put("scope", "all");
                map.put("sign", md5);
                map.put("timestamp", time);
                map.put("id", uuid);
                //参数处理
                ConvertUtil.mapFilter(map);
                String param = ConvertUtil.spliceParamsToString(map);
                String url = "https://open-api.10ss.net/oauth/oauth";
                String result = WebUtil.httpPost(url, param);
                System.out.println();
            }
        }, 60000, 86400000);
    }
}
