package com.example.foodplanner.Presenter;

import com.example.foodplanner.Controller.MealDetailsView;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImpl implements MealDetailsPresenter{
    private Repository repository;
    private MealDetailsView view;
    private OnMessageListener message;

    public MealDetailsPresenterImpl(Repository _repository, MealDetailsView _view, OnMessageListener _message){
        repository = _repository;
        view = _view;
        message = _message;
    }

    @Override
    public void getMeal(String id) {
        repository.getMealDetails(id)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getMeal())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeal(item),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void getFavMeal(String id) {
        repository.getMealById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeal(item),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void addToFav(MealDTO meal) {
        repository.getMealDetails(meal.getId())
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getMeal())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> addToFav2(item),
                        error -> message.showMsg(error.getMessage())
                );
    }
    private void addToFav2(MealDTO meal) {
        meal.setUserId(UserDTO.getUser().getId());
        repository.insertMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> message.showMsg("Add to favourite successfully"),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void removeFromFav(MealDTO meal) {
        repository.deleteMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> message.showMsg("delete from favourite successfully"),
                        error -> message.showMsg(error.getMessage())
                );
    }
}
