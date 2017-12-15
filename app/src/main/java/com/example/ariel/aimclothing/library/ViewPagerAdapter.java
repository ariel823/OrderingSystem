package com.example.ariel.aimclothing.library;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Ian on 12/13/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);

}

    public void addFragments(Fragment fragment, String title){
        fragments.add(fragment);
        titles.add(title);
    }

    public void updateFragment(int i, Fragment fragment){
        fragments.set(i, fragment);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
