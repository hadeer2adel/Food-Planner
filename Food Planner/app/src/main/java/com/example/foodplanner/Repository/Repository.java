package com.example.foodplanner.Repository;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.DetailedMealsDTO;
import com.example.foodplanner.Models.MealsDTO;

import io.reactivex.rxjava3.core.Observable;

public interface Repository {
    public Observable<MealsDTO> getRandomMeals();
    public Observable<CategoriesDTO> getCategories();
    public Observable<AreasDTO> getAreas();
    public Observable<MealsDTO> getMealsByCategory(String category);
    public Observable<MealsDTO> getMealsByArea(String area);
    public Observable<DetailedMealsDTO> getMealDetails(String id);
}
