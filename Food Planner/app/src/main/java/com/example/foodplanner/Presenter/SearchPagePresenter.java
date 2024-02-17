package com.example.foodplanner.Presenter;

import com.example.foodplanner.Models.IngredientsDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.MealOneDTO;

import io.reactivex.rxjava3.core.Observable;

public interface SearchPagePresenter {
    public void getCategories();
    public void getAreas();
    public void getIngredients();


    public void getMealsByCategory(String category);
    public void getMealsByArea(String area);
    public void getMealsByIngredient(String ingredient);
    public void searchByName(String name);


    public void addToFav(MealDTO meal);
}
