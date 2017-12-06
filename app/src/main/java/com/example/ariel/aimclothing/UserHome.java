package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserHome extends AppCompatActivity {

    Button shop, edit, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        shop = (Button) findViewById(R.id.shop);
        edit = (Button) findViewById(R.id.edit);
        logout = (Button) findViewById(R.id.logout);
    }

    public void shop(View v){
        Intent mainIntent = new Intent(UserHome.this,UserShop.class);
        startActivity(mainIntent);
        finish();
    }

    public void history(View v){
        Intent mainIntent = new Intent(UserHome.this,User_shop_hist.class);
        startActivity(mainIntent);
        finish();
    }

    public void edit(View v){
        Toast.makeText(getApplicationContext(), "Currently Unavailable", Toast.LENGTH_SHORT).show();
    }

    public void logout(View v){
        Intent mainIntent = new Intent(UserHome.this,Login.class);
        startActivity(mainIntent);
        finish();
    }
}
