package com.epam.jamp.manager;

import com.epam.jamp.db.PersonStorage;
import com.epam.jamp.model.Person;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class DBPersonManager implements Manager<Person> {

    private static Logger log = Logger.getLogger(DBPersonManager.class);

    private final PersonStorage storage = new PersonStorage();

    @Override
    public void write(Person person) {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        try {
            storage.savePerson(person);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> persons = null;
        try {
            persons = storage.gteAll();
        } catch (Exception e) {
            log.error(e);
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
            log.error(e);
        }
        return persons;
    }
}
