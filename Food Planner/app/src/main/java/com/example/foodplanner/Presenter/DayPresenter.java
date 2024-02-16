package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

public interface DayPresenter {
    public void getDayMeals(String day);
    public void deleteDayMeal(MealDTO day);
}
