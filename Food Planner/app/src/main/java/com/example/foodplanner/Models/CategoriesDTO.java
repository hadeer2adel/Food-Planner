package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesDTO {
    private List<CategoryDTO> categories;

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
