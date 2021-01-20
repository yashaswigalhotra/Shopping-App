package com.example.startup.ui;

public class Items {

    String Product,Price;
    public Items(){

    }
    public Items(String Product, String Price){
        this.Product = Product;
        this.Price = Price;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
}
