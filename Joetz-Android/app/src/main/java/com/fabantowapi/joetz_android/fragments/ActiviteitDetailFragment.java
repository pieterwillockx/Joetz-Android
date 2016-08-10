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
import com.fabantowapi.joetz_android.model.Activiteit;
import com.fabantowapi.joetz_android.model.Kamp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 3-8-2016.
 */
public class ActiviteitDetailFragment extends Fragment {



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

    private Activiteit activiteit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kampen_detail, container, false);
        ButterKnife.bind(this, view);
        Intent i = getActivity().getIntent();
        activiteit =(Activiteit) i.getSerializableExtra("activiteit");
        setDetails(activiteit);;

        return view;
    }

    public void setDetails(Activiteit activiteit){


    }


    }

