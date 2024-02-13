package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.Controller.HomePageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePagePresenterImpl implements HomePagePresenter{

    private int success = 0;
    private Repository repository;
    private HomePageView view;

    public HomePagePresenterImpl(Context context, HomePageView _view){
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource);
        view = _view;
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
                        error -> view.showErrorMsg(error.getMessage())
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
                        error -> view.showErrorMsg(error.getMessage())
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
                        error -> view.showErrorMsg(error.getMessage())
                );
    }
}
