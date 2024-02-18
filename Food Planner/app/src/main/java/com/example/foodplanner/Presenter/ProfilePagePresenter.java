package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.MealDTO;

public interface ProfilePagePresenter {
    public void deleteAllFav();
    public void deleteAllPlans();
    public void storeData();
    public void retrieveData();
    public void storeDataLocal();
    public void logOut();
}
