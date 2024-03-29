package com.example.foodplanner.Repository;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.IngredientsDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.MealOneDTO;
import com.example.foodplanner.Models.MealsDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Query;

public interface Repository {
    //Remote Data Source

    public Observable<MealsDTO> getRandomMeals();
    public Observable<CategoriesDTO> getCategories();
    public Observable<AreasDTO> getAreas();
    public Observable<MealsDTO> getMealsByCategory(String category);
    public Observable<MealsDTO> getMealsByArea(String area);
    public Observable<MealOneDTO> getMealDetails(String id);
    public Observable<IngredientsDTO> getIngredients();
    public Observable<MealsDTO> getMealsByIngredient(String ingredient);
    public Observable<MealsDTO> searchByName(String name);

    //Local Data Source
    Flowable<List<MealDTO>> getFavMeals();
    Flowable<MealDTO> getMealById(String mealId);
    Completable insertMeal(MealDTO meal);
    Completable deleteMeal(MealDTO meal);
    Flowable<List<MealDTO>> getDayMeals(String day);
    Completable insertDayMeal(MealDTO day);
    Completable deleteDayMeal(MealDTO day);
    Completable deleteAllDays();
    Completable deleteAllFav();

}
