package com;

import java.util.LinkedList;
import java.util.List;

public interface IMenu {
    List<Person> addPerson(List<Person> personList);

    List<Person> editPerson(List<Person> person) throws AddressBookException;

    void display(List<Person> person);

    List<Person> delete(List<Person> personList) throws AddressBookException;

    void sortRecords(List<Person> personList);

    void searchInRecords(List<Person> person);

    boolean checkExists(String firstName, List<Person> person);
}