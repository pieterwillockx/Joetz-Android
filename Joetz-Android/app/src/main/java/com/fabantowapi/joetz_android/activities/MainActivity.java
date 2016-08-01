package com.fabantowapi.joetz_android.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.ArtikelDetailFragment;
import com.fabantowapi.joetz_android.fragments.ArtikelListFragment;
import com.fabantowapi.joetz_android.fragments.LoginFragment;
import com.fabantowapi.joetz_android.model.Artikel;
import com.fabantowapi.joetz_android.model.ArtikelAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a_176_000 on 29-7-2016.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer)
    public NavigationView mLeftDrawer;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       initializeDrawer();
       laadArtikels();



    }
    public void laadArtikels(){

        Bundle args =new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ArtikelListFragment artikelDetailFragment = new ArtikelListFragment();
        artikelDetailFragment.setArguments(args);
        ft.replace(R.id.mainpage_container, artikelDetailFragment);
        ft.commit();
    }

    public void initializeDrawer(){
        // set on item click listener
        mLeftDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                navigate(item);
                return true;
            }
        });

        // fill in nav drawer header with current sellers username
        View headerLayout = mLeftDrawer.getHeaderView(0);
       // TextView headerLayoutUsernameTextView = (TextView) headerLayout.findViewById(R.id.nav_header_username);
       // headerLayoutUsernameTextView.setText(currentUser.getDisplayName());

        // set scrim color to transparent
        mDrawerLayout.setScrimColor(Color.parseColor("#00ffffff"));
    }
    public void navigate(MenuItem item) {
        final int itemId = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        switch (itemId) {
            case R.id.nav_historiek:
                System.out.println("TeST");
                fragmentClass = LoginFragment.class;
                break;
            case R.id.nav_forum:

                break;
            case R.id.nav_activiteiten:
                break;
            case R.id.nav_kampen:
                break;
            default:
                fragmentClass = MainActivity.class;
        }
        try {
            if (fragmentClass != null) {
                fragment = (Fragment) fragmentClass.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       // FragmentManager fm = getFragmentManager();
        //FragmentTransaction ft = fm.beginTransaction();
        //ft.replace(R.id.fragment_container, fragment);
        //ft.addToBackStack(Integer.toString(itemId));
        //ft.commit();
        //mDrawerLayout.closeDrawer(Gravity.LEFT);
    }







}
