package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsDTO {
    @SerializedName("meals")
    private List<IngredientDTO> ingredients;
    public  List<IngredientDTO> getAllIngredients(){
        return ingredients;
    }
}
