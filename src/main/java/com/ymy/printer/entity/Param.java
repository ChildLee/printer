package com.ymy.printer.entity;

import java.util.List;

public class Param {
    private List<Order> list;
    private String money;
    private String BoxPrice;
    private String sendPrice;
    private String totalPrice;
    private String remarks;
    private String name;
    private String phone;
    private String address;

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBoxPrice() {
        return BoxPrice;
    }

    public void setBoxPrice(String boxPrice) {
        BoxPrice = boxPrice;
    }

    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
