package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminHome extends AppCompatActivity {

    Button prod, user, logout;
    DBTools db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        db = new DBTools(this);

        prod = (Button) findViewById(R.id.btnProd);

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
                Intent mainIntent = new Intent(AdminHome.this,Login.class);
                startActivity(mainIntent);
                finish();
            }
        });



    }

    public void prod(View v){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Edit_Prods.class);
        startActivity(mainIntent);
        finish();
    }

    public void hist(View v){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Trans_Hist.class);
        startActivity(mainIntent);
        finish();
    }

    public void user(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Edit_User.class);
        startActivity(mainIntent);
        finish();
    }

}
