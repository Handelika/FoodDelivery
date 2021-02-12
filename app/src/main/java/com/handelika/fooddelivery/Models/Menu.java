package com.handelika.fooddelivery.Models;

public class Menu {

    private  int id;
    private String menu;
    private String menuDetail;
    private String menuPrice;
    private String menuImgUrl;

    public Menu(){}

    public Menu(int id, String menu, String menuDetail, String menuPrice, String menuImgUrl) {
        this.id = id;
        this.menu = menu;
        this.menuDetail = menuDetail;
        this.menuPrice = menuPrice;
        this.menuImgUrl = menuImgUrl;
    }

    public String getMenuImgUrl() {
        return menuImgUrl;
    }

    public void setMenuImgUrl(String menuImgUrl) {
        this.menuImgUrl = menuImgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMenuDetail() {
        return menuDetail;
    }

    public void setMenuDetail(String menuDetail) {
        this.menuDetail = menuDetail;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }
}
