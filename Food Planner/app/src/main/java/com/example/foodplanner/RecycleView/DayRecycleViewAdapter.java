package com.example.foodplanner.RecycleView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Controller.WeekListFragmentDirections;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.R;
import com.example.foodplanner.SQLlite.NetworkConnection;
import com.example.foodplanner.View.OnAddListener;

import java.util.List;

public class DayRecycleViewAdapter extends RecyclerView.Adapter<MealRecycleViewHolder> {
    private Context context;
    private List<MealDTO> days;
    private String cardSize;
    private OnAddListener listener;

    public DayRecycleViewAdapter(Context _context, List<MealDTO> _days, OnAddListener _listener, String _cardSize){
        context = _context;
        days = _days;
        listener =_listener;
        cardSize = _cardSize;
    }

    @NonNull
    @Override
    public MealRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_card_meal, parent, false);
        if(cardSize.equals("small"))
            view = inflater.inflate(R.layout.fragment_card_meal_small, parent, false);
        MealRecycleViewHolder viewHolder = new MealRecycleViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MealRecycleViewHolder holder, int position) {
        MealDTO day = days.get(position);

        if(!holder.isFav && cardSize.equals("small")) {
            holder.favBtn.setImageResource(R.drawable.ic_add_false);
        }
        else {
            holder.favBtn.setImageResource(R.drawable.ic_close);
        }

        if(day.getImgUrl() != null && !day.getImgUrl().equals(""))
            Glide.with(context).load(day.getImgUrl()).into(holder.imageView);

        holder.titleText.setText(""+day.getName());

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkConnection.isNetworkConnected(context)) {
                    if (cardSize.equals("small")) {
                        holder.favBtn.setImageResource(R.drawable.ic_add_true);
                    }
                    listener.clickOnAddListener(day);
                    holder.favBtn.setEnabled(false);
                }
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkConnection.isNetworkConnected(context) && cardSize.equals("small")) {
                    holder.favBtn.setImageResource(R.drawable.ic_add_true);
                    listener.clickOnAddListener(day);
                    holder.favBtn.setEnabled(false);
                }
                else {
                    com.example.foodplanner.Controller.WeekListFragmentDirections.ActionWeekFragmentToMealDetailsFragment action;
                    action = com.example.foodplanner.Controller.WeekListFragmentDirections.actionWeekFragmentToMealDetailsFragment(day.getId(), true, false);
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return days.size();
    }
}
