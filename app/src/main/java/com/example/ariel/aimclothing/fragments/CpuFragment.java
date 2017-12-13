package com.example.ariel.aimclothing.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ariel.aimclothing.library.DBTools;
import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.UserHome;
import com.example.ariel.aimclothing.library.Product;
import com.example.ariel.aimclothing.library.ProductAdapter;

import java.util.List;


public class CpuFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private List<Product> listItem;

    private Context context;
    private CardView btnBack;

    public CpuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cpu, container, false);
        context = getContext();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        DBTools db = new DBTools(context);
        listItem = db.getProductList();

        mAdapter = new ProductAdapter(listItem, context, 2);
        recyclerView.setAdapter(mAdapter);

        btnBack = (CardView)rootView.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });

        return rootView;
    }

    public void backToMenu(){

        startActivity(new Intent(getActivity(), UserHome.class));


    }



}
