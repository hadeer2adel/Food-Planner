package com.example.foodplanner.LocalDataSource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.Models.MealDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface LocalDAO {
    @Query("Select * from Meals where userId = :id")
    Flowable<List<MealDTO>> getFavMeals(String id);

    @Query("Select * from Meals where id = :mealId")
    Flowable<MealDTO> getMealById(String mealId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(MealDTO meal);

    @Delete
    Completable deleteMeal(MealDTO meal);

    @Query("SELECT * FROM Meals where userId = :id and day LIKE '%' || :weekDay || '%'")
    Flowable<List<MealDTO>> getDayMeals(String id, String weekDay);

    @Update
    Completable insertDayMeal(MealDTO day);

    @Update
    Completable deleteDayMeal(MealDTO day);

    @Query("UPDATE meals SET day = NULL")
    Completable deleteAllDays();
}
