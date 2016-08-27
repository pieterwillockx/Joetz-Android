package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.adapters.UserMugshotAdapter;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.model.Adres;
import com.fabantowapi.joetz_android.model.Camp;
import com.fabantowapi.joetz_android.model.User;
import com.fabantowapi.joetz_android.tasks.ImageDownloadTask;
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
public class KampenDetailFragment extends Fragment {

    @Bind(R.id.kampDetail_naam)
    public TextView txtNaam;
    @Bind(R.id.kampDetail_price)
    public TextView txtPrice;
    @Bind(R.id.kampDetail_datum)
    public TextView txtDatum;
    @Bind(R.id.kampDetail_locatie)
    public TextView txtLocatie;
    @Bind(R.id.kampDetail_image)
    public ImageView imgSfeerfoto;
    @Bind(R.id.kampDetail_omschrijving)
    public TextView txtOmschrijving;
    @Bind(R.id.kampDetail_vervoer)
    public TextView txtVervoer;
    @Bind(R.id.kampDetail_leeftijden)
    public TextView txtLeeftijden;
    @Bind(R.id.kampDetail_max_deelnemers)
    public TextView txtMaxDeelnemers;
    @Bind(R.id.kampDetail_contact)
    public TextView txtContact;
    @Bind(R.id.kampDetail_recyclerView_aanwezig)
    public RecyclerView mRecyclerViewAanwezigen;
    @Bind(R.id.kampDetail_recyclerView_medewerkers)
    public RecyclerView mRecyclerViewMedewerkers;

    RecyclerView.LayoutManager mLayoutManagerAanwezigen;
    RecyclerView.LayoutManager mLayoutManagerMedewerkers;
    UserMugshotAdapter mAdapterAanwezigen;
    UserMugshotAdapter mAdapterMedewerkers;

    private Camp camp;

    private List<User> aanwezigen;
    private List<User> medewerkers;

    private MainActivity activity;

    private MaterialDialog dialogProgress;
    private MaterialDialog dialogAddContributor;
    private MaterialDialog dialogAddAttendant;
    private MaterialDialog dialogContributorAlreadyInList;
    private MaterialDialog dialogAttendantAlreadyInList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kampen_detail, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        activity = (MainActivity) getActivity();

        activity.showActionBarAddPerson();

        Intent i = getActivity().getIntent();
        camp =(Camp) i.getSerializableExtra("kamp");

        mLayoutManagerAanwezigen = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerMedewerkers = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerViewAanwezigen.setLayoutManager(mLayoutManagerAanwezigen);
        mRecyclerViewMedewerkers.setLayoutManager(mLayoutManagerMedewerkers);

        mAdapterAanwezigen = new UserMugshotAdapter(this.getActivity());
        mAdapterMedewerkers = new UserMugshotAdapter(this.getActivity());

        mRecyclerViewAanwezigen.setAdapter(mAdapterAanwezigen);
        mRecyclerViewMedewerkers.setAdapter(mAdapterMedewerkers);

        medewerkers = camp.getMedewerkers();
        aanwezigen = camp.getAanwezigen();

        mAdapterMedewerkers.setUsers(medewerkers);
        mAdapterAanwezigen.setUsers(aanwezigen);

        setDetails();

        return view;
    }

    public void setDetails(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        txtNaam.setText(camp.getNaam());

        imgSfeerfoto.setImageDrawable(KampenDetailFragment.this.getActivity().getResources().getDrawable(R.drawable.offline_image));
        new ImageDownloadTask(imgSfeerfoto, getActivity()).execute(camp.getSfeerfoto());

        txtPrice.setText(camp.getPrijs() + " euro");

        Date begin = SharedHelper.parseDateStringToDate(camp.getStartDatum());
        Date end = SharedHelper.parseDateStringToDate(camp.getEindDatum());

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if(SharedHelper.compareDates(begin, end)){
            String beginHour = beginCalendar.get(Calendar.HOUR_OF_DAY) + ":" + beginCalendar.get(Calendar.MINUTE);
            String endHour = endCalendar.get(Calendar.HOUR_OF_DAY) + ":" + endCalendar.get(Calendar.MINUTE);

            txtDatum.setText(dateFormat.format(begin));
        }
        else{
            String beginDate = dateFormat.format(begin);
            String endDate = dateFormat.format(end);

            txtDatum.setText("Van " + beginDate + " tot " + endDate);
        }

        Adres adres = camp.getAdres();
        txtLocatie.setText(adres.getStraat() + " " + adres.getHuisnummer() + adres.getBus() + ", " + adres.getPostcode() + " " + adres.getGemeente());

        txtOmschrijving.setText(camp.getOmschrijving());

        txtVervoer.setText(camp.getVervoer());

        txtLeeftijden.setText(camp.getMinLeeftijd() + " tot " + camp.getMaxLeeftijd() + " jaar");

        txtContact.setText(camp.getContact());

        txtMaxDeelnemers.setText(camp.getMaxDeelnemers() + "");
    }

    private boolean isCurrentUserInContributorList(){
        boolean isInList = false;

        for(User u : medewerkers){
            if(u.getId().equals(activity.getCurrentUser().getId())){
                isInList = true;
            }
        }
        return isInList;
    }

    private boolean isCurrentUserInAttendantsList(){
        boolean isInList = false;

        for(User u : aanwezigen){
            if(u.getId().equals(activity.getCurrentUser().getId())){
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

    public void showAddContributorDialog(Context context){
        this.dialogAddContributor = new MaterialDialog.Builder(context)
                .content(R.string.dialog_add_user)
                .positiveText(R.string.dialog_yes)
                .negativeText(R.string.dialog_no)
                .onPositive((dialog, which) -> {
                    showProgressDialog(KampenDetailFragment.this.getActivity());
                    ApiHelper.addContributorToCamp(this.getActivity(), camp.getId(), activity.getCurrentUser().getEmail(), camp, activity.getCurrentUser()).subscribe(addContributorToCampObserver);
                })
                .show();
    }

    public void showAddAttendantDialog(Context context){
        this.dialogAddAttendant = new MaterialDialog.Builder(context)
                .content(R.string.dialog_add_user)
                .positiveText(R.string.dialog_yes)
                .negativeText(R.string.dialog_no)
                .onPositive((dialog, which) -> {
                    showProgressDialog(KampenDetailFragment.this.getActivity());
                    ApiHelper.addUserToCamp(this.getActivity(), camp.getId(), activity.getCurrentUser().getId(), camp.getNaam(), camp, activity.getCurrentUser()).subscribe(addUserToCampObserver);
                })
                .show();
    }

    public void showContributorAlreadyInListDialog(Context context){
        this.dialogContributorAlreadyInList = new MaterialDialog.Builder(context)
                .content(R.string.dialog_user_already_in_list)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    public void showAttendantAlreadyInListDialog(Context context){
        this.dialogAttendantAlreadyInList = new MaterialDialog.Builder(context)
                .content(R.string.dialog_user_already_in_list)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    public void addParticipant(){
        if(isCurrentUserInAttendantsList()){
            showAttendantAlreadyInListDialog(KampenDetailFragment.this.getActivity());
        }
        else{
            showAddAttendantDialog(KampenDetailFragment.this.getActivity());
        }
    }

    public void addContributor(){
        if(isCurrentUserInContributorList()){
            showContributorAlreadyInListDialog(KampenDetailFragment.this.getActivity());
        }
        else{
            showAddContributorDialog(KampenDetailFragment.this.getActivity());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_person:
                String role = activity.getCurrentUser().getRole();
                if(role.equals("lid")){
                    addParticipant();
                }else if(role.equals("monitor") || role.equals("beheerder")){
                    FragmentManager fragmentManager = KampenDetailFragment.this.getActivity().getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    AddContributorsListFragment addContributorsListFragment = new AddContributorsListFragment();
                    activity.getIntent().putExtra("CAMP", camp);
                    ft.replace(R.id.mainpage_container, addContributorsListFragment);
                    ft.addToBackStack("FRAGMENT");
                    ft.commit();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    Observer<Object> addContributorToCampObserver = new Observer<Object>(){
        @Override
        public void onCompleted()
        {
            if(KampenDetailFragment.this != null)
            {
                mAdapterMedewerkers.notifyDataSetChanged();
                Toast.makeText(KampenDetailFragment.this.getActivity(), "Medewerker toegevoegd!", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(KampenDetailFragment.this != null)
            {
                Toast.makeText(KampenDetailFragment.this.getActivity(), "Fout bij toevoegen van medewerker", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }
    };

    Observer<Object> addUserToCampObserver = new Observer<Object>(){
        @Override
        public void onCompleted()
        {
            if(KampenDetailFragment.this != null)
            {
                mAdapterAanwezigen.notifyDataSetChanged();
                Toast.makeText(KampenDetailFragment.this.getActivity(), "Aanwezige toegevoegd!", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(KampenDetailFragment.this != null)
            {
                Toast.makeText(KampenDetailFragment.this.getActivity(), "Fout bij toevoegen van aanwezige", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }
    };
}
