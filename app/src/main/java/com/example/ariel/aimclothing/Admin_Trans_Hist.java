package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_Trans_Hist extends AppCompatActivity {

    Button clr, ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__trans__hist);

        clr = (Button) findViewById(R.id.clr);
        ret = (Button) findViewById(R.id.ret);
    }

    public void clr(View v){
        Toast.makeText(getApplicationContext(), "Feature not yet available", Toast.LENGTH_SHORT).show();
    }

    public void ret(View v){
        Intent mainIntent = new Intent(Admin_Trans_Hist.this,AdminHome.class);
        startActivity(mainIntent);
        finish();
    }
}
