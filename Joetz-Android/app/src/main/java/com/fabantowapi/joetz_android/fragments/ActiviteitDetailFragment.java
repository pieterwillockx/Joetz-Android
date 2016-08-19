package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.adapters.UserMugshotAdapter;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.utils.Observer;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 3-8-2016.
 */
public class ActiviteitDetailFragment extends Fragment {


    @Bind(R.id.activiteitDetail_naam)
    public TextView txtName;
    @Bind(R.id.activiteitDetail_uur)
    public TextView txtHour;
    @Bind(R.id.activiteitDetail_datum)
    public TextView txtDate;
    @Bind(R.id.activiteitDetail_locatie)
    public TextView txtLocation;
    @Bind(R.id.activiteitDetail_recyclerView_aanwezig)
    public RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;
    UserMugshotAdapter mAdapter;

    private Activity activity;
    private List<User> attendees;

    private MainActivity mainActivity;

    private MaterialDialog dialogProgress;
    private MaterialDialog dialogAddUser;
    private MaterialDialog dialogUserAlreadyInList;

    private static final int PADDING_RIGHT = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activiteit_detail, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        mainActivity = (MainActivity) getActivity();

        mainActivity.showActionBarAddPerson();

        Intent i = getActivity().getIntent();
        activity =(Activity) i.getSerializableExtra("activity");

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UserMugshotAdapter(this.getActivity());
        mRecyclerView.setAdapter(mAdapter);

        attendees = activity.getAanwezigen();
        mAdapter.setUsers(attendees);

        setDetails();

        return view;
    }

    private void setDetails(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        txtName.setText(activity.getNaam());

        Date begin = SharedHelper.parseDateStringToDate(activity.getBeginUur());
        Date end = SharedHelper.parseDateStringToDate(activity.getEindUur());

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if(SharedHelper.compareDates(begin, end)){
            String beginHour = beginCalendar.get(Calendar.HOUR_OF_DAY) + ":" + (beginCalendar.get(Calendar.MINUTE) == 0 ? "00" : beginCalendar.get(Calendar.MINUTE));
            String endHour = endCalendar.get(Calendar.HOUR_OF_DAY) + ":" + (endCalendar.get(Calendar.MINUTE) == 0 ? "00" : endCalendar.get(Calendar.MINUTE));

            txtHour.setText(beginHour + " - " + endHour);

            txtDate.setText(dateFormat.format(begin));
        }
        else{
            txtHour.setText("n.v.t.");

            String beginDate = dateFormat.format(begin);
            String endDate = dateFormat.format(end);

            txtDate.setText("Van " + beginDate + " tot " + endDate);
        }

        txtLocation.setText(activity.getLocatie());
    }

    private boolean isCurrentUserInList(){
        boolean isInList = false;

        for(User u : attendees){
            if(u.getId().equals(mainActivity.getCurrentUser().getId())){
                isInList = true;
            }
        }

        return isInList;
    }

    public void showProgressDialog(Context context){
        this.dialogProgress = new MaterialDialog.Builder(context)
                .content(R.string.add_user_to_activity_progress)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    public void showAddUserDialog(Context context){
        this.dialogAddUser = new MaterialDialog.Builder(context)
                .content(R.string.dialog_add_user)
                .positiveText(R.string.dialog_yes)
                .negativeText(R.string.dialog_no)
                .onPositive((dialog, which) -> {
                    showProgressDialog(ActiviteitDetailFragment.this.getActivity());
                    ApiHelper.addUserToActivity(this.getActivity(), activity.getId(), mainActivity.getCurrentUser().getEmail(), activity, mainActivity.getCurrentUser()).subscribe(addUserToActivityObserver);
                })
                .show();
    }

    public void showUserAlreadyInListDialog(Context context){
        this.dialogUserAlreadyInList = new MaterialDialog.Builder(context)
                .content(R.string.dialog_user_already_in_list)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    public void gebruikerAanwezig(){
        if(isCurrentUserInList()){
            showUserAlreadyInListDialog(ActiviteitDetailFragment.this.getActivity());
        }else{
            showAddUserDialog(ActiviteitDetailFragment.this.getActivity());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_person:
                // User chose the "Settings" item, show the app settings UI...
                gebruikerAanwezig();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    Observer<Object> addUserToActivityObserver = new Observer<Object>(){
        @Override
        public void onCompleted()
        {
            if(ActiviteitDetailFragment.this != null)
            {
                mAdapter.notifyDataSetChanged();
                Toast.makeText(ActiviteitDetailFragment.this.getActivity(), "Gebruiker toegevoegd!", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(ActiviteitDetailFragment.this != null)
            {
                Toast.makeText(ActiviteitDetailFragment.this.getActivity(), "Fout bij toevoegen van gebruiker", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }
    };
}

