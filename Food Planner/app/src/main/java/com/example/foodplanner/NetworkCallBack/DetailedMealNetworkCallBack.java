package com.example.foodplanner.NetworkCallBack;

import com.example.foodplanner.Models.DetailedMealDTO;

public interface DetailedMealNetworkCallBack {
    public void onSuccess(DetailedMealDTO meal);
    public void onFailure(String errorMsg);
}
