package com.fabantowapi.joetz_android.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.tasks.ImageDownloadTask;

import org.w3c.dom.Text;

import java.io.InputStream;

/**
 * Created by a_176_000 on 30-7-2016.
 */
public class ArtikelAdapter extends ArrayAdapter<Artikel> {

    Context context;
    int layoutResourceId;
    Artikel[] artikels = null;

    View test;

    public ArtikelAdapter (Context context, int layoutResourceId, Artikel[] artikels){
        super(context,layoutResourceId,artikels);
        this.context = context;
        this.layoutResourceId =layoutResourceId;
        this.artikels =artikels;

    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        View row = convertView;
        ArtikelHolder holder = null;

        if (row == null) {

                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);
                test = parent;
                holder = new ArtikelHolder();
                holder.artikelTitel = (TextView) row.findViewById(R.id.artikeldetail_titel);
                holder.artikelImage = (ImageView) row.findViewById(R.id.artikel_image);

                row.setTag(holder);
            }
            else
            {
                holder = (ArtikelHolder) row.getTag();
            }
        Artikel artikel = artikels[position];
        holder.artikelTitel.setText(artikel.getArtikelTitel());
        holder.artikelImage.setImageDrawable(context.getResources().getDrawable(R.drawable.offline_image));

        new ImageDownloadTask(holder.artikelImage).execute(artikel.getArtikelImageUrl());



        return row;
    }



    static class ArtikelHolder{
        TextView artikelTitel;
        ImageView artikelImage;
    }
}
