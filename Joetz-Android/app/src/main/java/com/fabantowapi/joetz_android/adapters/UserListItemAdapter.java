package com.fabantowapi.joetz_android.adapters;

import android.app.FragmentTransaction;
import android.content.Context;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.fragments.UserDetailFragment;
import com.fabantowapi.joetz_android.model.api.User;

import java.util.List;

/**
 * Created by Pieter on 19-8-2016.
 */
public class UserListItemAdapter extends RecyclerView.Adapter<UserListItemAdapter.ViewHolder> {

    private Context context;

    private List<User> users;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imgAvatar;
        public TextView txtName;
        public TextView txtRole;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.user_item_image);
            txtName =(TextView) itemView.findViewById(R.id.user_item_name);
            txtRole = (TextView) itemView.findViewById(R.id.user_item_role);
        }
    }

    public UserListItemAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> contributors){
        this.users = contributors;
        this.notifyDataSetChanged();
    }

    @Override
    public UserListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final User user = users.get(position);

        holder.txtName.setText(user.getFirstname() + " " + user.getLastname());
        holder.txtRole.setText(user.getRole());

        // get image

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                UserDetailFragment userDetailFragment = new UserDetailFragment();

                ((MainActivity) context).getIntent().putExtra("USER", user);
                ft.replace(R.id.mainpage_container, userDetailFragment);
                ft.addToBackStack("FRAGMENT");
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.users != null ? this.users.size() : 0;
    }
}
