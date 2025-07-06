package files;

import models.Rental;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CarFileWriter {
    private String filename;

    public CarFileWriter(String filename) {
        this.filename = filename;
    }

    public void writeCars(List<Rental> cars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Id,Make,Model,Year,Type,Status,Renter,From,To");
            writer.newLine();


            for (Rental car : cars) {
                StringBuilder line = new StringBuilder();
                line.append(car.getId()).append(",")
                        .append(car.getMake()).append(",")
                        .append(car.getModel()).append(",")
                        .append(car.getYear()).append(",")
                        .append(car.getType()).append(",")
                        .append(car.getStatus());

                if ("Rented".equalsIgnoreCase(car.getStatus()) && car.getCustomer() != null) {
                    line.append(",")
                            .append(car.getCustomer().getName()).append(",")
                            .append(car.getStartDate()).append(",")
                            .append(car.getReturnDate());
                }

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing in file: " + filename);
        }
    }
}
