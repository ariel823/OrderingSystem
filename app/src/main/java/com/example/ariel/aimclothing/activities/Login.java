package com.example.ariel.aimclothing.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.library.DBTools;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity{

    EditText user, pass;
    CardView login;
    TextView register;
    TextInputLayout ilUsername, ilPassword;

    DBTools myDb;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        myDb = new DBTools(this);
        user = (EditText) findViewById(R.id.userInput);
        pass = (EditText) findViewById(R.id.passInput);
        ilPassword = (TextInputLayout) findViewById(R.id.ilPassword);
        ilUsername = (TextInputLayout) findViewById(R.id.ilUsername);

        login = (CardView) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        register = (TextView)findViewById(R.id.tvRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void login(){
        username = user.getText().toString().trim();
        password = pass.getText().toString().trim();
        int user_type = myDb.checkCredentials(username, password);

        switch (user_type){
            case -1:
                userNotFound();
                break;
            case 1: user(); break;
            case 2: admin(); break;
            case 3: wrongPassword(); break;
            default: break;
        }
    }

    private void userNotFound(){
        ilUsername.setError("User does not exist");
        return;
    }

    private void wrongPassword(){
        ilPassword.setError("Incorrect Password");
        return;
    }



    private void user(){
        Intent intent = new Intent(Login.this, UserHome.class);
        Bundle extras = new Bundle();
        extras.putString("name", myDb.getUserFullName(username));
        intent.putExtras(extras);
        startActivity(intent);

    }

    private void admin(){
        Intent intent = new Intent(Login.this, AdminHome.class);
        Bundle extras = new Bundle();
        extras.putString("name", myDb.getUserFullName(username));
        intent.putExtras(extras);
        startActivity(intent);

    }

    private void register(){
        Intent mainIntent = new Intent(Login.this,Registration.class);
        startActivity(mainIntent);
    }
}
