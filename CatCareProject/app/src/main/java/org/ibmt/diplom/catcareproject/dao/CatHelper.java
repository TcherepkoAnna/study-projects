package org.ibmt.diplom.catcareproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.ibmt.diplom.catcareproject.model.Cat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 19.12.2016.
 */

public class CatHelper extends DbHelper {

    public CatHelper(Context context) {
        super(context);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Adding new cat
    public long addCat(Cat cat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATS_COLUMN_NAME, cat.getName());
        // Inserting Row
        long newRowId = db.insert(CATS_TABLE_NAME, null, values);
//        db.close(); // Closing database connection
        return newRowId;
    }


    @NonNull
    private ContentValues getContentValues(Cat cat) {
        ContentValues values = new ContentValues();
        values.put(CATS_COLUMN_NAME, cat.getName());
        values.put(CATS_COLUMN_GENDER, cat.getGender());
        values.put(CATS_COLUMN_AGE, cat.getAge());
        values.put(CATS_COLUMN_WEIGHT, cat.getWeight());
        values.put(CATS_COLUMN_CONDITION, cat.getCondition());
        values.put(CATS_COLUMN_IRATE, cat.getIntakeRate());
        values.put(CATS_COLUMN_SEX, cat.getSex());
        values.put(CATS_COLUMN_IMAGE, cat.getImageUri());
        return values;
    }

    // Updating single cat
    public int updateCat(Cat cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValues(cat);
    // updating row
    long id = cat.getId();
    String selection = CATS_COLUMN_ID + "=?";
    String[] selectionArg = new String[]{String.valueOf(id)};
    int numberOfRowsAffected = db.update(CATS_TABLE_NAME, values, selection, selectionArg);
//        db.close();
    return numberOfRowsAffected;
}

    // Deleting single cat
    public int deleteCat(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numAffected = db.delete(CATS_TABLE_NAME, CATS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
//        db.close();
        return numAffected;
    }

    // Getting single Cat
    public Cat getCat(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cat cat = null;
        String[] fields = new String[]{
                CATS_COLUMN_ID, CATS_COLUMN_NAME, CATS_COLUMN_GENDER, CATS_COLUMN_AGE, CATS_COLUMN_WEIGHT, CATS_COLUMN_SEX, CATS_COLUMN_CONDITION, CATS_COLUMN_IRATE, CATS_COLUMN_IMAGE};
        String selection = CATS_COLUMN_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.query(CATS_TABLE_NAME, fields, selection, selectionArg, null, null, null);
        if (c != null) {
            c.moveToFirst();
            if ((!c.isAfterLast())) {
                cat = createCatFromDB(c);
            }
            c.close();
        }
//        db.close();
        return cat;
    }

    // Getting All Cats
    public List<Cat> getAllCats() {
        List<Cat> catList = new ArrayList<Cat>();
        SQLiteDatabase db = this.getReadableDatabase();
        String orderBy = CATS_COLUMN_NAME;
        Cursor c = db.query(CATS_TABLE_NAME, null, null, null, null, null, orderBy);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Cat cat = createCatFromDB(c);
                // Adding cat to list
                catList.add(cat);
            } while (c.moveToNext());
        }
//        db.close();
        // return cat list
        return catList;
    }

    @NonNull
    private Cat createCatFromDB(Cursor c) {
        return new Cat(
                c.getLong(c.getColumnIndex(CATS_COLUMN_ID)),
                c.getString(c.getColumnIndex(CATS_COLUMN_NAME)),
                c.getString(c.getColumnIndex(CATS_COLUMN_GENDER)),
                c.getInt(c.getColumnIndex(CATS_COLUMN_AGE)),
                c.getDouble(c.getColumnIndex(CATS_COLUMN_WEIGHT)),
                c.getString(c.getColumnIndex(CATS_COLUMN_SEX)),
                c.getString(c.getColumnIndex(CATS_COLUMN_CONDITION)),
                c.getLong(c.getColumnIndex(CATS_COLUMN_IRATE)),
                c.getString(c.getColumnIndex(CATS_COLUMN_IMAGE)));
    }

    // Getting cats Count
    public int getCatsCount() {
        String countQuery = "SELECT  * FROM " + CATS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
//        db.close();
        // return count
        return cursor.getCount();
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
