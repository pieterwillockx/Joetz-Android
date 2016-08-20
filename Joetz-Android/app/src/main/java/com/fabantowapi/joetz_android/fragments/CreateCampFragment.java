package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.utils.Observer;
import com.fabantowapi.joetz_android.utils.SharedHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pieter on 19-8-2016.
 */
public class CreateCampFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    @Bind(R.id.create_camp_val_name)
    public EditText txtName;
    @Bind(R.id.create_camp_val_description)
    public EditText txtDescription;
    @Bind(R.id.create_camp_val_price)
    public TextView txtPrice;
    @Bind(R.id.create_camp_val_transport)
    public TextView txtTransport;
    @Bind(R.id.create_camp_val_min_age)
    public TextView txtMinAge;
    @Bind(R.id.create_camp_val_max_age)
    public TextView txtMaxAge;
    @Bind(R.id.create_camp_val_max_participants)
    public TextView txtMaxParticipants;
    @Bind(R.id.create_camp_val_contact)
    public TextView txtContact;
    @Bind(R.id.create_camp_val_image)
    public TextView txtImage;
    @Bind(R.id.create_camp_val_buildingname)
    public TextView txtBuildingName;
    @Bind(R.id.create_camp_val_street)
    public TextView txtStreet;
    @Bind(R.id.create_camp_val_house_number)
    public TextView txtHouseNumber;
    @Bind(R.id.create_camp_val_extra_field)
    public TextView txtExtraField;
    @Bind(R.id.create_camp_val_city)
    public TextView txtCity;
    @Bind(R.id.create_camp_val_postal_code)
    public TextView txtPostalCode;
    @Bind(R.id.create_camp_val_begin)
    public TextView txtBegin;
    @Bind(R.id.create_camp_val_end)
    public TextView txtEnd;

    private MainActivity activity;

    private MaterialDialog dialogCreateCampError;
    private MaterialDialog dialogProgress;

    private Calendar beginDate;
    private Calendar endDate;

    private int dateValueSelected = 0;

    private static final int BEGIN = 1;
    private static final int END = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_camp, container, false);

        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();

        activity.hideActionBarMenu();;

        Intent i = getActivity().getIntent();

        initializeForm();

        return view;
    }

    public void initializeForm(){
        txtBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateValueSelected = BEGIN;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        CreateCampFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        txtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateValueSelected = END;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        CreateCampFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }

    @OnClick(R.id.create_camp_btn_confirm)
    public void createCamp(){
        if(beginDate == null || endDate == null){
            showCreateCampErrorDialog(CreateCampFragment.this.getActivity(), "Vul eerst de datum in alvorens te bevestigen.");
        }
        else {
            showProgressDialog(CreateCampFragment.this.getActivity());

            String name = txtName.getText().toString();
            String description = txtDescription.getText().toString();
            int price = Integer.parseInt(txtPrice.getText().toString());
            String transport = txtTransport.getText().toString();
            int minAge = Integer.parseInt(txtMinAge.getText().toString());
            int maxAge = Integer.parseInt(txtMaxAge.getText().toString());
            int maxParticipants = Integer.parseInt(txtMaxParticipants.getText().toString());
            String contact = txtContact.getText().toString();
            String imageUrl = txtImage.getText().toString();
            String buildingName = txtBuildingName.getText().toString();
            String street = txtStreet.getText().toString();
            int houseNumber = Integer.parseInt(txtHouseNumber.getText().toString());
            String extraField = txtExtraField.getText().toString();
            String city = txtCity.getText().toString();
            int postalCode = Integer.parseInt(txtPostalCode.getText().toString());

            int amountOfDays = SharedHelper.getDateDifference(beginDate, endDate);
            int amountOfNights = amountOfDays - 1;

            ApiHelper.createCamp(CreateCampFragment.this.getActivity(), name, description, beginDate.getTime(), endDate.getTime(), amountOfDays, amountOfNights, transport, price, maxAge, minAge, maxParticipants, contact, imageUrl, buildingName, street, houseNumber, extraField, city, postalCode).subscribe(createCampObserver);
        }
    }

    public void showProgressDialog(Context context){
        this.dialogProgress = new MaterialDialog.Builder(context)
                .content(R.string.dialog_create_camp)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    public void showCreateCampErrorDialog(Context context, String error){
        this.dialogCreateCampError = new MaterialDialog.Builder(context)
                .title(R.string.dialog_error)
                .content(error)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        switch(dateValueSelected){
            case BEGIN :
                txtBegin.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
                beginDate = Calendar.getInstance();
                beginDate.set(year, monthOfYear, dayOfMonth);
                break;
            case END :
                txtEnd.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
                endDate = Calendar.getInstance();
                endDate.set(year, monthOfYear, dayOfMonth);
                break;
        }
    }

    Observer<Object> createCampObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(CreateCampFragment.this.getActivity() != null)
            {
                Toast.makeText(activity, "Kamp aangemaakt!", Toast.LENGTH_SHORT).show();

                dialogProgress.dismiss();

                activity.getFragmentManager().popBackStack();
                activity.navigate(KampenListFragment.class, false);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(CreateCampFragment.this.getActivity() != null)
            {
                CreateCampFragment.this.showCreateCampErrorDialog(CreateCampFragment.this.getActivity(), "Fout bij aanmaken kamp: " + e.getMessage());
            }
        }
    };
}
