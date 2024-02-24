package com.example.foodplanner.Controller;

import android.os.Bundle;
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

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.CalenderListPresenter;
import com.example.foodplanner.Presenter.CalenderListPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.CalenderRecycleViewAdapter;
import com.example.foodplanner.RecycleView.DayRecycleViewAdapter;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Listeners.OnAddListener;
import com.example.foodplanner.Listeners.OnMessageListener;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CalenderListFragment extends Fragment  implements OnAddListener, CalenderListView, OnMessageListener {

    private RecyclerView recyclerView;
    private CalenderRecycleViewAdapter adapter;
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

        adapter = new CalenderRecycleViewAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(getContext());
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        Repository repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        presenter = new CalenderListPresenterImpl(repository, this, this);
        presenter.getFavMeals();
    }

    @Override
    public void showMeals(List<MealDTO> meals) {
        adapter = new CalenderRecycleViewAdapter(getContext(), meals,this);
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