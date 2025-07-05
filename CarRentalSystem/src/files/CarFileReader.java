package files;

import models.Car;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarFileReader {
    private String filename;

    public CarFileReader(String filename) {
        this.filename = filename;
    }

    public List<Car> readCars() {
        List<Car> cars = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line == null) {
                System.out.println("Empty file.");
                return cars;
            }
            String[] headers = line.split(",");
            if (headers.length < 9) {
                System.out.println("Not enough columns in header!");
                return cars;
            }

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].trim().isEmpty() || parts[3].trim().isEmpty()) {
                    System.out.println("Skipping invalid line: missing ID or Year");
                    continue; // пропускаме този ред
                }
                int id = Integer.parseInt(parts[0].trim());
                String make = parts[1].trim();
                String model = parts[2].trim();
                int year = Integer.parseInt(parts[3].trim());
                String type = parts[4].trim();
                String status = parts[5].trim();
                String renter = parts[6].trim();
                LocalDate startDate = null;
                LocalDate returnDate = null;

                if (parts.length > 7 && !parts[7].trim().isEmpty() && !parts[7].trim().equalsIgnoreCase("null")) {
                    startDate = LocalDate.parse(parts[7].trim());
                }
                if (parts.length > 8 && !parts[8].trim().isEmpty() && !parts[8].trim().equalsIgnoreCase("null")) {
                    returnDate = LocalDate.parse(parts[8].trim());
                }
                Car car = new Car(id, make, model, year, type);
                car.setStatus(status);
                car.setRenter(renter);
                car.setStartDate(startDate);
                car.setReturnDate(returnDate);
                cars.add(car);
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file.");
        }
        return cars;
    }
}
