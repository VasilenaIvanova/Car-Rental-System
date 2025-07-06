package services;


import models.Customer;
import models.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarRentalService {
    private List<Rental> cars;

    public CarRentalService(List<Rental> cars) {
                this.cars = new ArrayList<>(cars);
    }

    public void add(Rental car){
        cars.add(car);
    }

    public void remove(int id){
        Iterator<Rental> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Rental car = iterator.next();
            if (car.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public Rental getCarById(int id){
        for(Rental car: cars){
            if(car.getId() == id){
                return car;
            }
        }
        return null;
    }

    public List<Rental> allCars(){
       // return new ArrayList<>(cars);
        return this.cars;
    }

    public Rental searchById(int id){
        for(Rental car: cars){
            if(car.getId() == id){
                return car;
            }
        }
        return null;
    }

    public List<Rental> searchByModel(String model){
        List<Rental> result = new ArrayList<>();
        for(Rental car: cars){
            if(car.getModel().equals(model)){
                result.add(car);
            }
        }
        return result;
    }

    public List<Rental> searchByStatus(String status){
        List<Rental> result = new ArrayList<>();
        for(Rental car: cars){
            if(car.getStatus().equals(status)){
                result.add(car);
            }
        }
        return result;
    }

    public List<Rental> searchByPeriod(LocalDate startDate, LocalDate returnDate){
        List<Rental> result = new ArrayList<>();
        for(Rental car: cars){
            if(car.getStatus().equals("Rented")){
                if(car.getReturnDate().isBefore(startDate) || returnDate.isBefore(car.getStartDate())){
                    result.add(car);
                }
            }else{
                result.add(car);
            }
        }
        return result;
    }

    public void rent(int id, Customer customer, LocalDate startDate, LocalDate returnDate){
        Rental car = getCarById(id);
        if(car != null && car.getStatus().equals("Available")){
            car.rent(customer, startDate, returnDate);
            System.out.println("Car rented successfully.");
        }else if(car != null && car.getStatus().equals("Rented") && car.getReturnDate().isBefore(startDate)){
            car.rent(customer, startDate, returnDate);
            System.out.println("Car rented successfully.");
        }else if(car == null || car.getStatus().equals("Rented") && car.getReturnDate().isAfter(startDate)){
            System.out.printf("Car is not available or not found.");
        }
    }

    public void returnCar(int id){
        Rental car = getCarById(id);
        if(car != null && car.getStatus().equals("Rented")){
            car.returnCar();
            System.out.println("Car returned successfully.");
        }else{
            System.out.printf("Car is not rented or not found.");
        }
    }

}
