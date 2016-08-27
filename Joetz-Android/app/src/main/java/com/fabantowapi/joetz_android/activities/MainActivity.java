package com.fabantowapi.joetz_android.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.contentproviders.ActivityContentProvider;
import com.fabantowapi.joetz_android.contentproviders.ArticleContentProvider;
import com.fabantowapi.joetz_android.contentproviders.CampContentProvider;
import com.fabantowapi.joetz_android.contentproviders.ContactpersoonContentProvider;
import com.fabantowapi.joetz_android.contentproviders.ContributorCampContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserActivityContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserCampContentProvider;
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.fabantowapi.joetz_android.database.UserTable;
import com.fabantowapi.joetz_android.fragments.ActivityListFragment;
import com.fabantowapi.joetz_android.fragments.ArtikelListFragment;
import com.fabantowapi.joetz_android.fragments.EditContactPersonFragment;
import com.fabantowapi.joetz_android.fragments.ContributorsListFragment;
import com.fabantowapi.joetz_android.fragments.HistoriekListFragment;
import com.fabantowapi.joetz_android.fragments.KampenListFragment;
import com.fabantowapi.joetz_android.fragments.ProfielFragment;
import com.fabantowapi.joetz_android.fragments.UserListFragment;
import com.fabantowapi.joetz_android.model.Artikel;
import com.fabantowapi.joetz_android.model.api.Activity;
import com.fabantowapi.joetz_android.model.api.Camp;
import com.fabantowapi.joetz_android.model.api.Contactpersoon;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.model.api.UserActivity;
import com.fabantowapi.joetz_android.model.api.ContributorCamp;
import com.fabantowapi.joetz_android.model.api.UserCamp;
import com.fabantowapi.joetz_android.utils.Constants;
import com.fabantowapi.joetz_android.utils.Observer;
import com.fabantowapi.joetz_android.utils.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

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
    private List<Activity> activities;
    private List<UserActivity> userActivities;
    private List<User> allUsers;
    private List<Camp> camps;
    private List<ContributorCamp> contributorCamps;
    private List<UserCamp> userCamps;

    ActionBarDrawerToggle mDrawerToggle;
    private MaterialDialog dialogProgress;

    private int mActionBarState = Constants.ACTIONBAR_HIDE_MENU;

    @Override
    public void onCreate(Bundle savedInstanceState){
        email = getIntent().getStringExtra("EMAIL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeDrawer();
        this.getLoaderManager().initLoader(Constants.LOADER_USERS, null, this);
        navigate(ArtikelListFragment.class, false);
    }

    public void initializeDrawer(){
        LinearLayout navHeader = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.drawer_view_header, null);
        navHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(ProfielFragment.class, true);
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
    }

    public void handleUserPermissions(){
        // if user is not an admin, hide user lists
        if(!userHasAdminPermissions()){
            Menu menu = mLeftDrawer.getMenu();
            menu.findItem(R.id.nav_contibutors).setVisible(false);
            menu.findItem(R.id.nav_all_users).setVisible(false);
        }
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public List<User> getAllUsers() { return allUsers; }

    public List<User> getAllContributors() {
        List<User> contributors = new ArrayList<>();

        for(User user : allUsers){
            if(user.getRole().equals("monitor") || user.getRole().equals("beheerder")){
                contributors.add(user);
            }
        }

        return contributors;
    }

    public List<Activity> getActivities() { return activities; }

    public List<Camp> getCamps() { return camps; }

    public List<Camp> getSubscribedCamps(){
        User currentUser = getCurrentUser();
        List<Camp> result = new ArrayList<>();
        for(Camp c : getCamps()){
            for(User u: c.getAanwezigen()){
                System.out.println("Zoeken door users");
                System.out.println("User id " + u.getId() + " current userId "+ currentUser.getId());
                if(u.getId().equals(currentUser.getId())){
                    System.out.println("In check");
                    result.add(c);
                }
            }
        }
        return  result;
    }

    public boolean userHasAdminPermissions(){ return currentUser.getRole().equals("beheerder"); }

    public void navigate(MenuItem item) {
        final int itemId = item.getItemId();
        Class fragmentClass = null;

        switch (itemId) {
            case R.id.nav_historiek:
                fragmentClass = HistoriekListFragment.class;
                break;
            case R.id.nav_contibutors:
                fragmentClass = ContributorsListFragment.class;
                break;
            case R.id.nav_all_users:
                fragmentClass = UserListFragment.class;
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
            navigate(fragmentClass, true);
        }
    }

    public void navigate (Class fragmentClass, boolean addToBackStack){
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
        if(addToBackStack){
            ft.addToBackStack("FRAGMENT");
        }
        ft.commit();
        Log.d("BackStack", fragmentManager.getBackStackEntryCount() + "");
    }

    public void navigateToEditContactPerson(int contactpersonId, boolean addToBackStack){
        Fragment fragment = new EditContactPersonFragment();
        getIntent().putExtra("CONTACTPERSON_ID", contactpersonId);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.mainpage_container, fragment);
        if(addToBackStack){
            ft.addToBackStack("FRAGMENT");
        }
        ft.commit();
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

    private void showProgressDialog(Context context)
    {
        this.dialogProgress = new MaterialDialog.Builder(context)
                .content(R.string.get_data_progress)
                .progress(true, 0)
                .cancelable(false)
                .show();

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

            case Constants.LOADER_ACTIVITIES:
                Uri uri4 = ActivityContentProvider.CONTENT_URI;

                return new CursorLoader(this, uri4, null, null, null, "");

            case Constants.LOADER_USER_ACTIVITIES:
                Uri uri5 = UserActivityContentProvider.CONTENT_URI;

                return new CursorLoader(this, uri5, null, null, null, "");

            case Constants.LOADER_ALL_USERS:
                Uri uri6 = UserContentProvider.CONTENT_URI;

                return new CursorLoader(this, uri6, null, null, null, "");

            case Constants.LOADER_CAMPS:
                Uri uri7 = CampContentProvider.CONTENT_URI;

                return new CursorLoader(this, uri7, null, null, null, "");

            case Constants.LOADER_CONTRIBUTOR_CAMPS:
                Uri uri8 = ContributorCampContentProvider.CONTENT_URI;

                return new CursorLoader(this, uri8, null, null, null, "");

            case Constants.LOADER_USER_CAMPS:
                Uri uri9 = UserCampContentProvider.CONTENT_URI;

                return new CursorLoader(this, uri9, null, null, null, "");

            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        switch(loader.getId()){
            case Constants.LOADER_USERS:
                currentUser = User.constructFromCursor(data);
                LinearLayout navHeader = (LinearLayout) mLeftDrawer.getHeaderView(0);

                TextView navHeaderEmail = (TextView) navHeader.findViewById(R.id.nav_header_email);
                TextView navHeaderFullName = (TextView) navHeader.findViewById(R.id.nav_header_fullname);

                navHeaderEmail.setText(currentUser.getEmail());
                navHeaderFullName.setText(currentUser.getFirstname() + " " + currentUser.getLastname());

                // hide some drawer items if user is not admin
                handleUserPermissions();

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
                else{
                    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_ACTIVITIES, null, MainActivity.this);
                }

                break;

            case Constants.LOADER_CONTACTPERSOON1:
                Contactpersoon contactpersoon1 = Contactpersoon.constructFromCursor(data);
                currentUser.setContactpersoon1(contactpersoon1);

                // init next loader if user has contactpersoon2
                if(currentUser.getContactpersoon2Email() != null) {
                    Bundle args2 = new Bundle();
                    args2.putString("CONTACTPERSOON2_EMAIL", currentUser.getContactpersoon2Email());
                    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CONTACTPERSOON2, args2, MainActivity.this);
                }
                else{
                    MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_ACTIVITIES, null, MainActivity.this);
                }

                break;

            case Constants.LOADER_CONTACTPERSOON2:
                Contactpersoon contactpersoon2 = Contactpersoon.constructFromCursor(data);
                currentUser.setContactpersoon2(contactpersoon2);

                MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_ACTIVITIES, null, MainActivity.this);

                break;

            case Constants.LOADER_ACTIVITIES:
                activities = Activity.constructListFromCursor(data);

                // init next loader
                MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_USER_ACTIVITIES, null, MainActivity.this);

                break;

            case Constants.LOADER_USER_ACTIVITIES:
                userActivities = UserActivity.constructListFromCursor(data);

                //init next loader
                MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_ALL_USERS, null, MainActivity.this);

                break;

            case Constants.LOADER_ALL_USERS:
                allUsers = User.constructListFromCursor(data);

                MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CAMPS, null, MainActivity.this);

                break;

            case Constants.LOADER_CAMPS:
                camps = Camp.constructListFromCursor(data);

                // init next loader
                MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_CONTRIBUTOR_CAMPS, null, MainActivity.this);

                break;

            case Constants.LOADER_CONTRIBUTOR_CAMPS:
                contributorCamps = ContributorCamp.constructListFromCursor(data);

                // init next loader
                MainActivity.this.getLoaderManager().initLoader(Constants.LOADER_USER_CAMPS, null, MainActivity.this);

                break;

            case Constants.LOADER_USER_CAMPS:
                userCamps = UserCamp.constructListFromCursor(data);

                // last load, assign users to activities and camps, contributors to camps
                assignUsersToActivities();
                assignContributorsToCamps();
                assignUsersToCamps();

                break;

            default:
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){

    }

    private void assignUsersToActivities(){
        for(UserActivity ua : userActivities){
            String activityId = ua.getActivityId();
            String userId = ua.getUserId();

            User user = findUser(userId);
            Activity activity = findActivity(activityId);

            activity.addAanwezige(user);
        }
    }

    private void assignContributorsToCamps(){
        for(ContributorCamp uc : contributorCamps){
            String campId = uc.getCampId();
            String userId = uc.getUserId();

            User user = findUser(userId);
            Camp camp = findCamp(campId);

            camp.addMedewerker(user);
        }
    }

    private void assignUsersToCamps(){
        for(UserCamp uc : userCamps){
            String campId = uc.getCampId();
            String userId = uc.getUserId();

            User user = findUser(userId);
            Camp camp = findCamp(campId);

            camp.addAanwezige(user);
        }
    }

    private User findUser(String userId){
        User user = null;

        for(int i = 0; i < allUsers.size(); i++){
            if(allUsers.get(i).getId().equals(userId)){
                user = allUsers.get(i);
            }
        }

        return user;
    }

    private Activity findActivity(String activityId){
        Activity activity = null;

        for(int i = 0; i < activities.size(); i++){
            if(activities.get(i).getId().equals(activityId)){
                activity = activities.get(i);
            }
        }

        return activity;
    }

    private Camp findCamp(String campId){
        Camp camp = null;

        for(int i = 0; i < camps.size(); i++){
            if(camps.get(i).getId().equals(campId)){
                camp = camps.get(i);
            }
        }

        return camp;
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

    public void showActionBarAddPerson(){
        this.mActionBarState = Constants.ACTIONBAR_SHOW_ADD_PERSON;
        invalidateOptionsMenu();
    }

    public void showActionBarEditPerson(){
        this.mActionBarState = Constants.ACTIONBAR_SHOW_EDIT_PERSON;
        invalidateOptionsMenu();
    }

    public void showActionBarAddContributors(){
        this.mActionBarState = Constants.ACTIONBAR_SHOW_ADD_CONTRIBUTORS;
        invalidateOptionsMenu();
    }

    public void hideActionBarMenu(){
        this.mActionBarState = Constants.ACTIONBAR_HIDE_MENU;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);

        for(int i = 0; i < menu.size(); i++){
            menu.getItem(i).setVisible(false);
        }

        if(mActionBarState == Constants.ACTIONBAR_SHOW_EDIT_PERSON){
            menu.findItem(R.id.action_edit).setVisible(true);
        }
        if(mActionBarState == Constants.ACTIONBAR_SHOW_ADD_PERSON){
            menu.findItem(R.id.action_add_person).setVisible(true);
        }
        if(mActionBarState == Constants.ACTIONBAR_SHOW_ADD_CONTRIBUTORS){
            menu.findItem(R.id.action_add_contributors).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
