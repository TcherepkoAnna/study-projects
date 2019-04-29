package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Anna on 22.12.2016.
 */

public class ReportFoodQuantityByDate {

    private long ID;
    private String date;
    private Double quantity;

    public ReportFoodQuantityByDate(String date, Double quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    public ReportFoodQuantityByDate(long ID, String date, Double quantity) {

        this.ID = ID;
        this.date = date;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return date +
                ", quantity=" + quantity +
                "\n";
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
