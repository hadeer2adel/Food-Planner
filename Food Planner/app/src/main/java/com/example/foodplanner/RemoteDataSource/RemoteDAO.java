package com.example.foodplanner.RemoteDataSource;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.DetailedMealsDTO;
import com.example.foodplanner.Models.MealsDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteDAO {
    @GET("random.php")
    Call<MealsDTO> getRandomMeals();

    @GET("categories.php")
    Call<CategoriesDTO> getCategories();

    @GET("list.php?a=list")
    Call<AreasDTO> getAreas();

    @GET("filter.php")
    Call<MealsDTO> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealsDTO> getMealsByArea(@Query("a") String area);

    @GET("lookup.php")
    Call<DetailedMealsDTO> getMealDetails(@Query("i") String id);
}
