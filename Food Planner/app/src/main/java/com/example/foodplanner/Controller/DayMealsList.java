package com.example.foodplanner.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.DayPresenter;
import com.example.foodplanner.Presenter.DayPresenterImpl;
import com.example.foodplanner.RecycleView.DayRecycleViewAdapter;
import com.example.foodplanner.SQLlite.NetworkConnection;
import com.example.foodplanner.View.OnAddListener;
import com.example.foodplanner.View.OnShowMassege;

import java.util.ArrayList;
import java.util.List;

public class DayMealsList implements OnAddListener, DayMealsListView, OnShowMassege {

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

        adapter = new DayRecycleViewAdapter(context, new ArrayList<>(), this,"large");
        recyclerView.setAdapter(adapter);

        presenter = new DayPresenterImpl(context, this, this);
        presenter.getDayMeals(day);
    }


    @Override
    public void showDayMeals(List<MealDTO> days) {
        adapter = new DayRecycleViewAdapter(context, days,this, "large");
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
