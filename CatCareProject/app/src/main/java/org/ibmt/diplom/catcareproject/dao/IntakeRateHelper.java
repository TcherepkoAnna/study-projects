package org.ibmt.diplom.catcareproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.ibmt.diplom.catcareproject.model.IntakeRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 20.12.2016.
 */

public class IntakeRateHelper extends DbHelper {

    public IntakeRateHelper(Context context) {
        super(context);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    public long addRate(IntakeRate rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValues(rate);
        // Inserting Row
        long newRowId = db.insert(INTAKERATES_TABLE_NAME, null, values);
        return newRowId;
    }

    @NonNull
    private ContentValues getContentValues(IntakeRate rate) {
        ContentValues values = new ContentValues();
        values.put(INTAKERATES_COLUMN_NAME, rate.getName());
        if (rate.getGender()!=null){
        values.put(INTAKERATES_COLUMN_GENDER, rate.getGender());}
        values.put(INTAKERATES_COLUMN_AGE, rate.getAge());
        values.put(INTAKERATES_COLUMN_WEIGHT, rate.getWeight());
        if (rate.getSex()!=null){
            values.put(INTAKERATES_COLUMN_SEX, rate.getSex());}
        if (rate.getCondition()!=null){
            values.put(INTAKERATES_COLUMN_CONDITION, rate.getCondition());}
        values.put(INTAKERATES_COLUMN_ENERGY, rate.getEnergy());
        values.put(INTAKERATES_COLUMN_PROTEINS, rate.getProteins());
        values.put(INTAKERATES_COLUMN_FATS, rate.getFats());
        values.put(INTAKERATES_COLUMN_CARBS, rate.getCarbs());
        return values;
    }

    // Updating single rate
    public int updateRate(IntakeRate rate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValues(rate);

        // updating row
        long id = rate.getID();
        String selection = INTAKERATES_COLUMN_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        int numberOfRowsAffected = db.update(INTAKERATES_TABLE_NAME, values, selection, selectionArg);
        return numberOfRowsAffected;
    }

    // Deleting single rate
    public int deleteRate(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numAffected = db.delete(INTAKERATES_TABLE_NAME, INTAKERATES_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        return numAffected;
    }


    // Getting single rate
    public IntakeRate getRate(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        IntakeRate rate = null;
        String[] fields = new String[]{
                INTAKERATES_COLUMN_ID, INTAKERATES_COLUMN_NAME, INTAKERATES_COLUMN_GENDER, INTAKERATES_COLUMN_AGE, INTAKERATES_COLUMN_WEIGHT, INTAKERATES_COLUMN_SEX, INTAKERATES_COLUMN_CONDITION,
                INTAKERATES_COLUMN_ENERGY, INTAKERATES_COLUMN_PROTEINS, INTAKERATES_COLUMN_FATS, INTAKERATES_COLUMN_CARBS};
        String selection = INTAKERATES_COLUMN_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.query(INTAKERATES_TABLE_NAME, fields, selection, selectionArg, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            if ((!c.isAfterLast())) {
                rate = createRateFromDB(c);
            }
            c.close();
        }
        return rate;
    }

    // Getting All rates
    public List<IntakeRate> getAllRates() {
        List<IntakeRate> rateList = new ArrayList<IntakeRate>();
        SQLiteDatabase db = this.getReadableDatabase();
        String orderBy = INTAKERATES_COLUMN_NAME;
        Cursor c = db.query(INTAKERATES_TABLE_NAME, null, null, null, null, null, orderBy);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                IntakeRate rate = createRateFromDB(c);
                // Adding rate to list
                rateList.add(rate);
            } while (c.moveToNext());
        }
        // return rate list
        return rateList;
    }

    @NonNull
    private IntakeRate createRateFromDB(Cursor c) {
        return new IntakeRate(
                c.getLong(c.getColumnIndex(INTAKERATES_COLUMN_ID)),
                c.getString(c.getColumnIndex(INTAKERATES_COLUMN_NAME)),
                c.getString(c.getColumnIndex(INTAKERATES_COLUMN_GENDER)),
                c.getInt(c.getColumnIndex(INTAKERATES_COLUMN_AGE)),
                c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_WEIGHT)),
                c.getString(c.getColumnIndex(INTAKERATES_COLUMN_SEX)),
                c.getString(c.getColumnIndex(INTAKERATES_COLUMN_CONDITION)),
                c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_ENERGY)),
                c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_PROTEINS)),
                c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_FATS)),
                c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_CARBS)));
    }

    // Getting rates Count
    public int getRatesCount() {
        String countQuery = "SELECT  * FROM " + INTAKERATES_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
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
