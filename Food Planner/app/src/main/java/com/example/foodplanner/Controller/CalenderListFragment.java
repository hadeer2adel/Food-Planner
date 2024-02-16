package com.example.foodplanner.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.CalenderListPresenter;
import com.example.foodplanner.Presenter.CalenderListPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.DayRecycleViewAdapter;
import com.example.foodplanner.View.OnAddListener;

import java.util.ArrayList;
import java.util.List;

public class CalenderListFragment extends Fragment  implements OnAddListener, CalenderListView{

    private RecyclerView recyclerView;
    private DayRecycleViewAdapter adapter;
    private CalenderListPresenter presenter;
    public CalenderListFragment() {
    }

    public static CalenderListFragment newInstance(String param1, String param2) {
        CalenderListFragment fragment = new CalenderListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.cardListTitle);
        title.setText("Favourite");
        recyclerView = view.findViewById(R.id.mealListRecycleView);

        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);

        adapter = new DayRecycleViewAdapter(getContext(), new ArrayList<>(), this,"small");
        recyclerView.setAdapter(adapter);

        presenter = new CalenderListPresenterImpl(getContext(), this);
        presenter.getFavMeals();
    }

    @Override
    public void showMeals(List<MealDTO> meals) {
        adapter = new DayRecycleViewAdapter(getContext(), meals,this, "small");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addToCalender(MealDTO meal) {
        String currentDay = CalenderListFragmentArgs.fromBundle(getArguments()).getDay();
        String day = meal.getDay();
        if(day == null)
            day = currentDay;
        else
            day = day + currentDay;
        meal.setDay(day);
        presenter.insertDayMeal(meal);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clickOnAddListener(MealDTO day) {
        addToCalender(day);
    }
}