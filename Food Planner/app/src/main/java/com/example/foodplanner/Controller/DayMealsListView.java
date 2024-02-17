package com.example.foodplanner.Controller;

import com.example.foodplanner.Models.MealDTO;

import java.util.List;

public interface DayMealsListView {
    public void showDayMeals(List<MealDTO> days);
    public void removeDayMeal(MealDTO day);
}
