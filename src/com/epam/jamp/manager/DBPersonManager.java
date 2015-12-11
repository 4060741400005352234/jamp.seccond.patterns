package com.epam.jamp.manager;

import com.epam.jamp.model.Person;

import java.util.List;

public class DBPersonManager implements Manager<Person> {

    @Override
    public void write(Person person) {

    }

    @Override
    public List<Person> readAll() {
        return null;
    }

    @Override
    public Person read(String name) {
        return null;
    }
}
