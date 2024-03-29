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

import com.example.foodplanner.LocalDataSource.LocalDataSourse;
import com.example.foodplanner.LocalDataSource.LocalDataSourseImpl;
import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.HelperClasses.WeekManagement;
import com.example.foodplanner.Presenter.WeekListPresenter;
import com.example.foodplanner.Presenter.WeekListPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.RecycleView.WeekRecycleViewAdapter;
import com.example.foodplanner.RemoteDataSource.RemoteDataSource;
import com.example.foodplanner.RemoteDataSource.RemoteDataSourceImpl;
import com.example.foodplanner.HelperClasses.PreferenceManager;
import com.example.foodplanner.Repository.Repository;
import com.example.foodplanner.Repository.RepositoryImpl;
import com.example.foodplanner.View.LoginActivity;
import com.example.foodplanner.View.MainActivity;
import com.example.foodplanner.Listeners.OnMessageListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekListFragment extends Fragment  implements OnMessageListener {

    private RecyclerView recyclerView;
    private WeekRecycleViewAdapter adapter;
    private List<String> days;

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
        else if (isNewWeek()){
            LocalDataSourse localDataSourse = LocalDataSourseImpl.getInstance(getContext());
            RemoteDataSource remoteDataSource = RemoteDataSourceImpl.getInstance();
            Repository repository = RepositoryImpl.getInstance(remoteDataSource, localDataSourse);
            WeekListPresenter presenter = new WeekListPresenterImpl(repository, this);

            presenter.deleteAllPlans();
            PreferenceManager preferenceManager = new PreferenceManager(getContext());
            preferenceManager.saveUser(UserDTO.getUser().getId(), UserDTO.getUser().getName(), UserDTO.getUser().getEmail());
        }
    }

    private boolean isNewWeek() {
        String lastSaturday = UserDTO.getUser().getLastSaturday();

        if (lastSaturday != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date lastSaturdayDate = sdf.parse(lastSaturday);
                Date currentDate = new Date();
                if (currentDate.after(lastSaturdayDate)) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(lastSaturdayDate);
                    cal.add(Calendar.DAY_OF_YEAR, 7);
                    Date nextSaturday = cal.getTime();
                    if (currentDate.compareTo(nextSaturday) >= 0) {
                        return true;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WeekManagement.isNewWeek(getContext());

        TextView title = view.findViewById(R.id.cardListTitle);
        title.setText("Week");
        recyclerView = view.findViewById(R.id.mealListRecycleView);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        days = WeekManagement.getInitDays();
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