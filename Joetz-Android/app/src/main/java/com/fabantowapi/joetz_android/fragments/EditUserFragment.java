package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.model.Adres;
import com.fabantowapi.joetz_android.utils.Observer;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pieter on 17-8-2016.
 */
public class EditUserFragment extends Fragment {

    @Bind(R.id.edit_user_avatar)
    public ImageView imgAvatar;
    @Bind(R.id.edit_user_val_firstname)
    public EditText txtFirstname;
    @Bind(R.id.edit_user_val_lastname)
    public EditText txtLastname;
    @Bind(R.id.edit_user_val_birthdate)
    public EditText txtBirthdate;
    @Bind(R.id.edit_user_val_securitynumber)
    public EditText txtSecurityNumber;
    @Bind(R.id.edit_user_val_owner_code)
    public EditText txtOwnerCode;

    @Bind(R.id.edit_user_val_address_buildingname)
    public EditText txtAddressBuildingName;
    @Bind(R.id.edit_user_val_address_street)
    public EditText txtAddressStreet;
    @Bind(R.id.edit_user_val_address_house_number)
    public EditText txtAddressNumber;
    @Bind(R.id.edit_user_val_address_extra_field)
    public EditText txtAddressExtraField;
    @Bind(R.id.edit_user_val_address_city)
    public EditText txtAddressCity;
    @Bind(R.id.edit_user_val_address_postal_code)
    public EditText txtAddressPostalCode;

    private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();

        activity.hideActionBarMenu();;

        fillUserInfo();

        Intent i = getActivity().getIntent();
        return view;
    }

    public void fillUserInfo(){
        txtFirstname.setText(activity.getCurrentUser().getFirstname());
        txtLastname.setText(activity.getCurrentUser().getLastname());
        if(activity.getCurrentUser().getGeboortedatum() != null){
            txtBirthdate.setText(SharedHelper.convertDate(activity.getCurrentUser().getGeboortedatum()));
        }
        if(activity.getCurrentUser().getRijksregisternummer() != null){
            txtSecurityNumber.setText(activity.getCurrentUser().getRijksregisternummer());
        }
        if(activity.getCurrentUser().getCodegerechtigde() != null){
            txtOwnerCode.setText(activity.getCurrentUser().getCodegerechtigde());
        }

        Adres userAddress = activity.getCurrentUser().getAdres();

        if(userAddress != null){
            if(userAddress.getNaamgebouw() != null){
                txtAddressBuildingName.setText(userAddress.getNaamgebouw());
            }
            if(userAddress.getStraat() != null){
                txtAddressStreet.setText(userAddress.getStraat());
            }
            if(userAddress.getHuisnummer() != 0){
                txtAddressNumber.setText(Integer.toString(userAddress.getHuisnummer()));
            }
            if(userAddress.getBus() != null){
                txtAddressExtraField.setText(userAddress.getBus());
            }
            if(userAddress.getGemeente() != null){
                txtAddressCity.setText(userAddress.getGemeente());
            }
            if(userAddress.getPostcode() != 0){
                txtAddressPostalCode.setText(Integer.toString(userAddress.getPostcode()));
            }
        }
    }

    private void showEditUserErrorDialog(Context context, String error){
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_error)
                .content(error)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    @OnClick(R.id.edit_user_save)
    public void editUser(){
        String firstname = txtFirstname.getText().toString();
        String lastname = txtLastname.getText().toString();
        String birthdate = txtBirthdate.getText().toString();
        String securitynumber = txtSecurityNumber.getText().toString();

        ApiHelper.editUser(EditUserFragment.this.getActivity(), activity.getCurrentUser().getEmail(), firstname, lastname, securitynumber, birthdate, activity.getCurrentUser()).subscribe(editUserObserver);
    }

    Observer<Object> editUserObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(EditUserFragment.this.getActivity() != null)
            {
                String naamgebouw = txtAddressBuildingName.getText().toString();
                String straat = txtAddressStreet.getText().toString();
                int huisnummer = Integer.parseInt(txtAddressNumber.getText().toString());
                String bus = txtAddressExtraField.getText().toString();
                String gemeente = txtAddressCity.getText().toString();
                int postcode = Integer.parseInt(txtAddressPostalCode.getText().toString());

                ApiHelper.editAddress(EditUserFragment.this.getActivity(), activity.getCurrentUser().getEmail(), naamgebouw, straat, huisnummer, bus, gemeente, postcode, activity.getCurrentUser()).subscribe(editAddressObserver);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(EditUserFragment.this.getActivity() != null)
            {
                EditUserFragment.this.showEditUserErrorDialog(EditUserFragment.this.getActivity(), "Fout bij bewerken: " + e.getMessage());
            }
        }
    };

    Observer<Object> editAddressObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(EditUserFragment.this.getActivity() != null)
            {
                String codegerechtigde = txtOwnerCode.getText().toString();

                ApiHelper.editUserDetails(EditUserFragment.this.getActivity(), activity.getCurrentUser().getEmail(), codegerechtigde, activity.getCurrentUser()).subscribe(editDetailsObserver);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(EditUserFragment.this.getActivity() != null)
            {
                EditUserFragment.this.showEditUserErrorDialog(EditUserFragment.this.getActivity(), "Fout bij bewerken adres: " + e.getMessage());
            }
        }
    };

    Observer<Object> editDetailsObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(EditUserFragment.this.getActivity() != null)
            {
                Toast.makeText(activity, "Gegevens bewerkt!", Toast.LENGTH_SHORT).show();
                activity.getFragmentManager().popBackStack();

                activity.navigate(ProfielFragment.class, false);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(EditUserFragment.this.getActivity() != null)
            {
                EditUserFragment.this.showEditUserErrorDialog(EditUserFragment.this.getActivity(), "Fout bij bewerken details: " + e.getMessage());
            }
        }
    };
}
