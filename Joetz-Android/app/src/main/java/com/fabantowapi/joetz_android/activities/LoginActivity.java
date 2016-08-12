package com.fabantowapi.joetz_android.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.fragments.LoginFragment;
import butterknife.ButterKnife;


/**
 * Created by a_176_000 on 28-7-2016.
 */
public class LoginActivity extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        startLogin();
    }

    public void startLogin(){
        Bundle args =new Bundle();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new LoginFragment();
        fragment.setArguments(args);
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
