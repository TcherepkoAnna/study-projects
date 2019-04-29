package org.ibmt.diplom.catcareproject.model;

/**
 * Created by Anna on 04.03.2017.
 */

public class Contact {

    private long id;
    private String name;
    private String discription;
    private String number;

    public Contact(long id, String name, String discription, String number) {
        setAll(id, name, discription, number);
    }

    public Contact(String name, String discription, String number) {
        setAll(0, name, discription, number);
    }

    private void setAll(long id, String name, String discription, String number) {
        setId(id);
        setName(name);
        setDiscription(discription);
        setNumber(number);
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return name + ": " + number +
                ", \n " + discription + "\n";
    }
}
