package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.library.DBTools;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText user, pass;
    Button login;
    TextView register;

    DBTools myDb;

    String adminuser = "admin";
    String adminpass = "admin";
    String testuser = "testuser";
    String testpass = "testpass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        user = (EditText) findViewById(R.id.userInput);
        pass = (EditText) findViewById(R.id.passInput);

        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(this);

        register = (TextView)findViewById(R.id.tvRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        switch(b.getId()){
            case R.id.loginBtn: login(); break;
            default: break;
        }
    }

    private void login(){
        if(user.getText().toString().equalsIgnoreCase(adminuser) && pass.getText().toString().equalsIgnoreCase(adminpass)) {
            Intent mainIntent = new Intent(Login.this, AdminHome.class);
            startActivity(mainIntent);
        }

        else if(user.getText().toString().equalsIgnoreCase(testuser)&& pass.getText().toString().equalsIgnoreCase(testpass)){
            Intent mainIntent = new Intent(Login.this,UserHome.class);
            startActivity(mainIntent);
        }

        else{
            Toast.makeText(getApplication(), "Sorry invalid user credentials...", Toast.LENGTH_SHORT).show();
            user.setText("");
            pass.setText("");
            user.setFocusable(true);
        }
    }

    private void register(){
        Intent mainIntent = new Intent(Login.this,Registration.class);
        startActivity(mainIntent);
    }
}
