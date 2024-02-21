package com.example.foodplanner.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.Controller.FavListView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.Controller.HomePageView;
import com.example.foodplanner.View.OnShowMassege;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePagePresenterImpl implements HomePagePresenter{

    private int success = 0;
    private Repository repository;
    private HomePageView view;
    private OnShowMassege massege;

    public HomePagePresenterImpl(LocalDataSourse localDataSourse, RemoteDataSource remoteDataSource, HomePageView _view, OnShowMassege _massege){
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
        massege = _massege;
    }

    @Override
    public void getRandomMeals() {
        List<MealDTO> meals = new ArrayList<>();
        for (int i=0; i<10; i++){
            repository.getRandomMeals()
                    .subscribeOn(Schedulers.newThread())
                    .map(item -> item.getAllMeals().get(0))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        item -> {
                            meals.add(item);
                            success++;
                            if (success == 10)
                                view.showRandomMeals(meals);
                            },
                        error -> massege.showMsg(error.getMessage())
                    );
        }
    }

    @Override
    public void getCategories() {
        repository.getCategories()
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getCategories())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showCategories(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }

    @Override
    public void getAreas() {
        repository.getAreas()
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAreas())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showAreas(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }

    @Override
    public void addToFav(MealDTO meal) {
        repository.getMealDetails(meal.getId())
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getMeal())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> addToFav2(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }
    private void addToFav2(MealDTO meal) {
        meal.setUserId(UserDTO.getUser().getId());
        repository.insertMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> massege.showMsg("Add to favourite successfully"),
                        error -> massege.showMsg(error.getMessage())
                );
    }
}
