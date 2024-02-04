package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

public class IngredientDTO {

    @SerializedName("idIngredient")
    private String id;
    @SerializedName("strIngredient")
    private String name;
    private String measure;
    private String imgUrl;
    private String imgSmallUrl;

    public IngredientDTO(String name, String measure){
        this.name = name;
        this.measure = measure;
        imgUrl = "https://www.themealdb.com/images/ingredients/"+name+".png";
        imgSmallUrl = "https://www.themealdb.com/images/ingredients/"+name+"-Small.png";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public String getImgUrl() {
        imgUrl = "https://www.themealdb.com/images/ingredients/"+name+".png";
        return imgUrl;
    }

    public String getImgSmallUrl() {
        imgSmallUrl = "https://www.themealdb.com/images/ingredients/"+name+"-Small.png";
        return imgSmallUrl;
    }
}
