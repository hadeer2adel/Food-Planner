package com.example.foodplanner.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.Presenter.MealDetailsPresenter;
import com.example.foodplanner.Presenter.MealDetailsPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.IngreRecycleViewAdapter;
import com.example.foodplanner.RecycleView.StepsRecycleViewAdapter;
import com.example.foodplanner.View.OnFavListener;
import com.example.foodplanner.View.OnShowMassege;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class MealDetailsFragment extends Fragment implements OnFavListener, MealDetailsView, OnShowMassege {
    private ImageView image, areaImage;
    private TextView name, areaName;
    private ImageButton mealFav;
    private boolean favBtnClicked;
    private ChipGroup mealCategories;
    private YouTubePlayerView video;
    private RecyclerView ingrRecyclerView, stepRecyclerView;
    private IngreRecycleViewAdapter ingreAdapter;
    private StepsRecycleViewAdapter stepsAdapter;
    private MealDetailsPresenter presenter;

    private MealDTO thisMeal = null;

    public MealDetailsFragment() {}

    public static MealDetailsFragment newInstance(String param1, String param2) {
        MealDetailsFragment fragment = new MealDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image = view.findViewById(R.id.mealImage);
        areaImage = view.findViewById(R.id.areaImage);
        name = view.findViewById(R.id.mealTitle);
        areaName = view.findViewById(R.id.mealAreaName);
        video = view.findViewById(R.id.video);
        ingrRecyclerView = view.findViewById(R.id.mealIngrRecycleView);
        stepRecyclerView = view.findViewById(R.id.mealStepRecycleView);
        mealFav = view.findViewById(R.id.mealFav);
        mealCategories = view.findViewById(R.id.mealCategories);

        thisMeal = new MealDTO();

        favBtnClicked = com.example.foodplanner.Controller.MealDetailsFragmentArgs.fromBundle(getArguments()).getFavourite();
        if(favBtnClicked)
            mealFav.setImageResource(R.drawable.ic_favorite_true);
        else
            mealFav.setImageResource(R.drawable.ic_favorite_false);

        if(FirebaseAuth.getInstance().getCurrentUser() == null)
            mealFav.setEnabled(false);

        mealFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favBtnClicked) {
                    favBtnClicked = false;
                    mealFav.setImageResource(R.drawable.ic_favorite_false);
                }
                else {
                    favBtnClicked = true;
                    mealFav.setImageResource(R.drawable.ic_favorite_true);
                }
                clickOnFavListener(thisMeal);
                mealFav.setEnabled(false);
            }
        });


        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ingrRecyclerView.setLayoutManager(manager1);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        stepRecyclerView.setLayoutManager(manager2);

        ingreAdapter = new IngreRecycleViewAdapter(getContext(), new ArrayList<>());
        ingrRecyclerView.setAdapter(ingreAdapter);

        stepsAdapter = new StepsRecycleViewAdapter(getContext(), new String[]{});
        stepRecyclerView.setAdapter(stepsAdapter);

        String id = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealID();
        Boolean remote = MealDetailsFragmentArgs.fromBundle(getArguments()).getRemote();
        presenter = new MealDetailsPresenterImpl(getContext(),this, this);

        if(remote) {
            presenter.getMeal(id);
        }
        else {
            presenter.getFavMeal(id);
        }
    }

    @Override
    public void showMeal(MealDTO meal) {
        thisMeal = meal;

        showCategoriesAndTags(meal.getCategory(), meal.getTags());

        if(meal.getImgUrl() != null && !meal.getImgUrl().equals(""))
            Glide.with(this).load(meal.getImgUrl()).into(image);

        if(meal.getAreaImageUrl() != null && !meal.getAreaImageUrl().equals(""))
            Glide.with(this).load(meal.getAreaImageUrl()).into(areaImage);

        if(meal.getVideoUrl() != null && !meal.getVideoUrl().equals("")) {
            video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String[] videoId = meal.getVideoUrl().split("=");
                    if(videoId.length > 1)
                        youTubePlayer.cueVideo(videoId[1], 0);
                }
            });
        }

        ingreAdapter = new IngreRecycleViewAdapter(getContext(), meal.getIngredients());
        ingrRecyclerView.setAdapter(ingreAdapter);

        name.setText(meal.getName());
        areaName.setText(meal.getArea());

        if(meal.getInstructions() != null && !meal.getInstructions().equals("")) {
            String[] instructions = meal.getInstructions().split("\r\n");
            stepsAdapter = new StepsRecycleViewAdapter(getContext(), instructions);
            stepRecyclerView.setAdapter(stepsAdapter);
        }
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void showCategoriesAndTags(String _categories, String _tags){
        if (_categories != null && !_categories.equals("")) {
            String[] categories = _categories.split(",");
            for (String category : categories) {
                Chip chip = new Chip(getContext());
                chip.setText(category);
                mealCategories.addView(chip);
            }
        }
        if (_tags != null && !_tags.equals("")) {
            String[] tags = _tags.split(",");
            for (String tag : tags) {
                Chip chip = new Chip(getContext());
                chip.setText(tag);
                mealCategories.addView(chip);
            }
        }
    }

    @Override
    public void clickOnFavListener(MealDTO meal) {
        if(favBtnClicked){
            addToFav(meal);
        }
        else {
            removeFromFav(meal);
        }
    }

    @Override
    public void addToFav(MealDTO meal) {
        presenter.addToFav(meal);
    }

    @Override
    public void removeFromFav(MealDTO meal) {
        presenter.removeFromFav(meal);
    }
}