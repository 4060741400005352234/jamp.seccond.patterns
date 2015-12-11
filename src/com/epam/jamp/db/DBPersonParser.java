package com.epam.jamp.db;

import com.epam.jamp.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPersonParser {

    public Person parse(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setFirstName(resultSet.getString("F_NAME"));
        person.setSecondName(resultSet.getString("L_NAME"));
        person.setAge(resultSet.getLong("AGE"));
        return person;
    }
}
