package com.example.foodplanner.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Models.WeekDTO;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.WeekRecycleViewAdapter;
import com.example.foodplanner.View.LoginActivity;
import com.example.foodplanner.View.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class WeekListFragment extends Fragment  implements WeekListView{

    private RecyclerView recyclerView;
    private WeekRecycleViewAdapter adapter;
    private List<String> days;
    private View view;

    public WeekListFragment() {
    }

    public static FavListFragment newInstance(String param1, String param2) {
        FavListFragment fragment = new FavListFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            showDialog();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WeekDTO.isNewWeek(getContext());

        TextView title = view.findViewById(R.id.cardListTitle);
        title.setText("Week");
        recyclerView = view.findViewById(R.id.mealListRecycleView);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        days = WeekDTO.getInitDays();
        adapter = new WeekRecycleViewAdapter(getContext(), days);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void showDialog(){
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.signupfirst_title)
                .setMessage(R.string.signupfirst_calDec)
                .setNegativeButton(R.string.signupfirst_decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setPositiveButton(R.string.signupfirst_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}