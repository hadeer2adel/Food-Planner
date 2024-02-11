package com.example.foodplanner.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.HomePagePresenter;
import com.example.foodplanner.Presenter.HomePagePresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.AreaRecycleViewAdapter;
import com.example.foodplanner.RecycleView.CategoryRecycleViewAdapter;
import com.example.foodplanner.RecycleView.MealRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment implements HomePageView{

    private RecyclerView mealRecycleView, categoryRecycleView, areaRecycleView;
    private MealRecycleViewAdapter mealAdapter;
    private CategoryRecycleViewAdapter categoryAdapter;
    private AreaRecycleViewAdapter areaAdapter;
    private HomePagePresenter presenter;

    public HomePageFragment() { }

    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mealRecycleView = view.findViewById(R.id.mealRecycleView);
        categoryRecycleView = view.findViewById(R.id.categoryRecycleView);
        areaRecycleView = view.findViewById(R.id.areaRecycleView);

        presenter = new HomePagePresenterImpl(getContext(), this);

        setMealRecycleView();
        setCategoryRecycleView();
        setAreaRecycleView();
    }

    private void setMealRecycleView(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mealRecycleView.setLayoutManager(manager);

        mealAdapter = new MealRecycleViewAdapter(getContext(), new ArrayList<>(), "large");
        mealRecycleView.setAdapter(mealAdapter);
        presenter.getRandomMeals();
    }
    private void setCategoryRecycleView(){
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        categoryRecycleView.setLayoutManager(manager);

        categoryAdapter = new CategoryRecycleViewAdapter(getContext(), new ArrayList<>());
        categoryRecycleView.setAdapter(categoryAdapter);
        presenter.getCategories();
    }
    private void setAreaRecycleView(){
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        areaRecycleView.setLayoutManager(manager);

        areaAdapter = new AreaRecycleViewAdapter(getContext(), new ArrayList<>());
        areaRecycleView.setAdapter(areaAdapter);
        presenter.getAreas();
    }

    @Override
    public void showRandomMeals(List<MealDTO> meals) {
        mealAdapter = new MealRecycleViewAdapter(getContext(), meals, "large");
        mealRecycleView.setAdapter(mealAdapter);
    }

    @Override
    public void showCategories(List<CategoryDTO> categories) {
        categoryAdapter = new CategoryRecycleViewAdapter(getContext(), categories);
        categoryRecycleView.setAdapter(categoryAdapter);
    }

    @Override
    public void showAreas(List<AreaDTO> areas) {
        areaAdapter = new AreaRecycleViewAdapter(getContext(), areas);
        areaRecycleView.setAdapter(areaAdapter);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }
}