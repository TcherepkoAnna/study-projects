package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Anna on 19.12.2016.
 */

public class Cat {

    private long id;
    private String name;
    private String imageUri;
    private String sex;
    private String condition;
    private String gender;
    private long intakeRate;
    private int age;
    private Double weight;

    public Cat() {
        super();
    }

    public Cat(String name) {
        setName(name);
    }

    public Cat(String name, String gender, int age, Double weight, String sex, String condition, long intakeRate) {
        setAll(0, name, gender, age, weight, sex, condition, intakeRate, "");
    }

    public Cat(long id, String name, String gender, int age, Double weight, String sex, String condition, long intakeRate ) {
        setAll(id, name, gender, age, weight, sex, condition, intakeRate, "");
    }

    public Cat(long id, String name, String gender, int age, Double weight, String sex, String condition, long intakeRate, String imageUri) {
        setAll(id, name, gender, age, weight, sex, condition, intakeRate, imageUri);
    }

    private void setAll(long id, String name, String gender, int age, Double weight, String sex, String condition, long intakeRate, String imageUri) {
        setId(id);
        setName(name);
        setSex(sex);
        setCondition(condition);
        setGender(gender);
        setIntakeRate(intakeRate);
        setAge(age);
        setWeight(weight);
        setImageUri(imageUri);
    }


    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSex() {
        return sex;
    }

    public String getCondition() {
        return condition;
    }

    public long getIntakeRate() {
        return intakeRate;
    }

    public int getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setIntakeRate(long intakeRate) {
        this.intakeRate = intakeRate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return  name + ": " + gender +
                ", age=" + age +
                ", weight=" + weight +
                ", sex=" + sex +
                ", condition=" + condition +
                ", intakeRate=" + intakeRate + " \n ";
    }


}