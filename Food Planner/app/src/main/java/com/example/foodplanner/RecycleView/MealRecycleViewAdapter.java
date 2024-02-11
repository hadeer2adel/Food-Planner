package com.example.foodplanner.RecycleView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.R;

import java.util.List;

public class MealRecycleViewAdapter extends RecyclerView.Adapter<MealRecycleViewHolder> {
    private Context context;
    private List<MealDTO> meals;
    private String cardSize;

    public MealRecycleViewAdapter(Context _context, List<MealDTO> _meals, String _cardSize){
        context = _context;
        meals = _meals;
        cardSize = _cardSize;
    }

    @NonNull
    @Override
    public MealRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_meal_card, parent, false);
        if(cardSize.equals("small"))
            view = inflater.inflate(R.layout.fragment_meal_card_small, parent, false);
        MealRecycleViewHolder viewHolder = new MealRecycleViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MealRecycleViewHolder holder, int position) {
        MealDTO meal = meals.get(position);
        Glide.with(context).load(meal.getImgUrl()).into(holder.imageView);
        holder.titleText.setText(""+meal.getName());

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.favBtnClicked) {
                    holder.favBtnClicked = true;
                    holder.favBtn.setImageResource(R.drawable.ic_favorite_true);
                }
                else {
                    holder.favBtnClicked = false;
                    holder.favBtn.setImageResource(R.drawable.ic_favorite_false);
                }
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, meals.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
