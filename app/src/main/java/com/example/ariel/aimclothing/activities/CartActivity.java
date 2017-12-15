package com.example.ariel.aimclothing.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.library.CartAdapter;
import com.example.ariel.aimclothing.library.Cart;
import com.example.ariel.aimclothing.library.DBTools;
import com.example.ariel.aimclothing.library.ViewPagerAdapter;

public class CartActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;

    private CardView btnCheckOut;
    private TextView tvTotal;

    private Cart cart;

    private DBTools db;
    private UserShop userActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DBTools(this);
        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            cart = extras.getParcelable("cart");
            userActivity = extras.getParcelable("user_activity");
        }


        cartAdapter = new CartAdapter(cart, this, userActivity);
        recyclerView.setAdapter(cartAdapter);

        tvTotal = (TextView)findViewById(R.id.tvTotal);
        tvTotal.setText("P" + cart.getTotal());

        btnCheckOut = (CardView)findViewById(R.id.btnCheckout);
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();
            }
        });
    }

    private void checkout(){
        final Dialog dialog  = new Dialog(this);
        dialog.setContentView(R.layout.dialog_purchase);

        CardView btnExit = dialog.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finishAffinity();
            }
        });

        CardView btnNew = dialog.findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, UserHome.class));
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
    }

    public Cart getCart(){
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void updateUI(){
        tvTotal.setText("P" + cart.getTotal());
        cartAdapter = new CartAdapter(cart, this, userActivity);
        recyclerView.setAdapter(cartAdapter);
    }
}
