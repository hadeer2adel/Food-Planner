package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Models.MealDTO;

public interface ProfilePagePresenter {
    public void deleteAllFav();
    public void deleteAllPlans();
    public void storeData();
    public void retrieveData();
    public void logOut(Context context);
}
