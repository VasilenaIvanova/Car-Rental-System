package interfaces;

import models.Customer;

import java.time.LocalDate;

public interface Rentable {
    void rent(Customer customer, LocalDate startDate, LocalDate returnDate);
    void returnCar();
}
