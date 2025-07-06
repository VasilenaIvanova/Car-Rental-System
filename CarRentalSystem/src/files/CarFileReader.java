package files;

import models.Customer;
import models.Rental;

import java.io.BufferedReader;
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

    public List<Rental> readCars() {
        List<Rental> cars = new ArrayList<>();

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

                String renter = (parts.length > 6) ? parts[6].trim() : "";
                String fromStr = (parts.length > 7) ? parts[7].trim() : "";
                String toStr = (parts.length > 8) ? parts[8].trim() : "";

                LocalDate from = (!fromStr.isEmpty() && !fromStr.equals("null")) ? LocalDate.parse(fromStr) : null;
                LocalDate to = (!toStr.isEmpty() && !toStr.equals("null")) ? LocalDate.parse(toStr) : null;

                Customer customer = (!renter.isEmpty()) ? new Customer(renter, fromStr, toStr) : null;
                Rental car = new Rental(id, make, model, year, type, status, customer, from, to);
                car.setStatus(status);
                car.setCustomer(customer);
                car.setStartDate(from);
                car.setReturnDate(to);
                cars.add(car);

            }
        } catch (IOException e) {
            System.out.println("Error while reading the file.");
        }
        return cars;
    }
}
