package com.example.foodplanner.Presenter;

import com.example.foodplanner.Controller.CalenderListView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderListPresenterImpl implements CalenderListPresenter {

    private Repository repository;
    private CalenderListView view;
    private OnMessageListener massege;

    public CalenderListPresenterImpl(LocalDataSourse localDataSourse, RemoteDataSource remoteDataSource, CalenderListView _view, OnMessageListener _massege){
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
        massege = _massege;
    }

    @Override
    public void getFavMeals() {
        repository.getFavMeals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }

    @Override
    public void insertDayMeal(MealDTO day) {
        repository.insertDayMeal(day)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> massege.showMsg("Add to Calender successfully"),
                        error -> massege.showMsg(error.getMessage())
                );
    }

}
