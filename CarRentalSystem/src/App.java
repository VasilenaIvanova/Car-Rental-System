import fileOperations.CarFileReader;
import fileOperations.CarFileWriter;
import management.RentalManagement;
import models.Car;
import services.CarRentalService;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Car Rental System!");
        System.out.println("Available commands: add, list, edit, remove, rent, return, search, exit");

        CarFileReader reader = new CarFileReader("D:\\Sirma Academy\\Exam\\CarRentalSystem\\src\\cars.csv");
        CarFileWriter writer = new CarFileWriter("D:\\Sirma Academy\\Exam\\CarRentalSystem\\src\\cars.csv");

        List<Car> cars = null;
        try {
            cars = reader.readCars();
        } catch (Exception e) {
            System.out.println("Error while reading cars");
            return;
        }

        CarRentalService service = new CarRentalService(cars);
        RentalManagement manager = new RentalManagement(service, writer);

        boolean isRunning = true;
        while (isRunning) {
            System.out.print("\n> Enter command: ");
            String command = scanner.nextLine();
            isRunning = manager.execute(command);
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

}
