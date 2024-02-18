package com.example.foodplanner.Presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.foodplanner.Controller.ProfilePageView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.SQLlite.PreferenceManager;
import com.example.foodplanner.SQLlite.SQLAdapter;
import com.example.foodplanner.View.OnShowMassege;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekListPresenterImpl implements WeekListPresenter {
    private Repository repository;
    private OnShowMassege massege;

    public WeekListPresenterImpl(Context context, OnShowMassege _massege){
        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        massege = _massege;
    }

    @Override
    public void deleteAllPlans() {
        repository.deleteAllDays()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()->massege.showMsg("Start your plans for a new week"),
                        error -> massege.showMsg(error.getMessage())
                );
    }
}
