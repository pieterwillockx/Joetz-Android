package com.fabantowapi.joetz_android.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
<<<<<<< HEAD
import android.database.Cursor;
=======
import android.content.res.Configuration;
>>>>>>> feature/design
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
=======
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
>>>>>>> feature/design

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.database.UserTable;
import com.fabantowapi.joetz_android.fragments.ActivityListFragment;
import com.fabantowapi.joetz_android.fragments.ArtikelListFragment;
import com.fabantowapi.joetz_android.fragments.ForumFragment;
import com.fabantowapi.joetz_android.fragments.HistoriekListFragment;
import com.fabantowapi.joetz_android.fragments.KampenListFragment;
import com.fabantowapi.joetz_android.fragments.ProfielFragment;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.utils.Constants;
import com.fabantowapi.joetz_android.utils.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a_176_000 on 29-7-2016.
 */
public class MainActivity extends AppCompatActivity /*implements android.app.LoaderManager.LoaderCallbacks<Cursor>*/ {

    @Bind(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer)
    public NavigationView mLeftDrawer;

<<<<<<< HEAD
    private String email;
    private User currentUser;
=======
    ActionBarDrawerToggle mDrawerToggle;
>>>>>>> feature/design

    @Override
    public void onCreate(Bundle savedInstanceState){
        email = getIntent().getStringExtra("EMAIL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getUser();
        initializeDrawer();
        //this.getLoaderManager().initLoader(Constants.LOADER_USERS, null, this);
        navigate(ArtikelListFragment.class);
    }

    public void getUser(){
        ApiHelper.getUser(this, email).subscribe(this.getUserObserver);
    }

    public void initializeDrawer(){
        LinearLayout navHeader = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.drawer_view_header, null);
        navHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(ProfielFragment.class);
                mDrawerLayout.closeDrawers();
            }
        });

        // fill up header info here
        mLeftDrawer.addHeaderView(navHeader);
        mLeftDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                navigate(item);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        final ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.material_drawer_open, R.string.material_drawer_close){
                public void onDrawerClosed(View view){
                    supportInvalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView){
                    supportInvalidateOptionsMenu();
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }

        //mDrawerLayout.setScrimColor(Color.parseColor("#00ffffff"));
    }

    public void navigate(MenuItem item) {
        final int itemId = item.getItemId();
        Class fragmentClass = null;

        switch (itemId) {
            case R.id.nav_historiek:
                fragmentClass = HistoriekListFragment.class;
                break;
            case R.id.nav_forum:
                fragmentClass = ForumFragment.class;
                break;
            case R.id.nav_activiteiten:
                fragmentClass = ActivityListFragment.class;
                break;
            case R.id.nav_kampen:
                fragmentClass = KampenListFragment.class;
                break;
            case R.id.nav_home:
                fragmentClass = ArtikelListFragment.class;
                break;
            case R.id.nav_profiel :
                fragmentClass = ProfielFragment.class;
                break;
            default:
                fragmentClass = ArtikelListFragment.class;
                break;
        }
        navigate(fragmentClass);
    }

    public void navigate (Class fragmentClass){
        Fragment fragment = null;
        try {
            if (fragmentClass != null) {
                fragment = (Fragment) fragmentClass.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle args =new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragment.setArguments(args);
        ft.replace(R.id.mainpage_container, fragment);
        ft.commit();
    }

<<<<<<< HEAD
    //@Override
    //public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    //    switch (id) {
    //        case Constants.LOADER_USERS:
    //            Uri uri = UserContentProvider.CONTENT_URI;
    //            String selection = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
    //            String[] selectionArgs = new String[]{
    //                    this.email
    //            };
//
    //            return new CursorLoader(this, uri, null, selection, selectionArgs, "");
//
    //        default:
    //            return null;
    //    }
    //}
//
    //@Override
    //public void onLoadFinished(Loader<Cursor> loader, Cursor data){
    //    switch(loader.getId()){
    //        case Constants.LOADER_USERS:
    //            currentUser = User.constructFromCursor(data);
    //            LinearLayout navHeader = (LinearLayout) mLeftDrawer.getHeaderView(0);
    //            TextView navHeaderEmail = (TextView) navHeader.findViewById(R.id.nav_header_email);
    //            navHeaderEmail.setText(currentUser.getEmail());
    //            break;
    //    }
    //}
//
    //@Override
    //public void onLoaderReset(Loader<Cursor> loader){
//
    //}

    private Observer<Object> getUserObserver = new Observer<Object>(){
        @Override
        public void onCompleted(){
            Toast.makeText(getApplicationContext(), "User opgehaald!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(Throwable e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
=======
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
>>>>>>> feature/design
}
