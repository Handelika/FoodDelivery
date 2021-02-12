package com.handelika.fooddelivery.Models;

public class Orders {

    private int id;
    private int user_id;
    private String date;
    private String time;
    private String orderItem;
    private int piece;
    private String price;

    public Orders(){}

    public Orders(int id, int user_id, String date, String time, String orderItem, int piece, String price) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.orderItem = orderItem;
        this.piece = piece;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
