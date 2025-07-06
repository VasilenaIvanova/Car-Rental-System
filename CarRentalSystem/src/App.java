import files.CarFileReader;
import files.CarFileWriter;
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

        CarRentalService service = new CarRentalService(reader.readCars());
        RentalManagement manager = new RentalManagement(service, writer);

        boolean running = true;
        while (running) {
            System.out.print("\n> Enter command: ");
            String command = scanner.nextLine();
            running = manager.execute(command);
        }

        scanner.close();
    }
}
