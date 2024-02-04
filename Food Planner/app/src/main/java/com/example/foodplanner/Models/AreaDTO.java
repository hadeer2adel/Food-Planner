package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class AreaDTO {
    @SerializedName("strArea")
    private String name;
    private String imgUrl;

    public AreaDTO(String name) {
        this.name = name;
        String subName = name.toLowerCase(Locale.ROOT);
        subName = ""+subName.charAt(0)+subName.charAt(1);
        imgUrl = "https://www.themealdb.com/images/icons/flags/big/64/"+subName+".png";
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        String subName = name.toLowerCase(Locale.ROOT);
        subName = ""+subName.charAt(0)+subName.charAt(1);
        imgUrl = "https://www.themealdb.com/images/icons/flags/big/64/"+subName+".png";
        return imgUrl;
    }
}
