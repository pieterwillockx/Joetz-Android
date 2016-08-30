package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.adapters.itemdecorations.VerticalSpaceItemDecoration;
import com.fabantowapi.joetz_android.contentproviders.ActivityContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserActivityContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.model.Activiteit;
import com.fabantowapi.joetz_android.adapters.ActivityAdapter;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.GetActivityResponse;
import com.fabantowapi.joetz_android.model.api.UserActivity;
import com.fabantowapi.joetz_android.utils.Constants;

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
public class ActivityListFragment extends Fragment {

    @Bind(R.id.activity_recycler_view)
    public RecyclerView mRecyclerView;

    private ActivityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainActivity mainActivity;

    List<Activity> activities;

    private static final int VERTICAL_ITEM_SPACE = 10;
    private static final int PADDING_LEFT_RIGHT = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);
        ButterKnife.bind(this, view);

        mainActivity = (MainActivity) getActivity();

        mainActivity.hideActionBarMenu();

        if(mainActivity.userHasAdminPermissions()){
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Click action
                    mainActivity.navigate(CreateActivityFragment.class, true);
                }
            });
        }

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE, PADDING_LEFT_RIGHT));

        mAdapter = new ActivityAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setActivities(mainActivity.getActivities());

        return view;

    }
}
