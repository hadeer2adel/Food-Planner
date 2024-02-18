package com.example.foodplanner.SQLlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodplanner.Models.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class SQLAdapter {
    private SQLiteDatabase database;
    private SQLHelper helper;

    public SQLAdapter(Context context) {
        helper = new SQLHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public void insertMeal(MealDTO meal) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = mealToContentValues(meal);
        database.insert(helper.TABLE_MEALS, null, values);
    }

    public void insertAllMeals(List<MealDTO> meals) {
        SQLiteDatabase database = helper.getWritableDatabase();
        for (MealDTO meal : meals) {
            ContentValues values = mealToContentValues(meal);
            database.insert(helper.TABLE_MEALS, null, values);
        }
    }

    public void updateMeal(MealDTO meal) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = mealToContentValues(meal);
        database.update(helper.TABLE_MEALS, values, "id = ? AND userId = ?", new String[]{meal.getId(), meal.getUserId()});
    }

    public void removeMeal(String id, String userId) {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(helper.TABLE_MEALS, "id = ? AND userId = ?", new String[]{id, userId});
    }

    public void removeAllMeals(String userId) {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(helper.TABLE_MEALS, "userId = ?", new String[]{userId});
    }

    public List<MealDTO> getDayMeals(String userId, String day) {
        SQLiteDatabase database = helper.getReadableDatabase();
        List<MealDTO> meals = new ArrayList<>();
        Cursor cursor = database.query(helper.TABLE_MEALS, null, "userId = ? AND day LIKE ?", new String[]{userId, "%" + day + "%"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                MealDTO meal = cursorToMeal(cursor);
                meals.add(meal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return meals;
    }


    public MealDTO getMeal(String id, String userId) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query(helper.TABLE_MEALS, null, "id = ? AND userId = ?", new String[]{id, userId}, null, null, null);
        MealDTO meal = null;
        if (cursor.moveToFirst()) {
            meal = cursorToMeal(cursor);
        }
        cursor.close();
        return meal;
    }

    public List<MealDTO> getAllMeals(String userId) {
        SQLiteDatabase database = helper.getReadableDatabase();
        List<MealDTO> meals = new ArrayList<>();
        Cursor cursor = database.query(helper.TABLE_MEALS, null, " userId = ?", new String[]{userId}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                MealDTO meal = cursorToMeal(cursor);
                meals.add(meal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return meals;
    }

    private MealDTO cursorToMeal(Cursor cursor) {
        MealDTO meal = new MealDTO();
        meal.setId(cursor.getString(0));
        meal.setUserId(cursor.getString(1));
        meal.setDay(cursor.getString(2));
        meal.setName(cursor.getString(3));
        meal.setArea(cursor.getString(4));
        meal.setAreaImageUrl(cursor.getString(5));
        meal.setCategory(cursor.getString(6));
        meal.setTags(cursor.getString(7));
        meal.setInstructions(cursor.getString(8));
        meal.setImgUrl(cursor.getString(9));
        meal.setVideoUrl(cursor.getString(10));
        meal.setStrIngredient1(cursor.getString(11));
        meal.setStrIngredient2(cursor.getString(12));
        meal.setStrIngredient3(cursor.getString(13));
        meal.setStrIngredient4(cursor.getString(14));
        meal.setStrIngredient5(cursor.getString(15));
        meal.setStrIngredient6(cursor.getString(16));
        meal.setStrIngredient7(cursor.getString(17));
        meal.setStrIngredient8(cursor.getString(18));
        meal.setStrIngredient9(cursor.getString(19));
        meal.setStrIngredient10(cursor.getString(20));
        meal.setStrIngredient11(cursor.getString(21));
        meal.setStrIngredient12(cursor.getString(22));
        meal.setStrIngredient13(cursor.getString(23));
        meal.setStrIngredient14(cursor.getString(24));
        meal.setStrIngredient15(cursor.getString(25));
        meal.setStrIngredient16(cursor.getString(26));
        meal.setStrIngredient17(cursor.getString(27));
        meal.setStrIngredient18(cursor.getString(28));
        meal.setStrIngredient19(cursor.getString(29));
        meal.setStrIngredient20(cursor.getString(30));
        meal.setStrMeasure1(cursor.getString(31));
        meal.setStrMeasure2(cursor.getString(32));
        meal.setStrMeasure3(cursor.getString(33));
        meal.setStrMeasure4(cursor.getString(34));
        meal.setStrMeasure5(cursor.getString(35));
        meal.setStrMeasure6(cursor.getString(36));
        meal.setStrMeasure7(cursor.getString(37));
        meal.setStrMeasure8(cursor.getString(38));
        meal.setStrMeasure9(cursor.getString(39));
        meal.setStrMeasure10(cursor.getString(40));
        meal.setStrMeasure11(cursor.getString(41));
        meal.setStrMeasure12(cursor.getString(42));
        meal.setStrMeasure13(cursor.getString(43));
        meal.setStrMeasure14(cursor.getString(44));
        meal.setStrMeasure15(cursor.getString(45));
        meal.setStrMeasure16(cursor.getString(46));
        meal.setStrMeasure17(cursor.getString(47));
        meal.setStrMeasure18(cursor.getString(48));
        meal.setStrMeasure19(cursor.getString(49));
        meal.setStrMeasure20(cursor.getString(50));
        return meal;
    }

    private ContentValues mealToContentValues(MealDTO meal){
        ContentValues values = new ContentValues();
        values.put("id", meal.getId());
        values.put("userId", meal.getUserId());
        values.put("day", meal.getDay());
        values.put("name", meal.getName());
        values.put("area", meal.getArea());
        values.put("areaImageUrl", meal.getAreaImageUrl());
        values.put("category", meal.getCategory());
        values.put("tags", meal.getTags());
        values.put("instructions", meal.getInstructions());
        values.put("imgUrl", meal.getImgUrl());
        values.put("videoUrl", meal.getVideoUrl());
        values.put("strIngredient1", meal.getStrIngredient1());
        values.put("strIngredient2", meal.getStrIngredient2());
        values.put("strIngredient3", meal.getStrIngredient3());
        values.put("strIngredient4", meal.getStrIngredient4());
        values.put("strIngredient5", meal.getStrIngredient5());
        values.put("strIngredient6", meal.getStrIngredient6());
        values.put("strIngredient7", meal.getStrIngredient7());
        values.put("strIngredient8", meal.getStrIngredient8());
        values.put("strIngredient9", meal.getStrIngredient9());
        values.put("strIngredient10", meal.getStrIngredient10());
        values.put("strIngredient11", meal.getStrIngredient11());
        values.put("strIngredient12", meal.getStrIngredient12());
        values.put("strIngredient13", meal.getStrIngredient13());
        values.put("strIngredient14", meal.getStrIngredient14());
        values.put("strIngredient15", meal.getStrIngredient15());
        values.put("strIngredient16", meal.getStrIngredient16());
        values.put("strIngredient17", meal.getStrIngredient17());
        values.put("strIngredient18", meal.getStrIngredient18());
        values.put("strIngredient19", meal.getStrIngredient19());
        values.put("strIngredient20", meal.getStrIngredient20());

        values.put("strMeasure1", meal.getStrMeasure1());
        values.put("strMeasure2", meal.getStrMeasure2());
        values.put("strMeasure3", meal.getStrMeasure3());
        values.put("strMeasure4", meal.getStrMeasure4());
        values.put("strMeasure5", meal.getStrMeasure5());
        values.put("strMeasure6", meal.getStrMeasure6());
        values.put("strMeasure7", meal.getStrMeasure7());
        values.put("strMeasure8", meal.getStrMeasure8());
        values.put("strMeasure9", meal.getStrMeasure9());
        values.put("strMeasure10", meal.getStrMeasure10());
        values.put("strMeasure11", meal.getStrMeasure11());
        values.put("strMeasure12", meal.getStrMeasure12());
        values.put("strMeasure13", meal.getStrMeasure13());
        values.put("strMeasure14", meal.getStrMeasure14());
        values.put("strMeasure15", meal.getStrMeasure15());
        values.put("strMeasure16", meal.getStrMeasure16());
        values.put("strMeasure17", meal.getStrMeasure17());
        values.put("strMeasure18", meal.getStrMeasure18());
        values.put("strMeasure19", meal.getStrMeasure19());
        values.put("strMeasure20", meal.getStrMeasure20());

        return values;
    }

}
