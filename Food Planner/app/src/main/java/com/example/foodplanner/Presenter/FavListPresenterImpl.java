package com.example.foodplanner.Presenter;

import com.example.foodplanner.Controller.FavListView;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavListPresenterImpl implements FavListPresenter {

    private Repository repository;
    private FavListView view;
    private OnMessageListener message;

    public FavListPresenterImpl(Repository _repository, FavListView _view, OnMessageListener _message){
        repository = _repository;
        view = _view;
        message = _message;
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
    public void removeFromFav(MealDTO meal) {
        repository.deleteMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> message.showMsg("Delete from favourite successfully"),
                        error -> message.showMsg(error.getMessage())
                );
    }
}
