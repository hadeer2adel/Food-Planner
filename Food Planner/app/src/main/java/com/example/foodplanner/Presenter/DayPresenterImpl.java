package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Controller.DayMealsListView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.View.OnShowMassege;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DayPresenterImpl implements DayPresenter{

    private Repository repository;
    private DayMealsListView view;
    private OnShowMassege massege;

    public DayPresenterImpl(Context context, DayMealsListView _view, OnShowMassege _massege){
        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
        massege = _massege;
    }

    @Override
    public void getDayMeals(String day) {
        repository.getDayMeals(day)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showDayMeals(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }

    @Override
    public void deleteDayMeal(MealDTO day) {
        repository.deleteDayMeal(day)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> massege.showMsg("Delete from Calender successfully"),
                        error -> massege.showMsg(error.getMessage())
                );
    }
}
