package com.fabantowapi.joetz_android.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.ArtikelListFragment;
import com.fabantowapi.joetz_android.fragments.HistoriekListFragment;
import com.fabantowapi.joetz_android.fragments.KampenListFragment;
import com.fabantowapi.joetz_android.fragments.LoginFragment;

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
        navigate(ArtikelListFragment.class);

    }

    public void initializeDrawer(){
        mLeftDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                navigate(item);
                return true;
            }
        });

        mDrawerLayout.setScrimColor(Color.parseColor("#00ffffff"));
    }

    public void navigate(MenuItem item) {
        final int itemId = item.getItemId();
        Class fragmentClass = null;

        switch (itemId) {
            case R.id.nav_historiek:
                fragmentClass = HistoriekListFragment.class;
                break;
            case R.id.nav_forum:
                break;
            case R.id.nav_activiteiten:
                break;
            case R.id.nav_kampen:
                fragmentClass = KampenListFragment.class;
                break;
            default:
                fragmentClass = MainActivity.class;
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


}
