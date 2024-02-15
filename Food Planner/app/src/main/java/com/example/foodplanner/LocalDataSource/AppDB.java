package com.example.foodplanner.LocalDataSource;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodplanner.Models.MealDTO;

@Database(entities = {MealDTO.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public static AppDB instance = null;
    public static synchronized AppDB getInstance(Context context){
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "MealAppDB")
                    .build();
        return instance;
    }
    public abstract LocalDAO getLocalDAO();

}
