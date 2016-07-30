package com.fabantowapi.joetz_android.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.LoginFragment;
import com.fabantowapi.joetz_android.fragments.RegistratieFragment;
import com.fabantowapi.joetz_android.fragments.WachtwoordVergetenFragment;
import com.fabantowapi.joetz_android.model.Artikel;
import com.fabantowapi.joetz_android.model.ArtikelAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by a_176_000 on 29-7-2016.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer)
    public NavigationView mLeftDrawer;

    private List<Artikel> artikels;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       initializeDrawer();
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
               testArtikels();
                toonArtikelLijst();
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



    private void testArtikels(){
        artikels = new ArrayList<>();
        artikels.add(new Artikel(1,"Voorbeeld titel 1","Test",null));
        artikels.add(new Artikel(2,"Voorbeeld titel 2","Testen",null));
        artikels.add(new Artikel(3,"Voorbeeld titel 3","Test",null));
    }
    private void toonArtikelLijst(){
        ListView listView = (ListView) findViewById(R.id.artikel_list);
        ArtikelAdapter artikelAdapter = new ArtikelAdapter(
                this, R.layout.artikel_detail, artikels.toArray(new Artikel[artikels.size()]));
        listView.setAdapter(artikelAdapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
             //   getActivity().onChallengeItemClicked(position);
            }
        };

        listView.setOnItemClickListener(listener);
    }

}
