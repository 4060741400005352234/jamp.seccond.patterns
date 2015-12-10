package com.epam.jamp.manager;

import com.epam.jamp.model.Person;

import java.util.List;

public interface PersonManager {

    public void writePerson(Person person);
    public List<Person> readAllPerson();
    public Person readPerson(String name);
}
