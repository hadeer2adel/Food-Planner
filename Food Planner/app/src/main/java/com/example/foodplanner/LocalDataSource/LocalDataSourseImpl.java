package com.example.foodplanner.LocalDataSource;

import android.content.Context;

import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class LocalDataSourseImpl implements LocalDataSourse {
    private LocalDAO dao;
    private Flowable<List<MealDTO>> mealList;
    private static LocalDataSourseImpl repo = null;

    private LocalDataSourseImpl(Context _context){
        AppDB database = AppDB.getInstance(_context);
        dao = database.getLocalDAO();
        mealList = dao.getFavMeals(UserDTO.getUser().getId());
    }

    public static LocalDataSourseImpl getInstance(Context _context){
        if(repo == null)
            repo = new LocalDataSourseImpl(_context);
        return repo;
    }

    @Override
    public Flowable<List<MealDTO>> getFavMeals() {
        return mealList;
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

}
