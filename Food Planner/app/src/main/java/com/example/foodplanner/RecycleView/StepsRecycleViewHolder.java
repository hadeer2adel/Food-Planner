package com.example.foodplanner.RecycleView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class StepsRecycleViewHolder extends RecyclerView.ViewHolder {
    TextView stepNumber, stepText;

    public StepsRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        stepNumber = itemView.findViewById(R.id.stepNumber);
        stepText = itemView.findViewById(R.id.stepText);
    }
}