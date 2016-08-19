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
import com.fabantowapi.joetz_android.adapters.UserListItemAdapter;
import com.fabantowapi.joetz_android.adapters.itemdecorations.VerticalSpaceItemDecoration;
import com.fabantowapi.joetz_android.model.api.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 10-8-2016.
 */
public class UserListFragment extends Fragment {

    @Bind(R.id.contributors_recycler_view)
    public RecyclerView mRecyclerView;

    private UserListItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<User> users;

    private MainActivity activity;

    private static final int VERTICAL_ITEM_SPACE = 10;
    private static final int PADDING_LEFT_RIGHT = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);
        ButterKnife.bind(this, view);

        activity = (MainActivity) getActivity();

        users = activity.getAllUsers();

        activity.hideActionBarMenu();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE, PADDING_LEFT_RIGHT));

        mAdapter = new UserListItemAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setUsers(users);

        return view;
    }
}
