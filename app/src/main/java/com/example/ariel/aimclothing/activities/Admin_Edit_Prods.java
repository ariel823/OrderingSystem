package com.example.ariel.aimclothing.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.library.DBTools;
import com.example.ariel.aimclothing.library.Product;
import com.example.ariel.aimclothing.library.ProductAdapter;

import java.util.List;

public class Admin_Edit_Prods extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private List<Product> listItem;
    private DBTools db;

    private CardView btnBack;
    private CardView btnAdd;

    private Dialog dialog;

    //dialog ui
    private EditText etProdName;
    private Spinner spCategory;
    private Spinner spBrand;
    private EditText etPrice;
    private TextView tvClose;
    private CardView btnCancel;
    private CardView btnConfirm;
    private String[] arrayBrand = new String[]{
                 "AMD",
                "Asus",
                "Intel",
                "Logitech",
                "MSI",
                "Samsung",
                "A4Tech",
                "Others"
    };
    private String[] arrayCategory = new String[] {
            "Keyboard",
            "Mouse",
            "Processor",
            "Monitor",
            "Motherboard",
            "Memory",
            "Video Card",
            "Accessories"
    };

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__prods);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        context = this;

        db = new DBTools(this);
        listItem = db.getProductList();

        mAdapter = new ProductAdapter(listItem, this);
        recyclerView.setAdapter(mAdapter);

        btnBack = (CardView)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });

        btnAdd = (CardView)findViewById(R.id.btnAddProduct);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    private void updateUI(){
        listItem = db.getProductList();
        mAdapter = new ProductAdapter(listItem, context);
        recyclerView.setAdapter(mAdapter);
    }

    private void showDialog(){
        dialog  = new Dialog(this);
        dialog.setContentView(R.layout.dialog_product);

        etProdName = dialog.findViewById(R.id.etProdName);

        etPrice = dialog.findViewById(R.id.etPrice);

        spBrand = dialog.findViewById(R.id.spinnerBrand);
        spBrand.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrayBrand));

        spCategory = dialog.findViewById(R.id.spinnerCategory);
        spCategory.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrayCategory));

        btnConfirm = dialog.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();

                final String sName = etProdName.getText().toString().trim();
                final Double price = Double.parseDouble(etPrice.getText().toString());

                product.setBrand(spBrand.getSelectedItemPosition() - 1);
                product.setCategory(spCategory.getSelectedItemPosition() - 1);
                product.setProduct_name(sName);
                product.setPrice(price);
                db.addProduct(product);

                updateUI();
                dialog.dismiss();
            }
        });

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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

    private void backToMenu(){
        startActivity(new Intent(Admin_Edit_Prods.this, AdminHome.class));
        finish();
    }


}
