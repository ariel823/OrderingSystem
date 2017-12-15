package com.example.ariel.aimclothing.library;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 12/15/2017.
 */

public class Cart implements Parcelable {

    private List<Product> products;
    private Double total;

    public Cart() {
        products = new ArrayList<>();
        total = 0.0;

    }

    public Double getTotal(){
        return total;
    }

    public void addToCart(Product prod){
        products.add(prod);
        total += prod.getSubtotal();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void removeFromCart(int i){
        total -= products.get(i).getSubtotal();
        products.remove(i);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.products);
        dest.writeValue(this.total);

    }

    protected Cart(Parcel in) {
        this.products = in.createTypedArrayList(Product.CREATOR);
        this.total = (Double) in.readValue(Double.class.getClassLoader());

    }

    public static final Parcelable.Creator<Cart> CREATOR = new Parcelable.Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };
}
