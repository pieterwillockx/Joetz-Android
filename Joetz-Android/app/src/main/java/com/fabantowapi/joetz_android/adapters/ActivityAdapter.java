package com.fabantowapi.joetz_android.adapters;

import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.GetActivityResponse;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.ActiviteitDetailFragment;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Anton Rooseleer on 9-8-2016.
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder>{

    public List<Activity> activities;
    public Context context;
    public Date currentDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtDate;
        public TextView txtDuratie;
        public TextView txtNaam;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.activityLijst_Datum);
            txtDuratie = (TextView) itemView.findViewById(R.id.activityLijst_Duratie);
            txtNaam =(TextView) itemView.findViewById(R.id.activityLijst_Naam);

        }
    }
    public ActivityAdapter(Context context) {
        this.context = context;
    }

    public void setActivities(List<Activity> activities){
        this.activities = activities;
        this.notifyDataSetChanged();
    }

    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,  int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activity_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Activity activity = activities.get(position);

        Date beginDate = SharedHelper.parseDateStringToDate(activity.getBeginUur());
        Date endDate = SharedHelper.parseDateStringToDate(activity.getEindUur());
        if(beginDate != currentDate ){
            currentDate = beginDate;

            holder.txtDate.setText(dateFormat.format(beginDate));
        }
       holder.txtDuratie.setText("Van " + dateFormat.format(beginDate) + "\ntot " + dateFormat.format(endDate));

        holder.txtNaam.setText(activities.get(position).getNaam());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle args =new Bundle();
                FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ActiviteitDetailFragment activiteitDetailFragment = new ActiviteitDetailFragment();
                activiteitDetailFragment.setArguments(args);
                ((MainActivity) context).getIntent().putExtra("activity", activity);
                ft.replace(R.id.mainpage_container, activiteitDetailFragment);
                ft.addToBackStack("FRAGMENT");
                ft.commit();

            }
        });
    }
    @Override
    public int getItemCount() {
        return this.activities != null ? this.activities.size() : 0;
    }
}
