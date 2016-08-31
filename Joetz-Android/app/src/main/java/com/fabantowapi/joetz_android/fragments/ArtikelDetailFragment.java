package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.model.Article;
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

    private Article article;

    private MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel_detail, container, false);
        ButterKnife.bind(this, view);

        activity = (MainActivity) getActivity();
        activity.hideActionBarMenu();;

        loaddArticle();
        return view;
    }

    public void loaddArticle(){

        Intent i = getActivity().getIntent();
        article = (Article) i.getSerializableExtra("artikel");
        txtTitel.setText(article.getArtikelTitel());
        txtArtikelInhoud.setText(Html.fromHtml(article.getArtikelInhoud()));
        artikelImg.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.offline_image));
        new ImageDownloadTask(artikelImg, getActivity()).execute(article.getArtikelImageUrl());
    }
}
