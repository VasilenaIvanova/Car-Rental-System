package models;

import interfaces.Rentable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Car extends Vehicle implements Rentable{
    private List<Rental> rentals;

    public Car(String make, String model, int year, String type) {
        super(make, model, year, type);
        this.rentals = new ArrayList<>();
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
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

