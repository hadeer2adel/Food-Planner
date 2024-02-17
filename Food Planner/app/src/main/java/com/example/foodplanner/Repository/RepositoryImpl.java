package com.example.foodplanner.Repository;

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.IngredientsDTO;
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
        return remoteDataSource.getRandomMeals();
    }

    @Override
    public Observable<CategoriesDTO> getCategories() {
        return remoteDataSource.getCategories();
    }

    @Override
    public Observable<AreasDTO> getAreas() {
        return remoteDataSource.getAreas();
    }

    @Override
    public Observable<MealsDTO> getMealsByCategory(String category) {
        return remoteDataSource.getMealsByCategory(category);
    }

    @Override
    public Observable<MealsDTO> getMealsByArea(String area) {
        return remoteDataSource.getMealsByArea(area);
    }

    @Override
    public Observable<MealOneDTO> getMealDetails(String id) {
        return remoteDataSource.getMealDetails(id);
    }

    @Override
    public Observable<IngredientsDTO> getIngredients() {
        return remoteDataSource.getIngredients();
    }

    @Override
    public Observable<MealsDTO> getMealsByIngredient(String ingredient) {
        return remoteDataSource.getMealsByIngredient(ingredient);
    }

    @Override
    public Observable<MealsDTO> searchByName(String name) {
        return remoteDataSource.searchByName(name);
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
