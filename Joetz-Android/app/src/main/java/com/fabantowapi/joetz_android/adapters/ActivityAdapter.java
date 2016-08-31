package com.fabantowapi.joetz_android.adapters;

import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.model.Activity;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Anton Rooseleer on 9-8-2016.
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder>{

    public List<Activity> activities;
    public Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHour;
        public TextView txtDate;
        public TextView txtLocation;
        public TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtHour = (TextView) itemView.findViewById(R.id.activityLijst_uur);
            txtDate = (TextView) itemView.findViewById(R.id.activityLijst_datum);
            txtLocation = (TextView) itemView.findViewById(R.id.activityLijst_locatie);
            txtName =(TextView) itemView.findViewById(R.id.activityLijst_naam);
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

        final Activity activity = activities.get(position);

        holder.txtName.setText(activity.getNaam());

        Date begin = SharedHelper.parseDateStringToDate(activity.getBeginUur());
        Date end = SharedHelper.parseDateStringToDate(activity.getEindUur());

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if(SharedHelper.compareDates(begin, end)){
            String beginHour = beginCalendar.get(Calendar.HOUR_OF_DAY) + ":" + beginCalendar.get(Calendar.MINUTE);
            String endHour = endCalendar.get(Calendar.HOUR_OF_DAY) + ":" + (endCalendar.get(Calendar.MINUTE) == 0 ? "00" : endCalendar.get(Calendar.MINUTE));

            holder.txtHour.setText(beginHour + " - " + endHour);
            holder.txtDate.setText(dateFormat.format(begin));
        }
        else{

            String beginDate = dateFormat.format(begin);
            String endDate = dateFormat.format(end);

            holder.txtHour.setText("n.v.t.");
            holder.txtDate.setText("van " + beginDate + " tot " + endDate);
        }

        holder.txtLocation.setText(activity.getLocatie());
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
