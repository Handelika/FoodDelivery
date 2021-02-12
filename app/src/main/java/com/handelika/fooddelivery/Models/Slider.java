package com.handelika.fooddelivery.Models;

public class Slider {
    private int id;
    private String header;
    private String imgUrl;
    private String btnText;

    public Slider(){}

    public Slider(int id, String header, String imgUrl, String btnText) {
        this.id = id;
        this.header = header;
        this.imgUrl = imgUrl;
        this.btnText = btnText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }
}
