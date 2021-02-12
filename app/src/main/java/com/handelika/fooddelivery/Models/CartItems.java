package com.handelika.fooddelivery.Models;

import java.util.List;

public class CartItems {
    private int id;
    private int userID;
    private String cartItem;
    private String cartImagUrl;
    private int piece;
    private String price;
    private List<String> ingredients;

    public CartItems(){}

    public CartItems(int id, int userID, String cartItem, String cartImagUrl, int piece, String price, List<String> ingredients) {
        this.id = id;
        this.userID = userID;
        this.cartItem = cartItem;
        this.cartImagUrl = cartImagUrl;
        this.piece = piece;
        this.price = price;
        this.ingredients = ingredients;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCartItem() {
        return cartItem;
    }

    public void setCartItem(String cartItem) {
        this.cartItem = cartItem;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCartImagUrl() {
        return cartImagUrl;
    }

    public void setCartImagUrl(String cartImagUrl) {
        this.cartImagUrl = cartImagUrl;
    }
}
