package com.epam.jamp.main;

import com.epam.jamp.facade.PersonFacade;
import com.epam.jamp.service.person.PersonService;
import com.epam.jamp.model.Person;
import org.apache.log4j.Logger;

import java.util.List;

public class PersonOperator {

    private static Logger log = Logger.getLogger(PersonOperator.class);

    private PersonFacade personFacade;

    public PersonOperator(PersonService personService) {
        this.personFacade = new PersonFacade(personService);
    }

    public void imitateOperations() {
        log.info("Adding person with name - Alex...");
        Person personAlex = new Person("Alex", "Smith", 25, 100);
        personFacade.write(personAlex);

        log.info("Adding another person with name - John...");
        Person personJohn = new Person("John", "Pavlov", 30, 95);
        personFacade.write(personJohn);

        log.info("Getting all persons from store...");
        List<Person> allPersons = personFacade.readAll();
        log.info("Found persons - " + allPersons.size());
        for (Person person : allPersons) {
            log.info("Person from store - " + person.getFirstName());
        }

        log.info("Search person by name - Tom...");
        searchPersonByName("Tom");

        log.info("Search person by name - Alex...");
        searchPersonByName("Alex");
    }

    public void imitateIqOperations(){
        List<Person> persons = personFacade.readAll();
        Person personOne = persons.get(0);
        Person personTwo = persons.get(1);
        personFacade.moveIqAndStore(personOne, personTwo, 2);
    }

    private void searchPersonByName(String name) {
        List<Person> persons = personFacade.read(name);
        if (persons.size() == 0) {
            log.info("Person with " + name + " Tom not found.");
        } else {
            log.info("Found " + persons.size() + " person(s) with name " + name);
        }
    }
}
