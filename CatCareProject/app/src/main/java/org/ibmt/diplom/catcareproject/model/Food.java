package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Аня on 18.12.2016.
 */

public class Food  {

    private long ID;
    private String name;
    private String state;
    private Double energy;
    private Double proteins;
    private Double fats;
    private Double carbs;

    public Food() {
        super();
    }

    public Food(String name, String state, Double energy, Double proteins, Double fats, Double carbs) {
        this.name = name;
        this.state = state;
        this.energy = energy;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public Food(long ID, String name, String state, Double energy, Double proteins, Double fats, Double carbs) {
        this.ID = ID;
        this.name = name;
        this.state = state;
        this.energy = energy;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return "" + name +
                ", " + state +
                ", energy=" + energy +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbs=" + carbs +
                "; \n ";
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

}