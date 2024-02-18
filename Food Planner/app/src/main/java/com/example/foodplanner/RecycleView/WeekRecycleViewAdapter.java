package com.example.foodplanner.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Controller.DayMealsList;
import com.example.foodplanner.R;
import com.example.foodplanner.SQLlite.NetworkConnection;

import java.util.List;

public class WeekRecycleViewAdapter extends RecyclerView.Adapter<WeekRecycleViewHolder> {
    private Context context;
    private List<String> days;

    public WeekRecycleViewAdapter(Context _context, List<String> _days){
        context = _context;
        days = _days;
    }

    @NonNull
    @Override
    public WeekRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_card_day, parent, false);
        WeekRecycleViewHolder viewHolder = new WeekRecycleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeekRecycleViewHolder holder, int position) {
        String day = days.get(position);
        holder.dayText.setText(day);
        DayMealsList dayMealsList = new DayMealsList();
        dayMealsList.onViewCreated(context, holder.recyclerView, day);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkConnection.isNetworkConnected(context)) {
                    com.example.foodplanner.Controller.WeekListFragmentDirections.ActionWeekFragmentToCalenderListFragment action;
                    action = com.example.foodplanner.Controller.WeekListFragmentDirections.actionWeekFragmentToCalenderListFragment(day);
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
