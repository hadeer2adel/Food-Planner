package com.example.foodplanner.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Controller.HomePageFragmentDirections;
import com.example.foodplanner.Models.AreaDTO;
import com.example.foodplanner.Models.IngredientDTO;
import com.example.foodplanner.R;

import java.util.List;

public class IngreRecycleViewAdapter extends RecyclerView.Adapter<IngreRecycleViewHolder> {
    private Context context;
    private List<IngredientDTO> ingredients;

    public IngreRecycleViewAdapter(Context _context, List<IngredientDTO> _ingredients){
        context = _context;
        ingredients = _ingredients;
    }

    @NonNull
    @Override
    public IngreRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_ingredient_card, parent, false);
        IngreRecycleViewHolder viewHolder = new IngreRecycleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngreRecycleViewHolder holder, int position) {
        IngredientDTO ingredient = ingredients.get(position);
        Glide.with(context).load(ingredient.getImgSmallUrl()).into(holder.imageView);
        holder.titleText.setText(ingredient.getName());
        holder.measureText.setText(ingredient.getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
