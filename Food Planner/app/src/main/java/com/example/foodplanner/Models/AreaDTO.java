package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AreaDTO {
    @SerializedName("strArea")
    private String name;
    private String imgUrl;

    public AreaDTO(String name) {
        this.name = name;
        imgUrl = "https://www.themealdb.com/images/icons/flags/big/64/" + CountryCode.getCountryCode(name) + ".png";
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        String code;
        if(! name.toLowerCase().contains("unknown")) {
            code = CountryCode.getCountryCode(name);
            imgUrl = "https://flagsapi.com/"+code+"/shiny/64.png";
        }
        else
            imgUrl = "https://th.bing.com/th/id/OIP.EbhipA3qMGKX9kdRv8kfGQHaHa?rs=1&pid=ImgDetMain";
        return imgUrl;
    }
}