package org.ibmt.diplom.catcareproject.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.ibmt.diplom.catcareproject.model.ReportFoodQuantityByDate;
import org.ibmt.diplom.catcareproject.model.ReportMealValues;
import org.ibmt.diplom.catcareproject.model.ReportMealValuesByDate;
import org.ibmt.diplom.catcareproject.model.ReportRates;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 21.12.2016.
 */

public class ReportHelper extends DbHelper {

    NumberFormat nf = new DecimalFormat("#0.00");
    int yourScale = 10;

    public ReportHelper(Context context) {
        super(context);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    // 0) get cat's  selected intake rate's parameters
    public ReportRates getReportRates(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ReportRates report = null;
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(GET_RATES_FOR_CAT, selectionArg);
        if (c != null) {
            c.moveToFirst();
            if ((!c.isAfterLast())) {
                report = createReportRatesFromDB(c);
            }
            c.close();
        }
        db.close();
        return report;
    }

    @NonNull
    private ReportRates createReportRatesFromDB(Cursor c) {
        return new ReportRates(
                c.getLong(c.getColumnIndex(CATS_COLUMN_ID)),
                c.getLong(c.getColumnIndex(INTAKERATES_COLUMN_ID)),
                c.getString(c.getColumnIndex(INTAKERATES_COLUMN_NAME)),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_ENERGY))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_PROTEINS))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_FATS))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(INTAKERATES_COLUMN_CARBS))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }


    //1) get meals and its nutrition values for certain cat
    public List<ReportMealValues> getReportMValues(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ReportMealValues> reports = new ArrayList<ReportMealValues>();
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(GET_ALL_MEALS_VALUES_FOR_CAT, selectionArg);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ReportMealValues report = createReportMValuesFromDB(c);
                // Adding report to list
                reports.add(report);
            } while (c.moveToNext());
        }
        db.close();
        // return reports list
        return reports;
    }

    private ReportMealValues createReportMValuesFromDB(Cursor c) {
        return new ReportMealValues(
                c.getString(c.getColumnIndex(CALENDAR_COLUMN_DATE)),
                c.getString(c.getColumnIndex(MEALS_COLUMN_FOOD_NAME)),
                c.getString(c.getColumnIndex(MEALS_COLUMN_FOOD_STATE)),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(DAY_QUANTITY))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(ENERGY_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(PROTEIN_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(FATS_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(CARBS_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }


    //2) get meals' nutrition values for certain cat summed by date
    public List<ReportMealValuesByDate> getReportMValuesByDate(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ReportMealValuesByDate> reports = new ArrayList<ReportMealValuesByDate>();
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(GET_ALL_MEALS_SUM_VALUES_BYDATE_RATES_FOR_CAT, selectionArg);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ReportMealValuesByDate report = createReportMValuesByDateFromDB(c);
                // Adding report to list
                reports.add(report);
            } while (c.moveToNext());
        }
        db.close();
        // return reports list
        return reports;
    }

    private ReportMealValuesByDate createReportMValuesByDateFromDB(Cursor c) {
        return new ReportMealValuesByDate(
                c.getString(c.getColumnIndex(CALENDAR_COLUMN_DATE)),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(DAY_QUANTITY))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(ENERGY_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(PROTEIN_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(FATS_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue(),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(CARBS_VALUE))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    //3) get food quantity consumed by cat summed by date
    public List<ReportFoodQuantityByDate> getReportFQuantityByDate(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ReportFoodQuantityByDate> reports = new ArrayList<ReportFoodQuantityByDate>();
        ReportFoodQuantityByDate report = null;
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(GET_FOOD_QUANTITY_BYDATE_FOR_CAT, selectionArg);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                report = createReportFQuantityByDateFromDB(c);
                // Adding report to list
                reports.add(report);
            } while (c.moveToNext());
        }
        db.close();
        // return reports list
        return reports;
    }

    private ReportFoodQuantityByDate createReportFQuantityByDateFromDB(Cursor c) {
        return new ReportFoodQuantityByDate(
                c.getString(c.getColumnIndex(CALENDAR_COLUMN_DATE)),
                BigDecimal.valueOf(c.getDouble(c.getColumnIndex(DAY_QUANTITY))).setScale(yourScale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    //  4) get daily average food quantity consumed by cat
    public String getReportDailyAvgFood(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String avgQuantity = "";
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(GET_DAILY_AVG_FOOD_QUANTITY_FOR_CAT, selectionArg);
        if (c != null) {
            c.moveToFirst();
            if ((!c.isAfterLast())) {
                avgQuantity = nf.format(c.getDouble(c.getColumnIndex(AVG_DAY_QUANTITY)));
            }
            c.close();
        }
        db.close();
        return avgQuantity;
    }


}
