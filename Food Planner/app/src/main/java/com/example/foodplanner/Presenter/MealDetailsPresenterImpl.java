package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Controller.MealDetailsView;
import com.example.foodplanner.Controller.MealListView;
import com.example.foodplanner.Models.DetailedMealDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.NetworkCallBack.DetailedMealNetworkCallBack;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;

import java.util.List;

public class MealDetailsPresenterImpl implements MealDetailsPresenter, DetailedMealNetworkCallBack {
    private Repository repository;
    private MealDetailsView view;

    public MealDetailsPresenterImpl(Context context, MealDetailsView _view){
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource);
        view = _view;
    }

    @Override
    public void onSuccess(DetailedMealDTO meal) {
        view.showMeal(meal);
    }
    @Override
    public void onFailure(String errorMsg) {
        view.showErrorMsg(errorMsg);
    }

    @Override
    public void getMeal(String id) {
        repository.getMealDetails(this, id);
    }
}
