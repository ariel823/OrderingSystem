package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_shop_hist extends AppCompatActivity {

    Button ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop_hist);

        ret = (Button) findViewById(R.id.ret);
    }

    public void ret(View v){
        Intent mainIntent = new Intent(User_shop_hist.this,UserHome.class);
        startActivity(mainIntent);
        finish();
    }
}
