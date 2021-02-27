package com;

public interface IMenu {
    void addPerson();

    void editPerson() throws AddressBookException;

    void display();

    void delete() throws AddressBookException;

    void sortRecords();

    void searchInRecords();

    boolean checkExists(String firstName);
}
