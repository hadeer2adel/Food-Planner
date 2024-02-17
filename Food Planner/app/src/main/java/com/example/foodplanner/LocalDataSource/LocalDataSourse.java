package com.example.foodplanner.LocalDataSource;


import com.example.foodplanner.Models.MealDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface LocalDataSourse {
    //Favourite
    Flowable<List<MealDTO>> getFavMeals();
    Flowable<MealDTO> getMealById(String mealId);
    Completable insertMeal(MealDTO meal);
    Completable deleteMeal(MealDTO meal);
    Completable deleteAllFav();

    //Week
    Flowable<List<MealDTO>> getDayMeals(String day);
    Completable insertDayMeal(MealDTO day);
    Completable deleteDayMeal(MealDTO day);
    Completable deleteAllDays();
}
