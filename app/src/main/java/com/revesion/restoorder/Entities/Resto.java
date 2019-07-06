package com.revesion.restoorder.Entities;

public class Resto {
    int id;
    String name;
    String cuisine;
    String address;
    String min_order_price;
    String image;

    public Resto() {
    }

    public Resto(int id, String name, String cuisine, String address, String min_order_price, String image) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.address = address;
        this.min_order_price = min_order_price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMin_order_price() {
        return min_order_price;
    }

    public void setMin_order_price(String min_order_price) {
        this.min_order_price = min_order_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
