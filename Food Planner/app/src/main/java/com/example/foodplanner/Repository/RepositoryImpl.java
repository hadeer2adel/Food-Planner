package com.example.foodplanner.Repository;

import com.example.foodplanner.NetworkCallBack.AreaNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.CategoryNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.DetailedMealNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.MealNetworkCallBack;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;

public class RepositoryImpl implements Repository {
    private RemoteDataSource remoteDataSource;
    private static RepositoryImpl instance = null;

    private RepositoryImpl(RemoteDataSource _RemoteDataSource){
        remoteDataSource = _RemoteDataSource;
    }

    public static RepositoryImpl getInstance(RemoteDataSource _RemoteDataSource){
        if(instance == null)
            instance = new RepositoryImpl(_RemoteDataSource);
        return instance;
    }

    @Override
    public void getRandomMeals(MealNetworkCallBack networkCallBack) {
        remoteDataSource.makeNetworkCall_RandomMeals(networkCallBack);
    }

    @Override
    public void getCategories(CategoryNetworkCallBack networkCallBack) {
        remoteDataSource.makeNetworkCall_Categories(networkCallBack);
    }

    @Override
    public void getAreas(AreaNetworkCallBack networkCallBack) {
        remoteDataSource.makeNetworkCall_Areas(networkCallBack);
    }

    @Override
    public void getMealsByCategory(MealNetworkCallBack networkCallBack, String category) {
        remoteDataSource.makeNetworkCall_MealsByCategory(networkCallBack, category);
    }

    @Override
    public void getMealsByArea(MealNetworkCallBack networkCallBack, String area) {
        remoteDataSource.makeNetworkCall_MealsByArea(networkCallBack, area);
    }

    @Override
    public void getMealDetails(DetailedMealNetworkCallBack networkCallBack, String id) {
        remoteDataSource.makeNetworkCall_MealDetails(networkCallBack, id);
    }
}
