package com.fabantowapi.joetz_android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.model.api.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 20-8-2016.
 */
public class AddContributorAdapter extends RecyclerView.Adapter<AddContributorAdapter.ViewHolder> {

    private Context context;

    private List<User> users;
    private List<User> checkedUsers;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgAvatar;
        public TextView txtName;
        public TextView txtRole;
        public CheckBox chkChecked;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.add_contributor_item_image);
            txtName =(TextView) itemView.findViewById(R.id.add_contributor_item_name);
            txtRole = (TextView) itemView.findViewById(R.id.add_contributor_item_role);
            chkChecked = (CheckBox) itemView.findViewById(R.id.add_contributor_checkbox);
        }
    }

    public AddContributorAdapter(Context context) {
        this.context = context;
        checkedUsers = new ArrayList<>();
    }

    public void setUsers(List<User> contributors){
        this.users = contributors;
        this.notifyDataSetChanged();
    }

    public List<User> getCheckedUsers(){ return checkedUsers; }

    @Override
    public AddContributorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_add_contributor_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final User user = users.get(position);

        holder.txtName.setText(user.getFirstname() + " " + user.getLastname());
        holder.txtRole.setText(user.getRole());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(holder.chkChecked.isChecked()){
                    checkedUsers.remove(users.get(position));
                    holder.chkChecked.setChecked(false);
                }
                else{
                    checkedUsers.add(users.get(position));
                    holder.chkChecked.setChecked(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.users != null ? this.users.size() : 0;
    }
}
