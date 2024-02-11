package com.example.foodplanner.Controller;

import com.example.foodplanner.Models.MealDTO;

import java.util.List;

public interface MealListView {
    public void showMeals(List<MealDTO> meals);
    public void showErrorMsg(String errorMsg);
}
