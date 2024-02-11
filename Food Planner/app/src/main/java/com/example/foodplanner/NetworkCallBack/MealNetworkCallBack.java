package com.example.foodplanner.NetworkCallBack;

import com.example.foodplanner.Models.MealDTO;

import java.util.List;

public interface MealNetworkCallBack {
    public void onSuccess_Meal(List<MealDTO> meals);
    public void onFailure_Meal(String errorMsg);
}
