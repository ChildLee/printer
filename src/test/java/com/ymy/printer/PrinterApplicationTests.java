package com.ymy.printer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static com.ymy.printer.util.ConvertUtil.mapFilter;
import static com.ymy.printer.util.ConvertUtil.spliceParamsToString;
import static com.ymy.printer.util.KeyUtil.sign;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrinterApplicationTests {

    @Test
    public void tojson() {
        String str = "{\"error\":\"0\",\"error_description\":\"success\",\"body\":{\"access_token\":\"9097fbe8da08443e80f44f1f896470c1\",\"refresh_token\":\"fe0f1e20ce084c3dbcf7ea5958f77c9b\",\"expires_in\":2592000,\"scope\":\"all\"}}\n";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(str);
            System.out.println(node.get("body").get("access_token"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fo() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {


            }
        }, 1000, 1000);
    }

    @Test
    public void prin() {
        String u = UUID.randomUUID().toString().toUpperCase();
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String m = sign("1070784614" + time + "db8264dc3a54faa47ef85a896595f7bf");

        Map<String, String> map = new HashMap<>();
        map.put("client_id", "1070784614");
        map.put("grant_type", "client_credentials");
        map.put("sign", m);
        map.put("scope", "all");
        map.put("timestamp", time);
        map.put("id", u);
        //参数处理
        mapFilter(map);
        String param = spliceParamsToString(map);
        String url = "https://open-api.10ss.net/oauth/oauth";
    }
}
