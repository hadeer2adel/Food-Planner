package com.example.foodplanner.RecycleView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class IngreRecycleViewHolder extends RecyclerView.ViewHolder {
    TextView titleText, measureText;
    CircleImageView imageView;

    public IngreRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.ingrName);
        measureText = itemView.findViewById(R.id.ingrMeasure);
        imageView = itemView.findViewById(R.id.ingrImage);
    }
}