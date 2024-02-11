package com.example.foodplanner.RecycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.google.android.material.card.MaterialCardView;

public class CategoryRecycleViewHolder extends RecyclerView.ViewHolder {
    TextView titleText;
    ImageView imageView;
    MaterialCardView card;

    public CategoryRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.cateCardName);
        imageView = itemView.findViewById(R.id.cateCardImage);
        card = itemView.findViewById(R.id.cateCard);
    }
}
