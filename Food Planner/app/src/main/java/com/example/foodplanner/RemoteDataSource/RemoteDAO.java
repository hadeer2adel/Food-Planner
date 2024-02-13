package com.example.foodplanner.RemoteDataSource;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.DetailedMealsDTO;
import com.example.foodplanner.Models.MealsDTO;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteDAO {
    @GET("random.php")
    Observable<MealsDTO> getRandomMeals();

    @GET("categories.php")
    Observable<CategoriesDTO> getCategories();

    @GET("list.php?a=list")
    Observable<AreasDTO> getAreas();

    @GET("filter.php")
    Observable<MealsDTO> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Observable<MealsDTO> getMealsByArea(@Query("a") String area);

    @GET("lookup.php")
    Observable<DetailedMealsDTO> getMealDetails(@Query("i") String id);
}
