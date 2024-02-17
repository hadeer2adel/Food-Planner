package com.example.foodplanner.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.IngredientDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.HomePagePresenter;
import com.example.foodplanner.Presenter.MealListPresenterImpl;
import com.example.foodplanner.Presenter.SearchPagePresenter;
import com.example.foodplanner.Presenter.SearchPagePresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.AreaRecycleViewAdapter;
import com.example.foodplanner.RecycleView.CategoryRecycleViewAdapter;
import com.example.foodplanner.RecycleView.MealRecycleViewAdapter;
import com.example.foodplanner.View.OnFavListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchPageFragment extends Fragment implements OnFavListener, SearchPageView {
    private RecyclerView recycleView;
    private EditText seachBar;
    private Button areaBtn, cateBtn, ingrBtn;
    private ChipGroup chipGroup;
    private MealRecycleViewAdapter adapter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seachBar = view.findViewById(R.id.searchBar);
        areaBtn = view.findViewById(R.id.searchAreaBtn);
        cateBtn = view.findViewById(R.id.searchCateBtn);
        ingrBtn = view.findViewById(R.id.searchIngrBtn);
        chipGroup  = view.findViewById(R.id.searchChipGroup);
        recycleView = view.findViewById(R.id.searchMealsRecyclerView);

        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recycleView.setLayoutManager(manager);
        adapter = new MealRecycleViewAdapter(getContext(), new ArrayList<>(), this,"small", true, true);
        recycleView.setAdapter(adapter);

        presenter = new SearchPagePresenterImpl(getContext(), this);
        editSearchBar();

        areas = new ArrayList<>();
        categories = new ArrayList<>();
        ingredients = new ArrayList<>();

        areaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMeals(new ArrayList<>());
                chipGroup.removeAllViews();
                if(!isAreaBtnClicked){
                    areaBtn.setBackgroundColor(getResources().getColor(R.color.yellow));
                    cateBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    ingrBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    if(areas.isEmpty()){
                        seachBar.setEnabled(false);
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
                showMeals(new ArrayList<>());
                chipGroup.removeAllViews();
                if(!isCateBtnClicked) {
                    areaBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    cateBtn.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ingrBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    if(categories.isEmpty()){
                        seachBar.setEnabled(false);
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
                showMeals(new ArrayList<>());
                chipGroup.removeAllViews();
                if(!isIngrBtnClicked) {
                    areaBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    cateBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    ingrBtn.setBackgroundColor(getResources().getColor(R.color.yellow));
                    if(ingredients.isEmpty()){
                        seachBar.setEnabled(false);
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
        seachBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                chipGroup.removeAllViews();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isAreaBtnClicked){
                    Observable.create(emitter -> emitter.onNext(s))
                            .map(i -> i.toString().toLowerCase())
                            .debounce(2, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(item -> {
                                List<String> filteredAreas = areas.stream()
                                        .map(i -> i.getName())
                                        .filter(name -> name.toLowerCase().startsWith(item)||name.toLowerCase().contains(item))
                                        .collect(Collectors.toList());
                                showChips(filteredAreas);
                            });
                }
                else if (isCateBtnClicked){
                    Observable.create(emitter -> emitter.onNext(s))
                            .map(i -> i.toString().toLowerCase())
                            .debounce(2, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(item -> {
                                List<String> filteredAreas = categories.stream()
                                        .map(i -> i.getName())
                                        .filter(name -> name.toLowerCase().startsWith(item)||name.toLowerCase().contains(item))
                                        .collect(Collectors.toList());
                                showChips(filteredAreas);
                            });
                }
                else if(isIngrBtnClicked){
                    Observable.create(emitter -> emitter.onNext(s))
                            .map(i -> i.toString().toLowerCase())
                            .debounce(2, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(item -> {
                                List<String> filteredAreas = ingredients.stream()
                                        .map(i -> i.getName())
                                        .filter(name -> name.toLowerCase().startsWith(item)||name.toLowerCase().contains(item))
                                        .collect(Collectors.toList());
                                showChips(filteredAreas);
                            });
                }
                else {
                    presenter.searchByName(s.toString().toLowerCase());
                }
            }
        });
    }

    private void showChips(List<String> names) {
        chipGroup.removeAllViews();
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

                        if(isAreaBtnClicked)
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
    @Override
    public void showMeals(List<MealDTO> meals) {
        adapter = new MealRecycleViewAdapter(getContext(), meals, this,"small", true, true);
        recycleView.setAdapter(adapter);
    }
    @Override
    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
        seachBar.setEnabled(true);
    }
    @Override
    public void setAreas(List<AreaDTO> areas) {
        this.areas = areas;
        seachBar.setEnabled(true);
    }
    @Override
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
        seachBar.setEnabled(true);
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
}