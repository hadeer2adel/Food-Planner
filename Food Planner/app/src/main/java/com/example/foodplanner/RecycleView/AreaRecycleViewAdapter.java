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
import com.example.foodplanner.R;
import com.example.foodplanner.Controller.HomePageFragmentDirections.ActionHomeFragmentToCardListFragment;

import java.util.List;

public class AreaRecycleViewAdapter extends RecyclerView.Adapter<AreaRecycleViewHolder> {
    private Context context;
    private List<AreaDTO> areas;

    public AreaRecycleViewAdapter(Context _context, List<AreaDTO> _areas){
        context = _context;
        areas = _areas;
    }

    @NonNull
    @Override
    public AreaRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_card_area, parent, false);
        AreaRecycleViewHolder viewHolder = new AreaRecycleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaRecycleViewHolder holder, int position) {
        AreaDTO area = areas.get(position);

        if(area.getImgUrl() != null && !area.getImgUrl().equals(""))
            Glide.with(context).load(area.getImgUrl()).into(holder.imageView);

        holder.titleText.setText(""+area.getName());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionHomeFragmentToCardListFragment action;
                action = HomePageFragmentDirections.actionHomeFragmentToCardListFragment("area",area.getName());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }
}
