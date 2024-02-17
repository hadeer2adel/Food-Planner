package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Controller.MealDetailsView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.View.OnShowMassege;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImpl implements MealDetailsPresenter{
    private Repository repository;
    private MealDetailsView view;
    private OnShowMassege massege;

    public MealDetailsPresenterImpl(Context context, MealDetailsView _view, OnShowMassege _massege){
        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
        massege = _massege;
    }

    @Override
    public void getMeal(String id) {
        repository.getMealDetails(id)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getMeal())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeal(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }

    @Override
    public void getFavMeal(String id) {
        repository.getMealById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeal(item),
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

    @Override
    public void removeFromFav(MealDTO meal) {
        repository.deleteMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> massege.showMsg("delete from favourite successfully"),
                        error -> massege.showMsg(error.getMessage())
                );
    }
}
