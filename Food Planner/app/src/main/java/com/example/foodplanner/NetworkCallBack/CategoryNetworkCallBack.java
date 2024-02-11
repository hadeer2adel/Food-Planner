package com.example.foodplanner.NetworkCallBack;

import com.example.foodplanner.Models.CategoryDTO;

import java.util.List;

public interface CategoryNetworkCallBack {
    public void onSuccess_Category(List<CategoryDTO> categories);

    public void onFailure_Category(String errorMsg);
}
