package com.example.foodplanner.Presenter;

import android.content.Context;

import com.example.foodplanner.Controller.FavListView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavListPresenterImpl implements FavListPresenter {

    private Repository repository;
    private FavListView view;

    public FavListPresenterImpl(Context context, FavListView _view){
        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
    }

    @Override
    public void getFavMeals() {
        repository.getFavMeals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> view.showMsg(error.getMessage())
                );
    }

    @Override
    public void removeFromFav(MealDTO meal) {
        repository.deleteMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showMsg("delete from favourite successfully"),
                        error -> view.showMsg(error.getMessage())
                );
    }
}
