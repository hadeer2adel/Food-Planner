package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailedMealDTO {
    @SerializedName("idMeal")
    private String id;
    @SerializedName("strMeal")
    private String name;
    @SerializedName("strArea")
    private String area;
    private String areaImageUrl;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strTags")
    private String tags;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String imgUrl;
    @SerializedName("strYoutube")
    private String videoUrl;
    private List<IngredientDTO> ingredients;

    public DetailedMealDTO(String id, String name, String category, String area, String tags, String instructions, String imgUrl, String videoUrl, List<IngredientDTO> ingredients) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.tags = tags;
        this.instructions = instructions;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.ingredients = ingredients;
        String subName = area.toLowerCase(Locale.ROOT);
        subName = ""+subName.charAt(0)+subName.charAt(1);
        areaImageUrl = "https://www.themealdb.com/images/icons/flags/big/64/"+subName+".png";
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

    public String getAreaImageUrl() {
        String subName = area.toLowerCase(Locale.ROOT);
        subName = ""+subName.charAt(0)+subName.charAt(1);
        areaImageUrl = "https://www.themealdb.com/images/icons/flags/big/64/"+subName+".png";
        return areaImageUrl;
    }

    public String getTags() {
        return tags;
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
