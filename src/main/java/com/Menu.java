package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Menu implements IMenu {
    public static void searchByCity(List<Person> person) {
        String search;
        List<Person> matches = new ArrayList<>();
        System.out.println("Enter First Name to search : ");
        search = GetData.getStringValue();
        int flag = 0;
        for (Person p : person) {
            if (p.getCity().equalsIgnoreCase(search)) {
                flag = 1;
                matches.add(p);
            }
        }
        if (flag == 1) {
            System.out.println("...Match Found...");
            for (Person p : matches) {
                System.out.println(p);
            }
        } else {
            System.out.println("Match Not Found!!!");
        }
    }

    public static void searchByState(List<Person> person) {
        String search;
        int flag = 0;
        List<Person> matches = new ArrayList<>();
        System.out.println("Enter First Name to search : ");
        search = GetData.getStringValue();
        for (Person p : person) {
            if (p.getState().equalsIgnoreCase(search)) {
                flag = 1;
                matches.add(p);
            }
        }
        if (flag == 1) {
            System.out.println("...Match Found...");
            for (Person p : matches) {
                System.out.println(p);
            }
        } else {
            System.out.println("Match Not Found!!!");
        }
    }

    public static void sortData(List<Person> person, sortOptions sortOptions) {
        person.stream().sorted(sortOptions.comparator).forEach(System.out::println);
    }

    public List<Person> addPerson(List<Person> personList) {
        int flag = 0;
        String firstName = null;
        final String lastName, address, city, state, phone, zip;
        while (flag == 0) {
            System.out.print("Enter First Name : ");
            firstName = GetData.getStringValue();
            if (checkExists(firstName, personList)) {
                System.out.println("Person Name Already Exists!!\nPlease enter different name...");
            } else {
                flag = 1;
            }
        }
        System.out.print("Enter Last Name : ");
        lastName = GetData.getStringValue();
        System.out.print("Enter Phone Number : ");
        phone = GetData.getStringValue();
        System.out.print("Enter Address : ");
        address = GetData.getStringValue();
        System.out.print("Enter city : ");
        city = GetData.getStringValue();
        System.out.print("Enter zip : ");
        zip = GetData.getStringValue();
        System.out.print("Enter state : ");
        state = GetData.getStringValue();
        Person person = new Person(firstName, lastName, address, city, state, zip, phone);
        personList.add(person);
        return personList;
    }

    public void display(List<Person> person) {
        if (person.isEmpty()) {
            System.out.println("No Records To Display!!!");
        } else {
            person.forEach(System.out::println);
        }
    }

    public List<Person> editPerson(List<Person> person) throws AddressBookException {
        int id, flag = 0;
        String address, city, state, phone, zip;
        try {
            if (person.isEmpty()) {
                System.out.println("No Records To Edit!!!");
            } else {
                for (Person person1 : person) {
                    System.out.println("ID: #" + person.indexOf(person1) + " : " + person1);
                }
                System.out.print("\nEnter #ID to Edit Contact : ");
                id = GetData.getIntValue();
                System.out.println(person.get(id));
                while (flag == 0) {
                    System.out.println("What You Want to edit...\n"
                            + "\t1: Address\n"
                            + "\t2: city\n"
                            + "\t3: State\n"
                            + "\t4: Phone\n"
                            + "\t5: Zip Code\n"
                            + "\t6. Save And Exit\n");
                    int choice = GetData.getIntValue();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter new Address : ");
                            address = GetData.getStringValue();
                            person.get(id).setAddress(address);
                            break;
                        case 2:
                            System.out.print("Enter new City : ");
                            city = GetData.getStringValue();
                            person.get(id).setCity(city);
                            break;
                        case 3:
                            System.out.print("Enter new State : ");
                            state = GetData.getStringValue();
                            person.get(id).setState(state);
                            break;
                        case 4:
                            System.out.print("Enter new Phone : ");
                            phone = GetData.getStringValue();
                            person.get(id).setPhone(phone);
                            break;
                        case 5:
                            System.out.print("Enter new Zip Code : ");
                            zip = GetData.getStringValue();
                            person.get(id).setZip(zip);
                            break;
                        case 6:
                            flag = 1;
                            break;
                        default:
                            System.out.println("Please Enter Valid Option");
                    }
                    System.out.println(person.get(id));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID",
                    AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
        return person;
    }

    public List<Person> delete(List<Person> personList) throws AddressBookException {
        try {
            int id;
            if (personList.isEmpty()) {
                System.out.println("No Records To Delete!!!");
            } else {
                personList.stream().map(p -> "ID: #" + personList.indexOf(p) + " : " + p).forEach(System.out::println);
                System.out.print("\nEnter #ID to delete Contact : ");
                id = GetData.getIntValue();
                personList.remove(id);
                WriteToCSV.writeFromDelete(personList);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID",
                    AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
        return personList;
    }

    public void sortRecords(List<Person> personList) {
        System.out.println("Sort By...\n"
                + "1: First Name\n"
                + "2: City\n"
                + "3: State\n"
                + "4: Zip Code\n"
                + "5: Back");
        int choice = GetData.getIntValue();
        switch (choice) {
            case 1:
                sortData(personList, sortOptions.NAME);
                break;
            case 2:
                sortData(personList, sortOptions.CITY);
                break;
            case 3:
                sortData(personList, sortOptions.STATE);
                break;
            case 4:
                sortData(personList, sortOptions.ZIP);
                break;
            case 5:
                return;
            default:
                System.out.println("Please Enter Valid Option...");
        }
    }

    public boolean checkExists(String firstName, List<Person> person) {
        int flag = person.stream()
                .anyMatch(p -> p.getFirstName().equalsIgnoreCase(firstName)) ? 1 : 0;
        return flag == 1;
    }

    public void searchInRecords(List<Person> person) {
        int flag = 0;
        while (flag == 0) {
            System.out.println("1. Search By City\n" +
                    "2. Search By State\n" +
                    "3. Back\n" +
                    "Choose Your Option");
            int choice = GetData.getIntValue();
            switch (choice) {
                case 1:
                    searchByCity(person);
                    break;
                case 2:
                    searchByState(person);
                    break;
                case 3:
                    flag = 1;
                    break;
                default:
                    System.out.println("Please Enter Correct Option...");
            }
        }
    }
}