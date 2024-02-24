package com.example.foodplanner.Controller;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.IngredientDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.SearchPagePresenter;
import com.example.foodplanner.Presenter.SearchPagePresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.SearchRecycleViewAdapter;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.HelperClasses.NetworkConnection;
import com.example.foodplanner.Listeners.OnFavListener;
import com.example.foodplanner.Listeners.OnMessageListener;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class SearchPageFragment extends Fragment implements OnFavListener, SearchPageView , OnMessageListener {
    private RecyclerView recycleView;
    private EditText searchBar;
    private Button areaBtn, cateBtn, ingrBtn;
    private ChipGroup chipGroup;
    private SearchRecycleViewAdapter adapter;
    private SearchPagePresenter presenter;
    private boolean isAreaBtnClicked = false;
    private boolean isCateBtnClicked = false;
    private boolean isIngrBtnClicked = false;

    private List<CategoryDTO> categories;
    private List<AreaDTO> areas;
    private List<IngredientDTO> ingredients;
    public SearchPageFragment() { }
    public static SearchPageFragment newInstance(String param1, String param2) {
        SearchPageFragment fragment = new SearchPageFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchBar = view.findViewById(R.id.searchBar);
        areaBtn = view.findViewById(R.id.searchAreaBtn);
        cateBtn = view.findViewById(R.id.searchCateBtn);
        ingrBtn = view.findViewById(R.id.searchIngrBtn);
        chipGroup  = view.findViewById(R.id.searchChipGroup);
        recycleView = view.findViewById(R.id.searchMealsRecyclerView);

        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recycleView.setLayoutManager(manager);
        adapter = new SearchRecycleViewAdapter(getContext(), new ArrayList<>(), this);
        recycleView.setAdapter(adapter);

        LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(getContext());
        RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
        Repository repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
        presenter = new SearchPagePresenterImpl(repository, this, this);

        editSearchBar();

        areas = new ArrayList<>();
        categories = new ArrayList<>();
        ingredients = new ArrayList<>();

        areaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIngrBtnClicked = false;
                isCateBtnClicked = false;
                showMeals(new ArrayList<>());
                chipGroup.removeAllViews();
                if(!isAreaBtnClicked){
                    areaBtn.setBackgroundColor(getResources().getColor(R.color.yellow));
                    cateBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    ingrBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    if(areas.isEmpty()){
                        searchBar.setEnabled(false);
                        presenter.getAreas();
                    }
                }
                else
                    areaBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                isAreaBtnClicked = !isAreaBtnClicked;
            }
        });
        cateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIngrBtnClicked = false;
                isAreaBtnClicked = false;
                showMeals(new ArrayList<>());
                chipGroup.removeAllViews();
                if(!isCateBtnClicked) {
                    areaBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    cateBtn.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ingrBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    if(categories.isEmpty()){
                        searchBar.setEnabled(false);
                        presenter.getCategories();
                    }
                }
                else
                    cateBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                isCateBtnClicked = !isCateBtnClicked;
            }
        });
        ingrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAreaBtnClicked = false;
                isCateBtnClicked = false;
                showMeals(new ArrayList<>());
                chipGroup.removeAllViews();
                if(!isIngrBtnClicked) {
                    areaBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    cateBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    ingrBtn.setBackgroundColor(getResources().getColor(R.color.yellow));
                    if(ingredients.isEmpty()){
                        searchBar.setEnabled(false);
                        presenter.getIngredients();
                    }
                }
                else
                    ingrBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                isIngrBtnClicked = !isIngrBtnClicked;
            }
        });
    }

    private void editSearchBar(){
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                chipGroup.removeAllViews();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(isAreaBtnClicked){
                    presenter.searchByArea(s, areas);
                }
                else if (isCateBtnClicked){
                    presenter.searchByCategory(s, categories);
                }
                else if(isIngrBtnClicked){
                    presenter.searchByIngredient(s, ingredients);
                }
                else {
                    presenter.searchByName(s);
                }
            }
        });
    }

    @Override
    public void showChips(List<String> names) {
        chipGroup.removeAllViews();
        if(names.isEmpty())
            showMsg("NO MATCH");
        else {
            for (String name : names) {
                Chip chip = new Chip(getContext());
                chip.setText(name);
                chip.setCheckable(true);
                chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            chipGroup.clearCheck();
                            chip.setChecked(true);

                            if (isAreaBtnClicked)
                                presenter.getMealsByArea(name);
                            else if (isCateBtnClicked)
                                presenter.getMealsByCategory(name);
                            else if (isIngrBtnClicked)
                                presenter.getMealsByIngredient(name);

                        } else {
                            showMeals(new ArrayList<>());
                        }
                    }
                });
                chipGroup.addView(chip);
            }
        }
    }
    @Override
    public void showMeals(List<MealDTO> meals) {
        adapter = new SearchRecycleViewAdapter(getContext(), meals, this);
        recycleView.setAdapter(adapter);
    }
    @Override
    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
        searchBar.setEnabled(true);
    }
    @Override
    public void setAreas(List<AreaDTO> areas) {
        this.areas = areas;
        searchBar.setEnabled(true);
    }
    @Override
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
        searchBar.setEnabled(true);
    }
    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void addToFav(MealDTO meal) {
        presenter.addToFav(meal);
    }
    @Override
    public void clickOnFavListener(MealDTO meal) {
        addToFav(meal);
    }

    private void showNetworkDialog(){
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.network_title)
                .setMessage(R.string.network_Dec)
                .setNegativeButton(R.string.network_decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(getView()).navigate(com.example.foodplanner.Controller.SearchPageFragmentDirections.actionSearchPageFragmentToFavFragment());
                    }
                })
                .show();
    }
}