package com.example.foodplanner.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Controller.SearchPageFragmentDirections;
import com.example.foodplanner.HelperClasses.NetworkConnection;
import com.example.foodplanner.Listeners.OnFavListener;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class SearchRecycleViewAdapter extends RecyclerView.Adapter<MealRecycleViewHolder> {
    private Context context;
    private List<MealDTO> meals;
    private OnFavListener listener;

    public SearchRecycleViewAdapter(Context _context, List<MealDTO> _meals, OnFavListener _listener){
        context = _context;
        meals = _meals;
        listener =_listener;
    }

    @NonNull
    @Override
    public MealRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_card_meal_small, parent, false);
        MealRecycleViewHolder viewHolder = new MealRecycleViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MealRecycleViewHolder holder, int position) {
        MealDTO meal = meals.get(position);

        holder.isFav = false;
        if(holder.isFav) {
            holder.favBtn.setImageResource(R.drawable.ic_favorite_true);
        }
        else {
            holder.favBtn.setImageResource(R.drawable.ic_favorite_false);
        }

        if(meal.getImgUrl() != null && !meal.getImgUrl().equals(""))
            Glide.with(context).load(meal.getImgUrl()).into(holder.imageView);

        holder.titleText.setText(""+meal.getName());

        if(FirebaseAuth.getInstance().getCurrentUser() == null)
            holder.favBtn.setEnabled(false);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkConnection.isNetworkConnected(context)) {
                    if (holder.isFav) {
                        holder.favBtn.setImageResource(R.drawable.ic_favorite_false);
                    } else {
                        holder.favBtn.setImageResource(R.drawable.ic_favorite_true);
                    }
                    holder.isFav = !holder.isFav;
                    listener.clickOnFavListener(meal);
                    holder.favBtn.setEnabled(false);
                }
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchPageFragmentDirections.ActionSearchPageFragmentToMealDetailsFragment action;
                action = com.example.foodplanner.Controller.SearchPageFragmentDirections.actionSearchPageFragmentToMealDetailsFragment(meal.getId(), holder.isFav, true);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
