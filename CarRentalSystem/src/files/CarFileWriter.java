package files;

import models.Car;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CarFileWriter {
    private String filename;

    public CarFileWriter(String filename){
        this.filename = filename;
    }

    public void writeCars(List<Car> cars){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Id,Make,Model,Year,Type,Status,Renter,From,To");
            writer.newLine();

            for(Car car: cars){
                String line = car.getId()+","+
                        car.getMake()+","+
                        car.getModel()+","+
                        car.getYear()+","+
                        car.getType()+","+
                        car.getStatus()+","+
                        car.getRenter()+","+
                        car.getStartDate()+","+
                        car.getReturnDate();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing in file: " + filename);
        }
    }
}
