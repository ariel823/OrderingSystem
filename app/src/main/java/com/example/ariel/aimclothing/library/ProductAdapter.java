package com.example.ariel.aimclothing.library;

import android.app.Dialog;
import android.content.Context;
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
import com.example.ariel.aimclothing.activities.UserShop;

import java.util.List;

/**
 * Created by Ian on 12/12/2017.
 */


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Product> listItem;
    private Context context;
    private int SHOP_OR_ADMIN;

    private Cart cart;
    private UserShop parent;

    public ProductAdapter(List<Product> cpuProducts, Context mContext,UserShop parentActivity){
        this.listItem = cpuProducts;
        this.context = mContext;

        parent = parentActivity;
        cart = parent.getCart();

    }

    public ProductAdapter(List<Product> cpuProducts, Context mContext){
        this.listItem = cpuProducts;
        this.context = mContext;

    }

    public Boolean isAdmin(){

        if(parent!=null){
            return false;
        } else{
            return true;
        }

    }

    public List<Product> getListItem() {
        return listItem;
    }


    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if(isAdmin()) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
        }

        else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_shop, parent,false);
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

        if(!isAdmin()){
            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addToCart(product);
                }
            });
        }


        final DBTools db = new DBTools(context);
        int resourceId = db.getImageResource(product.getBrand());
        product.setImage(resourceId);
        holder.ivProduct.setImageResource(product.getImage());

        if(isAdmin()){
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
                                    db.deleteProduct(product.getProduct_name());
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


    private void addToCart(final Product product){
        final Dialog dialog  = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final TextView tvBrand, tvProd, tvPrice, tvClose, tvQuantity;
        ImageView ivProd;
        Button increment, decrement;
        CardView btnCancel, btnConfirm;

        tvBrand = dialog.findViewById(R.id.tvBrand);
        tvBrand.setText(product.getBrand_name());

        tvProd = dialog.findViewById(R.id.tvProductName);
        tvProd.setText(product.getProduct_name());

        tvPrice = dialog.findViewById(R.id.tvPrice);
        tvPrice.setText("P" + product.getPrice());

        tvQuantity = dialog.findViewById(R.id.tvQuantity);

        increment = dialog.findViewById(R.id.increment);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = Integer.parseInt(tvQuantity.getText().toString());
                current++;
                tvQuantity.setText("" + (current));
                tvPrice.setText("P" + (product.getPrice()*current));
            }
        });
        decrement = dialog.findViewById(R.id.decrement);
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = Integer.parseInt(tvQuantity.getText().toString());

                if(current!=1){
                    current--;
                    tvQuantity.setText("" + (current));
                    tvPrice.setText("P" + (product.getPrice()*current));
                }
            }
        });

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
                product.setQuantity(Integer.parseInt(tvQuantity.getText().toString()));
                cart.addToCart(product);

                Toast.makeText(context, "Product added successfully", Toast.LENGTH_SHORT).show();
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

        ivProd = dialog.findViewById(R.id.ivProd);
        ivProd.setImageResource(product.getImage());

        dialog.show();
    }
}
