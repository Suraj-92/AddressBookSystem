package com;

import java.util.Comparator;

public class Person {

    public static Comparator<Person> firstNameSorting = (p1, p2) -> {
        String firstName = p1.getFirstName();
        String firstName2 = p2.getFirstName();
        return firstName.compareToIgnoreCase(firstName2);
    };

    public static Comparator<Person> citySorting = (p1, p2) -> {
        String city1 = p1.getCity();
        String city2 = p2.getCity();
        return city1.compareToIgnoreCase(city2);
    };

    public static Comparator<Person> stateSorting = (p1, p2) -> {
        String state1 = p1.getState();
        String state2 = p2.getState();
        return state1.compareToIgnoreCase(state2);
    };

    public static Comparator<Person> zipSorting = (p1, p2) -> {
        String zipCode1 = p1.getZipCode();
        String zipCode2 = p2.getZipCode();
        return zipCode1.compareToIgnoreCase(zipCode2);
    };
    private final String firstName;
    private final String lastName;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private String zipCode;

    public Person(String firstName, String lastName, String address, String city, String state, String phoneNumber, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode() {
        return getZipCode();
    }

    public void setZip(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "First Name ='" + firstName + '\'' +
                ", Last Name ='" + lastName + '\'' +
                ", Address ='" + address + '\'' +
                ", City ='" + city + '\'' +
                ", State ='" + state + '\'' +
                ", Phone ='" + phoneNumber + '\'' +
                ", Zip ='" + zipCode + '\'' +
                '}';
    }
}