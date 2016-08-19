package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.api.ApiHelper;
import com.fabantowapi.joetz_android.model.api.Camp;
import com.fabantowapi.joetz_android.model.api.User;
import com.fabantowapi.joetz_android.utils.Observer;
import com.fabantowapi.joetz_android.utils.SharedHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton Rooseleer on 11-8-2016.
 */
public class UserDetailFragment extends Fragment {

    @Bind(R.id.user_detail_name)
    public TextView txtName;
    @Bind(R.id.user_detail_val_email)
    public TextView txtEmail;
    @Bind(R.id.user_detail_val_username)
    public TextView txtUsername;
    @Bind(R.id.user_detail_val_buildingname)
    public TextView txtBuildingName;
    @Bind(R.id.user_detail_val_street_number)
    public TextView txtStreetNumber;
    @Bind(R.id.user_detail_val_postalcode_city)
    public TextView txtPostalcodeCity;
    @Bind(R.id.user_detail_val_role)
    public TextView txtRole;
    @Bind(R.id.user_detail_val_securitynumber)
    public TextView txtSecurityNumber;
    @Bind(R.id.user_detail_val_birthdate)
    public TextView txtBirthDate;
    @Bind(R.id.user_detail_val_owner_code)
    public TextView txtOwnerCode;
    @Bind(R.id.user_detail_val_datejoined)
    public TextView txtDateJoined;

    private MainActivity activity;

    private User currentUser;

    private String editedRole;

    private MaterialDialog dialogEditUserRole;
    private MaterialDialog dialogProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);

        activity = (MainActivity) getActivity();

        setHasOptionsMenu(true);

        activity.showActionBarEditPerson();

        Intent i = getActivity().getIntent();
        currentUser =(User) i.getSerializableExtra("USER");

        ButterKnife.bind(this, view);

        setProfile();
        return view;
    }

    public void setProfile(){
        txtName.setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        txtEmail.setText(currentUser.getEmail());
        txtUsername.setText(currentUser.getUsername());

        if(currentUser.getAdres() != null && currentUser.getAdres().getStraat() != null){
            txtBuildingName.setText(currentUser.getAdres().getNaamgebouw());
            txtStreetNumber.setText(currentUser.getAdres().getStraat() + " " + currentUser.getAdres().getHuisnummer() + " " + currentUser.getAdres().getBus());
            txtPostalcodeCity.setText(currentUser.getAdres().getPostcode() + " " + currentUser.getAdres().getGemeente());
        }
        else{
            txtBuildingName.setText(R.string.not_provided);
        }

        txtRole.setText(currentUser.getRole());

        if(currentUser.getRijksregisternummer() != null){
            txtSecurityNumber.setText(currentUser.getRijksregisternummer());
        }
        else{
            txtSecurityNumber.setText(R.string.not_provided);
        }

        if(currentUser.getGeboortedatum() != null){
            String birthDate = SharedHelper.convertDate(currentUser.getGeboortedatum());
            txtBirthDate.setText(birthDate);
        }
        else{
            txtBirthDate.setText(R.string.not_provided);
        }

        if(currentUser.getCodegerechtigde() != null){
            txtOwnerCode.setText(currentUser.getCodegerechtigde());
        }
        else{
            txtOwnerCode.setText(R.string.not_provided);
        }

        String dateJoined = SharedHelper.convertDate(currentUser.getDateJoined());
        txtDateJoined.setText(dateJoined);
    }

    public void showProgressDialog(Context context){
        this.dialogProgress = new MaterialDialog.Builder(context)
                .content(R.string.edit_user_role_progress)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    private void showEditUserRoleDialog(Context context){
        this.dialogEditUserRole = new MaterialDialog.Builder(context)
                .title(R.string.dialog_choose_role)
                .items(R.array.dialog_roles)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        switch(which){
                            case 0 : editUserRole("lid"); break;
                            case 1 : editUserRole("monitor"); break;
                            case 2 : editUserRole("beheerder"); break;
                        }
                        return true;
                    }
                })
                .positiveText(R.string.dialog_positive)
                .show();
    }

    private void editUserRole(String role){
        editedRole = role;
        showProgressDialog(UserDetailFragment.this.getActivity());
        ApiHelper.editUserRole(UserDetailFragment.this.getActivity(), currentUser.getEmail(), role, currentUser).subscribe(editUserRoleObserver);
    }

    Observer<Object> editUserRoleObserver = new Observer<Object>(){
        @Override
        public void onCompleted()
        {
            if(UserDetailFragment.this != null)
            {
                UserDetailFragment.this.txtRole.setText(editedRole);
                Toast.makeText(UserDetailFragment.this.getActivity(), "Rol bewerkt!", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }

        @Override
        public void onError(Throwable e)
        {
            if(UserDetailFragment.this != null)
            {
                Toast.makeText(UserDetailFragment.this.getActivity(), "Fout bij bewerken van rol", Toast.LENGTH_SHORT).show();
                dialogProgress.dismiss();
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                // User chose the "Settings" item, show the app settings UI...
                showEditUserRoleDialog(UserDetailFragment.this.getActivity());

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
