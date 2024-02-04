package com.example.foodplanner.Models;

import java.util.List;

public class DetailedMealsDTO {
    private List<DetailedMealDTO> meals;

    public DetailedMealDTO getMeal() {
        return meals.get(0);
    }
}
