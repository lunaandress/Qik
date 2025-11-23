package com.example.app_qik_tfg;

public class Product {

    private String  name ;
    private double price;
    private  int imgProduct;
    private int cantidad;

    //constructor

    public Product(String name, double price, int imgProduct) {
        this.name = name;
        this.price = price;
        this.imgProduct = imgProduct;

    }


    //get and set

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getImgProduct() {
        return imgProduct;
    }
    public void setImgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }

}


