package com.example.foodplanner.Models;

import java.util.List;

public class MealOneDTO {
    private List<MealDTO> meals;

    public MealDTO getMeal() {
        return meals.get(0);
    }
}
