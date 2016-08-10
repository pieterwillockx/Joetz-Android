package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by a_176_000 on 28-7-2016.
 */
public class RegistratieFragment extends Fragment{

    @Bind(R.id.txtEmailRegistreer)
    public EditText txtEmail;
    @Bind(R.id.txtWachtwoordRegistreer)
    public EditText txtWachtwoord;
    @Bind(R.id.txtNaam)
    public EditText txtNaam;
    @Bind(R.id.txtVoornaam)
    public EditText txtVoornaam;
    @Bind(R.id.txtRijksregisternummer)
    public EditText txtRijksregisternumer;
    @Bind(R.id.txtGeboorteDatum)
    public EditText txtGeboorteDatum;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registreer, container, false);
        ButterKnife.bind(this, view);
        txtEmail.requestFocus();
        return view;
    }

    @OnClick(R.id.btnRegistreer)
    public void Registreer(){
    //todo sla de nieuwe gebruiker op in de databank

    }

}
