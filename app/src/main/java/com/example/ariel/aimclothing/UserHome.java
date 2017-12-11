package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserHome extends AppCompatActivity implements View.OnClickListener {

    Button shop, edit, logout, hist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        init();


    }

    public void init(){
        shop = (Button) findViewById(R.id.btnShop);
        shop.setOnClickListener(this);

        hist = (Button) findViewById(R.id.btnHist);
        hist.setOnClickListener(this);

        edit = (Button) findViewById(R.id.btnEdit);
        edit.setOnClickListener(this);

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button b = (Button)view;

        switch(b.getId()){
            case R.id.btnLogout:
                logout();
                break;
            case R.id.btnShop:
                shop();
                break;
            case R.id.btnHist:
                hist();
                break;
            case R.id.btnEdit:
                edit();
                break;
            default:
                break;
        }
    }

    public void shop(){
        Intent mainIntent = new Intent(UserHome.this,UserShop.class);
        startActivity(mainIntent);
        finish();
    }

    public void hist(){
        Intent mainIntent = new Intent(UserHome.this,User_shop_hist.class);
        startActivity(mainIntent);
        finish();
    }

    public void edit(){
        Toast.makeText(getApplicationContext(), "UserInfo: \n", Toast.LENGTH_SHORT).show();
    }

    public void logout(){
        Intent mainIntent = new Intent(UserHome.this,Login.class);
        startActivity(mainIntent);
        finish();
    }
}
