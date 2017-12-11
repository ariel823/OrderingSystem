package com.example.ariel.aimclothing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "AimStore";
    TextView name, uname, pass, cpass, contact;
    Button submit, cancel;
    DBTools db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DBTools(this);

        name = (TextView) findViewById(R.id.name);
        uname = (TextView) findViewById(R.id.username);
        pass = (TextView) findViewById(R.id.password);
        cpass = (TextView) findViewById(R.id.confirmPassword);
        contact = (TextView) findViewById(R.id.contact);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);

        cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button b = (Button)view;
        switch(b.getId()){
            case R.id.submit: submit(); break;
            case R.id.btnCancel: cancel(); break;
            default: break;
        }
    }

    private void submit(){
        Boolean error = false;

//                ProgressDialog progressDialog = new ProgressDialog(Registration.this);
//                progressDialog.setMessage("Loading...");
//                progressDialog.show();

        final String sName = name.getText().toString().trim();
        final String user = uname.getText().toString().trim();
        final String password = pass.getText().toString().trim();
        final String password2 = cpass.getText().toString().trim();
        final String cont = contact.getText().toString().trim();

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

        if(TextUtils.isEmpty(cont)){
            contact.setError("Input required field!");
            contact.requestFocus();
            error = true;
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
        registeredUser.setContactNo(cont);

        long result = db.registerUser(registeredUser);
        if(result!=-1){
            Log.d(TAG,"Data inserted: \n" + registeredUser);
            startActivity(new Intent(Registration.this, UserHome.class));
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
