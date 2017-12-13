package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ariel.aimclothing.library.DBTools;

public class AdminHome extends AppCompatActivity {

    Button prod, user, logout;
    DBTools db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        db= new DBTools(this);


        prod = (Button) findViewById(R.id.btnProd);
        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prod();
            }
        });

        user = (Button) findViewById(R.id.btnUsers);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user();
            }
        });

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    public void prod(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Edit_Prods.class);
        startActivity(mainIntent);
    }

    public void hist(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Trans_Hist.class);
        startActivity(mainIntent);
        finish();
    }

    public void user(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Edit_User.class);
        startActivity(mainIntent);
    }

    public void logout(){
        Intent mainIntent = new Intent(AdminHome.this,Login.class);
        startActivity(mainIntent);
        finish();
    }

}
