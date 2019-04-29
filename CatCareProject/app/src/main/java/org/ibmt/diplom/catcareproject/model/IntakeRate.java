package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Anna on 20.12.2016.
 */

public class IntakeRate {

    private long ID;
    private String name;
    private String gender;
    private int age;
    private Double weight;
    private String sex;
    private String condition;
    private Double energy;
    private Double proteins;
    private Double fats;
    private Double carbs;

    public IntakeRate() {
    }

    public IntakeRate(String name) {
        this.name = name;
    }

    public IntakeRate(long ID, String name, String gender, int age, Double weight, String sex, String condition, Double energy, Double proteins, Double fats, Double carbs) {
        setAll(ID, name, gender, age, weight, sex, condition, energy, proteins, fats, carbs);
    }

    public IntakeRate(String name, String gender, int age, Double weight, String sex, String condition, Double energy, Double proteins, Double fats, Double carbs) {
        setAll(0, name, gender, age, weight, sex, condition, energy, proteins, fats, carbs);
    }

    private void setAll(long ID, String name, String gender, int age, Double weight, String sex, String condition, Double energy, Double proteins, Double fats, Double carbs) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        this.condition = condition;
        this.energy = energy;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return "rate: " + name +
                ", pet gender=" + gender +
                ", age=" + age +
                ", weight=" + weight +
                ", sex=" + sex +
                ", condition=" + condition +
                "; Rates: energy=" + energy +
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
