package com.example.ariel.aimclothing.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.library.Cart;
import com.example.ariel.aimclothing.library.ProductAdapter;
import com.example.ariel.aimclothing.library.ViewPagerAdapter;

import com.example.ariel.aimclothing.fragments.AccessoriesFragment;
import com.example.ariel.aimclothing.fragments.CpuFragment;
import com.example.ariel.aimclothing.fragments.MonitorFragment;

public class UserShop extends AppCompatActivity implements Parcelable {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Cart cart;


    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop);



        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setTitle("CPU");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cart = new Cart();



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.cpu);
                tabLayout.getTabAt(1).setIcon(R.drawable.accessories);
                tabLayout.getTabAt(2).setIcon(R.drawable.monitor);
            }
        });



        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new CpuFragment(),"");
        viewPagerAdapter.addFragments(new AccessoriesFragment(),"");
        viewPagerAdapter.addFragments(new MonitorFragment(),"");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        updateTitle();

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(5);

    }

    public void updateTitle(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0: setTitle("CPU"); break;
                    case 1: setTitle("Accessories"); break;
                    case 2: setTitle("Monitor"); break;
                    default: break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(this.cart, flags);
    }

    public UserShop() {
    }

    protected UserShop(Parcel in) {

        this.cart = in.readParcelable(Cart.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserShop> CREATOR = new Parcelable.Creator<UserShop>() {
        @Override
        public UserShop createFromParcel(Parcel source) {
            return new UserShop(source);
        }

        @Override
        public UserShop[] newArray(int size) {
            return new UserShop[size];
        }
    };
}
