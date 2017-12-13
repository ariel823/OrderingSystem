package com.example.ariel.aimclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ariel.aimclothing.library.DBTools;
import com.example.ariel.aimclothing.library.Product;
import com.example.ariel.aimclothing.library.ProductAdapter;

import java.util.List;

public class Admin_Edit_Prods extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private List<Product> listItem;

    private CardView btnBack;
    private CardView btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__prods);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBTools db = new DBTools(this);
        listItem = db.getProductList();

        mAdapter = new ProductAdapter(listItem, this, 1);
        recyclerView.setAdapter(mAdapter);

        btnBack = (CardView)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });

        btnAdd = (CardView)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void addProducts(){
        //TODO: ADD PRODUCT METHOD
    }

    private void backToMenu(){
        startActivity(new Intent(Admin_Edit_Prods.this, AdminHome.class));
        finish();
    }


}
