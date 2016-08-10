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
import com.fabantowapi.joetz_android.model.Kamp;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 3-8-2016.
 */
public class KampenDetailFragment extends Fragment {



    @Bind(R.id.kampenDetail_datum)
    public TextView datum;
    @Bind(R.id.kampenDetail_image)
    public ImageView image;
    @Bind(R.id.kampenDetail_inhoud)
    public TextView inhoud;
    @Bind(R.id.kampenDetail_locatie)
    public TextView locatie;
    @Bind(R.id.kampenDetail_titel)
    public TextView titel;

    private Kamp kamp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kampen_detail, container, false);
        ButterKnife.bind(this, view);
        Intent i = getActivity().getIntent();
        kamp =(Kamp) i.getSerializableExtra("kamp");
        setDetails(kamp);;

        return view;
    }

    public void setDetails(Kamp kamp){

           datum.setText("Temp Date");
           image.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.offline_image));
           inhoud.setText("Temp inhoud");
           locatie.setText("Temp locatie");
           titel.setText(kamp.getNaam());


    }
    @OnClick(R.id.kampenDetail_inschrvijven)
    public void schrijfIn(){
        // todo schrijf in voor kamp logica

    }

    @OnClick(R.id.kampenDetail_mededelers)
    public void toonMededelers(){
          //todo toon alle mededelers


    }
}
