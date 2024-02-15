package com.example.foodplanner.RemoteDataSource;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.MealOneDTO;
import com.example.foodplanner.Models.MealsDTO;

import io.reactivex.rxjava3.core.Observable;

public interface RemoteDataSource {
    public Observable<MealsDTO> makeNetworkCall_RandomMeals();
    public Observable<CategoriesDTO> makeNetworkCall_Categories();
    public Observable<AreasDTO> makeNetworkCall_Areas();
    public Observable<MealsDTO> makeNetworkCall_MealsByCategory(String category);
    public Observable<MealsDTO> makeNetworkCall_MealsByArea(String area);
    public Observable<MealOneDTO> makeNetworkCall_MealDetails(String id);
}
