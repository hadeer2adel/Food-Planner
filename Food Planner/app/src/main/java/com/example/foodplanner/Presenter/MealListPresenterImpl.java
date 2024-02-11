package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Controller.HomePageView;
import com.example.foodplanner.Controller.MealListView;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.NetworkCallBack.MealNetworkCallBack;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;

import java.util.List;

public class MealListPresenterImpl implements MealListPresenter, MealNetworkCallBack {

    private Repository repository;
    private MealListView view;

    public MealListPresenterImpl(Context context, MealListView _view){
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource);
        view = _view;
    }

    @Override
    public void onSuccess_Meal(List<MealDTO> meals) {
        view.showMeals(meals);
    }
    @Override
    public void onFailure_Meal(String errorMsg) {
        view.showErrorMsg(errorMsg);
    }

    @Override
    public void getMealsByCategory(String category) {
        repository.getMealsByCategory(this, category);
    }

    @Override
    public void getMealsByArea(String area) {
        repository.getMealsByArea(this, area);
    }
}
