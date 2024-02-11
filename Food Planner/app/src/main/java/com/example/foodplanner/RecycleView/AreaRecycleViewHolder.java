package com.example.foodplanner.RecycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.google.android.material.card.MaterialCardView;

public class AreaRecycleViewHolder extends RecyclerView.ViewHolder {
    TextView titleText;
    ImageView imageView;
    MaterialCardView card;

    public AreaRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.areaCardName);
        imageView = itemView.findViewById(R.id.areaCardImage);
        card = itemView.findViewById(R.id.areaCard);
    }
}
