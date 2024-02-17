package com.example.foodplanner.Controller;

import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.IngredientDTO;
import com.example.foodplanner.Models.MealDTO;

import java.util.List;

public interface SearchPageView {
    public void showMeals(List<MealDTO> meals);
    public void setCategories(List<CategoryDTO> categories);
    public void setAreas(List<AreaDTO> areas);
    public void setIngredients(List<IngredientDTO> ingredients);
    public void addToFav(MealDTO meal);
}
