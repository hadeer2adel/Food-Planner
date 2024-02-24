package com.example.foodplanner.Presenter;

import com.example.foodplanner.Controller.DayMealsListView;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DayPresenterImpl implements DayPresenter{

    private Repository repository;
    private DayMealsListView view;
    private OnMessageListener message;

    public DayPresenterImpl(Repository _repository, DayMealsListView _view, OnMessageListener _message){
        repository = _repository;
        view = _view;
        message = _message;
    }

    @Override
    public void getDayMeals(String day) {
        repository.getDayMeals(day)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showDayMeals(item),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void deleteDayMeal(MealDTO day) {
        repository.deleteDayMeal(day)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> message.showMsg("Delete from Calender successfully"),
                        error -> message.showMsg(error.getMessage())
                );
    }
}
