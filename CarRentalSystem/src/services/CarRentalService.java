package services;

import models.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarRentalService {
    private List<Car> cars;

    public CarRentalService(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public void add(Car car){
        cars.add(car);
    }

    public void remove(int id){
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public Car getCarById(int id){
        for(Car car: cars){
            if(car.getId() == id){
                return car;
            }
        }
        return null;
    }

    public List<Car> allCars(){
        return new ArrayList<>(cars);
    }

    public Car searchById(int id){
        for(Car car: cars){
            if(car.getId() == id){
                return car;
            }
        }
        return null;
    }

    public List<Car> searchByModel(String model){
        List<Car> result = new ArrayList<>();
        for(Car car: cars){
            if(car.getModel().equals(model)){
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> searchByStatus(String status){
        List<Car> result = new ArrayList<>();
        for(Car car: cars){
            if(car.getStatus().equals(status)){
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> searchByPeriod(LocalDate startDate, LocalDate returnDate){
        List<Car> result = new ArrayList<>();
        for(Car car: cars){
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

    public void rent(int id, String renter, LocalDate startDate, LocalDate returnDate){
        Car car = getCarById(id);
        if(car != null && car.getStatus().equals("Available")){
            car.rent(renter, startDate, returnDate);
            System.out.println("Car rented successfully.");
        }else{
            System.out.printf("Car is not available or not found.");
        }
    }

    public void returnCar(int id){
        Car car = getCarById(id);
        if(car != null && car.getStatus().equals("Rented")){
            car.returnCar();
            System.out.println("Car returned successfully.");
        }else{
            System.out.printf("Car is not rented or not found.");
        }
    }

}
