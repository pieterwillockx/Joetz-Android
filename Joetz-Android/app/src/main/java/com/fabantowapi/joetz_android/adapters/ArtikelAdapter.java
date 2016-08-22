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
import com.fabantowapi.joetz_android.fragments.ArtikelDetailFragment;
import com.fabantowapi.joetz_android.model.Artikel;
import com.fabantowapi.joetz_android.tasks.ImageDownloadTask;

import java.util.List;

/**
 * Created by a_176_000 on 30-7-2016.
 */
public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ViewHolder> {

    public List<Artikel> artikels;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView artikelTitel;
        public ImageView artikelImage;

        public ViewHolder(View  itemView) {
            super(itemView);
            artikelTitel = (TextView) itemView.findViewById(R.id.artikeldetail_titel);
            artikelImage = (ImageView) itemView.findViewById(R.id.artikel_image);


        }
    }

    public ArtikelAdapter (Context context){
        this.context = context;

    }

    public void setArtikels(List<Artikel> artikels) {
        this.artikels = artikels;
        notifyDataSetChanged();
    }

    @Override
    public ArtikelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_artikel_item, parent, false);
        ViewHolder vh =  new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Artikel artikel = artikels.get(position);
        holder.artikelTitel.setText(artikels.get(position).getArtikelTitel());
        holder.artikelImage.setImageDrawable(context.getResources().getDrawable(R.drawable.offline_image));
        new ImageDownloadTask(holder.artikelImage, context).execute(artikel.getArtikelImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle args =new Bundle();
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ArtikelDetailFragment kampenDetailFragment = new ArtikelDetailFragment();
                kampenDetailFragment.setArguments(args);
                ((Activity) context).getIntent().putExtra("artikel", artikel);
                ft.replace(R.id.mainpage_container, kampenDetailFragment);
                ft.addToBackStack("FRAGMENT");
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() { return this.artikels != null ? this.artikels.size() : 0; }

}
