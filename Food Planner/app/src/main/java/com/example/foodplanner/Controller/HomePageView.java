package com.example.foodplanner.Controller;
import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.MealDTO;

import java.util.List;

public interface HomePageView {
    public void showRandomMeals(List<MealDTO> meals);
    public void showCategories(List<CategoryDTO> categories);
    public void showAreas(List<AreaDTO> areas);
    public void showErrorMsg(String errorMsg);

}
