package org.ibmt.diplom.catcareproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.ibmt.diplom.catcareproject.model.Food;

import java.util.ArrayList;
import java.util.List;


public class FoodHelper extends DbHelper {

    public FoodHelper(Context context) {
        super(context);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new food
   public long addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValues(food);
       // Inserting Row
       long newRowId = db.insert(FOODS_TABLE_NAME, null, values);

//        db.close(); // Closing database connection
       return newRowId;
   }

    @NonNull
    private ContentValues getContentValues(Food food) {
        ContentValues values = new ContentValues();
        values.put(FOODS_COLUMN_NAME, food.getName());
        values.put(FOODS_COLUMN_STATE, food.getState());
        values.put(FOODS_COLUMN_ENERGY, food.getEnergy());
        values.put(FOODS_COLUMN_PROTEINS, food.getProteins());
        values.put(FOODS_COLUMN_FATS, food.getFats());
        values.put(FOODS_COLUMN_CARBS, food.getCarbs());
        return values;
    }

    // Getting single food
    public Food getFood(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Food food = null;
        String[] fields = new String[]{
                FOODS_COLUMN_ID, FOODS_COLUMN_NAME, FOODS_COLUMN_STATE, FOODS_COLUMN_ENERGY, FOODS_COLUMN_PROTEINS, FOODS_COLUMN_FATS, FOODS_COLUMN_CARBS};
        String selection = FOODS_COLUMN_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.query(FOODS_TABLE_NAME, fields, selection, selectionArg, null, null, null, null);
        if (c != null) {
            c.moveToFirst();

            if ((!c.isAfterLast())) {
                food = createFoodFromDB(c);
            }
        }
        c.close();
//        db.close(); // Closing database connection
        return food;
    }

    // Getting All Foods
    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<Food>();
        SQLiteDatabase db = this.getReadableDatabase();
        String orderBy = FOODS_COLUMN_NAME;
        Cursor c = db.query(FOODS_TABLE_NAME, null, null, null, null, null, orderBy);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Food food = createFoodFromDB(c);
                // Adding food to list
                foodList.add(food);
            } while (c.moveToNext());
        }
        c.close();
//        db.close(); // Closing database connection
        // return foods list
        return foodList;
    }

    @NonNull
    private Food createFoodFromDB(Cursor c) {
        return new Food(
                c.getLong(c.getColumnIndex(FOODS_COLUMN_ID)),
                c.getString(c.getColumnIndex(FOODS_COLUMN_NAME)),
                c.getString(c.getColumnIndex(FOODS_COLUMN_STATE)),
                c.getDouble(c.getColumnIndex(FOODS_COLUMN_ENERGY)),
                c.getDouble(c.getColumnIndex(FOODS_COLUMN_PROTEINS)),
                c.getDouble(c.getColumnIndex(FOODS_COLUMN_FATS)),
                c.getDouble(c.getColumnIndex(FOODS_COLUMN_CARBS)));
    }

    // Updating single food
    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FOODS_COLUMN_NAME, food.getName());
        values.put(FOODS_COLUMN_STATE, food.getState());
        values.put(FOODS_COLUMN_ENERGY, food.getEnergy());
        values.put(FOODS_COLUMN_PROTEINS, food.getProteins());
        values.put(FOODS_COLUMN_FATS, food.getFats());
        values.put(FOODS_COLUMN_CARBS, food.getCarbs());

        // updating row
        long id = food.getID();
        String selection = FOODS_COLUMN_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        int numberOfRowsAffected = db.update(FOODS_TABLE_NAME, values, selection, selectionArg);
//        db.close();
        return numberOfRowsAffected;
    }

    // Deleting single food
    public int deleteFood(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numAffected = db.delete(FOODS_TABLE_NAME, FOODS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
//        db.close();
        return numAffected;
    }

    // Getting foods Count
    public int getFoodsCount() {
        String countQuery = "SELECT  * FROM " + FOODS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int  i = cursor.getCount();
        cursor.close();

        // return count
        return i;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
