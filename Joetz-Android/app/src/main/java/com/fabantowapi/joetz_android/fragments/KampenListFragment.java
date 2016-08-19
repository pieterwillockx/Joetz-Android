package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.adapters.itemdecorations.VerticalSpaceItemDecoration;
import com.fabantowapi.joetz_android.model.Kamp;
import com.fabantowapi.joetz_android.adapters.KampAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 2-8-2016.
 */
public class KampenListFragment extends Fragment {

    @Bind(R.id.kampen_recycler_view)
    public RecyclerView mRecyclerView;
    private KampAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainActivity mainActivity;

    private static final int VERTICAL_ITEM_SPACE = 10;
    private static final int PADDING_LEFT_RIGHT = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kampen_list, container, false);
        ButterKnife.bind(this, view);

        mainActivity = (MainActivity) getActivity();

        mainActivity.hideActionBarMenu();;

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE, PADDING_LEFT_RIGHT));

        mAdapter = new KampAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setCamps(mainActivity.getCamps());

        return view;
    }
}
