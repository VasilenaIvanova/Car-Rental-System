package management;

import files.CarFileWriter;
import models.Car;
import models.Customer;
import models.Rental;
import services.CarRentalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class RentalManagement {
    private final CarRentalService service;
    private final CarFileWriter writer;
    private final Scanner scanner;

    public RentalManagement(CarRentalService service, CarFileWriter writer) {
        this.service = service;
        this.writer = writer;
        this.scanner = new Scanner(System.in);
    }

    public boolean execute(String command) {
        switch (command.toLowerCase()) {
            case "add":
                handleAdd();
                break;
            case "remove":
                handleRemove();
                break;
            case "edit":
                handleEdit();
                break;
            case "list":
                handleList();
                break;
            case "rent":
                handleRent();
                break;
            case "return":
                handleReturn();
                break;
            case "search":
                handleSearch();
                break;
            case "exit":
                writer.writeCars(service.allCars());
                return false;
            default:
                System.out.println("Try again.");
        }
        return true;
    }

    private void handleAdd() {
        System.out.println("Enter Make: ");
        String make = scanner.nextLine();

        System.out.println("Enter Model: ");
        String model = scanner.nextLine();

        System.out.println("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Type: ");
        String type = scanner.nextLine();

        Car car = new Car(make, model, year, type);
        service.add(car);
        writer.writeCars(service.allCars());
        System.out.println("Car added.");
    }

    private void handleRemove() {
        System.out.println("Enter car ID to remove: ");
        int id = Integer.parseInt(scanner.nextLine());

        service.remove(id);
        writer.writeCars(service.allCars());
        System.out.println("Car removed.");
    }

    private void handleEdit() {
        System.out.print("Enter car ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        Car car = service.getCarById(id);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        System.out.println("Editing car: " + car.getMake() + " " + car.getModel());
        System.out.println("Options for editing:  Make, Model, Year, Type");
        System.out.println("What do you want to edit?: ");
        String option = scanner.nextLine();
        switch(option){
            case "Make":
                System.out.println("Enter a new Make: ");
                String newMake = scanner.nextLine();
                car.setMake(newMake);
                System.out.println("Car edited successfully.");
                break;
            case "Model":
                System.out.println("Enter a new Model: ");
                String newModel = scanner.nextLine();
                car.setModel(newModel);
                System.out.println("Car edited successfully.");
                break;
            case "Year":
                System.out.println("Enter a new Year: ");
                int newYear = Integer.parseInt(scanner.nextLine());
                car.setYear(newYear);
                System.out.println("Car edited successfully.");
                break;
            case "Type":
                System.out.println("Enter a new Type: ");
                String newType = scanner.nextLine();
                car.setType(newType);
                System.out.println("Car edited successfully.");
                break;
            default:
                System.out.println("Invalid option!");
        }

        writer.writeCars(service.allCars());
    }

    private void handleList() {
        List<Car> cars = service.allCars();
        for (Car car : cars) {
            car.information();
        }
    }

    private void handleRent() {
        System.out.println("Enter car ID to rent: ");
        int id = Integer.parseInt(scanner.nextLine());

        Car car = service.getCarById(id);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        System.out.println("Enter a Renter Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter a Renter PhoneNumber: ");
        String phoneNumber = scanner.nextLine();
        if(phoneNumber.length() != 10){
            System.out.println("Invalid phoneNumber!");
            return;
        }
        System.out.println("Enter a Renter Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter period (yyyy-mm-dd)");
        System.out.println("From: ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("To: ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

        Customer customer = new Customer(name, phoneNumber, email);

        if (car.rent(customer, startDate, returnDate)) {
            System.out.println("Car " + car.getMake() +" "+ car.getModel() + " rented to " + customer.getName() + " from " + startDate + " to " + returnDate);
        } else {
            System.out.println("Car could not be rented for the given period.");
        }
        writer.writeCars(service.allCars());
    }

    private void handleReturn() {
        System.out.println("Enter car ID to return: ");
        int id = Integer.parseInt(scanner.nextLine());

        Car car = service.getCarById(id);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        Rental activeRental = car.getActiveRental();
        if (activeRental == null) {
            System.out.println("This car is not currently rented.");
            return;
        }

        System.out.print("Enter renter name: ");
        String name = scanner.nextLine();

        if (!activeRental.getCustomer().getName().equals(name)) {
            System.out.println("Customer name does not match the active rental.");
            return;
        }

        System.out.print("Enter return date (yyyy-mm-dd): ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

        activeRental.setActive(false);
        System.out.println("Car " + car.getMake() + " " + car.getModel() + " returned successfully by " + name + ".");

        writer.writeCars(service.allCars());
    }



    private void handleSearch() {
        System.out.println("Search by Model/Type: ");
        String search = scanner.nextLine();

        if(search.equals("Model"))
        {
            System.out.println("Search by Model: ");
            String model = scanner.nextLine();

            List<Car> result = service.searchByModel(model);
            for (Car car : result) {
                car.information();
            }
        }else if(search.equals("Type"))
        {
            System.out.println("Search by Type: ");
            String type = scanner.nextLine();

            List<Car> result = service.searchByType(type);
            for (Car car : result) {
                car.information();
            }
        }else{
            System.out.println("Invalid search!");
        }
    }


}
