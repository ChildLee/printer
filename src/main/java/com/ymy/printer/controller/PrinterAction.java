package com.ymy.printer.controller;

import com.ymy.printer.entity.Order;
import com.ymy.printer.entity.Param;
import com.ymy.printer.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@RestController
public class PrinterAction {
    @GetMapping("Callback")
    public String Callback() {
        return "{\"data\":\"OK\"}";
    }

    @PostMapping("print")
    public String setPrint(@RequestBody Param param) {
        if (null == param) return null;
        StringBuilder sb = new StringBuilder();
        sb.append("<MA>2</MA>");
        sb.append("<FS2><center>香里约丰收辣椒</center></FS2>");
        sb.append("--------------------------------\n");
        sb.append("下单时间:").append(TimeUtil.getFormatTime()).append("\n");
        sb.append("订单编号:").append(TimeUtil.getNoSpaceTime().concat(RandomUtil.getRandomNumber(9))).append("\n");
        sb.append("**************商品**************\n");
        sb.append("<table><tr><td>名称</td><td>数量</td><td>单价</td></tr></table>");

        Iterator<Order> iterator = param.getList().iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();

            sb.append("<table><tr><td>").append(order.getName())
                    .append("</td><td>x").append(order.getNumber())
                    .append("</td><td>￥").append(order.getOnePrice())
                    .append("</td></tr></table>");
        }

        sb.append("***************完***************\n");
        sb.append("餐盒费:￥").append(param.getBoxPrice()).append("\n");
        sb.append("配送费:￥").append(param.getSendPrice()).append("\n");
        sb.append("总  价:￥").append(param.getTotalPrice()).append("\n");
        sb.append("--------------------------------\n");
        sb.append("姓名:").append(param.getName()).append("\n");
        sb.append("电话:").append(param.getPhone()).append("\n");
        sb.append("地址:").append(param.getAddress()).append("\n");
        sb.append("订单备注：").append(null != param.getRemarks() ? param.getRemarks() : "").append("\n");
        sb.append("--------------------------------\n");

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
        String url = "https://open-api.10ss.net/print/index";
        String result = WebUtil.httpPost(url, ConvertUtil.spliceParamsToString(map));
        return result;
    }
}
