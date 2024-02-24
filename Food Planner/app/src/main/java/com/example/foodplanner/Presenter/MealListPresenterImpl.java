package com.example.foodplanner.Presenter;

import com.example.foodplanner.Controller.MealListView;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealListPresenterImpl implements MealListPresenter {

    private Repository repository;
    private MealListView view;
    private OnMessageListener message;

    public MealListPresenterImpl(Repository _repository, MealListView _view, OnMessageListener _message){
        repository = _repository;
        view = _view;
        message = _message;
    }
    @Override
    public void getMealsByCategory(String category) {
        repository.getMealsByCategory(category)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void getMealsByArea(String area) {
        repository.getMealsByArea(area)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
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
}
