package com.example.foodplanner.Repository;

import com.example.foodplanner.NetworkCallBack.AreaNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.CategoryNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.DetailedMealNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.MealNetworkCallBack;

public interface Repository {
    public void getRandomMeals(MealNetworkCallBack networkCallBack);
    public void getCategories(CategoryNetworkCallBack networkCallBack);
    public void getAreas(AreaNetworkCallBack networkCallBack);
    public void getMealsByCategory(MealNetworkCallBack networkCallBack, String category);
    public void getMealsByArea(MealNetworkCallBack networkCallBack, String area);
    public void getMealDetails(DetailedMealNetworkCallBack networkCallBack, String id);
}
