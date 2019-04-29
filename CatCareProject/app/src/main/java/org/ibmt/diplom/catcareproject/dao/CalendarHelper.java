package org.ibmt.diplom.catcareproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.ibmt.diplom.catcareproject.model.Calendar;

/**
 * Created by Anna on 20.12.2016.
 */

public class CalendarHelper extends DbHelper {

    public CalendarHelper(Context context) {
        super(context);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    public long addCalendar(Calendar calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CALENDAR_COLUMN_DATE, calendar.getDate());
        values.put(CALENDAR_COLUMN_MEAL_ID, calendar.getMealID());
        values.put(CALENDAR_COLUMN_CAT_ID, calendar.getCatID());
        // Inserting Row
        long newRowId = db.insert(CALENDAR_TABLE_NAME, null, values);
        return newRowId;
    }

    // Deleting single calendar entry
    public int deleteCaledarEntry(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numAffected = db.delete(CALENDAR_TABLE_NAME, CALENDAR_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        return numAffected;
    }

}
