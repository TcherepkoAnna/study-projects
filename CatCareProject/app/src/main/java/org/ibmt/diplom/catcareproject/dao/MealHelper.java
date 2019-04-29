package org.ibmt.diplom.catcareproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.ibmt.diplom.catcareproject.model.Meal;


/**
 * Created by Anna on 20.12.2016.
 */

public class MealHelper extends DbHelper {

    public MealHelper(Context context) {
        super(context);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    public long addMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MEALS_COLUMN_FOOD_ID, meal.getFoodID());
        values.put(MEALS_COLUMN_FOOD_NAME, meal.getFoodName());
        values.put(MEALS_COLUMN_FOOD_STATE, meal.getFoodState());
        values.put(MEALS_COLUMN_QUANTITY, meal.getQuantity());
        // Inserting Row
        long newRowId = db.insert(MEALS_TABLE_NAME, null, values);
        return newRowId;
    }

    // Deleting single meal
    public int deleteMeal(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numAffected = db.delete(MEALS_TABLE_NAME, MEALS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        return numAffected;
    }



}
