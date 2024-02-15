package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

import io.reactivex.rxjava3.core.Flowable;

public interface MealDetailsPresenter {
    public void getMeal(String id);
    public void addToFav(MealDTO meal);
    public void removeFromFav(MealDTO meal);
    public void getFavMeal(String id);
}
