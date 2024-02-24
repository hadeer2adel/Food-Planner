package com.example.foodplanner.Presenter;

import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekListPresenterImpl implements WeekListPresenter {
    private Repository repository;
    private OnMessageListener message;

    public WeekListPresenterImpl(Repository _repository, OnMessageListener _message){
        repository = _repository;
        message = _message;
    }
    @Override
    public void deleteAllPlans() {
        repository.deleteAllDays()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()-> message.showMsg("Start your plans for a new week"),
                        error -> message.showMsg(error.getMessage())
                );
    }
}
