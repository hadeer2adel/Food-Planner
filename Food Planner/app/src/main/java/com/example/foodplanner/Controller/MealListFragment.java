package com.example.foodplanner.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.HomePagePresenter;
import com.example.foodplanner.Presenter.HomePagePresenterImpl;
import com.example.foodplanner.Presenter.MealListPresenter;
import com.example.foodplanner.Presenter.MealListPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.MealRecycleViewAdapter;
import com.example.foodplanner.View.OnFavListener;

import java.util.ArrayList;
import java.util.List;

public class MealListFragment extends Fragment implements OnFavListener, MealListView{
    private RecyclerView recyclerView;
    private MealRecycleViewAdapter adapter;
    private MealListPresenter presenter;

    public MealListFragment() {}

    public static MealListFragment newInstance(String param1, String param2) {
        MealListFragment fragment = new MealListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mealListRecycleView);
        TextView title = view.findViewById(R.id.cardListTitle);

        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);

        adapter = new MealRecycleViewAdapter(getContext(), new ArrayList<>(), this,"small", true);
        recyclerView.setAdapter(adapter);

        presenter = new MealListPresenterImpl(getContext(), this);
        String type = MealListFragmentArgs.fromBundle(getArguments()).getType();
        String value = MealListFragmentArgs.fromBundle(getArguments()).getValue();

        if(type.equals("category")){
            title.setText(value);
            presenter.getMealsByCategory(value);
        }
        else if(type.equals("area")){
            title.setText(value);
            presenter.getMealsByArea(value);
        }

    }

    @Override
    public void showMeals(List<MealDTO> meals) {
        adapter = new MealRecycleViewAdapter(getContext(), meals, this,"small", true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMsg(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clickOnFavListener(MealDTO meal) {
        addToFav(meal);
    }

    @Override
    public void addToFav(MealDTO meal) {
        presenter.addToFav(meal);
    }

}