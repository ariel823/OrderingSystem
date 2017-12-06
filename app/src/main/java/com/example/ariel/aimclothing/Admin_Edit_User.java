package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Admin_Edit_User extends AppCompatActivity {

    Button add, edi, ret, rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__user);

        add = (Button) findViewById(R.id.add);
        edi = (Button) findViewById(R.id.edi);
        rem = (Button) findViewById(R.id.rem);
        ret = (Button) findViewById(R.id.ret);
    }

    public void add(){

    }

    public void edi(){

    }

    public void rem(){

    }

    public void ret(){
        Intent mainIntent = new Intent(Admin_Edit_User.this,AdminHome.class);
        startActivity(mainIntent);
        finish();
    }
}
