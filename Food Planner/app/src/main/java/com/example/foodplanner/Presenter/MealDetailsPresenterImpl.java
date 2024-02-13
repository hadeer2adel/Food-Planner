package com.example.foodplanner.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.Controller.MealDetailsView;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImpl implements MealDetailsPresenter{
    private Repository repository;
    private MealDetailsView view;

    public MealDetailsPresenterImpl(Context context, MealDetailsView _view){
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        repository = RepositoryImpl.getInstance(remoteDataSource);
        view = _view;
    }

    @Override
    public void getMeal(String id) {
        repository.getMealDetails(id)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getMeal())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeal(item),
                        error -> {view.showErrorMsg(error.getMessage());
                            Log.i("test", "getMeal: ----- "+error.getMessage());}
                );
    }
}
