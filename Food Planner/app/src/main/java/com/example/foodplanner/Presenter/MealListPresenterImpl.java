package com.example.foodplanner.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.Controller.MealListView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealListPresenterImpl implements MealListPresenter {

    private Repository repository;
    private MealListView view;

    public MealListPresenterImpl(Context context, MealListView _view){
        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
    }

    @Override
    public void getMealsByCategory(String category) {
        repository.getMealsByCategory(category)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> view.showMsg(error.getMessage())
                );
    }

    @Override
    public void getMealsByArea(String area) {
        repository.getMealsByArea(area)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> view.showMsg(error.getMessage())
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
                        error -> view.showMsg(error.getMessage())
                );
    }
    private void addToFav2(MealDTO meal) {
        meal.setUserId(UserDTO.getUser().getId());
        repository.insertMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showMsg("Add to favourite successfully"),
                        error -> view.showMsg(error.getMessage())
                );
    }
}
