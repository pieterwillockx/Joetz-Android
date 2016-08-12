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
import android.widget.ImageView;
import android.widget.TextView;;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.KampenDetailFragment;

import butterknife.OnClick;


/**
 * Created by Anton Rooseleer on 2-8-2016.
 */
public class KampAdapter extends RecyclerView.Adapter<KampAdapter.ViewHolder>{

    public Kamp[] kampen;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtKampTitel;
        public ImageView imgKamp;
        public TextView txtLocatie;
        public TextView txtDatum;

        public ViewHolder(View  itemView) {
            super(itemView);
            txtKampTitel = (TextView) itemView.findViewById(R.id.kampenLijst_titel);
            imgKamp = (ImageView) itemView.findViewById(R.id.kampenLijst_image);
            txtLocatie = (TextView) itemView.findViewById(R.id.kampenLijst_locatie);
            txtDatum =(TextView) itemView.findViewById(R.id.kampenLijstDatum);

        }
    }
    public KampAdapter(Kamp[] kampen,Context context) {
        this.kampen = kampen;
        this.context = context;

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
        final Kamp kamp = kampen[position];
        holder.txtKampTitel.setText(kampen[position].getNaam());
        holder.txtDatum.setText("Placeholder Datum");
        holder.txtLocatie.setText("Placeholder Locatie");
        holder.imgKamp.setImageDrawable(context.getResources().getDrawable(R.drawable.offline_image));

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
