package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Controller.HomePageView;
import com.example.foodplanner.Listeners.OnMessageListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePagePresenterImpl implements HomePagePresenter{

    private int success = 0;
    private Repository repository;
    private HomePageView view;
    private OnMessageListener message;

    public HomePagePresenterImpl(Repository _repository, HomePageView _view, OnMessageListener _message){
        repository = _repository;
        view = _view;
        message = _message;
    }

    @Override
    public void getRandomMeals() {
        List<MealDTO> meals = new ArrayList<>();
        for (int i=0; i<10; i++){
            repository.getRandomMeals()
                    .subscribeOn(Schedulers.newThread())
                    .map(item -> item.getAllMeals().get(0))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        item -> {
                            meals.add(item);
                            success++;
                            if (success == 10)
                                view.showRandomMeals(meals);
                            },
                        error -> message.showMsg(error.getMessage())
                    );
        }
    }

    @Override
    public void getCategories() {
        repository.getCategories()
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getCategories())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showCategories(item),
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void getAreas() {
        repository.getAreas()
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAreas())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showAreas(item),
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
