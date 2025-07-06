package models;

import java.time.LocalDate;

public class Rental<T extends Vehicle> {
    private T vehicle;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate returnDate;
    private boolean isActive;

    public Rental(T vehicle, Customer customer, LocalDate startDate, LocalDate returnDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.returnDate = returnDate;
        isActive = true;
    }

    public T getVehicle() {
        return vehicle;
    }

    public void setCar(T vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void information() {
        System.out.println("Vehicle ID: " + vehicle.getId() + " is rented to: " + customer);
        System.out.println("From: " + startDate + ", To: " + returnDate);
    }
}
