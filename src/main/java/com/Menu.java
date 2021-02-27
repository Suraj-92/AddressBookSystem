package com;
import java.util.*;

public class Menu {
    List<Person> personList = new ArrayList<>();

    public void addPerson() {
        int i = 0;
        String firstName = null;
        final String lastName, address, city, state, phoneNumber, zipCode;
        while (i == 0) {
            System.out.print("Enter First Name : ");
            firstName = GetData.getStringValue();
            if (checkExists(firstName)) {
                System.out.println("Person Name Already Exists!!\nPlease enter different name...");
            } else {
                i = 1;
            }
        }
        System.out.print("Enter Last Name : ");
        lastName = GetData.getStringValue();
        System.out.print("Enter Phone Number : ");
        phoneNumber = GetData.getStringValue();
        System.out.print("Enter Address : ");
        address = GetData.getStringValue();
        System.out.print("Enter city : ");
        city = GetData.getStringValue();
        System.out.print("Enter zip : ");
        zipCode = GetData.getStringValue();
        System.out.print("Enter state : ");
        state = GetData.getStringValue();

        personList.add(new Person(firstName, lastName, address, city, state, phoneNumber, zipCode));
    }

    public void display() {
        if (personList.isEmpty()) {
            System.out.println("No Records To Display!!!");
        } else {
            personList.forEach(System.out::println);
        }

    }

    public void editPerson() throws AddressBookException {
        int id, i = 0;
        String address, city, state, phoneNumber, zipCode;
        try {
            if (personList.isEmpty()) {
                System.out.println("No Records To Edit!!!");
            } else {
                for (Person person : personList) {
                    System.out.println("ID: #" + personList.indexOf(person) + " : " + person);
                }
                System.out.print("\nEnter #ID to Edit Contact : ");
                id = GetData.getIntValue();
                System.out.println(personList.get(id));
                while (i == 0) {
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
                            personList.get(id).setAddress(address);
                            break;
                        case 2:
                            System.out.print("Enter new City : ");
                            city = GetData.getStringValue();
                            personList.get(id).setCity(city);
                            break;
                        case 3:
                            System.out.print("Enter new State : ");
                            state = GetData.getStringValue();
                            personList.get(id).setState(state);
                            break;
                        case 4:
                            System.out.print("Enter new Phone : ");
                            phoneNumber = GetData.getStringValue();
                            personList.get(id).setPhone(phoneNumber);
                            break;
                        case 5:
                            System.out.print("Enter new Zip Code : ");
                            zipCode = GetData.getStringValue();
                            personList.get(id).setZip(zipCode);
                            break;
                        case 6:
                            i = 1;
                            break;
                        default:
                            System.out.println("Please Enter Valid Option");
                    }
                    System.out.println(personList.get(id));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID", AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
    }

    public void delete() throws AddressBookException {
        try {
            int id;
            if (personList.isEmpty()) {
                System.out.println("No Records To Delete!!!");
            } else {
                personList.stream().map(p -> "ID: #" + personList.indexOf(p) + " : " + p).forEach(System.out::println);
                System.out.print("\nEnter #ID to delete Contact : ");
                id = GetData.getIntValue();
                personList.remove(id);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID", AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
    }

    public void sortRecords() {
        System.out.println("Sort By...\n"
                + "1: First Name\n"
                + "2: City\n"
                + "3: State\n"
                + "4: Zip Code\n"
                + "5: Back");
        int choice = GetData.getIntValue();
        switch (choice) {
            case 1:
                Sort.sortByName(personList);
                break;
            case 2:
                Sort.sortByCity(personList);
                break;
            case 3:
                Sort.sortByState(personList);
                break;
            case 4:
                Sort.sortByZipCode(personList);
                break;
            case 5:
                return;
            default:
                System.out.println("Please Enter Valid Option...");
        }
    }

    public boolean checkExists(String firstName) {
        int flag = personList.stream().anyMatch(p -> p.getFirstName().equalsIgnoreCase(firstName)) ? 1 : 0;
        return flag == 1;
    }

    public void searchInRecords() {
        int i = 0;
        while (i == 0) {
            System.out.println("1. Search By City\n" +
                    "2. Search By State\n" +
                    "3. Back\n" +
                    "Choose Your Option");
            int choice = GetData.getIntValue();
            switch (choice) {
                case 1:
                    Sort.searchByCity(personList);
                    break;
                case 2:
                    Sort.searchByState(personList);
                    break;
                case 3:
                    i = 1;
                    break;
                default:
                    System.out.println("Please Enter Correct Option...");
            }
        }
    }
}