package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.adapters.ActivityDetailAdapter;
import com.fabantowapi.joetz_android.adapters.itemdecorations.HorizontalSpaceItemDecoration;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.utils.Observer;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 3-8-2016.
 */
public class ActiviteitDetailFragment extends Fragment {


    @Bind(R.id.activiteitDetail_naam)
    public TextView txtNaam;
    @Bind(R.id.activiteitDetail_uur)
    public TextView txtUur;
    @Bind(R.id.activiteitDetail_datum)
    public TextView txtDatum;
    @Bind(R.id.activiteitDetail_locatie)
    public TextView txtLocatie;
    @Bind(R.id.activiteitDetail_aanwezig_btn)
    public Button btnAddUserToActivity;
    @Bind(R.id.activiteitDetail_recyclerView_aanwezig)
    public RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;
    ActivityDetailAdapter mAdapter;

    private Activity activiteit;
    private List<User> aanwezigen;

    private MainActivity mainActivity;

    private MaterialDialog dialogProgress;

    private static final int PADDING_LEFT_RIGHT = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activiteit_detail, container, false);
        ButterKnife.bind(this, view);

        mainActivity = (MainActivity) getActivity();

        Intent i = getActivity().getIntent();
        activiteit =(Activity) i.getSerializableExtra("activity");

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ActivityDetailAdapter(this.getActivity());
        mRecyclerView.setAdapter(mAdapter);

        aanwezigen = activiteit.getAanwezigen();
        mAdapter.setUsers(aanwezigen);

        setDetails(activiteit);

        return view;
    }

    private void setDetails(Activity activiteit){
        txtNaam.setText(activiteit.getNaam());

        String hourString = SharedHelper.convertDateToHourString(activiteit.getBeginUur()) + " - " + SharedHelper.convertDateToHourString(activiteit.getEindUur());
        txtUur.setText(hourString);

        txtDatum.setText(SharedHelper.convertDate(activiteit.getDatum()));

        txtLocatie.setText(activiteit.getLocatie());

        if(isCurrentUserInList()){
            disableButton();
        }
    }

    private boolean isCurrentUserInList(){
        boolean isInList = false;

        for(User u : aanwezigen){
            if(u.getId().equals(mainActivity.getCurrentUser().getId())){
                isInList = true;
            }
        }

        return isInList;
    }

    private void disableButton(){
        btnAddUserToActivity.setEnabled(false);
        btnAddUserToActivity.setBackgroundColor(getActivity().getResources().getColor(R.color.colorButtonDisabled));
        btnAddUserToActivity.setText("Je bent aanwezig");
    }

    public void showProgressDialog(Context context){
        this.dialogProgress = new MaterialDialog.Builder(context)
                .content(R.string.add_user_to_activity_progress)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    @OnClick(R.id.activiteitDetail_aanwezig_btn)
    public void gebruikerAanwezig(){
        showProgressDialog(ActiviteitDetailFragment.this.getActivity());
        ApiHelper.addUserToActivity(this.getActivity(), activiteit.getId(), mainActivity.getCurrentUser().getEmail(), activiteit, mainActivity.getCurrentUser()).subscribe(addUserToActivityObserver);
    }

    Observer<Object> addUserToActivityObserver = new Observer<Object>(){
        @Override
        public void onCompleted()
        {
            if(ActiviteitDetailFragment.this != null)
            {
                mAdapter.notifyDataSetChanged();
                disableButton();
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
            }
        }
    };
}

