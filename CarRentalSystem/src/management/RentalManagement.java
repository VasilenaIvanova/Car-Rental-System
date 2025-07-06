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
            case "exit":
                writer.writeCars(service.allCars());
                System.out.println("Data saved!");
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
        System.out.printf("Car added.");
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
                break;
            case "Model":
                System.out.println("Enter a new Model: ");
                String newModel = scanner.nextLine();
                car.setModel(newModel);
                break;
            case "Year":
                System.out.println("Enter a new Year: ");
                int newYear = Integer.parseInt(scanner.nextLine());
                car.setYear(newYear);
                break;
            case "Type":
                System.out.println("Enter a new Type: ");
                String newType = scanner.nextLine();
                car.setType(newType);
                break;
            default:
                System.out.println("Invalid option!");
        }

        writer.writeCars(service.allCars());
        System.out.println("Car edited successfully.");
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
        System.out.println("Enter a Renter Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter period");
        System.out.println("From: ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("To: ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

        Customer customer = new Customer(name, phoneNumber, email);
        boolean rented = car.rent(customer, startDate, returnDate);

        if (rented) {
            System.out.println("Car rented successfully.");
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

        System.out.print("Enter renter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter rental return date (yyyy-MM-dd): ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

        Customer dummyCustomer = new Customer(name, "", ""); // сравнение по име
        boolean returned = car.returnCar(dummyCustomer, returnDate);

        if (returned) {
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("No matching active rental found.");
        }
        writer.writeCars(service.allCars());
    }

    /*
    private void handleSearch() {
        System.out.println("Search by model: ");
        String model = scanner.nextLine();

        List<Rental> result = service.searchByStatus(status);
        for (Rental car : result) {
            car.information();
        }
    }

     */
}
