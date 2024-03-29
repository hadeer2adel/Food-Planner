package com.example.foodplanner.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.MealListPresenter;
import com.example.foodplanner.Presenter.MealListPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.MealHorizontalRecycleViewAdapter;
import com.example.foodplanner.RecycleView.MealVerticalRecycleViewAdapter;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Listeners.OnFavListener;
import com.example.foodplanner.Listeners.OnMessageListener;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MealListFragment extends Fragment implements OnFavListener, MealListView, OnMessageListener {
    private RecyclerView recyclerView;
    private MealVerticalRecycleViewAdapter adapter;
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

        adapter = new MealVerticalRecycleViewAdapter(getContext(), new ArrayList<>(), this, true);
        recyclerView.setAdapter(adapter);

        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(getContext());
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        Repository repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        presenter = new MealListPresenterImpl(repository, this, this);

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
        adapter = new MealVerticalRecycleViewAdapter(getContext(), meals, this,true);
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