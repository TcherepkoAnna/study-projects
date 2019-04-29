package org.ibmt.diplom.catcareproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anna on 21.12.2016.
 */

public class ReportRates {


    private long catID;
    private long id;
    private String name;
    private Double energy;
    private Double proteins;
    private Double fats;
    private Double carbs;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", energy=" + energy +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbs=" + carbs;
    }

    public long getCatID() {
        return catID;
    }

    public void setCatID(long catID) {
        this.catID = catID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ReportRates(long catID, long id, String name, Double energy, Double proteins, Double fats, Double carbs) {
        setAll(catID, id, name, energy, proteins, fats, carbs);
    }

    public ReportRates(long catID, String name, Double energy, Double proteins, Double fats, Double carbs) {
        setAll(catID, 0, name, energy, proteins, fats, carbs);
    }

    private void setAll(long catID, long id, String name, Double energy, Double proteins, Double fats, Double carbs) {
        this.catID = catID;
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }


    public Map convertToMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("energy", String.valueOf(getEnergy()));
        map.put("proteins", String.valueOf(getProteins()));
        map.put("fats", String.valueOf(getFats()));
        map.put("carbs", String.valueOf(getCarbs()));

        return map;
    }

    public ArrayList<String> toSringArrayList(){
        ArrayList<String> array = new ArrayList<String>() ;
        array.add(String.valueOf(getEnergy()));
        array.add(String.valueOf(getProteins()));
        array.add(String.valueOf(getFats()));
        array.add(String.valueOf(getCarbs()));
        return array;
    }
}
