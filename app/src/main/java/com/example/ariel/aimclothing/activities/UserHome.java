package com.example.ariel.aimclothing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.R;

public class UserHome extends AppCompatActivity implements View.OnClickListener {

    Button shop, edit, logout, hist;
    TextView tvName;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        init();


    }

    public void init(){

        extras = getIntent().getExtras();

        shop = (Button) findViewById(R.id.btnShop);
        shop.setOnClickListener(this);
//
//        hist = (Button) findViewById(R.id.btnHist);
//        hist.setOnClickListener(this);

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);

        tvName = (TextView) findViewById(R.id.tvName);

        if(extras!=null){
            tvName.setText(extras.getString("name"));
        } else{
            tvName.setText("User!");
        }
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
            default:
                break;
        }
    }

    public void shop(){
        Intent mainIntent = new Intent(UserHome.this,UserShop.class);
        startActivity(mainIntent);
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
