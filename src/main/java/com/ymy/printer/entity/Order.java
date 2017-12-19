package com.ymy.printer.entity;

public class Order {
    private String image;
    private String lunchBoxPrice;
    private String name;
    private String number;
    private String onePrice;
    private String productId;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLunchBoxPrice() {
        return lunchBoxPrice;
    }

    public void setLunchBoxPrice(String lunchBoxPrice) {
        this.lunchBoxPrice = lunchBoxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOnePrice() {
        return onePrice;
    }

    public void setOnePrice(String onePrice) {
        this.onePrice = onePrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "image='" + image + '\'' +
                ", lunchBoxPrice='" + lunchBoxPrice + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", onePrice='" + onePrice + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
