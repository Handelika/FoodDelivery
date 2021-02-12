package com.handelika.fooddelivery.Models;

public class UserAddress {
    private int id;
    private String addressHeader;
    private String addressDetail;

    public UserAddress(){}

    public UserAddress(int id, String addressHeader, String addressDetail) {
        this.id = id;
        this.addressHeader = addressHeader;
        this.addressDetail = addressDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressHeader() {
        return addressHeader;
    }

    public void setAddressHeader(String addressHeader) {
        this.addressHeader = addressHeader;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
