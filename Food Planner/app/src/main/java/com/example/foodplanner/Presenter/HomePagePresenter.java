package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

public interface HomePagePresenter {
    public void getRandomMeals();
    public void getCategories();
    public void getAreas();
    public void addToFav(MealDTO meal);
}
