package com.example.ariel.aimclothing.library;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ian on 12/12/2017.
 */

public class Product implements Parcelable {


    private int brand;

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    private String brand_name;
    private int category;
    private String category_name;
    private String product_name;
    private double price;
    private int image;
    private int quantity;


    public Product(int brand, int category, String product_name, double price, int image) {
        this.brand = brand;
        this.category = category;
        this.product_name = product_name;
        this.price = price;
        this.image = image;
    }

    public Product(){
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brandId) {
            this.brand = brandId;
        }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getBrand_name() {
        switch(brand){
            case 0: brand_name = "Default"; break;
            case 1: brand_name = "AMD"; break;
            case 2: brand_name = "Asus"; break;
            case 3: brand_name = "Intel"; break;
            case 4: brand_name = "Logitech"; break;
            case 5: brand_name = "MSI"; break;
            case 6: brand_name = "Samsung"; break;
            case 7: brand_name = "A4Tech"; break;
        }
        return brand_name;
    }

    public String getCategory_name() {
        switch(category){
            case 1: category_name = "Keyboard"; break;
            case 2: category_name = "Mouse"; break;
            case 3: category_name = "Processor"; break;
            case 4: category_name = "Monitor"; break;
            case 5: category_name = "Motherboard"; break;
            case 6: category_name = "Memory"; break;
            case 7: category_name = "Video Card"; break;
            case 8: category_name = "Accessories"; break;
        }

        return category_name;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal(){
        return price*quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand=" + brand +
                ", category=" + category +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                ", image=" + image +
                "} \n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.brand);
        dest.writeString(this.brand_name);
        dest.writeInt(this.category);
        dest.writeString(this.category_name);
        dest.writeString(this.product_name);
        dest.writeDouble(this.price);
        dest.writeInt(this.image);
        dest.writeInt(this.quantity);
    }

    protected Product(Parcel in) {
        this.brand = in.readInt();
        this.brand_name = in.readString();
        this.category = in.readInt();
        this.category_name = in.readString();
        this.product_name = in.readString();
        this.price = in.readDouble();
        this.image = in.readInt();
        this.quantity = in.readInt();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
