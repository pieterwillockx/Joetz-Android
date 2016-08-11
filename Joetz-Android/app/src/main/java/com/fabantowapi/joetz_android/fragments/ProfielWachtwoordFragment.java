package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabantowapi.joetz_android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 11-8-2016.
 */
public class ProfielWachtwoordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profiel_wachtwoord, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnWachtwoordSave)
    public void saveNieuwWachtwoord(){
        //todo save nieuw ww
    }
}
