package models;

public class Customer {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer(String name, String phoneNumber, String email) {
        this.id = idCounter++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }
}
