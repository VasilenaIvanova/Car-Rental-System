package models;

import interfaces.Rentable;

import java.time.LocalDate;

public class Car extends Vehicle implements Rentable {
    private String renter;
    private LocalDate startDate;
    private LocalDate returnDate;

    public Car(int id, String make, String model, int year, String type) {
        super(id, make, model, year, type);
        this.renter = "";
        this.startDate = null;
        this.returnDate = null;
    }

    public String getRenter() {
        return this.renter;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public void information() {
        System.out.print("Car -> ID: "+id+", Make: "+make+", Model: "+model+", Year: "+year+", Type: "+type+", Status: "+status);
        if(status.equals("Rented")){
            System.out.println(", Renter: "+renter+", From: "+startDate+", To: "+returnDate);
        }else{
            System.out.println();
        }
    }

    @Override
    public void rent(String renterName, LocalDate startDate, LocalDate returnDate) {
        if(this.status.equals("Available")){
            this.status = "Rented";
            this.renter = renterName;
            this.startDate = startDate;
            this.returnDate = returnDate;
            System.out.println("Car -> ID: "+id+" rented to "+renterName+" for period: "+startDate+","+returnDate);
        }else{
            System.out.println("Car -> ID: "+id+" is not available for rent.");
        }
    }

    @Override
    public void returnCar() {
        if(this.status.equals("Rented")){
            this.status = "Available";
            this.renter = "";
            this.startDate = null;
            this.returnDate = null;
            System.out.println("Car -> ID: "+id+" has been returned.");
        }else{
            System.out.println("Car -> ID: "+id+" is not rented.");
        }
    }
}
