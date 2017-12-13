package com.example.ariel.aimclothing.library;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.R;

import java.util.List;

/**
 * Created by Ian on 12/12/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Product> listItem;
    private Context context;
    private int mode;

    public ProductAdapter(List<Product> listItem, Context mContext, int mode){
        this.listItem = listItem;
        this.context = mContext;
        this.mode = mode;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (mode){
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent,false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_shop, parent,false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent,false);
                break;

        }
        return new ProductAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Product product = listItem.get(position);
        holder.tvBrand.setText(product.getBrand_name());
        holder.tvCategory.setText(product.getCategory_name());
        holder.tvPrice.setText("P" + product.getPrice());
        holder.tvProdName.setText(product.getProduct_name());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(mode){
                    case 1: break;
                    case 2: addToCart(product); break;
                    default: break;
                }
            }
        });

        DBTools db = new DBTools(context);
        int resourceId = db.getImageResource(product.getBrand());
        product.setImage(resourceId);
        holder.ivProduct.setImageResource(product.getImage());



        if(mode==1){
            holder.tvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context,holder.tvOption);
                    popupMenu.inflate(R.menu.option_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch(item.getItemId()){
                                case R.id.menu_item_delete:
                                    Toast.makeText(context, "Deleted: " + product.getProduct_name(), Toast.LENGTH_SHORT).show();
                                    listItem.remove(position);
                                    notifyDataSetChanged();
                                    break;
                                default:
                                    break;
                            }

                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvBrand;
        public TextView tvCategory;
        public TextView tvPrice;
        public TextView tvProdName;
        public TextView tvOption;
        public ImageView ivProduct;
        public Button btnAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            tvBrand = (TextView) itemView.findViewById(R.id.tvBrand);
            tvCategory = (TextView) itemView.findViewById(R.id.tvCategory);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvProdName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvOption = (TextView) itemView.findViewById(R.id.tvOption);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
            btnAdd = (Button) itemView.findViewById(R.id.btnAdd);

        }
    }

    private void addToCart(Product product){
        final Dialog dialog  = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        TextView tvBrand, tvProd, tvPrice, tvClose;
        ImageView ivProd;
        CardView btnCancel, btnConfirm;

        tvBrand = dialog.findViewById(R.id.tvBrand);
        tvBrand.setText(product.getBrand_name());

        tvProd = dialog.findViewById(R.id.tvProductName);
        tvProd.setText(product.getProduct_name());

        tvPrice = dialog.findViewById(R.id.tvPrice);
        tvPrice.setText("P" + product.getPrice());

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
                //TODO
            }
        });


        tvClose = dialog.findViewById(R.id.tvClose);
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ivProd = dialog.findViewById(R.id.ivProd);
        ivProd.setImageResource(product.getImage());

        dialog.show();
    }
}
