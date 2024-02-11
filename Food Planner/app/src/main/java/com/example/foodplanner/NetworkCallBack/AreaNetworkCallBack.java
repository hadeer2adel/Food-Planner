package com.example.foodplanner.NetworkCallBack;

import com.example.foodplanner.Models.AreaDTO;

import java.util.List;

public interface AreaNetworkCallBack {
    public void onSuccess_Area(List<AreaDTO> areas);
    public void onFailure_Area(String errorMsg);
}
