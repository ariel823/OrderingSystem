package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    TextView name, uname, pass, cpass, contact;
    Button submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (TextView) findViewById(R.id.name);
        uname = (TextView) findViewById(R.id.username);
        pass = (TextView) findViewById(R.id.password);
        cpass = (TextView) findViewById(R.id.confirmpassword);
        contact = (TextView) findViewById(R.id.contact);
    }

    public void submit(View v){
        Toast.makeText(getApplicationContext(), "Feature not yet available", Toast.LENGTH_SHORT).show();
    }

    public void cancel(View v){
        Intent mainIntent = new Intent(Registration.this,Login.class);
        startActivity(mainIntent);
        finish();
    }
}
