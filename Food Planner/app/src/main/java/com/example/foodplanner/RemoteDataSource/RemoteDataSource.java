package com.example.foodplanner.RemoteDataSource;

import com.example.foodplanner.NetworkCallBack.AreaNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.CategoryNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.DetailedMealNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.MealNetworkCallBack;

public interface RemoteDataSource {
    public void makeNetworkCall_RandomMeals(MealNetworkCallBack networkCallBack);
    public void makeNetworkCall_Categories(CategoryNetworkCallBack networkCallBack);
    public void makeNetworkCall_Areas(AreaNetworkCallBack networkCallBack);
    public void makeNetworkCall_MealsByCategory(MealNetworkCallBack networkCallBack, String category);
    public void makeNetworkCall_MealsByArea(MealNetworkCallBack networkCallBack, String area);
    public void makeNetworkCall_MealDetails(DetailedMealNetworkCallBack networkCallBack, String id);
}
