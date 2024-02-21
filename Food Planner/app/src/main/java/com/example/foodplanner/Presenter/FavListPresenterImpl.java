package com.example.foodplanner.Presenter;

import com.example.foodplanner.Controller.FavListView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.Listeners.OnMessageListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavListPresenterImpl implements FavListPresenter {

    private Repository repository;
    private FavListView view;
    private OnMessageListener massege;

    public FavListPresenterImpl(LocalDataSourse localDataSourse, RemoteDataSource remoteDataSource, FavListView _view, OnMessageListener _massege){
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
        massege = _massege;
    }

    @Override
    public void getFavMeals() {
        repository.getFavMeals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }

    @Override
    public void removeFromFav(MealDTO meal) {
        repository.deleteMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> massege.showMsg("Delete from favourite successfully"),
                        error -> massege.showMsg(error.getMessage())
                );
    }
}
