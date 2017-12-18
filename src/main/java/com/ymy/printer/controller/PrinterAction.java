package com.ymy.printer.controller;

import com.ymy.printer.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PrinterAction {

    @RequestMapping("getToKen")
    public String setPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("<center>香里约</center>\n");
        sb.append("--------------------------------\n");
        sb.append("下单时间:2017年06月22日18时23分\\\n");
        sb.append("订单编号:40807603938532\n");
        sb.append("**************商品**************\n");
        sb.append("红焖猪手砂锅饭 x1 19\n");
        sb.append("********************************\n");
        sb.append("订单备注：多放点辣子\n");
        sb.append("电话:15922222222\n");
        sb.append("地址:广东省深圳市龙华新区牛栏前大厦301号~\n");
        sb.append("--------------------------------\n");
        sb.append("<center>**完**</center>\n");

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
        return result;
    }
}
