package files;

import models.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String make = parts[1];
                    String model = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    String type = parts[4];

                    Car car = new Car(make, model, year, type);
                    cars.add(car);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return cars;
    }
}
