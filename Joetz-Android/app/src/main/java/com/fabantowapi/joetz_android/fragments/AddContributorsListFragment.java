package com.fabantowapi.joetz_android.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.adapters.AddContributorAdapter;
import com.fabantowapi.joetz_android.adapters.itemdecorations.VerticalSpaceItemDecoration;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.model.Camp;
import com.fabantowapi.joetz_android.model.User;
import com.fabantowapi.joetz_android.utils.Observer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pieter on 20-8-2016.
 */
public class AddContributorsListFragment extends Fragment {

    @Bind(R.id.contributors_recycler_view)
    public RecyclerView mRecyclerView;

    private AddContributorAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<User> users;
    private Camp currentCamp;

    private MainActivity activity;

    private MaterialDialog dialogProgress;

    private static final int VERTICAL_ITEM_SPACE = 10;
    private static final int PADDING_LEFT_RIGHT = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contributor_list, container, false);
        ButterKnife.bind(this, view);

        activity = (MainActivity) getActivity();

        users = activity.getAllContributors();

        Intent i = getActivity().getIntent();
        currentCamp = (Camp) i.getSerializableExtra("CAMP");

        setHasOptionsMenu(true);

        activity.showActionBarAddContributors();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE, PADDING_LEFT_RIGHT));

        mAdapter = new AddContributorAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setUsers(users);

        return view;
    }

    public void showProgressDialog(Context context){
        this.dialogProgress = new MaterialDialog.Builder(context)
                .content(R.string.add_contributor_progress)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_contributors:
                if(mAdapter.getCheckedUsers().size() == 0){
                    Toast.makeText(AddContributorsListFragment.this.getActivity(), "Vink tenminste één medewerker aan.", Toast.LENGTH_SHORT).show();
                }
                else {
                    showProgressDialog(AddContributorsListFragment.this.getActivity());
                    List<User> checkedUsers = mAdapter.getCheckedUsers();
                    for (int i = 0; i < checkedUsers.size(); i++) {
                        if (i < checkedUsers.size() - 1) {
                            ApiHelper.addContributorToCamp(AddContributorsListFragment.this.getActivity(), currentCamp.getId(), checkedUsers.get(i).getEmail(), currentCamp, checkedUsers.get(i));
                        } else {
                            ApiHelper.addContributorToCamp(AddContributorsListFragment.this.getActivity(), currentCamp.getId(), checkedUsers.get(i).getEmail(), currentCamp, checkedUsers.get(i)).subscribe(addContributorToCampObserver);
                        }
                    }
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
            if(AddContributorsListFragment.this != null)
            {
                dialogProgress.dismiss();
                activity.getFragmentManager().popBackStack();
                activity.getIntent().putExtra("kamp", currentCamp);

                activity.navigate(KampenDetailFragment.class, false);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(AddContributorsListFragment.this != null)
            {
                Toast.makeText(AddContributorsListFragment.this.getActivity(), "Fout bij toevoegen van medewerker", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }
    };
}
