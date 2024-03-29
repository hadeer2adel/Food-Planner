package com.example.foodplanner.Controller;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.foodplanner.Presenter.FavListPresenter;
import com.example.foodplanner.Presenter.FavListPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.MealHorizontalRecycleViewAdapter;
import com.example.foodplanner.RecycleView.MealVerticalRecycleViewAdapter;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.View.LoginActivity;
import com.example.foodplanner.View.MainActivity;
import com.example.foodplanner.Listeners.OnFavListener;
import com.example.foodplanner.Listeners.OnMessageListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class FavListFragment extends Fragment  implements OnFavListener, FavListView, OnMessageListener {

    private RecyclerView recyclerView;
    private MealVerticalRecycleViewAdapter adapter;
    private FavListPresenter presenter;

    public FavListFragment() {
    }

    public static FavListFragment newInstance(String param1, String param2) {
        FavListFragment fragment = new FavListFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            showDialog();
        }
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

        adapter = new MealVerticalRecycleViewAdapter(getContext(), new ArrayList<>(), this,false);
        recyclerView.setAdapter(adapter);

        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(getContext());
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        Repository repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        presenter = new FavListPresenterImpl(repository, this, this);
        presenter.getFavMeals();
    }

    @Override
    public void showMeals(List<MealDTO> meals) {
        adapter = new MealVerticalRecycleViewAdapter(getContext(), meals,this, false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clickOnFavListener(MealDTO meal) {
        removeFromFav(meal);
    }
    @Override
    public void removeFromFav(MealDTO meal) {
        presenter.removeFromFav(meal);
    }

    private void showDialog(){
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.signupfirst_title)
            .setMessage(R.string.signupfirst_favDec)
            .setNegativeButton(R.string.signupfirst_decline, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            })
            .setPositiveButton(R.string.signupfirst_accept, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            })
            .show();
    }
}