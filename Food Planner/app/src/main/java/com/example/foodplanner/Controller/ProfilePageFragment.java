package com.example.foodplanner.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.Presenter.ProfilePagePresenter;
import com.example.foodplanner.Presenter.ProfilePagePresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.SQLlite.NetworkConnection;
import com.example.foodplanner.View.LoginActivity;
import com.example.foodplanner.View.MainActivity;
import com.example.foodplanner.View.OnShowMassege;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

public class ProfilePageFragment extends Fragment implements ProfilePageView, OnShowMassege {
    private Button deleteFavBtn, deletePlanBtn, backupBtn, retrieveBtn, offlineBtn, logOutBtn;
    private TextView name, email;
    private ProfilePagePresenter presenter;
    public ProfilePageFragment() { }
    public static ProfilePageFragment newInstance(String param1, String param2) {
        ProfilePageFragment fragment = new ProfilePageFragment();
        return fragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkConnection.isNetworkConnected(getContext())) {
            showNetworkDialog();
        }
        else if(FirebaseAuth.getInstance().getCurrentUser() == null){
            showDialog();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ProfilePagePresenterImpl(getContext(), this, this);

        name = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.userEmail);

        name.setText(UserDTO.getUser().getName());
        email.setText(UserDTO.getUser().getEmail());

        deleteFavBtn = view.findViewById(R.id.deleteFavBtn);
        deletePlanBtn = view.findViewById(R.id.deletePlanBtn);
        backupBtn = view.findViewById(R.id.backupBtn);
        retrieveBtn = view.findViewById(R.id.retrieveBtn);
        offlineBtn = view.findViewById(R.id.offlineBtn);
        logOutBtn = view.findViewById(R.id.logOutBtn);


        deleteFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAcceptDialog(getString(R.string.profile_option1), 1);
            }
        });
        deletePlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAcceptDialog(getString(R.string.profile_option2), 2);
            }
        });
        backupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAcceptDialog(getString(R.string.profile_option3), 3);
            }
        });
        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAcceptDialog(getString(R.string.profile_option4), 4);
            }
        });
        offlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.storeDataLocal();
            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAcceptDialog(getString(R.string.profile_option6), 6);
            }
        });
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialog(){
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.signupfirst_title)
                .setMessage(R.string.signupfirst_profileDec)
                .setNegativeButton(R.string.signupfirst_decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setPositiveButton(R.string.signupfirst_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void showAcceptDialog(String msg, int option){
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(msg)
                .setMessage(getString(R.string.profile_Dec1)+" "+msg.toLowerCase())
                .setNegativeButton(R.string.profile_decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.profile_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(option == 1){
                            presenter.deleteAllFav();
                        }
                        else if(option == 2){
                            presenter.deleteAllPlans();
                        }
                        else if(option == 3){
                            presenter.storeData();
                        }
                        else if(option == 4){
                            presenter.deleteAllFav();
                            presenter.retrieveData();
                        }
                        else if(option == 6){
                            presenter.logOut();
                        }
                    }
                })
                .show();
    }

    private void showNetworkDialog(){
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.network_title)
                .setMessage(R.string.network_Dec)
                .setNegativeButton(R.string.network_decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(getView()).navigate(com.example.foodplanner.Controller.ProfilePageFragmentDirections.actionProfilePageFragmentToFavFragment());
                    }
                })
                .show();
    }
}