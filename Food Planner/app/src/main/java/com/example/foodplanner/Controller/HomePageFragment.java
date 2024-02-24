package com.example.foodplanner.Controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.HomePagePresenter;
import com.example.foodplanner.Presenter.HomePagePresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.AreaRecycleViewAdapter;
import com.example.foodplanner.RecycleView.CategoryRecycleViewAdapter;
import com.example.foodplanner.RecycleView.MealHorizontalRecycleViewAdapter;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.HelperClasses.NetworkConnection;
import com.example.foodplanner.Listeners.OnFavListener;
import com.example.foodplanner.Listeners.OnMessageListener;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment implements OnFavListener, HomePageView, OnMessageListener {

    private RecyclerView mealRecycleView, categoryRecycleView, areaRecycleView;
    private MealHorizontalRecycleViewAdapter mealAdapter;
    private CategoryRecycleViewAdapter categoryAdapter;
    private AreaRecycleViewAdapter areaAdapter;
    private HomePagePresenter presenter;

    public HomePageFragment() { }

    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkConnection.isNetworkConnected(getContext())) {
            showNetworkDialog();
        }
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

        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(getContext());
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        Repository repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        presenter = new HomePagePresenterImpl(repository, this, this);

        setMealRecycleView();
        setCategoryRecycleView();
        setAreaRecycleView();
    }

    private void setMealRecycleView(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mealRecycleView.setLayoutManager(manager);

        mealAdapter = new MealHorizontalRecycleViewAdapter(getContext(), new ArrayList<>(),this, true);
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
        mealAdapter = new MealHorizontalRecycleViewAdapter(getContext(), meals,this, true);
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
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clickOnFavListener(MealDTO meal) {
        addToFav(meal);
    }

    @Override
    public void addToFav(MealDTO meal) {
        presenter.addToFav(meal);
    }

    private void showNetworkDialog(){
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.network_title)
                .setMessage(R.string.network_Dec)
                .setNegativeButton(R.string.network_decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(getView()).navigate(com.example.foodplanner.Controller.HomePageFragmentDirections.actionHomeFragmentToFavFragment());
                    }
                })
                .show();
    }
}