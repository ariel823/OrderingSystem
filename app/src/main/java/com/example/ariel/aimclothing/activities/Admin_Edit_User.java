package com.example.ariel.aimclothing.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.fragments.AdminFragment;
import com.example.ariel.aimclothing.library.ViewPagerAdapter;

import com.example.ariel.aimclothing.fragments.NormalUserFragment;

public class Admin_Edit_User extends AppCompatActivity {


    private Toolbar toolbar;
    TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    Fragment defaultFrag;
    Fragment defaultFrag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__user);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setTitle("User Accounts");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        defaultFrag = new NormalUserFragment();
        defaultFrag2 = new AdminFragment();

        viewPagerAdapter.addFragments(defaultFrag, "Normal users");
        viewPagerAdapter.addFragments(defaultFrag2, "Administrators");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(5);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
//                    viewPagerAdapter.updateFragment(position, new NormalUserFragment());
                    //TODO: PROBLEM HERE NOT UPDATING UI

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }




}
