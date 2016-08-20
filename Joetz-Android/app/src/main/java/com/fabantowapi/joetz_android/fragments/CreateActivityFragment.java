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
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.utils.Observer;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pieter on 19-8-2016.
 */
public class CreateActivityFragment extends Fragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    @Bind(R.id.create_activity_val_name)
    public EditText txtName;
    @Bind(R.id.create_activity_val_location)
    public EditText txtLocation;
    @Bind(R.id.create_activity_val_date)
    public TextView txtDate;
    @Bind(R.id.create_activity_val_whole_day)
    public CheckBox chkWholeDay;
    @Bind(R.id.create_activity_val_begin)
    public TextView txtBegin;
    @Bind(R.id.create_activity_val_end)
    public TextView txtEnd;

    private MainActivity activity;

    private MaterialDialog dialogCreateActivityError;
    private MaterialDialog dialogProgress;

    private Calendar date;

    private int timeValueSelected = 0;

    private static final int BEGIN = 1;
    private static final int END = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_activity, container, false);

        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();

        activity.hideActionBarMenu();;

        Intent i = getActivity().getIntent();

        initializeForm();

        return view;
    }

    public void initializeForm(){
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        CreateActivityFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        txtBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    timeValueSelected = BEGIN;
                    Calendar now = Calendar.getInstance();
                    TimePickerDialog tpd = TimePickerDialog.newInstance(
                            CreateActivityFragment.this,
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            true
                    );
                    tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        txtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeValueSelected = END;
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        CreateActivityFragment.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });
    }

    @OnClick(R.id.create_activity_btn_confirm)
    public void createActivity(){
        showProgressDialog(CreateActivityFragment.this.getActivity());

        // set begin time
        Calendar beginCal = Calendar.getInstance();
        beginCal.set(Calendar.YEAR, date.get(Calendar.YEAR));
        beginCal.set(Calendar.MONTH, date.get(Calendar.MONTH));
        beginCal.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));

        String beginHour = txtBegin.getText().toString().split(":")[0];
        String beginMin = txtBegin.getText().toString().split(":")[1];

        beginCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(beginHour));
        beginCal.set(Calendar.MINUTE, Integer.parseInt(beginMin));

        // set end time
        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.YEAR, date.get(Calendar.YEAR));
        endCal.set(Calendar.MONTH, date.get(Calendar.MONTH));
        endCal.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));

        String endHour = txtEnd.getText().toString().split(":")[0];
        String endMin = txtEnd.getText().toString().split(":")[1];

        endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
        endCal.set(Calendar.MINUTE, Integer.parseInt(endMin));

        String name = txtName.getText().toString();
        String location = txtLocation.getText().toString();
        boolean entireDay = chkWholeDay.isChecked();

        ApiHelper.createActivity(CreateActivityFragment.this.getActivity(), name, date.getTime(), location, entireDay, beginCal.getTime(), endCal.getTime()).subscribe(createActivityObserver);
    }

    public void showProgressDialog(Context context){
        this.dialogProgress = new MaterialDialog.Builder(context)
                .content(R.string.dialog_create_activity)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    public void showCreateActivityErrorDialog(Context context, String error){
        this.dialogCreateActivityError = new MaterialDialog.Builder(context)
                .title(R.string.dialog_error)
                .content(error)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        switch(timeValueSelected){
            case BEGIN :
                txtBegin.setText(hourOfDay + ":" + minute);
                break;
            case END :
                txtEnd.setText(hourOfDay + ":" + minute);
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        txtDate.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
        date = Calendar.getInstance();
        date.set(year, monthOfYear, dayOfMonth);
    }

    Observer<Object> createActivityObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(CreateActivityFragment.this.getActivity() != null)
            {
                Toast.makeText(activity, "Activiteit aangemaakt!", Toast.LENGTH_SHORT).show();

                dialogProgress.dismiss();

                activity.getFragmentManager().popBackStack();
                activity.navigate(ActivityListFragment.class, false);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(CreateActivityFragment.this.getActivity() != null)
            {
                CreateActivityFragment.this.showCreateActivityErrorDialog(CreateActivityFragment.this.getActivity(), "Fout bij aanmaken activiteit: " + e.getMessage());
            }
        }
    };
}
