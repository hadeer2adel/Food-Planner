package com.example.foodplanner.Presenter;

import android.text.Editable;

import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.IngredientDTO;
import com.example.foodplanner.Models.IngredientsDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.MealOneDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SearchPagePresenter {
    public void getCategories();
    public void getAreas();
    public void getIngredients();


    public void getMealsByCategory(String category);
    public void getMealsByArea(String area);
    public void getMealsByIngredient(String ingredient);
    public void searchByName(Editable editable);
    public void searchByArea(Editable editable, List<AreaDTO> areas);
    public void searchByCategory(Editable editable, List<CategoryDTO> categories);
    public void searchByIngredient(Editable editable, List<IngredientDTO> ingredients);


    public void addToFav(MealDTO meal);
}
