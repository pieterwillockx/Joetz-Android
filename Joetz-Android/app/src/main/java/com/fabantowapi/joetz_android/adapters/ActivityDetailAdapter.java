package com.fabantowapi.joetz_android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.User;

import java.util.List;

/**
 * Created by Pieter on 18-8-2016.
 */
public class ActivityDetailAdapter extends RecyclerView.Adapter<ActivityDetailAdapter.ViewHolder> {

    List<User> users;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaam;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNaam = (TextView) itemView.findViewById(R.id.activity_detail_person_name);
        }
    }

    public ActivityDetailAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users){
        this.users = users;
        this.notifyDataSetChanged();
    }

    @Override
    public ActivityDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activity_detail_person_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ActivityDetailAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final User user = users.get(position);

        holder.txtNaam.setText(users.get(position).getFirstname() + " " + users.get(position).getLastname());

        //holder.itemView.setOnClickListener(new View.OnClickListener(){
        //    @Override
        //    public void onClick(View v) {
//
        //    }
        //});
    }

    @Override
    public int getItemCount() {
        return this.users != null ? this.users.size() : 0;
    }
}
