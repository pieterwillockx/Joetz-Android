package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.database.UserTable;
import com.fabantowapi.joetz_android.utils.Constants;
import com.fabantowapi.joetz_android.utils.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by a_176_000 on 29-7-2016.
 */
public class LoginFragment extends Fragment{

    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.txtWachtwoord)
    EditText txtWachtwoord;

    MaterialDialog dialogProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);
        txtEmail.requestFocus();
        return view;
    }


    @OnClick(R.id.btnLogin)
    public void onLoginButtonClicked() {
        String email = txtEmail.getText().toString();
        String password = txtWachtwoord.getText().toString();

        if(email != "" && password != ""){
            showProgressDialog(this.getActivity());
            dialogProgress.setContent(R.string.login_progress);

            ApiHelper.logIn(LoginFragment.this.getActivity(), email, password, true).subscribe(this.loginObserver);
        }
        else {
            showLoginErrorDialog(LoginFragment.this.getActivity(), "Alle velden moeten ingevuld zijn!");
        }
    }

    @OnClick(R.id.txtwwVergeten)
    public void wachtwoordVergeten(){
        navigate(2);
    }

    @OnClick(R.id.txtRegistreer)
    public void registreer(){
      navigate(1);
    }

    @OnClick(R.id.btnBeQuick)
    public void beQuick(){
        txtEmail.setText("pieter@test.com");
        txtWachtwoord.setText("@Test123456789");
    }

    public void navigate(int fragmentId){
        Fragment fragment;

        Bundle args = new Bundle();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch(fragmentId){
            case 1: fragment = new RegistratieFragment();
                break;
            case 2: fragment = new WachtwoordVergetenFragment();
                break;
            default : fragment = new LoginFragment();
        }

        fragment.setArguments(args);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showLoginErrorDialog(Context context, String error)
    {
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_error)
                .content(error)
                .positiveText(R.string.dialog_positive)
                .show();
    }

    private void showProgressDialog(Context context)
    {
        this.dialogProgress = new MaterialDialog.Builder(context)
                .progress(false, 100)
                .cancelable(false)
                .show();

    }

    private Observer<Object> loginObserver = new Observer<Object>()
    {
        @Override
        public void onCompleted()
        {
            if(LoginFragment.this.getActivity() != null)
            {
                String email = txtEmail.getText().toString();

                dialogProgress.setContent(R.string.get_user_progress);
                dialogProgress.setProgress(33);
                ApiHelper.getUser(LoginFragment.this.getActivity(), email).subscribe(LoginFragment.this.getUserObserver);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(LoginFragment.this.getActivity() != null)
            {
                LoginFragment.this.dialogProgress.dismiss();
                LoginFragment.this.showLoginErrorDialog(LoginFragment.this.getActivity(), "Error while logging in: " + e.getMessage());
            }
        }
    };

    public Observer<Object> getUserObserver = new Observer<Object>(){
        @Override
        public void onCompleted()
        {
            if(LoginFragment.this.getActivity() != null)
            {
                dialogProgress.setContent(R.string.get_activities_progress);
                dialogProgress.setProgress(66);

                ApiHelper.getActivities(LoginFragment.this.getActivity()).subscribe(LoginFragment.this.getActivitiesObserver);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(LoginFragment.this.getActivity() != null)
            {
                LoginFragment.this.dialogProgress.dismiss();
                LoginFragment.this.showLoginErrorDialog(LoginFragment.this.getActivity(), "Error while getting user: " + e.getMessage());
            }
        }
    };

    public Observer<Object> getActivitiesObserver = new Observer<Object>(){
        @Override
        public void onCompleted(){
            if(LoginFragment.this.getActivity() != null){
                dialogProgress.setContent(R.string.get_all_users_progress);
                dialogProgress.setProgress(100);

                ApiHelper.getUsers(LoginFragment.this.getActivity()).subscribe(LoginFragment.this.getUsersObserver);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(LoginFragment.this.getActivity() != null)
            {
                LoginFragment.this.dialogProgress.dismiss();
                LoginFragment.this.showLoginErrorDialog(LoginFragment.this.getActivity(), "Error while getting activities: " + e.getMessage());
            }
        }
    };

    public Observer<Object> getUsersObserver = new Observer<Object>(){
        @Override
        public void onCompleted(){
            if(LoginFragment.this.getActivity() != null){
                Intent intent = new Intent(getActivity(), MainActivity.class);
                String email = txtEmail.getText().toString();
                intent.putExtra("EMAIL", email);

                LoginFragment.this.dialogProgress.dismiss();

                getActivity().startActivity(intent);
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(LoginFragment.this.getActivity() != null)
            {
                LoginFragment.this.dialogProgress.dismiss();
                LoginFragment.this.showLoginErrorDialog(LoginFragment.this.getActivity(), "Error while getting all users: " + e.getMessage());
            }
        }
    };
}
