package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 11-8-2016.
 */
public class ProfielFragment extends Fragment {

    @Bind(R.id.profielNaam)
    TextView txtProfielNaam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profiel, container, false);

        ButterKnife.bind(this, view);
        setProfile();
        return view;
    }
    public void setProfile(){
        //todo get real user
        txtProfielNaam.setText("Anton Rooseleer");
    }

    @OnClick(R.id.profielDeelnemer)
    public void editDeelnemer(){

        Fragment fragment = new ProfielDeelnemerFragment();
        Bundle args =new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragment.setArguments(args);
        ft.replace(R.id.mainpage_container, fragment);
        ft.commit();

    }
    @OnClick(R.id.profielOuder)
    public void editOuder(){

    }

    @OnClick(R.id.profielWachtwoord)
    public void editWachtwoord(){

    }
}
