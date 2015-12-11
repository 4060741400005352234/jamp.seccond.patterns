package com.epam.jamp.main;

import com.epam.jamp.manager.Manager;
import com.epam.jamp.model.Person;
import org.apache.log4j.Logger;

import java.util.List;

public class PersonOperator {

    private static Logger log = Logger.getLogger(PersonOperator.class);

    private Manager<Person> personManager;

    public PersonOperator(Manager<Person> personManager) {
        this.personManager = personManager;
    }

    public void imitateOperations() {
        log.info("Adding person with name - Alex...");
        Person personAlex = new Person("Alex", "Smith", 25);
        personManager.write(personAlex);

        log.info("Adding another person with name - John...");
        Person personJohn = new Person("John", "Pavlov", 30);
        personManager.write(personJohn);

        log.info("Getting all persons from store...");
        List<Person> allPersons = personManager.readAll();
        log.info("Found persons - " + allPersons.size());
        for (Person person : allPersons) {
            log.info("Person from store - " + person.getFirstName());
        }

        log.info("Search person by name - Tom...");
        searchPersonByName("Tom");

        log.info("Search person by name - Alex...");
        searchPersonByName("Alex");

    }

    private void searchPersonByName(String name) {
        List<Person> persons = personManager.read(name);
        if (persons.size() == 0) {
            log.info("Person with " + name + " Tom not found.");
        } else {
            log.info("Found " + persons.size() + " person(s) with name " + name);
        }
    }
}
