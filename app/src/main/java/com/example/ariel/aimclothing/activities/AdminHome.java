package com.example.ariel.aimclothing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.library.DBTools;

public class AdminHome extends AppCompatActivity {

    Button prod, user, logout;
    TextView tvName;
    DBTools db;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        init();

    }

    private void init(){
        extras = getIntent().getExtras();

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

        tvName = (TextView) findViewById(R.id.tvName);

        if(extras!=null){
            tvName.setText(extras.getString("name") + "(Admin)");
        } else{
            tvName.setText("Administrator");
        }

    }

    public void prod(){
        Intent mainIntent = new Intent(AdminHome.this,Admin_Edit_Prods.class);
        startActivity(mainIntent);
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
