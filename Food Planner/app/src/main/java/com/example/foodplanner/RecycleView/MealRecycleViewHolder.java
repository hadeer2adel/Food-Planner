package com.example.foodplanner.RecycleView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.google.android.material.card.MaterialCardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

public class MealRecycleViewHolder extends RecyclerView.ViewHolder {
    TextView titleText;
    ImageView imageView;
    ImageButton favBtn;
    MaterialCardView card;
    boolean isFav;

    public MealRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.mealCardTitle);
        imageView = itemView.findViewById(R.id.mealCardImage);
        favBtn = itemView.findViewById(R.id.mealCardFav);
        card = itemView.findViewById(R.id.mealCard);
        isFav = false;
    }
}
