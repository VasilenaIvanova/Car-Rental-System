package management;

import files.CarFileWriter;
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

    public boolean execute(String command){
        switch (command.toLowerCase()){
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
                System.out.println("Data saved!");
                return false;
            default:
                System.out.println("Try again.");
        }
        return true;
    }

    private void handleAdd(){
        System.out.println("Enter ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Make: ");
        String make = scanner.nextLine();

        System.out.println("Enter Model: ");
        String model = scanner.nextLine();

        System.out.println("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Type: ");
        String type = scanner.nextLine();

        Rental car = new Rental(id, make, model, year, type, "Available", null, null, null);
        service.add(car);
        writer.writeCars(service.allCars());
        System.out.printf("Car added.");
    }

    private void handleRemove(){
        System.out.println("Enter car ID to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.remove(id);
        writer.writeCars(service.allCars());
        System.out.println("Car removed.");
    }

    private void handleEdit(){
        System.out.println("Enter car ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());
        Rental car = service.getCarById(id);
        if(car == null){
            System.out.println("Car not found.");
            return;
        }
        String oldStatus = car.getStatus();
        System.out.println("New Status(Available/Rented): ");
        car.setStatus(scanner.nextLine());
        if(car.getStatus().equals("Available") && !oldStatus.equals("Available")) {
            car.setCustomer(null);
            car.setStartDate(null);
            car.setReturnDate(null);
            writer.writeCars(service.allCars());
            System.out.println("Car updated.");
        }else if(car.getStatus().equals("Rented") && !oldStatus.equals("Rented")){
            System.out.println("Enter a Renter Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter a Renter PhoneNumber: ");
            String phone = scanner.nextLine();
            System.out.println("Enter a Renter Email: ");
            String email = scanner.nextLine();
            car.setCustomer(new Customer(name, phone, email));
            System.out.println("Enter a start date: ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            car.setStartDate(startDate);
            System.out.println("Enter a return date: ");
            LocalDate returnDate = LocalDate.parse(scanner.nextLine());
            car.setReturnDate(returnDate);
            writer.writeCars(service.allCars());
            System.out.println("Car updated.");
        }else{
            System.out.println("Invalid status.");
        }
    }

    private void handleList(){
        List<Rental> cars = service.allCars();
        for(Rental car: cars){
            car.information();
        }
    }

    private void handleRent(){
        System.out.println("Enter car ID to rent: ");
        int id = Integer.parseInt(scanner.nextLine());

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

        service.rent(id,new Customer(name,phoneNumber,email), startDate, returnDate);
        writer.writeCars(service.allCars());
    }

    private void handleReturn(){
        System.out.println("Enter car ID to return: ");
        int id = Integer.parseInt(scanner.nextLine());

        service.returnCar(id);
        writer.writeCars(service.allCars());
    }

    private void handleSearch(){
        System.out.println("Search by model/status: ");
        String type = scanner.nextLine();

        if(type.equals("model")){
            System.out.println("Enter model: ");
            String model = scanner.nextLine();
            List<Rental> result = service.searchByModel(model);
            for(Rental car: result){
                car.information();
            }
        }else if(type.equals("status")){
            System.out.println("Enter status: ");
            String status = scanner.nextLine();
            List<Rental> result = service.searchByStatus(status);
            for(Rental car: result){
                car.information();
            }
        }else{
            System.out.println("Invalid search type.");
        }
    }
}
