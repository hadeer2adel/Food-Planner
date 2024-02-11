package com.example.foodplanner.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Models.IngredientDTO;
import com.example.foodplanner.R;

import java.util.List;

public class StepsRecycleViewAdapter extends RecyclerView.Adapter<StepsRecycleViewHolder> {
    private Context context;
    private String[] steps;

    private int stepNumber;

    public StepsRecycleViewAdapter(Context _context, String[] _steps){
        context = _context;
        steps = _steps;
        stepNumber = 1;
    }

    @NonNull
    @Override
    public StepsRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_instruction_card, parent, false);
        StepsRecycleViewHolder viewHolder = new StepsRecycleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsRecycleViewHolder holder, int position) {
        if(!(steps[position].isEmpty() || steps[position].equals(""))) {
            holder.stepNumber.setText("" + stepNumber);
            holder.stepText.setText(steps[position]);
            stepNumber++;
        }
        else {
            holder.stepNumber.setVisibility(View.GONE);
            holder.stepText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return steps.length;
    }
}
