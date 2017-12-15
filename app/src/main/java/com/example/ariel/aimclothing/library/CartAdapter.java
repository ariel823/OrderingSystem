package com.example.ariel.aimclothing.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.R;
import com.example.ariel.aimclothing.activities.CartActivity;
import com.example.ariel.aimclothing.activities.UserShop;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    private Cart cart;
    private List<Product> listItem;
    private Context context;
    private int mode;
    private CartActivity cartActivity;
    private UserShop userActivity;

    public CartAdapter(Cart cart, CartActivity cartActivity, UserShop userActivity){
        this.cart = cart;
        listItem = cart.getProducts();

        this.userActivity = userActivity;
        this.cartActivity = cartActivity;
        context = cartActivity.getApplicationContext();
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart, parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {

        final Product product = listItem.get(position);
        holder.tvBrand.setText(product.getBrand_name());
        holder.tvProdName.setText(product.getProduct_name());
        holder.tvSubtotal.setText("P" + product.getSubtotal());
        holder.tvQuantity.setText("" + product.getQuantity());

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, product.getProduct_name() + " has been removed.", Toast.LENGTH_SHORT).show();

                cart.removeFromCart(position);
                listItem = cart.getProducts();
                notifyDataSetChanged();

                userActivity.setCart(cart);
                cartActivity.setCart(userActivity.getCart());
                cartActivity.updateUI();
            }
        });

        DBTools db = new DBTools(context);
        int resourceId = db.getImageResource(product.getBrand());
        product.setImage(resourceId);
        holder.ivProduct.setImageResource(product.getImage());


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvBrand;
        public TextView tvSubtotal;
        public TextView tvProdName;
        public TextView tvQuantity;
        public ImageView ivProduct;
        public Button btnRemove;

        public ViewHolder(View itemView) {
            super(itemView);
            tvBrand = (TextView) itemView.findViewById(R.id.tvBrand);
            tvSubtotal = (TextView) itemView.findViewById(R.id.tvSubtotal);
            tvProdName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);

        }
    }



}
