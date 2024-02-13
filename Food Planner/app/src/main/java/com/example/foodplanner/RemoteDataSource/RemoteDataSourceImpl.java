package com.example.foodplanner.RemoteDataSource;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.DetailedMealsDTO;
import com.example.foodplanner.Models.MealsDTO;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceImpl implements RemoteDataSource {
    public static final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static RemoteDataSourceImpl instance = null;
    private RemoteDAO service;

    private RemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = retrofit.create(RemoteDAO.class);
    }

    public static RemoteDataSourceImpl getInstance() {
        if (instance == null)
            instance = new RemoteDataSourceImpl();
        return instance;
    }

    @Override
    public Observable<MealsDTO> makeNetworkCall_RandomMeals() {
        return service.getRandomMeals();
    }

    @Override
    public Observable<CategoriesDTO> makeNetworkCall_Categories() {
        return service.getCategories();
    }

    @Override
    public Observable<AreasDTO> makeNetworkCall_Areas() {
        return service.getAreas();
    }

    @Override
    public Observable<MealsDTO> makeNetworkCall_MealsByCategory(String category) {
        return service.getMealsByCategory(category);
    }

    @Override
    public Observable<MealsDTO> makeNetworkCall_MealsByArea(String area) {
        return service.getMealsByArea(area);
    }

    @Override
    public Observable<DetailedMealsDTO> makeNetworkCall_MealDetails(String id) {
        return service.getMealDetails(id);
    }
}