package com.example.foodplanner.Presenter;

import android.text.Editable;

import com.example.foodplanner.Controller.SearchPageView;
import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.IngredientDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Listeners.OnMessageListener;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPagePresenterImpl implements SearchPagePresenter{

    private Repository repository;
    private SearchPageView view;
    private OnMessageListener message;

    public SearchPagePresenterImpl(Repository _repository, SearchPageView _view, OnMessageListener _message){
        repository = _repository;
        view = _view;
        message = _message;
    }

    @Override
    public void getCategories() {
        repository.getCategories()
                .subscribeOn(Schedulers.newThread())
                .map(item -> item.getCategories())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.setCategories(item),
                        error -> message.showMsg(error.getMessage())
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
                        error -> message.showMsg(error.getMessage())
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
                        error -> message.showMsg(error.getMessage())
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
                        error -> message.showMsg(error.getMessage())
                );
    }
    private void addToFav2(MealDTO meal) {
        meal.setUserId(UserDTO.getUser().getId());
        repository.insertMeal(meal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> message.showMsg("Add to favourite successfully"),
                        error -> message.showMsg(error.getMessage())
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
                        error -> message.showMsg(error.getMessage())
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
                        error -> message.showMsg(error.getMessage())
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
                        error -> message.showMsg(error.getMessage())
                );

    }

    @Override
    public void searchByName(Editable editable) {
        Observable.create(emitter -> emitter.onNext(editable))
                .map(i -> i.toString().toLowerCase())
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(
                        name -> {
                            repository.searchByName(name)
                                    .debounce(2, TimeUnit.SECONDS)
                                    .subscribeOn(Schedulers.newThread())
                                    .map(item -> item.getAllMeals())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            item -> view.showMeals(item),
                                            error -> message.showMsg(error.getMessage())
                                    );
                        },
                        error -> message.showMsg(error.getMessage())
                );
    }

    @Override
    public void searchByArea(Editable editable, List<AreaDTO> areas) {
        Observable.create(emitter -> emitter.onNext(editable))
                .map(i -> i.toString().toLowerCase())
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    List<String> filteredAreas = areas.stream()
                            .map(i -> i.getName())
                            .filter(name -> name.toLowerCase().startsWith(item)||name.toLowerCase().contains(item))
                            .collect(Collectors.toList());
                    view.showChips(filteredAreas);
                });
    }

    @Override
    public void searchByCategory(Editable editable, List<CategoryDTO> categories) {
        Observable.create(emitter -> emitter.onNext(editable))
                .map(i -> i.toString().toLowerCase())
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    List<String> filteredAreas = categories.stream()
                            .map(i -> i.getName())
                            .filter(name -> name.toLowerCase().startsWith(item)||name.toLowerCase().contains(item))
                            .collect(Collectors.toList());
                    view.showChips(filteredAreas);
                });
    }

    @Override
    public void searchByIngredient(Editable editable, List<IngredientDTO> ingredients) {
        Observable.create(emitter -> emitter.onNext(editable))
                .map(i -> i.toString().toLowerCase())
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    List<String> filteredAreas = ingredients.stream()
                            .map(i -> i.getName())
                            .filter(name -> name.toLowerCase().startsWith(item)||name.toLowerCase().contains(item))
                            .collect(Collectors.toList());
                    view.showChips(filteredAreas);
                });
    }
}
