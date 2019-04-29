package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Anna on 20.12.2016.
 */

public class Calendar {

    private long ID;
    private String date;
    private long mealID;
    private long catID;

    public Calendar(long ID, String date, long mealID, long catID) {
        this.ID = ID;
        this.date = date;
        this.mealID = mealID;
        this.catID = catID;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "ID=" + ID +
                ", date=" + date +
                ", mealID=" + mealID +
                ", catID=" + catID +
                '}';
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getMealID() {
        return mealID;
    }

    public void setMealID(long mealID) {
        this.mealID = mealID;
    }

    public long getCatID() {
        return catID;
    }

    public void setCatID(long catID) {
        this.catID = catID;
    }

    public Calendar(String date, long mealID, long catID) {

        this.date = date;
        this.mealID = mealID;
        this.catID = catID;
    }
}
