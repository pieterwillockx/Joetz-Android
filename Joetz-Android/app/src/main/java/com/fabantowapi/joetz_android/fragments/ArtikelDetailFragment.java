package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.model.Artikel;
import com.fabantowapi.joetz_android.tasks.ImageDownloadTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 1-8-2016.
 */
public class ArtikelDetailFragment extends Fragment {

     @Bind(R.id.artikel_detail_titel)
     public TextView txtTitel;

    @Bind(R.id.artikel_detail_inhoud)
    public TextView txtArtikelInhoud;

    @Bind(R.id.artikel_detail_image)
    public ImageView artikelImg;

    private Artikel artikel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel_detail, container, false);
        ButterKnife.bind(this, view);
        Intent i = getActivity().getIntent();
        artikel = (Artikel) i.getSerializableExtra("artikel");
        laadArtikel();
        return view;
    }

    public void laadArtikel(){

        txtTitel.setText(artikel.getArtikelTitel());
        txtArtikelInhoud.setText(artikel.getArtikelInhoud());
        artikelImg.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.offline_image));

        new ImageDownloadTask(artikelImg).execute(artikel.getArtikelImageUrl());

    }


}
