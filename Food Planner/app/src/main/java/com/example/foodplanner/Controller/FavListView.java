package com.example.foodplanner.Controller;

import com.example.foodplanner.Models.MealDTO;

import java.util.List;

public interface FavListView {
    public void showMeals(List<MealDTO> meals);
    public void showMsg(String msg);
    public void removeFromFav(MealDTO meal);
}
