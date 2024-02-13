package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Controller.MealListView;
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
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource);
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
                        error -> view.showErrorMsg(error.getMessage())
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
                        error -> view.showErrorMsg(error.getMessage())
                );
    }
}
