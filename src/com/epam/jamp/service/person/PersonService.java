package com.epam.jamp.service.person;

import com.epam.jamp.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {

    void write(Person person);

    List<Person> readAll();

    List<Person> read(String name);

    void rewrite(Person person);
}
