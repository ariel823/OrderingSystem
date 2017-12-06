package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_Edit_Prods extends AppCompatActivity {

    Button add, remove, edit, ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__prods);

        add = (Button) findViewById(R.id.Add);
        remove = (Button) findViewById(R.id.Remove);
        edit = (Button) findViewById(R.id.Edit);
        ret = (Button) findViewById(R.id.Ret);
    }

    public void add(View v){
        Toast.makeText(getApplicationContext(), "Feature not yet available", Toast.LENGTH_SHORT).show();
    }

    public void rem(View v){
        Toast.makeText(getApplicationContext(), "Feature not yet available", Toast.LENGTH_SHORT).show();
    }

    public void edi(View v){
        Toast.makeText(getApplicationContext(), "Feature not yet available", Toast.LENGTH_SHORT).show();
    }

    public void ret(View v){
        Intent mainIntent = new Intent(Admin_Edit_Prods.this,AdminHome.class);
        startActivity(mainIntent);
        finish();
    }
}
