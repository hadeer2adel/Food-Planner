package com.example.foodplanner.Presenter;

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekListPresenterImpl implements WeekListPresenter {
    private Repository repository;
    private OnMessageListener massege;

    public WeekListPresenterImpl(LocalDataSourse localDataSourse, RemoteDataSource remoteDataSource, OnMessageListener _massege){
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
