package com.example.foodplanner.Presenter;

import com.example.foodplanner.Controller.SearchPageView;
import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.Listeners.OnMessageListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPagePresenterImpl implements SearchPagePresenter{

    private Repository repository;
    private SearchPageView view;
    private OnMessageListener massege;

    public SearchPagePresenterImpl(LocalDataSourse localDataSourse, RemoteDataSource remoteDataSource, SearchPageView _view, OnMessageListener _massege){
        repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        view = _view;
        massege = _massege;
    }

    @Override
    public void getCategories() {
        repository.getCategories()
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getCategories())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.setCategories(item),
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
                        item -> view.setAreas(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }


    @Override
    public void getIngredients() {
        repository.getIngredients()
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllIngredients())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.setIngredients(item),
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
    public void getMealsByCategory(String category) {
        repository.getMealsByCategory(category)
                .debounce(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> massege.showMsg(error.getMessage())
                );

    }

    @Override
    public void getMealsByArea(String area) {
        repository.getMealsByArea(area)
                .debounce(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }

    @Override
    public void getMealsByIngredient(String ingredient) {
        repository.getMealsByIngredient(ingredient)
                .debounce(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> massege.showMsg(error.getMessage())
                );

    }

    @Override
    public void searchByName(String name) {
        repository.searchByName(name)
                .debounce(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getAllMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.showMeals(item),
                        error -> massege.showMsg(error.getMessage())
                );
    }
}
