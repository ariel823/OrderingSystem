package com.example.ariel.aimclothing.library;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.aimclothing.R;

import java.util.List;

/**
 * Created by Ian on 12/12/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{


    private List<User> listItem;
    private Context context;

    public UserAdapter(List<User> listItem, Context mContext){
        this.listItem = listItem;
        this.context = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final User users = listItem.get(position);
        holder.tvFullName.setText(users.getName());
        holder.tvUsername.setText(users.getUsername());
        holder.tvPassword.setText(users.getPassword());
        holder.tvContact.setText(users.getContactNo());
        holder.ivUser.setImageResource(R.drawable.usericon);
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
                                Toast.makeText(context, "Deleted: " + users.getName(), Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvFullName;
        public TextView tvUsername;
        public TextView tvPassword;
        public TextView tvContact;
        public TextView tvOption;
        public ImageView ivUser;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.tvFullName);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvPassword = (TextView) itemView.findViewById(R.id.tvPassword);
            tvContact = (TextView) itemView.findViewById(R.id.tvContact);
            tvOption = (TextView) itemView.findViewById(R.id.tvOption);
            ivUser = (ImageView) itemView.findViewById(R.id.ivUser);
        }



    }
}
