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
    private OnMessageListener message;

    public CalenderListPresenterImpl(Repository _repository, CalenderListView _view, OnMessageListener _massege){
        repository = _repository;
        view = _view;
        message = _massege;
    }

    @Override
    public void getFavMeals() {
        repository.getFavMeals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void insertDayMeal(MealDTO day) {
        repository.insertDayMeal(day)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> message.showMsg("Add to Calender successfully"),
                        error -> message.showMsg(error.getMessage())
                );
    }

}
