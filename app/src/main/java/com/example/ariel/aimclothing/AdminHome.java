package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    Button prod, user, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        prod = (Button) findViewById(R.id.prod);
        user = (Button) findViewById(R.id.prod);
        logout = (Button) findViewById(R.id.prod);
    }

    public void prod(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Edit_Prods.class);
        startActivity(mainIntent);
        finish();
    }

    public void hist(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Trans_Hist.class);
        startActivity(mainIntent);
        finish();
    }

    public void user(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Edit_User.class);
        startActivity(mainIntent);
        finish();
    }

    public void logout(){
        Intent mainIntent = new Intent(AdminHome.this,Login.class);
        startActivity(mainIntent);
        finish();
    }
}
