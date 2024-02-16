package com.example.foodplanner.RecycleView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Controller.WeekListFragmentDirections;
import com.example.foodplanner.R;

public class WeekRecycleViewHolder extends RecyclerView.ViewHolder {
    TextView dayText;
    Button addBtn;
    RecyclerView recyclerView;
    ConstraintLayout card;

    public WeekRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        dayText = itemView.findViewById(R.id.dayCardName);
        addBtn = itemView.findViewById(R.id.dayCardBtn);
        recyclerView = itemView.findViewById(R.id.dayCardRecyclerView);
        card = itemView.findViewById(R.id.dayCard);
    }
}