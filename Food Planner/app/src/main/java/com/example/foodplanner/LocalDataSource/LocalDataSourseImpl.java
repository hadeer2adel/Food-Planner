package com.example.foodplanner.LocalDataSource;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class LocalDataSourseImpl implements LocalDataSourse {
    private LocalDAO dao;
    private static LocalDataSourseImpl repo = null;

    private LocalDataSourseImpl(Context _context){
        AppDB database = AppDB.getInstance(_context);
        dao = database.getLocalDAO();
    }

    public static LocalDataSourseImpl getInstance(Context _context){
        if(repo == null)
            repo = new LocalDataSourseImpl(_context);
        return repo;
    }

    @Override
    public Flowable<List<MealDTO>> getFavMeals() {
        return dao.getFavMeals(UserDTO.getUser().getId());
    }
    @Override
    public Flowable<MealDTO> getMealById(String mealId){
        return dao.getMealById(mealId);
    }
    @Override
    public Completable insertMeal(MealDTO meal) {
        return dao.insertMeal(meal);
    }
    @Override
    public Completable deleteMeal(MealDTO meal) {
        return dao.deleteMeal(meal);
    }

    @Override
    public Flowable<List<MealDTO>> getDayMeals(String day) {
        return dao.getDayMeals(UserDTO.getUser().getId(), day);
    }

    @Override
    public Completable insertDayMeal(MealDTO day) {
        return dao.insertDayMeal(day);
    }

    @Override
    public Completable deleteDayMeal(MealDTO day) {
        return dao.deleteDayMeal(day);
    }

    @Override
    public Completable deleteAllDays() {
        return dao.deleteAllDays();
    }

}
