package com.example.foodplanner.Models;

import com.google.gson.annotations.SerializedName;

public class CategoryDTO {
    @SerializedName("idCategory")
    private String id;
    @SerializedName("strCategory")
    private String name;
    @SerializedName("strCategoryThumb")
    private String imgUrl;

    public CategoryDTO(String id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
