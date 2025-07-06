package models;

import interfaces.Rentable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Car implements Rentable{
    protected static int idCounter = 1;
    protected int id;
    protected String make;
    protected String model;
    protected int year;
    protected String type;
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
        if(rentals.isEmpty()){
            System.out.println(", Status: Available");
        }else{
            boolean rented = rentals.stream().anyMatch(Rental::isActive);
            System.out.println(", Status: "+(rented?"Rented":"Available"));
        }
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
        System.out.println("Car " + this.getMake() +" "+ this.getModel() + " rented to " + customer.getName() + " from " + startDate + " to " + returnDate);
        return true;
    }

    @Override
    public boolean returnCar(Customer customer, LocalDate returnDate){

        for(Rental rental: rentals){
            if(rental.getCustomer()!= null && rental.getCustomer().equals(customer)){
                rental.setCustomer(null);
                rental.setStartDate(null);
                rental.setReturnDate(null);
                System.out.println("Car " + this.getMake() + " " + this.getModel() + " returned by " + customer.getName());
                return true;
            }
        }
        System.out.println("This car is not rented.");
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

