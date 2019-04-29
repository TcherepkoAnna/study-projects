package org.ibmt.diplom.catcareproject.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.ibmt.diplom.catcareproject.ConstsDB;

/**
 * Created by Anna on 20.12.2016.
 */
public class DbHelper extends SQLiteOpenHelper implements ConstsDB {

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FOODS_TABLE);
        db.execSQL(CREATE_INTAKERATES_TABLE);
        db.execSQL(CREATE_CATS_TABLE);
        db.execSQL(CREATE_MEALS_TABLE);
        db.execSQL(CREATE_CALENDAR_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);

        db.execSQL(INITIAL_FOODS_ENTRIES);
        db.execSQL(INITIAL_INTAKERATES_ENTRIES);
        db.execSQL(INITIAL_CATS_ENTRIES);
        db.execSQL(INITIAL_MEALS_ENTRIES);
        db.execSQL(INITIAL_CALENDAR_ENTRIES);
        db.execSQL(INITIAL_CONTACTS_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CALENDAR_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MEALS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CATS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + INTAKERATES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOODS_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
