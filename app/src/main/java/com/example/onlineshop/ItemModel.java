package com.example.onlineshop;

public class ItemModel {
    private int item_id;
    private String item_name;
    private int item_price;
    private int item_gender_category; //0 if female, 1 if male, 2 if child, 3 if either male or female, 4 for all category
    private String item_category; //baju, jaket, short, jeans, sepatu, sandal,  accessories
    private String item_size;
    private String item_material;
    private  String item_desc;
    private String item_color;
    private String item_image;

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public String getItem_color() {
        return item_color;
    }

    public void setItem_color(String item_color) {
        this.item_color = item_color;
    }

    public int getItem_gender_category() {
        return item_gender_category;
    }

    public void setItem_gender_category(int item_gender_category) {
        this.item_gender_category = item_gender_category;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public String getItem_size() {
        return item_size;
    }

    public void setItem_size(String item_size) {
        this.item_size = item_size;
    }

    public String getItem_material() {
        return item_material;
    }

    public void setItem_material(String item_material) {
        this.item_material = item_material;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }
}
