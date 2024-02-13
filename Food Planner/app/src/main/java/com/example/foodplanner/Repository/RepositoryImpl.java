package com.example.foodplanner.Repository;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.DetailedMealsDTO;
import com.example.foodplanner.Models.MealsDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;

import io.reactivex.rxjava3.core.Observable;

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
    public Observable<MealsDTO> getRandomMeals() {
        return remoteDataSource.makeNetworkCall_RandomMeals();
    }

    @Override
    public Observable<CategoriesDTO> getCategories() {
        return remoteDataSource.makeNetworkCall_Categories();
    }

    @Override
    public Observable<AreasDTO> getAreas() {
        return remoteDataSource.makeNetworkCall_Areas();
    }

    @Override
    public Observable<MealsDTO> getMealsByCategory(String category) {
        return remoteDataSource.makeNetworkCall_MealsByCategory(category);
    }

    @Override
    public Observable<MealsDTO> getMealsByArea(String area) {
        return remoteDataSource.makeNetworkCall_MealsByArea(area);
    }

    @Override
    public Observable<DetailedMealsDTO> getMealDetails(String id) {
        return remoteDataSource.makeNetworkCall_MealDetails(id);
    }
}
