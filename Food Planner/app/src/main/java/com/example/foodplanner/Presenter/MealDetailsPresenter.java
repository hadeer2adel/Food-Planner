package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

public interface MealDetailsPresenter {
    public void getMeal(String id);
    public void addToFav(MealDTO meal);
    public void removeFromFav(MealDTO meal);
    public void getFavMeal(String id);
    public void getLocalFavMeal(String id);
}
