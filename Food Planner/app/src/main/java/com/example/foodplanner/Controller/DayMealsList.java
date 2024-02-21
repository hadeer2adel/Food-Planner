package com.example.foodplanner.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.DayPresenter;
import com.example.foodplanner.Presenter.DayPresenterImpl;
import com.example.foodplanner.RecycleView.DayRecycleViewAdapter;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Listeners.OnAddListener;
import com.example.foodplanner.Listeners.OnMessageListener;

import java.util.ArrayList;
import java.util.List;

public class DayMealsList implements OnAddListener, DayMealsListView, OnMessageListener {

    private RecyclerView recyclerView;
    private DayRecycleViewAdapter adapter;
    private DayPresenter presenter;
    private Context context;
    private String currentDay;



    public void onViewCreated(Context _context,RecyclerView _recyclerView, String day) {
        context = _context;
        recyclerView = _recyclerView;
        currentDay = day;

        LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new DayRecycleViewAdapter(context, new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        presenter = new DayPresenterImpl(localDataSourse, remoteDataSource, this, this);
        presenter.getDayMeals(day);
    }


    @Override
    public void showDayMeals(List<MealDTO> days) {
        adapter = new DayRecycleViewAdapter(context, days,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void removeDayMeal(MealDTO day) {
        presenter.deleteDayMeal(day);
    }

    @Override
    public void clickOnAddListener(MealDTO meal) {
        String day = meal.getDay();
        String modifiedDay = day.replace(currentDay, "");
        meal.setDay(modifiedDay);
        removeDayMeal(meal);
    }
}
