package com.example.ariel.aimclothing.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.activities.AdminHome;
import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.library.DBTools;
import com.example.ariel.aimclothing.library.User;
import com.example.ariel.aimclothing.library.UserAdapter;

import java.util.List;


public class AdminFragment extends Fragment {

    private String TAG = "AIMStore";

    //Dialog ui
    TextView tvClose;
    EditText etFullname, etUsername, etPassword;
    CardView btnCancel, btnConfirm;
    TextInputLayout ilName, ilUsername, ilPass;
    TextView tvLabel;

    private DBTools db;
    Dialog dialog;

    private View rootView;
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private List<User> listItem;

    private Context context;
    private CardView btnBack;
    private CardView btnAddUser;


    public AdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        context = getContext();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        db = new DBTools(context);
        listItem = db.getAdminList();

        mAdapter = new UserAdapter(listItem, context);
        recyclerView.setAdapter(mAdapter);

        //buttons
        btnBack = rootView.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });

        btnAddUser = rootView.findViewById(R.id.btnAdd2);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        return rootView;
    }



    private void backToMenu(){
        getActivity().finish();
    }


    public void updateUI(){
        listItem = db.getAdminList();
        mAdapter = new UserAdapter(listItem, context);
        recyclerView.setAdapter(mAdapter);
    }


    private void addUser(){
        dialog  = new Dialog(context);
        dialog.setContentView(R.layout.dialog_user);


        etFullname = dialog.findViewById(R.id.etFullname);
        etUsername = dialog.findViewById(R.id.etUsername);
        etPassword = dialog.findViewById(R.id.etPassword);
        ilName = dialog.findViewById(R.id.inputLayoutName);
        ilUsername = dialog.findViewById(R.id.inputLayoutUName);
        ilPass = dialog.findViewById(R.id.inputLayoutPassword);
        tvLabel = dialog.findViewById(R.id.tvLabel);
        tvLabel.setText("NEW ADMINISTRATOR");


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

        final String sName = etFullname.getText().toString().trim();
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        int i = db.checkCredentials(username, password);
        if (i != -1){
            ilUsername.setError("Username exist!");
            return;
        }

        User user = new User();
        user.setName(sName);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(2);


        long result = db.registerUser(user);

        if(result!=-1){
            Log.d(TAG,"Data inserted: \n" + user);
            Toast.makeText(context, "User added successfully!", Toast.LENGTH_SHORT).show();
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




}
