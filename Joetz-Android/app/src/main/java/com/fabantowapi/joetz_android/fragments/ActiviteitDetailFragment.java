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

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 3-8-2016.
 */
public class ActiviteitDetailFragment extends Fragment {




    @Bind(R.id.activiteitDetail_aanwezig_img1)
    public ImageView img_aanwezig_img1;
    @Bind(R.id.activiteitDetail_aanwezig_img2)
    public ImageView img_aanwezig_img2;
    @Bind(R.id.activiteitDetail_aanwezig_img3)
    public ImageView img_aanwezig_img3;
    @Bind(R.id.activiteitDetail_mischien_aanwezig_img1)
    public ImageView img_mischien_aanwezig_img1;
    @Bind(R.id.activiteitDetail_mischien_aanwezig_img2)
    public  ImageView img_mischien_aanwezig_img2;
    @Bind(R.id.activiteitDetail_mischien_aanwezig_img3)
    public  ImageView img_mischien_aanwezig_img3;
    @Bind(R.id.activiteitDetail_inhoud_text)
    public TextView txtInhoud;
    @Bind(R.id.activiteitDetail_datum)
    public TextView txtDatum;
    @Bind(R.id.activiteitDetail_plaats)
    public TextView txtPlaats;
    @Bind(R.id.activiteitDetail_naam)
    public TextView txtNaam;

    private Activiteit activiteit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activiteit_detail, container, false);
        ButterKnife.bind(this, view);
        Intent i = getActivity().getIntent();
        activiteit =(Activiteit) i.getSerializableExtra("activiteit");
        setDetails(activiteit);;

        return view;
    }

    public void setDetails(Activiteit activiteit){

        txtNaam.setText(activiteit.getNaam());
        txtDatum.setText("placeholder");
        txtPlaats.setText(activiteit.getLocatie());
        img_aanwezig_img1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_action_user));
        img_aanwezig_img2.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_action_user));
        img_aanwezig_img3.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_action_user));
        img_mischien_aanwezig_img1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_action_user));
        img_mischien_aanwezig_img2.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_action_user));
        img_mischien_aanwezig_img3.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_action_user));

        //todo load user images

    }

    @OnClick(R.id.activiteitDetail_aanwezig_btn)
    public void gebruikerAanwezig(){
      //todo voeg gebruiker aanwezig logica toe

    }

    @OnClick(R.id.activiteitDetail_mischien_aanwezig_btn)
    public void gebruikerMischienAanwezig(){
        //todo voeg gebruiker mischien aanwezig logica toe
    }
    }

