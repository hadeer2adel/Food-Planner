package com.example.foodplanner.Repository;

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.MealOneDTO;
import com.example.foodplanner.Models.MealsDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class RepositoryImpl implements Repository {
    private RemoteDataSource remoteDataSource;
    private LocalDataSourse localDataSourse;
    private static RepositoryImpl instance = null;

    private RepositoryImpl(RemoteDataSource _RemoteDataSource, LocalDataSourse _localDataSourse){
        remoteDataSource = _RemoteDataSource;
        localDataSourse = _localDataSourse;
    }

    public static RepositoryImpl getInstance(RemoteDataSource _RemoteDataSource, LocalDataSourse _localDataSourse){
        if(instance == null)
            instance = new RepositoryImpl(_RemoteDataSource, _localDataSourse);
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
    public Observable<MealOneDTO> getMealDetails(String id) {
        return remoteDataSource.makeNetworkCall_MealDetails(id);
    }

    @Override
    public Flowable<List<MealDTO>> getFavMeals() {
        return localDataSourse.getFavMeals();
    }
    @Override
    public Flowable<MealDTO> getMealById(String mealId){
        return localDataSourse.getMealById(mealId);
    }

    @Override
    public Completable insertMeal(MealDTO meal) {
        return localDataSourse.insertMeal(meal);
    }

    @Override
    public Completable deleteMeal(MealDTO meal) {
        return localDataSourse.deleteMeal(meal);
    }

    @Override
    public Flowable<List<MealDTO>> getDayMeals(String day) {
        return localDataSourse.getDayMeals(day);
    }

    @Override
    public Completable insertDayMeal(MealDTO day) {
        return localDataSourse.insertDayMeal(day);
    }

    @Override
    public Completable deleteDayMeal(MealDTO day) {
        return localDataSourse.deleteDayMeal(day);
    }

    @Override
    public Completable deleteAllDays() {
        return localDataSourse.deleteAllDays();
    }

}
