package com.epam.jamp.facade;

import com.epam.jamp.model.Person;
import com.epam.jamp.service.person.PersonService;

import java.util.List;

public class PersonFacade {

    private PersonService personService;

    public PersonFacade(PersonService personService) {
        this.personService = personService;
    }

    public Person getSmarter(Person personOne, Person personTwo) {
        return personOne.getIq() > personTwo.getIq() ? personOne : personTwo;
    }

    public void moveIqAndStore(Person from, Person to, int iqAmount) {
        from.reduceIq(iqAmount);
        to.increaseIq(iqAmount);
        personService.rewrite(from);
        personService.rewrite(to);
    }

    public void increaseIqAndStore(Person person, int iqAmount) {
        person.increaseIq(iqAmount);
        personService.rewrite(person);
    }

    public void reduceIqAndStore(Person person, int iqAmount) {
        person.reduceIq(iqAmount);
        personService.rewrite(person);
    }

    public void write(Person person) {
        personService.write(person);
    }

    public List<Person> readAll() {
        return personService.readAll();
    }

    public List<Person> read(String name) {
        return personService.read(name);
    }
}
