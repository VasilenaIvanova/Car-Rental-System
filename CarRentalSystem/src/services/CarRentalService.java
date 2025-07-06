package services;

import models.Car;
import models.Customer;
import models.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarRentalService {
    private List<Car> cars;

    public CarRentalService(){
        this.cars = new ArrayList<>();
    }

    public CarRentalService(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public void add(Car car) {
        cars.add(car);
    }

    public void remove(int id) {
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId() == id) {
                iterator.remove();
                return;
            }
        }
    }

    public Car getCarById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public List<Car> allCars() {
        return this.cars;
    }

    public List<Rental> allRentals(){
        List<Rental> allRentals = new ArrayList<>();
        for(Car car: cars){
            allRentals.addAll(car.getRentals());
        }
        return allRentals;
    }

    public Car searchById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public List<Car> searchByModel(String model) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equals(model)) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> searchByType(String type) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getType().equals(type)) {
                result.add(car);
            }
        }
        return result;
    }

    public void rent(Car car, Customer customer, LocalDate startDate, LocalDate returnDate) {
        if(car == null){
            return;
        }
        car.rent(customer, startDate, returnDate);
    }

    public void returnCar(Car car, Customer customer, LocalDate returnDate) {
        if (car == null){
            return;
        }
        car.returnCar(customer, returnDate);
    }

    public Rental getActiveRentalByCarId(int carId) {
        Car car = getCarById(carId);
        if (car == null) {
            return null;
        }
        return car.getActiveRental();
    }

}