package com.example.foodplanner.Controller;

import com.example.foodplanner.Models.MealDTO;

import java.util.List;

public interface CalenderListView {
    public void showMeals(List<MealDTO> meals);
    public void addToCalender(MealDTO day);
}
