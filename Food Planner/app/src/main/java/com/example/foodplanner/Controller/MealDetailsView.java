package com.example.foodplanner.Controller;

import com.example.foodplanner.Models.DetailedMealDTO;

public interface MealDetailsView {
    public void showMeal(DetailedMealDTO meal);
    public void showErrorMsg(String errorMsg);
}
