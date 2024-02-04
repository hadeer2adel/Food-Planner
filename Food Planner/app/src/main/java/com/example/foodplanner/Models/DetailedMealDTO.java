package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DetailedMealDTO {
    @SerializedName("idMeal")
    private String id;
    @SerializedName("strMeal")
    private String name;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strArea")
    private String area;
    @SerializedName("strTags")
    private String tags;
    @SerializedName("strDrinkAlternate")
    private String drinkAlternate;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String imgUrl;
    @SerializedName("strYoutube")
    private String videoUrl;
    private List<IngredientDTO> ingredients;

    public DetailedMealDTO(String id, String name, String category, String area, String tags, String drinkAlternate, String instructions, String imgUrl, String videoUrl, List<IngredientDTO> ingredients) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.tags = tags;
        this.drinkAlternate = drinkAlternate;
        this.instructions = instructions;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getTags() {
        return tags;
    }

    public String getDrinkAlternate() {
        return drinkAlternate;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public List<IngredientDTO> getIngredients() {
        assignIngredients();
        return ingredients;
    }

    public void assignIngredients() {
        ingredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {  // Assuming the maximum number of ingredients is 20
            String ingredientName = getFieldValue("strIngredient" + i);
            String ingredientMeasure = getFieldValue("strMeasure" + i);
            if (ingredientName != null && !ingredientName.isEmpty()) {
                IngredientDTO ingredient = new IngredientDTO(ingredientName, ingredientMeasure);
                ingredients.add(ingredient);
            }
        }
    }

    private String getFieldValue(String fieldName) {
        try {
            return (String) getClass().getDeclaredField(fieldName).get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
