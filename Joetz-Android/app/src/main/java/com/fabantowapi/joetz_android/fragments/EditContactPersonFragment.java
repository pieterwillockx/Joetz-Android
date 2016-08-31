package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.model.Adres;
import com.fabantowapi.joetz_android.model.Contactpersoon;
import com.fabantowapi.joetz_android.utils.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pieter on 17-8-2016.
 */
public class EditContactPersonFragment extends Fragment {

    @Bind(R.id.edit_cp_val_firstname)
    public EditText txtFirstname;
    @Bind(R.id.edit_cp_val_lastname)
    public EditText txtLastname;
    @Bind(R.id.edit_cp_val_email)
    public EditText txtEmail;
    @Bind(R.id.edit_cp_val_telephone_number)
    public EditText txtTelephoneNumber;
    @Bind(R.id.edit_cp_val_security_number)
    public EditText txtSecurityNumber;
    @Bind(R.id.edit_cp_val_join_number)
    public EditText txtJoinNumber;
    @Bind(R.id.edit_cp_val_paying)
    public CheckBox chkPaying;
    @Bind(R.id.edit_cp_val_parent)
    public CheckBox chkParent;

    @Bind(R.id.edit_cp_val_address_buildingname)
    public EditText txtAddressBuildingName;
    @Bind(R.id.edit_cp_val_address_street)
    public EditText txtAddressStreet;
    @Bind(R.id.edit_cp_val_address_house_number)
    public EditText txtAddressNumber;
    @Bind(R.id.edit_cp_val_address_extra_field)
    public EditText txtAddressExtraField;
    @Bind(R.id.edit_cp_val_address_city)
    public EditText txtAddressCity;
    @Bind(R.id.edit_cp_val_address_postal_code)
    public EditText txtAddressPostalCode;

    private MainActivity activity;

    private int contactPersonId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_contactperson, container, false);

        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();

        activity.hideActionBarMenu();;

        Intent i = getActivity().getIntent();
        contactPersonId = i.getIntExtra("CONTACTPERSON_ID", 0);

        fillContactPersonInfo();

        return view;
    }

    public void fillContactPersonInfo(){
        Contactpersoon contactPerson;

        switch(contactPersonId){
            case 1 : contactPerson = activity.getCurrentUser().getContactpersoon1(); break;
            case 2 : contactPerson = activity.getCurrentUser().getContactpersoon2(); break;
            default : contactPerson = null;
        }

        if(contactPerson != null){
            if(contactPerson.getFirstname() != null){
                txtFirstname.setText(contactPerson.getFirstname());
            }
            if(contactPerson.getLastname() != null){
                txtLastname.setText(contactPerson.getLastname());
            }
            if(contactPerson.getEmail() != null){
                txtEmail.setText(contactPerson.getEmail());
            }
            if(contactPerson.getTelefoonnummer() != null){
                txtTelephoneNumber.setText(contactPerson.getTelefoonnummer());
            }
            if(contactPerson.getRijksregisternummer() != null){
                txtSecurityNumber.setText(contactPerson.getRijksregisternummer());
            }
            if(contactPerson.getAansluitnummer() != null){
                txtJoinNumber.setText(contactPerson.getAansluitnummer());
            }
            chkPaying.setChecked(contactPerson.isBetalend());
            chkParent.setChecked(contactPerson.isOuder());

            Adres address = contactPerson.getAdres();

            if(address != null){
                if(address.getNaamgebouw() != null){
                    txtAddressBuildingName.setText(address.getNaamgebouw());
                }
                if(address.getStraat() != null){
                    txtAddressStreet.setText(address.getStraat());
                }
                if(address.getHuisnummer() != 0){
                    txtAddressNumber.setText(Integer.toString(address.getHuisnummer()));
                }
                if(address.getBus() != null){
                    txtAddressExtraField.setText(address.getBus());
                }
                if(address.getGemeente() != null){
                    txtAddressCity.setText(address.getGemeente());
                }
                if(address.getPostcode() != 0){
                    txtAddressPostalCode.setText(Integer.toString(address.getPostcode()));
                }
            }
        }
    }

    @OnClick(R.id.edit_cp_save)
    public void editContactPerson(){
        String firstname = txtFirstname.getText().toString();
        String lastname = txtLastname.getText().toString();
        String email = txtEmail.getText().toString();
        String telephonenumber = txtTelephoneNumber.getText().toString();
        String securitynumber = txtSecurityNumber.getText().toString();
        String joinnumber = txtJoinNumber.getText().toString();
        boolean paying = chkPaying.isChecked();
        boolean parent = chkParent.isChecked();

        String buildingname = txtAddressBuildingName.getText().toString();
        String street = txtAddressStreet.getText().toString();
        int housenumber = Integer.parseInt(txtAddressNumber.getText().toString());
        String extrafield = txtAddressExtraField.getText().toString();
        String city = txtAddressCity.getText().toString();
        int postalcode = Integer.parseInt(txtAddressPostalCode.getText().toString());

        Adres address = new Adres(buildingname, street, housenumber, extrafield, city, postalcode);

        if(contactPersonId == 1) {
            ApiHelper.editContactperson1(EditContactPersonFragment.this.getActivity(), activity.getCurrentUser().getEmail(), firstname, lastname, email, telephonenumber, securitynumber, joinnumber, paying, parent, address, activity.getCurrentUser()).subscribe(editContactpersonObserver);
        }
        else if(contactPersonId == 2){
            ApiHelper.editContactperson2(EditContactPersonFragment.this.getActivity(), activity.getCurrentUser().getEmail(), firstname, lastname, email, telephonenumber, securitynumber, joinnumber, paying, parent, address, activity.getCurrentUser()).subscribe(editContactpersonObserver);
        }
    }

    public void showEditContactpersonErrorDialog(Context context, String error){
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_error)
                .content(error)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    Observer<Object> editContactpersonObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(EditContactPersonFragment.this.getActivity() != null)
            {
                Toast.makeText(activity, "Contactpersoon bewerkt!", Toast.LENGTH_SHORT).show();
                activity.getFragmentManager().popBackStack();

                activity.navigate(ProfielFragment.class, false);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(EditContactPersonFragment.this.getActivity() != null)
            {
                EditContactPersonFragment.this.showEditContactpersonErrorDialog(EditContactPersonFragment.this.getActivity(), "Fout bij bewerken: " + e.getMessage());
            }
        }
    };
}
