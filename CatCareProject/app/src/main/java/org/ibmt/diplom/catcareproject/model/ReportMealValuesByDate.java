package org.ibmt.diplom.catcareproject.model;


/**
 * Created by Anna on 21.12.2016.
 */

public class ReportMealValuesByDate {

    private long id;
    private String date;
    private Double quantity;
    private Double energyValue;
    private Double proteinValue;
    private Double fatsValue;
    private Double carbsValue;

    @Override
    public String toString() {
        return "date=" + date +
                ", food quantity=" + quantity +
                ", energyValue=" + energyValue +
                ", proteinValue=" + proteinValue +
                ", fatsValue=" + fatsValue +
                ", carbsValue=" + carbsValue +
                "\n";
    }

    public ReportMealValuesByDate(long id, String date, Double quantity, Double energyValue, Double proteinValue, Double fatsValue, Double carbsValue) {
        setAll(id, date, quantity, energyValue, proteinValue, fatsValue, carbsValue);
    }

    public ReportMealValuesByDate(String date, Double quantity, Double energyValue, Double proteinValue, Double fatsValue, Double carbsValue) {
        setAll(0, date, quantity, energyValue, proteinValue, fatsValue, carbsValue);
    }

    private void setAll(long id, String date, Double quantity, Double energyValue, Double proteinValue, Double fatsValue, Double carbsValue) {
        setId(id);
        setDate(date);
        setQuantity(quantity);
        setEnergyValue(energyValue);
        setProteinValue(proteinValue);
        setFatsValue(fatsValue);
        setCarbsValue(carbsValue);
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(Double energyValue) {
        this.energyValue = energyValue;
    }

    public Double getProteinValue() {
        return proteinValue;
    }

    public void setProteinValue(Double proteinValue) {
        this.proteinValue = proteinValue;
    }

    public Double getFatsValue() {
        return fatsValue;
    }

    public void setFatsValue(Double fatsValue) {
        this.fatsValue = fatsValue;
    }

    public Double getCarbsValue() {
        return carbsValue;
    }

    public void setCarbsValue(Double carbsValue) {
        this.carbsValue = carbsValue;
    }
}

