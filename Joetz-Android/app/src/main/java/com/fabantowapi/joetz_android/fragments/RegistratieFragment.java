package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.LoginActivity;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.utils.Observer;

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
    @Bind(R.id.txtGebruikersnaam)
    public EditText txtGebruikersnaam;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registreer, container, false);
        ButterKnife.bind(this, view);
        txtEmail.requestFocus();
        return view;
    }

    @OnClick(R.id.btnRegistreer)
    public void Registreer(){
        String email = txtEmail.getText().toString();
        String firstname = txtVoornaam.getText().toString();
        String lastname = txtNaam.getText().toString();
        String password = txtWachtwoord.getText().toString();
        String username = txtGebruikersnaam.getText().toString();

        if(email != "" && firstname != "" && lastname != "" && password != "" && username != ""){
            ApiHelper.register(RegistratieFragment.this.getActivity(), email, firstname, lastname, password, username).subscribe(this.registerObserver);
        }
        else {
            showRegisterErrorDialog(RegistratieFragment.this.getActivity(), "Alle velden moeten ingevuld zijn!");
        }

    }

    private void showRegisterErrorDialog(Context context, String error){
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_error)
                .content(error)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    private Observer<Object> registerObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(RegistratieFragment.this.getActivity() != null)
            {
                Toast.makeText(RegistratieFragment.this.getActivity(), "Account aangemaakt!", Toast.LENGTH_LONG).show();
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(RegistratieFragment.this.getActivity() != null)
            {
                RegistratieFragment.this.showRegisterErrorDialog(RegistratieFragment.this.getActivity(), e.getMessage());
            }
        }
    };

}
