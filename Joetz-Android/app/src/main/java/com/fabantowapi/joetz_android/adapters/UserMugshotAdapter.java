package com.fabantowapi.joetz_android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.model.User;

import java.util.List;

/**
 * Created by Pieter on 18-8-2016.
 */
public class UserMugshotAdapter extends RecyclerView.Adapter<UserMugshotAdapter.ViewHolder> {

    List<User> users;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.activity_detail_person_name);
        }
    }

    public UserMugshotAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users){
        this.users = users;
        this.notifyDataSetChanged();
    }

    @Override
    public UserMugshotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activity_detail_person_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(UserMugshotAdapter.ViewHolder holder, int position) {

        User user = users.get(position);
        holder.txtName.setText(users.get(position).getFirstname() + " " + users.get(position).getLastname());
    }

    @Override
    public int getItemCount() {
        return this.users != null ? this.users.size() : 0;
    }
}
