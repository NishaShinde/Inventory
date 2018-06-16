package com.example.nisha.inventorydatabaseconcept;

public class Inventory {

    String product_name;
    int product_quanity;
    double product_price;
    String product_unit;
    String product_brand;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inventory(String product_name, int product_quanity, double product_price, String product_unit, String product_brand, int id) {

        this.product_name = product_name;
        this.product_quanity = product_quanity;
        this.product_price = product_price;
        this.product_unit = product_unit;
        this.product_brand = product_brand;
        this.id = id;
    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_quanity() {
        return product_quanity;
    }

    public void setProduct_quanity(int product_quanity) {
        this.product_quanity = product_quanity;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }
}
