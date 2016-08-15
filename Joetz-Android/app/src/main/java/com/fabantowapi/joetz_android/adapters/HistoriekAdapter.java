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
import android.widget.TextView;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.KampenDetailFragment;
import com.fabantowapi.joetz_android.model.Kamp;

/**
 * Created by Anton Rooseleer on 9-8-2016.
 */
public class HistoriekAdapter  extends RecyclerView.Adapter<HistoriekAdapter.ViewHolder>{

    public Kamp[] kampen;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHistoriekTitel;
        public ImageView imgHistoriek;
        public TextView txtLocatie;
        public TextView txtDatum;

        public ViewHolder(View itemView) {
            super(itemView);
            txtHistoriekTitel = (TextView) itemView.findViewById(R.id.historiekLijst_titel);
            imgHistoriek = (ImageView) itemView.findViewById(R.id.historiekLijst_image);
            txtLocatie = (TextView) itemView.findViewById(R.id.historiekLijst_locatie);
            txtDatum =(TextView) itemView.findViewById(R.id.historiekDatum);

        }
    }
    public HistoriekAdapter(Kamp[] kampen,Context context) {
        this.kampen = kampen;
        this.context = context;

    }

    @Override
    public HistoriekAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,  int viewType) {

        // create a new view
        System.out.println("View type " + viewType);
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.fragment_historiek_item, parent, false);

        // TextView test =(TextView)view.findViewById(R.id.test);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Kamp kamp = kampen[position];
        holder.txtHistoriekTitel.setText(kampen[position].getNaam());
        holder.imgHistoriek.setImageDrawable(context.getResources().getDrawable(R.drawable.offline_image));
        holder.txtDatum.setText("Placeholder Datum");
        holder.txtLocatie.setText("Placeholder Locatie");

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle args =new Bundle();
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                KampenDetailFragment kampenDetailFragment = new KampenDetailFragment();
                kampenDetailFragment.setArguments(args);

                ((Activity) context).getIntent().putExtra("kamp", kamp);
                ft.replace(R.id.mainpage_container, kampenDetailFragment);
                ft.commit();

            }
        });

    }
    @Override
    public int getItemCount() {
        return kampen.length;

    }


}
