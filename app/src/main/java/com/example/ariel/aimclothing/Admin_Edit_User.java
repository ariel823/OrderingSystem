package com.example.ariel.aimclothing;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.library.DBTools;
import com.example.ariel.aimclothing.library.UserAdapter;
import com.example.ariel.aimclothing.library.User;

import org.w3c.dom.Text;

import java.util.List;

public class Admin_Edit_User extends AppCompatActivity {

    private String TAG = "AIMStore";
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private List<User> listItem;

    private CardView btnBack;
    private CardView btnAddUser;

    private DBTools db;
    Dialog dialog;

    //Dialog ui
    TextView tvClose;
    EditText etFullname, etUsername, etPassword, etContact;
    CardView btnCancel, btnConfirm;
    TextInputLayout ilName, ilUsername, ilPass, ilContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__user);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = new DBTools(this);
        listItem = db.getUserList();

        mAdapter = new UserAdapter(listItem, this);
        recyclerView.setAdapter(mAdapter);

        btnBack = (CardView)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });

        btnAddUser = (CardView)findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }

    private void updateUI(){
        db = new DBTools(this);
        listItem = db.getUserList();

        mAdapter = new UserAdapter(listItem, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void backToMenu(){
        startActivity(new Intent(Admin_Edit_User.this, AdminHome.class));
        finish();
    }

    private void addUser(){
        dialog  = new Dialog(this);
        dialog.setContentView(R.layout.dialog_user);

        etFullname = dialog.findViewById(R.id.etFullname);
        etUsername = dialog.findViewById(R.id.etUsername);
        etPassword = dialog.findViewById(R.id.etPassword);
        etContact = dialog.findViewById(R.id.etContact);
        ilName = dialog.findViewById(R.id.inputLayoutName);
        ilUsername = dialog.findViewById(R.id.inputLayoutUName);
        ilPass = dialog.findViewById(R.id.inputLayoutPassword);
        ilContact = dialog.findViewById(R.id.inputLayoutContact);

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        btnConfirm = dialog.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitUser();
            }
        });


        tvClose = dialog.findViewById(R.id.tvClose);
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void submitUser(){
        if(!validateName()){
            return;
        }

        if(!validateUsername()){
            return;
        }

        if(!validatePassword()){
            return;
        }

        if(!validateContact()){
            return;
        }

        final String sName = etFullname.getText().toString().trim();
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String cont = etContact.getText().toString().trim();

        User user = new User();
        user.setName(sName);
        user.setUsername(username);
        user.setPassword(password);
        user.setContactNo(cont);

        long result = db.registerUser(user);
        if(result!=-1){
            Log.d(TAG,"Data inserted: \n" + user);
            Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show();
            updateUI();
            dialog.dismiss();
        } else {
            Log.e(TAG,"Error inserting data");
        }

    }

    private boolean validateName(){
        final String sName = etFullname.getText().toString().trim();
        if(sName.isEmpty()){
            ilName.setError("Enter required field!");
            etFullname.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validateUsername(){
        final String user = etUsername.getText().toString().trim();

        if(user.isEmpty()){
            ilUsername.setError("Enter required field!");
            etUsername.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validatePassword(){
        final String password = etPassword.getText().toString().trim();

        if(password.isEmpty()){
            ilPass.setError("Enter required field!");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validateContact(){
        final String cont = etContact.getText().toString().trim();

        if(cont.isEmpty()){
            ilContact.setError("Enter required field!");
            etContact.requestFocus();
            return false;
        }

        return true;
    }



}
