package com.fabantowapi.joetz_android.model;

import android.app.Activity;
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
import com.fabantowapi.joetz_android.fragments.KampenDetailFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anton Rooseleer on 9-8-2016.
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder>{

    public Activiteit[] activiteiten;
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
    public ActivityAdapter(Activiteit[] activiteiten, Context context) {
        this.activiteiten = activiteiten;
        this.context = context;

    }

    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_activity_item, parent, false);

        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Activiteit activity = activiteiten[position];
        if(activiteiten[position].getBegin() != currentDate ){
            currentDate =activiteiten[position].getBegin();

            holder.txtDate.setText(dateFormat.format(activiteiten[position].getBegin()));
        }
       holder.txtDuratie.setText("Van " +dateFormat.format(activiteiten[position].getBegin()) + "\ntot "+ dateFormat.format(activiteiten[position].getEinde()));

        holder.txtNaam.setText(activiteiten[position].getNaam());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle args =new Bundle();
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ActiviteitDetailFragment activiteitDetailFragment = new ActiviteitDetailFragment();
                activiteitDetailFragment.setArguments(args);
                ((Activity) context).getIntent().putExtra("activiteit", activity);
                ft.replace(R.id.mainpage_container, activiteitDetailFragment);
                ft.commit();

            }
        });

    }
    @Override
    public int getItemCount() {
        return activiteiten.length;

    }


}
