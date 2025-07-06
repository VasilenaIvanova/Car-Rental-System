package interfaces;

import models.Customer;

import java.time.LocalDate;

public interface Rentable {
    boolean rent(Customer customer, LocalDate startDate, LocalDate returnDate);

    boolean returnCar(Customer customer, LocalDate returnDate);
}
