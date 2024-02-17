package com.example.foodplanner.Controller;

import com.example.foodplanner.Models.MealDTO;

public interface MealDetailsView {
    public void showMeal(MealDTO meal);
    public void addToFav(MealDTO meal);
    public void removeFromFav(MealDTO meal);
}
