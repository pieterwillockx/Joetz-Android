package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabantowapi.joetz_android.R;

import butterknife.ButterKnife;

/**
 * Created by a_176_000 on 28-7-2016.
 */
public class RegistratieFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registreer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
