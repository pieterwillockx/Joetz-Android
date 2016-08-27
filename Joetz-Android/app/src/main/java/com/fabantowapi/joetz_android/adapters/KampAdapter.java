package com.fabantowapi.joetz_android.adapters;



import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.KampenDetailFragment;
import com.fabantowapi.joetz_android.model.Kamp;
import com.fabantowapi.joetz_android.model.api.Adres;
import com.fabantowapi.joetz_android.model.api.Camp;
import com.fabantowapi.joetz_android.tasks.ImageDownloadTask;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Anton Rooseleer on 2-8-2016.
 */
public class KampAdapter extends RecyclerView.Adapter<KampAdapter.ViewHolder>{

    public List<Camp> camps;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public ImageView imgSfeerfoto;
        public TextView txtPrice;
        public TextView txtLocation;
        public TextView txtDate;

        public ViewHolder(View  itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.kampLijst_naam);
            imgSfeerfoto = (ImageView) itemView.findViewById(R.id.kampLijst_image);
            txtPrice = (TextView) itemView.findViewById(R.id.kampLijst_prijs);
            txtLocation = (TextView) itemView.findViewById(R.id.kampLijst_locatie);
            txtDate =(TextView) itemView.findViewById(R.id.kampLijst_datum);
        }
    }
    public KampAdapter(Context context) {
        this.context = context;
    }

    public void setCamps(List<Camp> camps){
        this.camps = camps;
        notifyDataSetChanged();
    }

    @Override
    public KampAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_kampen_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        final Camp camp = camps.get(position);
        holder.txtName.setText(camp.getNaam());
        holder.txtPrice.setText(camp.getPrijs() + " euro");

        Date begin = SharedHelper.parseDateStringToDate(camp.getStartDatum());
        Date end = SharedHelper.parseDateStringToDate(camp.getEindDatum());

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if(SharedHelper.compareDates(begin, end)){
            String beginHour = beginCalendar.get(Calendar.HOUR_OF_DAY) + ":" + beginCalendar.get(Calendar.MINUTE);
            String endHour = endCalendar.get(Calendar.HOUR_OF_DAY) + ":" + endCalendar.get(Calendar.MINUTE);

            holder.txtDate.setText(dateFormat.format(begin));
        }
        else{
            String beginDate = dateFormat.format(begin);
            String endDate = dateFormat.format(end);

            holder.txtDate.setText("Van " + beginDate + " tot " + endDate);
        }

        Adres adres = camp.getAdres();

        holder.txtLocation.setText(adres.getStraat() + " " + adres.getHuisnummer() + adres.getBus() + ", " + adres.getPostcode() + " " + adres.getGemeente());

        holder.imgSfeerfoto.setImageDrawable(context.getResources().getDrawable(R.drawable.offline_image));
        new ImageDownloadTask(holder.imgSfeerfoto, context).execute(camp.getSfeerfoto());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle args =new Bundle();
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                KampenDetailFragment kampenDetailFragment = new KampenDetailFragment();
                kampenDetailFragment.setArguments(args);
                ((Activity) context).getIntent().putExtra("kamp", camp);
                ft.replace(R.id.mainpage_container, kampenDetailFragment);
                ft.addToBackStack("FRAGMENT");
                ft.commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        return camps.size();
    }
}
