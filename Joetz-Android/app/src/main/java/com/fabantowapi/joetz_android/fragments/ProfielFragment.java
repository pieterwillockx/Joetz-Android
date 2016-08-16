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
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 11-8-2016.
 */
public class ProfielFragment extends Fragment {

    @Bind(R.id.profile_name)
    public TextView txtName;
    @Bind(R.id.profile_val_email)
    public TextView txtEmail;
    @Bind(R.id.profile_val_username)
    public TextView txtUsername;
    @Bind(R.id.profile_val_buildingname)
    public TextView txtBuildingName;
    @Bind(R.id.profile_val_street_number)
    public TextView txtStreetNumber;
    @Bind(R.id.profile_val_postalcode_city)
    public TextView txtPostalcodeCity;
    @Bind(R.id.profile_val_role)
    public TextView txtRole;
    @Bind(R.id.profile_val_datejoined)
    public TextView txtDateJoined;

    @Bind(R.id.profile_val_contactperson1_email)
    public TextView txtContactperson1Email;
    @Bind(R.id.profile_val_contactperson1_name)
    public TextView txtContactperson1Name;
    @Bind(R.id.profile_val_contactperson1_telephonenumber)
    public TextView txtContactperson1Telephonenumber;

    @Bind(R.id.profile_val_contactperson2_email)
    public TextView txtContactperson2Email;
    @Bind(R.id.profile_val_contactperson2_name)
    public TextView txtContactperson2Name;
    @Bind(R.id.profile_val_contactperson2_telephonenumber)
    public TextView txtContactperson2Telephonenumber;

    private MainActivity activity;
    private User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profiel, container, false);

        activity = (MainActivity) ProfielFragment.this.getActivity();
        currentUser = activity.getCurrentUser();

        ButterKnife.bind(this, view);
        setProfile();
        return view;
    }

    public void setProfile(){
        txtName.setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        txtEmail.setText(currentUser.getEmail());
        txtUsername.setText(currentUser.getUsername());

        if(currentUser.getAdres() != null && currentUser.getAdres().getStraat() != null){
            txtBuildingName.setText(currentUser.getAdres().getNaamgebouw());
            txtStreetNumber.setText(currentUser.getAdres().getStraat() + " " + currentUser.getAdres().getHuisnummer() + " " + currentUser.getAdres().getBus());
            txtPostalcodeCity.setText(currentUser.getAdres().getPostcode() + " " + currentUser.getAdres().getGemeente());
        }
        else{
            txtBuildingName.setText(R.string.not_provided);
        }

        txtRole.setText(currentUser.getRole());

        String date = SharedHelper.convertDate(currentUser.getDateJoined());
        txtDateJoined.setText(date);

        if(currentUser.getContactpersoon1() != null){
            txtContactperson1Email.setText(currentUser.getContactpersoon1().getEmail());
            txtContactperson1Name.setText(currentUser.getContactpersoon1().getFirstname() + " " + currentUser.getContactpersoon1().getLastname());
            txtContactperson1Telephonenumber.setText(currentUser.getContactpersoon1().getTelefoonnummer());
        }
        else{
            txtContactperson1Name.setText(R.string.not_provided);
        }

        if(currentUser.getContactpersoon2() != null){
            txtContactperson2Email.setText(currentUser.getContactpersoon2().getEmail());
            txtContactperson2Name.setText(currentUser.getContactpersoon2().getFirstname() + " " + currentUser.getContactpersoon2().getLastname());
            txtContactperson2Telephonenumber.setText(currentUser.getContactpersoon2().getTelefoonnummer());
        }
        else{
            txtContactperson2Name.setText(R.string.not_provided);
        }
    }

    @OnClick(R.id.profile_button_edit)
    public void editProfile(){

    }
}
