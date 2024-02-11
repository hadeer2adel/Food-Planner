package com.example.foodplanner.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Controller.HomePageFragmentDirections;
import com.example.foodplanner.Controller.HomePageFragmentDirections.ActionHomeFragmentToCardListFragment;
import com.example.foodplanner.Models.CategoryDTO;
import com.example.foodplanner.Models.MealDTO;
import com.example.foodplanner.R;

import java.util.List;

public class CategoryRecycleViewAdapter extends RecyclerView.Adapter<CategoryRecycleViewHolder> {
    private Context context;
    private List<CategoryDTO> categories;

    public CategoryRecycleViewAdapter(Context _context, List<CategoryDTO> _categories){
        context = _context;
        categories = _categories;
    }

    @NonNull
    @Override
    public CategoryRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_category_card, parent, false);
        CategoryRecycleViewHolder viewHolder = new CategoryRecycleViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryRecycleViewHolder holder, int position) {
        CategoryDTO category = categories.get(position);
        Glide.with(context).load(category.getImgUrl()).into(holder.imageView);
        holder.titleText.setText(""+category.getName());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionHomeFragmentToCardListFragment action;
                action = HomePageFragmentDirections.actionHomeFragmentToCardListFragment("category",category.getName());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
