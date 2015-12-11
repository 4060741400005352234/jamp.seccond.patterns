package com.epam.jamp.main;

import com.epam.jamp.manager.Manager;
import com.epam.jamp.model.Person;

import java.util.List;

public class PersonOperator {

    private Manager<Person> personManager;

    public PersonOperator(Manager<Person> personManager) {
        this.personManager = personManager;
    }

    public void imitateOperations() {
        System.out.println("Adding person with name - Alex...");
        Person personAlex = new Person("Alex", "Smith", 25);
        personManager.write(personAlex);

        System.out.println("Adding another person with name - John...");
        Person personJohn = new Person("John", "Pavlov", 30);
        personManager.write(personJohn);

        System.out.println("Getting all persons from store...");
        List<Person> allPersons = personManager.readAll();
        System.out.println("Found persons - " + allPersons.size());
        for (Person person : allPersons) {
            System.out.println("Person from store - " + person.getFirstName());
        }

        System.out.println("Search person by name - Tom...");
        searchPersonByName("Tom");

        System.out.println("Search person by name - Alex...");
        searchPersonByName("Alex");

    }

    private void searchPersonByName(String name) {
        List<Person> persons = personManager.read(name);
        if (persons.size() == 0) {
            System.out.println("Person with " + name + " Tom not found.");
        } else {
            System.out.println("Found " + persons.size() + " person(s) with name " + name);
        }
    }
}
