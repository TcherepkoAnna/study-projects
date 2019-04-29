package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Anna on 20.12.2016.
 */

public class Meal {

    private long ID;
    private long foodID;
    private String foodName;
    private String foodState;
    private Double quantity;


    public Meal(long ID, long foodID, String foodName, String foodState, Double quantity) {
        this.ID = ID;
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodState = foodState;
        this.quantity = quantity;
    }

    public Meal(long foodID, String foodName, String foodState, Double quantity) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodState = foodState;
        this.quantity = quantity;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getFoodID() {
        return foodID;
    }

    public void setFoodID(long foodID) {
        this.foodID = foodID;
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

    @Override
    public String toString() {
        return "\n" + "meal: " +
                foodName +
                ", " + foodState +
                " - " + quantity +
                "gr; \n ";
    }
}
