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

    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;

    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;


    public DetailedMealDTO(String id, String name, String category, String area, String tags, String instructions, String imgUrl, String videoUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.tags = tags;
        this.instructions = instructions;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        String subName = area.toLowerCase(Locale.ROOT);
        subName = "" + subName.charAt(0) + subName.charAt(1);
        areaImageUrl = "https://www.themealdb.com/images/icons/flags/big/64/" + subName + ".png";
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
        subName = "" + subName.charAt(0) + subName.charAt(1);
        areaImageUrl = "https://www.themealdb.com/images/icons/flags/big/64/" + subName + ".png";
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
        List<IngredientDTO> ingredients = new ArrayList<>();

        if (strIngredient1 != null && !strIngredient1.isEmpty()) {
            IngredientDTO ingredient1 = new IngredientDTO(strIngredient1, strMeasure1);
            ingredients.add(ingredient1);
        }
        if (strIngredient2 != null && !strIngredient2.isEmpty()) {
            IngredientDTO ingredient2 = new IngredientDTO(strIngredient2, strMeasure2);
            ingredients.add(ingredient2);
        }
        if (strIngredient3 != null && !strIngredient3.isEmpty()) {
            IngredientDTO ingredient3 = new IngredientDTO(strIngredient3, strMeasure3);
            ingredients.add(ingredient3);
        }
        if (strIngredient4 != null && !strIngredient4.isEmpty()) {
            IngredientDTO ingredient4 = new IngredientDTO(strIngredient4, strMeasure4);
            ingredients.add(ingredient4);
        }
        if (strIngredient5 != null && !strIngredient5.isEmpty()) {
            IngredientDTO ingredient5 = new IngredientDTO(strIngredient5, strMeasure5);
            ingredients.add(ingredient5);
        }
        if (strIngredient6 != null && !strIngredient6.isEmpty()) {
            IngredientDTO ingredient6 = new IngredientDTO(strIngredient6, strMeasure6);
            ingredients.add(ingredient6);
        }
        if (strIngredient7 != null && !strIngredient7.isEmpty()) {
            IngredientDTO ingredient7 = new IngredientDTO(strIngredient7, strMeasure7);
            ingredients.add(ingredient7);
        }
        if (strIngredient8 != null && !strIngredient8.isEmpty()) {
            IngredientDTO ingredient8 = new IngredientDTO(strIngredient8, strMeasure8);
            ingredients.add(ingredient8);
        }
        if (strIngredient9 != null && !strIngredient9.isEmpty()) {
            IngredientDTO ingredient9 = new IngredientDTO(strIngredient9, strMeasure9);
            ingredients.add(ingredient9);
        }
        if (strIngredient10 != null && !strIngredient10.isEmpty()) {
            IngredientDTO ingredient10 = new IngredientDTO(strIngredient10, strMeasure10);
            ingredients.add(ingredient10);
        }
        if (strIngredient11 != null && !strIngredient11.isEmpty()) {
            IngredientDTO ingredient11 = new IngredientDTO(strIngredient11, strMeasure11);
            ingredients.add(ingredient11);
        }
        if (strIngredient12 != null && !strIngredient12.isEmpty()) {
            IngredientDTO ingredient12 = new IngredientDTO(strIngredient12, strMeasure12);
            ingredients.add(ingredient12);
        }
        if (strIngredient13 != null && !strIngredient13.isEmpty()) {
            IngredientDTO ingredient13 = new IngredientDTO(strIngredient13, strMeasure13);
            ingredients.add(ingredient13);
        }
        if (strIngredient14 != null && !strIngredient14.isEmpty()) {
            IngredientDTO ingredient14 = new IngredientDTO(strIngredient14, strMeasure14);
            ingredients.add(ingredient14);
        }
        if (strIngredient15 != null && !strIngredient15.isEmpty()) {
            IngredientDTO ingredient15 = new IngredientDTO(strIngredient15, strMeasure15);
            ingredients.add(ingredient15);
        }
        if (strIngredient16 != null && !strIngredient16.isEmpty()) {
            IngredientDTO ingredient16 = new IngredientDTO(strIngredient16, strMeasure16);
            ingredients.add(ingredient16);
        }
        if (strIngredient17 != null && !strIngredient17.isEmpty()) {
            IngredientDTO ingredient17 = new IngredientDTO(strIngredient17, strMeasure17);
            ingredients.add(ingredient17);
        }
        if (strIngredient18 != null && !strIngredient18.isEmpty()) {
            IngredientDTO ingredient18 = new IngredientDTO(strIngredient18, strMeasure18);
            ingredients.add(ingredient18);
        }
        if (strIngredient19 != null && !strIngredient19.isEmpty()) {
            IngredientDTO ingredient19 = new IngredientDTO(strIngredient19, strMeasure19);
            ingredients.add(ingredient19);
        }
        if (strIngredient20 != null && !strIngredient20.isEmpty()) {
            IngredientDTO ingredient20 = new IngredientDTO(strIngredient20, strMeasure20);
            ingredients.add(ingredient20);
        }
        return ingredients;
    }
}
