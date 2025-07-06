package models;

import java.time.LocalDate;

public class Rental {
    private Car car;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate returnDate;
    private boolean isActive;

    public Rental(Car car, Customer customer, LocalDate startDate, LocalDate returnDate) {
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.returnDate = returnDate;
        isActive = true;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
        System.out.println("Car -> ID: " + car.getId() + " is rented to: " + customer);
        System.out.println("From: " + startDate + ", To: " + returnDate);
    }
}
