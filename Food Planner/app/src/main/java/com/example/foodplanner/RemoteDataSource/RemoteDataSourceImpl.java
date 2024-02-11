package com.example.foodplanner.RemoteDataSource;

import com.example.foodplanner.Models.AreasDTO;
import com.example.foodplanner.Models.CategoriesDTO;
import com.example.foodplanner.Models.DetailedMealsDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.MealsDTO;
import com.example.foodplanner.NetworkCallBack.AreaNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.CategoryNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.DetailedMealNetworkCallBack;
import com.example.foodplanner.NetworkCallBack.MealNetworkCallBack;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceImpl implements RemoteDataSource {
    public static final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static RemoteDataSourceImpl instance = null;
    private RemoteDAO service;
    private RemoteDataSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RemoteDAO.class);
    }

    public static RemoteDataSourceImpl getInstance(){
        if(instance == null)
            instance = new RemoteDataSourceImpl();
        return instance;
    }

    int success = 0;
    @Override
    public void makeNetworkCall_RandomMeals(MealNetworkCallBack networkCallBack){
        List<MealDTO> meals = new ArrayList<>();

        for (int i=0; i<10; i++){
            Call<MealsDTO> call = service.getRandomMeals();
            call.enqueue(new Callback<MealsDTO>() {
                @Override
                public void onResponse(Call<MealsDTO> call, Response<MealsDTO> response) {
                    meals.add(response.body().getAllMeals().get(0));
                    success++;
                    if (success == 10) {
                        networkCallBack.onSuccess_Meal(meals);
                    }
                }

                @Override
                public void onFailure(Call<MealsDTO> call, Throwable t) {
                    networkCallBack.onFailure_Meal(t.getMessage());
                }
            });
        }
    }

    @Override
    public void makeNetworkCall_Categories(CategoryNetworkCallBack networkCallBack) {
        Call<CategoriesDTO> call = service.getCategories();
        call.enqueue(new Callback<CategoriesDTO>() {
            @Override
            public void onResponse(Call<CategoriesDTO> call, Response<CategoriesDTO> response) {
                networkCallBack.onSuccess_Category(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoriesDTO> call, Throwable t) {
                networkCallBack.onFailure_Category(t.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCall_Areas(AreaNetworkCallBack networkCallBack) {
        Call<AreasDTO> call = service.getAreas();
        call.enqueue(new Callback<AreasDTO>() {
            @Override
            public void onResponse(Call<AreasDTO> call, Response<AreasDTO> response) {
                networkCallBack.onSuccess_Area(response.body().getAreas());
            }

            @Override
            public void onFailure(Call<AreasDTO> call, Throwable t) {
                networkCallBack.onFailure_Area(t.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCall_MealsByCategory(MealNetworkCallBack networkCallBack, String category){
        Call<MealsDTO> call = service.getMealsByCategory(category);
        call.enqueue(new Callback<MealsDTO>() {
            @Override
            public void onResponse(Call<MealsDTO> call, Response<MealsDTO> response) {
                networkCallBack.onSuccess_Meal(response.body().getAllMeals());
            }

            @Override
            public void onFailure(Call<MealsDTO> call, Throwable t) {
                networkCallBack.onFailure_Meal(t.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCall_MealsByArea(MealNetworkCallBack networkCallBack, String area){
        Call<MealsDTO> call = service.getMealsByArea(area);
        call.enqueue(new Callback<MealsDTO>() {
            @Override
            public void onResponse(Call<MealsDTO> call, Response<MealsDTO> response) {
                networkCallBack.onSuccess_Meal(response.body().getAllMeals());
            }

            @Override
            public void onFailure(Call<MealsDTO> call, Throwable t) {
                networkCallBack.onFailure_Meal(t.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCall_MealDetails(DetailedMealNetworkCallBack networkCallBack, String id){
        Call<DetailedMealsDTO> call = service.getMealDetails(id);
        call.enqueue(new Callback<DetailedMealsDTO>() {
            @Override
            public void onResponse(Call<DetailedMealsDTO> call, Response<DetailedMealsDTO> response) {
                networkCallBack.onSuccess(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<DetailedMealsDTO> call, Throwable t) {
                networkCallBack.onFailure(t.getMessage());
            }
        });
    }
}
