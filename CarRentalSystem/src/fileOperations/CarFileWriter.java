package fileOperations;

import models.Car;
import models.Rental;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CarFileWriter {
    private final String filename;

    public CarFileWriter(String filename) {
        this.filename = filename;
    }

    private Rental<Car> getActiveRental(Car car) {
        for (Rental<Car> rental : car.getRentals()) { // предполага се, че имаш List<Rental> в Car
            if (rental.isActive()) {
                return rental;
            }
        }
        return null;
    }

    public boolean isFileEmpty() {
        File file = new File(filename);
        return !file.exists() || file.length() == 0;
    }

    public void writeCars(List<Car> cars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            if (isFileEmpty()) {
                writer.write("Id,Make,Model,Year,Type,Status,Renter,From,To");
                writer.newLine();
            }

            for (Car car : cars) {
                StringBuilder line = new StringBuilder();
                line.append(car.getId()).append(",")
                        .append(car.getMake()).append(",")
                        .append(car.getModel()).append(",")
                        .append(car.getYear()).append(",")
                        .append(car.getType());

                Rental<Car> activeRental = car.getActiveRental();

                if (activeRental != null) {
                    line.append(",Rented")
                            .append(",").append(activeRental.getCustomer().getName())
                            .append(",").append(activeRental.getStartDate())
                            .append(",").append(activeRental.getReturnDate() != null ? activeRental.getReturnDate() : "");
                } else {
                    line.append(",Available");
                }

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing in file: " + filename);
        }
    }
}

