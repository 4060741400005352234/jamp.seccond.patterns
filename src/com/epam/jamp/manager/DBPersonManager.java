package com.epam.jamp.manager;

import com.epam.jamp.db.PersonStorage;
import com.epam.jamp.model.Person;

import java.sql.SQLException;
import java.util.List;

public class DBPersonManager implements Manager<Person> {

    private final PersonStorage storage = new PersonStorage();

    @Override
    public void write(Person person) throws SQLException {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        try {
            storage.savePerson(person);
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> persons = null;
        try {
            persons = storage.gteAll();
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public List<Person> read(String name) {
        if (name == null || name.length() == 0) {
            throw new RuntimeException("Incorrect parameter.");
        }
        List<Person> persons = null;
        try {
            persons = storage.getPerson(name);
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
        return persons;
    }
}
