package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.fabantowapi.joetz_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 11-8-2016.
 */
public class ProfielDeelnemerFragment extends Fragment {

    @Bind(R.id.profielDeelnemerAchternaam)
    EditText txtAchternaam;
    @Bind(R.id.profielDeelnemerVoornaam)
    EditText txtVoornaam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profiel_deelnemer, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    public void initDate(){
        txtAchternaam.setText("Rooseleer");
        txtVoornaam.setText("Anton");
    }

    @OnClick(R.id.btnSave)
    public void saveInfo(){
        //todo sla nieuwe info op
    }
}
