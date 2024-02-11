package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.NetworkCallBack.AreaNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.CategoryNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.MealNetworkCallBack;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.Controller.HomePageView;

import java.util.List;

public class HomePagePresenterImpl implements HomePagePresenter, MealNetworkCallBack, CategoryNetworkCallBack, AreaNetworkCallBack {

    private Repository repository;
    private HomePageView view;

    public HomePagePresenterImpl(Context context, HomePageView _view){
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource);
        view = _view;
    }

    @Override
    public void onSuccess_Meal(List<MealDTO> meals) {
        view.showRandomMeals(meals);
    }
    @Override
    public void onFailure_Meal(String errorMsg) {
        view.showErrorMsg(errorMsg);
    }
    @Override
    public void onSuccess_Category(List<CategoryDTO> categories) {
        view.showCategories(categories);
    }
    @Override
    public void onFailure_Category(String errorMsg) {
        view.showErrorMsg(errorMsg);
    }
    @Override
    public void onSuccess_Area(List<AreaDTO> areas) {
        view.showAreas(areas);
    }
    @Override
    public void onFailure_Area(String errorMsg) {
        view.showErrorMsg(errorMsg);
    }

    @Override
    public void getRandomMeals() {
        repository.getRandomMeals(this);
    }
    @Override
    public void getCategories() {
        repository.getCategories(this);
    }
    @Override
    public void getAreas() {
        repository.getAreas(this);
    }
}
