package com.example.foodplanner.LocalDataSource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Models.MealDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface LocalDAO {
    @Query("Select * from Meal where userId = :id")
    Flowable<List<MealDTO>> getFavMeals(String id);

    @Query("Select * from Meal where id = :mealId")
    Flowable<MealDTO> getMealById(String mealId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(MealDTO meal);

    @Delete
    Completable deleteMeal(MealDTO meal);
}
