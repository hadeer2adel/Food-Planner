package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreasDTO {
    @SerializedName("meals")
    private List<AreaDTO> areas;
    public  List<AreaDTO> getAreas(){
        return areas;
    }
}
