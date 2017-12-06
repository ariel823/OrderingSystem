package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText user, pass;
    Button login;

    String adminuser = "admin";
    String adminpass = "admin";
    String testuser = "testuser";
    String testpass = "testpass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.userInput);
        pass = (EditText) findViewById(R.id.passInput);
        login = (Button) findViewById(R.id.loginBtn);
    }

    public void onClick(){
        if(user.getText().toString() == adminuser && pass.getText().toString() == adminpass){
            Intent mainIntent = new Intent(Login.this,AdminHome.class);
            startActivity(mainIntent);
            finish();
        }
        else if(user.getText().toString() == testuser && pass.getText().toString() == testpass){
            Intent mainIntent = new Intent(Login.this,UserHome.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
