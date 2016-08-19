package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 10-8-2016.
 */
public class ForumFragment extends Fragment {

    private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        activity = (MainActivity) getActivity();

        activity.hideActionBarMenu();;

        ButterKnife.bind(this, view);

        return view;
    }
}
