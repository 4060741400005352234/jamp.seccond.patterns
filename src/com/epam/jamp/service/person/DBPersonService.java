package com.epam.jamp.service.person;

import com.epam.jamp.db.PersonDao;
import com.epam.jamp.model.Person;
import org.apache.log4j.Logger;

import java.util.List;

public class DBPersonService implements PersonService {

    private static Logger log = Logger.getLogger(DBPersonService.class);

    private final PersonDao storage = new PersonDao();

    @Override
    public void write(Person person) {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        try {
            storage.save(person);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> persons = null;
        try {
            persons = storage.getAll();
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
            persons = storage.find(name);
        } catch (Exception e) {
            log.error(e);
        }
        return persons;
    }

    @Override
    public void rewrite(Person person){
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        try {
            storage.update(person);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
