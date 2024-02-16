package com.example.foodplanner.LocalDataSource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Models.MealDTO;

@Database(entities = {MealDTO.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public static AppDB instance = null;
    public static synchronized AppDB getInstance(Context context){
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "FoodDB")
                    .build();
        return instance;
    }
    public abstract LocalDAO getLocalDAO();

}
