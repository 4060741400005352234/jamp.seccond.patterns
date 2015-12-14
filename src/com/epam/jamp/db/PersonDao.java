package com.epam.jamp.db;

import com.epam.jamp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    private final ConnectionCreator connectionCreator = ConnectionCreator.getInstance();

    public void save(Person person) throws SQLException {
        Connection connection = connectionCreator.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Person (ID, F_NAME, L_NAME, AGE, IQ) VALUES (HIBERNATE_SEQUENCE.nextval, ?,?,?,?)");
            try {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getSecondName());
                statement.setInt(3, person.getAge());
                statement.setInt(4, person.getIq());
                statement.execute();
            } finally {
                statement.close();
            }
        } finally {
            connection.close();
        }
    }

    public void update(Person person) throws SQLException {
        Connection connection = connectionCreator.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Person SET IQ = ? WHERE F_NAME = ? AND L_NAME = ? AND AGE = ?");
            try {
                statement.setInt(1, person.getIq());
                statement.setString(2, person.getFirstName());
                statement.setString(3, person.getSecondName());
                statement.setInt(4, person.getAge());
                statement.execute();
            } finally {
                statement.close();
            }
        } finally {
            connection.close();
        }
    }

    public List<Person> find(String name) throws SQLException {
        List<Person> persons = new ArrayList<Person>();
        Connection connection = connectionCreator.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE F_NAME = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            try {
                while (resultSet.next()) {
                    persons.add(parse(resultSet));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            connection.close();
        }
        return persons;
    }

    public List<Person> getAll() throws SQLException {
        List<Person> persons = new ArrayList<Person>();
        Connection connection = connectionCreator.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");
            try {
                while (resultSet.next()) {
                    persons.add(parse(resultSet));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            connection.close();
        }
        return persons;
    }

    private Person parse(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setFirstName(resultSet.getString("F_NAME"));
        person.setSecondName(resultSet.getString("L_NAME"));
        person.setAge(resultSet.getInt("AGE"));
        person.setIq(resultSet.getInt("IQ"));
        return person;
    }
}
