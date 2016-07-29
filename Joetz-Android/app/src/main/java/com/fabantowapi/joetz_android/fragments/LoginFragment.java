package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabantowapi.joetz_android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by a_176_000 on 29-7-2016.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.btnLogin)
    public void onLoginButtonClicked() {
        // valideer;
    }
    @OnClick(R.id.txtwwVergeten)
    public void wachtwoordVergeten(){
        // Start ww vergeten procedure
    }

    @OnClick(R.id.txtRegistreer)
    public void registreer(){
        // start registreer procedure
        Bundle args =new Bundle();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new RegistratieFragment();
        fragment.setArguments(args);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
