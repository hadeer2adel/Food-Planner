package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

public interface FavListPresenter {
    public void getFavMeals();
    public void getLocalFavMeals();
    public void removeFromFav(MealDTO meal);
}
