package com.example.ariel.aimclothing.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.library.DBTools;
import com.example.ariel.aimclothing.library.User;

public class Registration extends AppCompatActivity {


    private static final String TAG = "AimStore";
    TextView name, uname, pass, cpass;
    CardView submit, cancel;
    TextInputLayout ilName, ilPass, ilPass2, ilUname;
    DBTools db;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DBTools(this);

        name = (TextView) findViewById(R.id.name);
        uname = (TextView) findViewById(R.id.username);
        pass = (TextView) findViewById(R.id.password);
        cpass = (TextView) findViewById(R.id.confirmPassword);

        submit = (CardView) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

        cancel = (CardView) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }


    private void submit(){
        Boolean error = false;

        final String sName = name.getText().toString().trim();
        final String user = uname.getText().toString().trim();
        final String password = pass.getText().toString().trim();
        final String password2 = cpass.getText().toString().trim();

        if(TextUtils.isEmpty(sName)){
            name.setError("Input required field!");
            name.requestFocus();
            error = true;
            return;
        }

        if(TextUtils.isEmpty(user)){
            uname.setError("Input required field!");
            uname.requestFocus();
            error = true;
            return;
        }

        if(!password.equalsIgnoreCase(password2)){
            pass.setError("Password doesn't match!");
            pass.requestFocus();
            return;
        }

        if(error){
            Toast.makeText(Registration.this, "There is a problem with registration.", Toast.LENGTH_SHORT).show();
            return;
        }

        User registeredUser = new User();
        registeredUser.setName(name.getText().toString());
        registeredUser.setUsername(uname.getText().toString());
        registeredUser.setPassword(pass.getText().toString());
        registeredUser.setUserType(1);

        int i = db.checkCredentials(user, password);
        if (i != -1){
            uname.setError("Username exist!");
            return;
        }


        long result = db.registerUser(registeredUser);
        if(result!=-1){
            Log.d(TAG,"Data inserted: \n" + registeredUser);
            Intent intent = new Intent(Registration.this, UserHome.class);
            Bundle extras = new Bundle();
            extras.putString("name", registeredUser.getName());
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        } else {
            Log.e(TAG,"Error inserting data");
        }


    }

    private void cancel(){
        Intent mainIntent = new Intent(Registration.this,Login.class);
        startActivity(mainIntent);
        finish();
    }

}
