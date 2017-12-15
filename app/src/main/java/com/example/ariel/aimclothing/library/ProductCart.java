package com.example.ariel.aimclothing.library;

/**
 * Created by Ian on 12/15/2017.
 */

public class ProductCart {

    private int quantity;
    private Product product;

    public ProductCart(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public ProductCart(){
        //Empty Constructor
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Double getSubTotal(){
        return product.getPrice() * quantity;
    }





}
