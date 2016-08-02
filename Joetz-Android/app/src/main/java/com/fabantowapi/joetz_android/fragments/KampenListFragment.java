package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.model.Kamp;
import com.fabantowapi.joetz_android.model.KampAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 2-8-2016.
 */
public class KampenListFragment extends Fragment {

    @Bind(R.id.my_recycler_view)
    public RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kampen_list, container, false);
        ButterKnife.bind(this, view);

       // mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
       mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new KampAdapter(testData().toArray(new Kamp[testData().size()]),getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public List<Kamp> testData(){
       List<Kamp> testData = new ArrayList<>();
        testData.add(new Kamp("Smurf's up","test omschrijving",null,null,4,4,"auto","goede",20.0,30.0,5,10,10,5,10,"mij",null,null,null));
        testData.add(new Kamp("Boekenwurmen En Speelvogels","test omschrijving",null,null,4,4,"auto","goede",20.0,30.0,5,10,10,5,10,"mij",null,null,null));
        testData.add(new Kamp("Sierkus Hatsjoe","test omschrijving",null,null,4,4,"auto","goede",20.0,30.0,5,10,10,5,10,"mij",null,null,null));
        return testData;
    }
}
