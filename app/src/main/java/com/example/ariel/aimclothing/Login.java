package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    public void login(View v){
        /*if(user.getText().toString() == adminuser && pass.getText().toString() == adminpass){
            Intent mainIntent = new Intent(Login.this,AdminHome.class);
            startActivity(mainIntent);
            finish();
        }
        else if(user.getText().toString() == testuser && pass.getText().toString() == testpass){
            Intent mainIntent = new Intent(Login.this,UserHome.class);
            startActivity(mainIntent);
            finish();
        }
        else{
            Toast.makeText(getApplication(), "Sorry invalid user credentials...", Toast.LENGTH_SHORT).show();
            user.setText("");
            pass.setText("");
            user.setFocusable(true);
        }*/

        if(user.getText().toString().equalsIgnoreCase(adminuser) && pass.getText().toString().equalsIgnoreCase(adminpass)) {
            Intent mainIntent = new Intent(Login.this, AdminHome.class);
            startActivity(mainIntent);
            finish();
        }

        else if(user.getText().toString().equalsIgnoreCase(testuser)&& pass.getText().toString().equalsIgnoreCase(testpass)){
            Intent mainIntent = new Intent(Login.this,UserHome.class);
            startActivity(mainIntent);
            finish();
        }

        else{
            Toast.makeText(getApplication(), "Sorry invalid user credentials...", Toast.LENGTH_SHORT).show();
            user.setText("");
            pass.setText("");
            user.setFocusable(true);
        }
    }

    public void register(View v){
        Intent mainIntent = new Intent(Login.this,Registration.class);
        startActivity(mainIntent);
        finish();
    }
}
