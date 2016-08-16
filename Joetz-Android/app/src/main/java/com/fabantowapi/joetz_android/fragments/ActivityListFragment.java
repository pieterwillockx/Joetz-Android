package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.adapters.itemdecorations.VerticalSpaceItemDecoration;
import com.fabantowapi.joetz_android.contentproviders.ActivityContentProvider;
import com.fabantowapi.joetz_android.contentproviders.ContactpersoonContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.database.ActiviteitTable;
import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.fabantowapi.joetz_android.database.UserTable;
import com.fabantowapi.joetz_android.model.Activiteit;
import com.fabantowapi.joetz_android.adapters.ActivityAdapter;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.Contactpersoon;
import com.fabantowapi.joetz_android.model.api.User;
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
public class ActivityListFragment extends Fragment implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.activity_list_currentDate)
    TextView currentDate;
    @Bind(R.id.activity_recycler_view)
    public RecyclerView mRecyclerView;
    private ActivityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Activity> activityList;

    private static final int VERTICAL_ITEM_SPACE = 10;
    private static final int PADDING_LEFT_RIGHT = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);
        ButterKnife.bind(this, view);
        initActiviteitenLijst();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE, PADDING_LEFT_RIGHT));

        mAdapter = new ActivityAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        this.getActivity().getLoaderManager().initLoader(Constants.LOADER_ACTIVITIES, null, this);
        return view;

    }

    public List<Activiteit> testData(){

        //todo haal data op uit api
        List<Activiteit> activiteiten = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        currentDate.setText(df.format(calendar.getTime()));
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case Constants.LOADER_ACTIVITIES:
                Uri uri1 = ActivityContentProvider.CONTENT_URI;

                return new CursorLoader(this.getActivity(), uri1, null, null, null, "");

            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        switch(loader.getId()){
            case Constants.LOADER_ACTIVITIES:
                //log cursor contents
                Log.d("ActivityListFragment", "Printing cursor contents...");
                Log.d("ActivityListFragment", DatabaseUtils.dumpCursorToString(data));

                activityList = Activity.constructListFromCursor(data);
                this.mAdapter.setActivities(activityList);

                //currentUser = User.constructFromCursor(data);
                //LinearLayout navHeader = (LinearLayout) mLeftDrawer.getHeaderView(0);
//
                //TextView navHeaderEmail = (TextView) navHeader.findViewById(R.id.nav_header_email);
                //TextView navHeaderFullName = (TextView) navHeader.findViewById(R.id.nav_header_fullname);
//
                //navHeaderEmail.setText(currentUser.getEmail());
                //navHeaderFullName.setText(currentUser.getFirstname() + " " + currentUser.getLastname());
//
//
                //// init next loader if user has contactpersons
                //if(currentUser.getContactpersoon1Email() != null) {
                //    Bundle args1 = new Bundle();
                //    args1.putString("CONTACTPERSOON1_EMAIL", currentUser.getContactpersoon1Email());
                //    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CONTACTPERSOON1, args1, MainActivity.this);
                //}
                //else if(currentUser.getContactpersoon2Email() != null){
                //    Bundle args1 = new Bundle();
                //    args1.putString("CONTACTPERSOON2_EMAIL", currentUser.getContactpersoon2Email());
                //    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CONTACTPERSOON2, args1, MainActivity.this);
                //}

                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){

    }
}
