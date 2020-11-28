package com.example.onlineshop;

public class CartModel {
    private int cart_id;
    private int item_id;
    private int user_id;
    private int buy_status;//0 if not paid, 1 if alredy paid

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBuy_status() {
        return buy_status;
    }

    public void setBuy_status(int buy_status) {
        this.buy_status = buy_status;
    }
}
