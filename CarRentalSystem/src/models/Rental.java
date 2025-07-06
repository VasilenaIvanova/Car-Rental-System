package models;

import interfaces.Rentable;

import java.time.LocalDate;

public class Rental extends Car implements Rentable {
    private String status;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate returnDate;

    public Rental(int id, String make, String model, int year, String type, String status, Customer customer, LocalDate startDate, LocalDate returnDate) {
        super(id, make, model, year, type);
        this.status = status;
        this.customer = customer;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void information() {
        System.out.print("Car -> ID: " + id + ", Make: " + make + ", Model: " + model + ", Year: " + year + ", Type: " + type + ", Status: " + status);
        if (status.equals("Rented")) {
            System.out.println(", Renter: " + customer + ", From: " + startDate + ", To: " + returnDate);
        } else {
            System.out.println();
        }
    }

    @Override
    public void rent(Customer customer, LocalDate startDate, LocalDate returnDate) {
        if (this.status.equals("Available")) {
            this.setStatus("Rented");
            this.customer = customer;
            this.startDate = startDate;
            this.returnDate = returnDate;
            System.out.println("Car -> ID: " + id + " rented to " + customer.getName() + " for period: " + startDate + "," + returnDate);
        }else if (this.status.equals("Rented")) {
                if (this.returnDate != null && this.returnDate.isBefore(startDate)) {
                    this.customer = customer;
                    this.startDate = startDate;
                    this.returnDate = returnDate;
                    System.out.println("Car -> ID: " + id + " rented to " + customer.getName() + " for period: " + startDate + " to " + returnDate);
                } else {
                    System.out.println("Car -> ID: " + id + " is already rented during that period.");
                }
        } else {
            System.out.println("Car -> ID: " + id + " is not available for rent.");
        }
    }

    @Override
    public void returnCar() {
        if (this.status.equals("Rented")) {
            this.setStatus("Available");
            this.customer = null;
            this.startDate = null;
            this.returnDate = null;
            System.out.println("Car -> ID: " + id + " has been returned.");
        } else {
            System.out.println("Car -> ID: " + id + " is not rented.");
        }
    }
}
