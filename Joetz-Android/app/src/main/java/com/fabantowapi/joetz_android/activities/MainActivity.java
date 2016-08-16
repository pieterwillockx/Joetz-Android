package com.fabantowapi.joetz_android.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.content.res.Configuration;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.contentproviders.ContactpersoonContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.fabantowapi.joetz_android.database.UserTable;
import com.fabantowapi.joetz_android.fragments.ActivityListFragment;
import com.fabantowapi.joetz_android.fragments.ArtikelListFragment;
import com.fabantowapi.joetz_android.fragments.ForumFragment;
import com.fabantowapi.joetz_android.fragments.HistoriekListFragment;
import com.fabantowapi.joetz_android.fragments.KampenListFragment;
import com.fabantowapi.joetz_android.fragments.ProfielFragment;
import com.fabantowapi.joetz_android.model.api.Contactpersoon;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.utils.Constants;
import com.fabantowapi.joetz_android.utils.Observer;
import com.fabantowapi.joetz_android.utils.PreferencesHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a_176_000 on 29-7-2016.
 */
public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer)
    public NavigationView mLeftDrawer;

    private String email;
    private User currentUser;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState){
        email = getIntent().getStringExtra("EMAIL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeDrawer();
        this.getLoaderManager().initLoader(Constants.LOADER_USERS, null, this);
        navigate(ArtikelListFragment.class);
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

        mDrawerLayout.setScrimColor(Color.parseColor("#00ffffff"));
    }

    public User getCurrentUser(){
        return currentUser;
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
            case R.id.nav_logout :
                showLogoutConfirmDialog(MainActivity.this);
                break;
            default:
                fragmentClass = ArtikelListFragment.class;
                break;
        }
        if(itemId != R.id.nav_logout){
            navigate(fragmentClass);
        }
    }

    private void showLogoutConfirmDialog(Context context)
    {
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_logout)
                .content("Weet u zeker dat u wil uitloggen?")
                .positiveText(R.string.dialog_yes)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        String refreshToken = PreferencesHelper.getRefreshToken(MainActivity.this);
                        ApiHelper.logOut(MainActivity.this, refreshToken).subscribe(MainActivity.this.logoutObserver);
                    }
                })
                .negativeText(R.string.dialog_no)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
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

    private Observer<Object> logoutObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(MainActivity.this != null)
            {
                MainActivity.this.finish();
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(MainActivity.this != null)
            {
                Toast.makeText(MainActivity.this, "Fout bij het uitloggen", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case Constants.LOADER_USERS:
                Uri uri1 = UserContentProvider.CONTENT_URI;
                String selection1 = UserTable.TABLE_NAME + "." + UserTable.COLUMN_EMAIL + " = ?";
                String[] selectionArgs1 = new String[]{
                        this.email
                };

                return new CursorLoader(this, uri1, null, selection1, selectionArgs1, "");

            case Constants.LOADER_CONTACTPERSOON1:
                String contactpersoon1Email = args.getString("CONTACTPERSOON1_EMAIL");
                Uri uri2 = ContactpersoonContentProvider.CONTENT_URI;
                String selection2 = ContactpersoonTable.TABLE_NAME + "." + ContactpersoonTable.COLUMN_EMAIL + " = ?";
                String[] selectionArgs2 = new String[]{
                        contactpersoon1Email
                };

                return new CursorLoader(this, uri2, null, selection2, selectionArgs2, "");

            case Constants.LOADER_CONTACTPERSOON2:
                String contactpersoon2Email = args.getString("CONTACTPERSOON2_EMAIL");
                Uri uri3 = ContactpersoonContentProvider.CONTENT_URI;
                String selection3 = ContactpersoonTable.TABLE_NAME + "." + ContactpersoonTable.COLUMN_EMAIL + " = ?";
                String[] selectionArgs3 = new String[]{
                        contactpersoon2Email
                };

                return new CursorLoader(this, uri3, null, selection3, selectionArgs3, "");

            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        switch(loader.getId()){
            case Constants.LOADER_USERS:
                //log cursor contents
                Log.d("MainActivity", "Printing cursor contents...");
                Log.d("MainActivity", DatabaseUtils.dumpCursorToString(data));

                currentUser = User.constructFromCursor(data);
                LinearLayout navHeader = (LinearLayout) mLeftDrawer.getHeaderView(0);

                TextView navHeaderEmail = (TextView) navHeader.findViewById(R.id.nav_header_email);
                TextView navHeaderFullName = (TextView) navHeader.findViewById(R.id.nav_header_fullname);

                navHeaderEmail.setText(currentUser.getEmail());
                navHeaderFullName.setText(currentUser.getFirstname() + " " + currentUser.getLastname());


                // init next loader if user has contactpersons
                if(currentUser.getContactpersoon1Email() != null) {
                    Bundle args1 = new Bundle();
                    args1.putString("CONTACTPERSOON1_EMAIL", currentUser.getContactpersoon1Email());
                    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CONTACTPERSOON1, args1, MainActivity.this);
                }
                else if(currentUser.getContactpersoon2Email() != null){
                    Bundle args1 = new Bundle();
                    args1.putString("CONTACTPERSOON2_EMAIL", currentUser.getContactpersoon2Email());
                    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CONTACTPERSOON2, args1, MainActivity.this);
                }

                break;

            case Constants.LOADER_CONTACTPERSOON1:
                //log cursor contents
                Log.d("MainActivity", "Printing cursor contents...");
                Log.d("MainActivity", DatabaseUtils.dumpCursorToString(data));

                Contactpersoon contactpersoon1 = Contactpersoon.constructFromCursor(data);
                currentUser.setContactpersoon1(contactpersoon1);

                // init next loader if user has contactpersoon2
                if(currentUser.getContactpersoon2Email() != null) {
                    Bundle args2 = new Bundle();
                    args2.putString("CONTACTPERSOON2_EMAIL", currentUser.getContactpersoon2Email());
                    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CONTACTPERSOON2, args2, MainActivity.this);
                }

                break;

            case Constants.LOADER_CONTACTPERSOON2:
                //log cursor contents
                Log.d("MainActivity", "Printing cursor contents...");
                Log.d("MainActivity", DatabaseUtils.dumpCursorToString(data));

                Contactpersoon contactpersoon2 = Contactpersoon.constructFromCursor(data);
                currentUser.setContactpersoon2(contactpersoon2);

                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){

    }

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
}
