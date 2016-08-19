package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.adapters.UserAdapter;
import com.fabantowapi.joetz_android.model.api.Adres;
import com.fabantowapi.joetz_android.model.api.Camp;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.tasks.ImageDownloadTask;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    UserAdapter mAdapterAanwezigen;
    UserAdapter mAdapterMedewerkers;

    private Camp camp;

    private List<User> aanwezigen;
    private List<User> medewerkers;

    private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kampen_detail, container, false);
        ButterKnife.bind(this, view);

        activity = (MainActivity) getActivity();

        Intent i = getActivity().getIntent();
        camp =(Camp) i.getSerializableExtra("kamp");

        mLayoutManagerAanwezigen = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerMedewerkers = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerViewAanwezigen.setLayoutManager(mLayoutManagerAanwezigen);
        mRecyclerViewMedewerkers.setLayoutManager(mLayoutManagerMedewerkers);

        mAdapterAanwezigen = new UserAdapter(this.getActivity());
        mAdapterMedewerkers = new UserAdapter(this.getActivity());

        mRecyclerViewAanwezigen.setAdapter(mAdapterAanwezigen);
        mRecyclerViewMedewerkers.setAdapter(mAdapterMedewerkers);

        medewerkers = camp.getMedewerkers();
        //aanwezigen = camp.getAanwezigen();

        mAdapterMedewerkers.setUsers(medewerkers);
        //mAdapterAanwezigen.setUsers(aanwezigen);

        setDetails();

        return view;
    }

    public void setDetails(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        txtNaam.setText(camp.getNaam());

        // image
        imgSfeerfoto.setImageDrawable(KampenDetailFragment.this.getActivity().getResources().getDrawable(R.drawable.offline_image));
        new ImageDownloadTask(imgSfeerfoto).execute(camp.getSfeerfoto());

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
        txtLocatie.setText(adres.getStraat() + " " + adres.getHuisnummer() + ", " + adres.getPostcode() + " " + adres.getGemeente());

        txtOmschrijving.setText(camp.getOmschrijving());

        txtVervoer.setText(camp.getVervoer());

        txtLeeftijden.setText(camp.getMinLeeftijd() + " tot " + camp.getMaxLeeftijd() + " jaar");

        txtContact.setText(camp.getContact());

        // change it up
        txtMaxDeelnemers.setText(camp.getMaxDeelnemers() + "");
    }

    @OnClick(R.id.kampenDetail_inschrvijven)
    public void schrijfIn(){
        // todo schrijf in voor kamp logica

    }
}
