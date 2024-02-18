package com.example.foodplanner.SQLlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MealsDB";
    public static final String TABLE_MEALS = "Meals";

    private static final String CREATE_MEALS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MEALS + "( id TEXT NOT NULL, userId TEXT NOT NULL, day TEXT, name TEXT, "+
            "area TEXT, areaImageUrl TEXT, category TEXT, tags TEXT, instructions TEXT, imgUrl TEXT, videoUrl TEXT, " +
            "strIngredient1 TEXT, strIngredient2 TEXT, strIngredient3 TEXT, strIngredient4 TEXT, strIngredient5 TEXT, " +
            "strIngredient6 TEXT, strIngredient7 TEXT, strIngredient8 TEXT, strIngredient9 TEXT, strIngredient10 TEXT, " +
            "strIngredient11 TEXT, strIngredient12 TEXT, strIngredient13 TEXT, strIngredient14 TEXT, strIngredient15 TEXT, " +
            "strIngredient16 TEXT, strIngredient17 TEXT, strIngredient18 TEXT, strIngredient19 TEXT, strIngredient20 TEXT, " +
            "strMeasure1 TEXT, strMeasure2 TEXT, strMeasure3 TEXT, strMeasure4 TEXT, strMeasure5 TEXT, " +
            "strMeasure6 TEXT, strMeasure7 TEXT, strMeasure8 TEXT, strMeasure9 TEXT, strMeasure10 TEXT, " +
            "strMeasure11 TEXT, strMeasure12 TEXT, strMeasure13 TEXT, strMeasure14 TEXT, strMeasure15 TEXT, " +
            "strMeasure16 TEXT, strMeasure17 TEXT, strMeasure18 TEXT, strMeasure19 TEXT, strMeasure20 TEXT, " +
            "PRIMARY KEY (id, userId) );";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "onCreate: hhhhh ");
        db.execSQL(CREATE_MEALS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALS);
        onCreate(db);
    }
}
