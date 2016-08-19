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
        // each data item is just a string in this case
        public TextView txtNaam;
        public ImageView imgSfeerfoto;
        public TextView txtPrijs;
        public TextView txtLocatie;
        public TextView txtDatum;

        public ViewHolder(View  itemView) {
            super(itemView);
            txtNaam = (TextView) itemView.findViewById(R.id.kampLijst_naam);
            imgSfeerfoto = (ImageView) itemView.findViewById(R.id.kampLijst_image);
            txtPrijs = (TextView) itemView.findViewById(R.id.kampLijst_prijs);
            txtLocatie = (TextView) itemView.findViewById(R.id.kampLijst_locatie);
            txtDatum =(TextView) itemView.findViewById(R.id.kampLijst_datum);

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

        // create a new view
        System.out.println("View type " + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_kampen_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        final Camp camp = camps.get(position);
        holder.txtNaam.setText(camp.getNaam());
        holder.txtPrijs.setText(camp.getPrijs() + " euro");

        Date begin = SharedHelper.parseDateStringToDate(camp.getStartDatum());
        Date end = SharedHelper.parseDateStringToDate(camp.getEindDatum());

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if(SharedHelper.compareDates(begin, end)){
            String beginHour = beginCalendar.get(Calendar.HOUR_OF_DAY) + ":" + beginCalendar.get(Calendar.MINUTE);
            String endHour = endCalendar.get(Calendar.HOUR_OF_DAY) + ":" + endCalendar.get(Calendar.MINUTE);

            holder.txtDatum.setText(dateFormat.format(begin));
        }
        else{
            String beginDate = dateFormat.format(begin);
            String endDate = dateFormat.format(end);

            holder.txtDatum.setText("Van " + beginDate + " tot " + endDate);
        }

        Adres adres = camp.getAdres();

        holder.txtLocatie.setText(adres.getStraat() + " " + adres.getHuisnummer() + adres.getBus() + ", " + adres.getPostcode() + " " + adres.getGemeente());

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
