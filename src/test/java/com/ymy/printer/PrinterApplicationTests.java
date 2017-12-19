package com.ymy.printer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymy.printer.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        System.out.println(result);
        //66f21cf8f6c74bbda6fd8fb7893037cc
    }

    @Test
    public void prin() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append("<MA>2</MA>");
//        sb.append("<FS2><center>香里约丰收辣椒</center></FS2>");
//        sb.append("--------------------------------\n");
//        sb.append("下单时间:").append(TimeUtil.getFormatTime()).append("\n");
//        sb.append("订单编号:20171222182312345678901\n");
//        sb.append("**************商品**************\n");
//        sb.append("<table><tr><td>烧烤鱿鱼蛋炒饭</td><td>x100</td><td>￥5000</td></tr></table>");
//        sb.append("<table><tr><td>西红柿蛋炒饭</td><td>x100</td><td>￥5000</td></tr></table>");
//        sb.append("<table><tr><td>红烧清蒸烧烤鱿鱼</td><td>x100</td><td>￥5000</td></tr></table>");
//        sb.append("<table><tr><td>").append("铁板鱿鱼炒饭")
//                .append("</td><td>x").append("100")
//                .append("</td><td>￥").append("500")
//                .append("</td></tr></table>");
//        sb.append("***************完***************\n");
//        sb.append("电话:18888888888\n");
//        sb.append("地址:广东省深圳市龙华新区牛栏前大厦301号~\n");
//        sb.append("订单备注：少放点辣子\n");
//        sb.append("--------------------------------\n");

        String origin_id = TimeUtil.getNoSpaceTime().concat(RandomUtil.getRandomNumber(18));
        String uuid = UUID.randomUUID().toString().toUpperCase();
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String md5 = KeyUtil.getMD5("1070784614" + time + "db8264dc3a54faa47ef85a896595f7bf");

        Map<String, String> map = new HashMap<>();
        map.put("client_id", "1070784614");
        map.put("access_token", "66f21cf8f6c74bbda6fd8fb7893037cc");
        map.put("machine_code", "4004546993");
        map.put("sign", md5);
        map.put("timestamp", time);
        map.put("id", uuid);
        map.put("origin_id", origin_id);
        map.put("content", sb.toString());
        //参数处理
        ConvertUtil.mapFilter(map);
        String param = ConvertUtil.spliceParamsToString(map);
        String url = "https://open-api.10ss.net/print/index";
        String result = WebUtil.httpPost(url, param);
        System.out.println(result);
    }
}
