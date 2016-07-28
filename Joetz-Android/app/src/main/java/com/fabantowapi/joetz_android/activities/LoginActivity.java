package com.fabantowapi.joetz_android.activities;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.fabantowapi.joetz_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by a_176_000 on 28-7-2016.
 */
public class LoginActivity extends AppCompatActivity{

    @Bind(R.id.btnLogin)
    public Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onLoginButtonClicked() {
    // valideer;
    }
}
