package com.example.foodplanner.RemoteDataSource;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.IngredientsDTO;
import com.example.foodplanner.Models.MealOneDTO;
import com.example.foodplanner.Models.MealsDTO;

import io.reactivex.rxjava3.core.Observable;

public interface RemoteDataSource {
    public Observable<MealsDTO> getRandomMeals();
    public Observable<CategoriesDTO> getCategories();
    public Observable<AreasDTO> getAreas();
    public Observable<MealsDTO> getMealsByCategory(String category);
    public Observable<MealsDTO> getMealsByArea(String area);
    public Observable<MealOneDTO> getMealDetails(String id);
    public Observable<IngredientsDTO> getIngredients();
    public Observable<MealsDTO> getMealsByIngredient(String ingredient);
    public Observable<MealsDTO> searchByName(String name);
}
