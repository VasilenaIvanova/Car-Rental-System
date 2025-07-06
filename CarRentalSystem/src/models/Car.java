package models;

import interfaces.Rentable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Car implements Rentable{
    private static int idCounter = 1;
    private int id;
    private String make;
    private String model;
    private int year;
    private String type;
    private List<Rental> rentals;

    public Car(String make, String model, int year, String type) {
        this.id = idCounter++;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.rentals = new ArrayList<>();
    }

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

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void information() {
        System.out.print("Car -> ID: " + id + ", Make: " + make + ", Model: " + model + ", Year: " + year + ", Type: " + type);
            boolean rented = rentals.stream().anyMatch(Rental::isActive);
            System.out.println(", Status: "+(rented?"Rented":"Available"));
    }

    @Override
    public boolean rent(Customer customer, LocalDate startDate, LocalDate returnDate) {
        for (Rental rental : rentals) {
            if (rental.getReturnDate().isAfter(startDate)) {
                System.out.println("Car is already rented.");
                return false;
            }
        }
        Rental newRental = new Rental(this, customer, startDate, returnDate);
        rentals.add(newRental);
        return true;
    }

    @Override
    public boolean returnCar(Customer customer, LocalDate returnDate) {
        for (Rental rental : rentals) {
            if (rental.isActive() && rental.getCustomer() != null &&
                    rental.getCustomer().getName().equalsIgnoreCase(customer.getName())) {

                rental.setActive(false); // деактивира рентала
                System.out.println("Rental ended for customer: " + customer.getName());
                return true;
            }
        }
        return false;
    }


    public Rental getActiveRental() {
        for (Rental rental : rentals) {
            if (rental.isActive()) {
                return rental;
            }
        }
        return null;
    }

}

