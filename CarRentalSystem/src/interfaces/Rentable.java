package interfaces;

import java.time.LocalDate;

public interface Rentable {
    void rent(String renterName, LocalDate startDate, LocalDate returnDate);
    void returnCar();
}
