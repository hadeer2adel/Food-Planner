package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

public interface CalenderListPresenter {
    public void getFavMeals();
    public void insertDayMeal(MealDTO day);
}
