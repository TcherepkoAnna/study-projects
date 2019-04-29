package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Anna on 22.12.2016.
 */

public class ReportMealValues {

    private long id;
    private String date;
    private String foodName;
    private String foodState;
    private Double quantity;
    private Double energyValue;
    private Double proteinValue;
    private Double fatsValue;
    private Double carbsValue;


    public ReportMealValues(long id, String date, String foodName, String foodState, Double quantity, Double energyValue, Double proteinValue, Double fatsValue, Double carbsValue) {
        setAll(id, date, foodName, foodState, quantity, energyValue, proteinValue, fatsValue, carbsValue);
    }

    public ReportMealValues(String date, String foodName, String foodState, Double quantity, Double energyValue, Double proteinValue, Double fatsValue, Double carbsValue) {
        setAll(0, date, foodName, foodState, quantity, energyValue, proteinValue, fatsValue, carbsValue);
    }

    private void setAll(long id, String date, String foodName, String foodState, Double quantity, Double energyValue, Double proteinValue, Double fatsValue, Double carbsValue) {
        setId(id);
        setDate(date);
        setFoodName(foodName);
        setFoodState(foodState);
        setQuantity(quantity);
        setEnergyValue(energyValue);
        setProteinValue(proteinValue);
        setFatsValue(fatsValue);
        setCarbsValue(carbsValue);
    }

    @Override
    public String toString() {
        return date +
                ", food=" + foodName +
                ", " + foodState +
                ", quantity=" + quantity +
                ", energy=" + energyValue +
                ", proteins=" + proteinValue +
                ", fats=" + fatsValue +
                ", carbs=" + carbsValue + "\n";
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodState() {
        return foodState;
    }

    public void setFoodState(String foodState) {
        this.foodState = foodState;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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