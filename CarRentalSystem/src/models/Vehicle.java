package models;

import interfaces.Identifiable;
import interfaces.Printable;

public abstract class Vehicle implements Printable, Identifiable {
    private static int idCounter = 1;
    protected int id;
    protected String make;
    protected String model;
    protected int year;
    protected String type;

    public Vehicle(String make, String model, int year, String type) {
        this.id = idCounter++;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public abstract void information();
}
