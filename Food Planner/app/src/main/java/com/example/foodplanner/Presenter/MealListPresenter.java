package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

public interface MealListPresenter {
    public void getMealsByCategory(String category);
    public void getMealsByArea(String area);
    public void addToFav(MealDTO meal);
}
