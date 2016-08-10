package com.fabantowapi.joetz_android.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.model.Activiteit;
import com.fabantowapi.joetz_android.model.ActivityAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 10-8-2016.
 */
public class ActivityListFragment extends Fragment{

    @Bind(R.id.activity_list_currentDate)
    TextView currentDate;
    @Bind(R.id.activity_recycler_view)
    public RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);
        ButterKnife.bind(this, view);
        initActiviteitenLijst();


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ActivityAdapter(testData().toArray(new Activiteit[testData().size()]),getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return view;


    }

    public List<Activiteit> testData(){
        List<Activiteit> activiteiten = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        currentDate.setText(df.format(calendar.getTime()));
        Date ter= new Date(31,5,1994);
        Date niew = new Date(1994,5,31);
        System.out.println("datum" +niew.toString());
        Date test = calendar.getTime();
        activiteiten.add(new Activiteit("test activiteit",test,test,"belgie"));

        return activiteiten;
    }
    public void initActiviteitenLijst(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        currentDate.setText(df.format(calendar.getTime()));

    }

}
