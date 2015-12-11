package com.epam.jamp.db;

import com.epam.jamp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonStorage {

    public static final String url = "jdbc:oracle:thin:@eprumossd0143:1523:cheetah";
    public static final String name = "spoobk";
    public static final String password = "spoobk";

    private static Connection connection = null;

    public void savePerson(Person person) throws SQLException {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Person (ID, F_NAME, L_NAME, AGE) VALUES (HIBERNATE_SEQUENCE.nextval, ?,?,?)");
            try {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getSecondName());
                statement.setLong(3, person.getAge());
                statement.execute();
            } finally {
                statement.close();
            }
        } finally {
            connection.close();
        }
    }

    public List<Person> getPerson(String name) throws SQLException {
        DBPersonParser parser = new DBPersonParser();
        List<Person> persons = new ArrayList<Person>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE F_NAME = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            try {
                while (resultSet.next()) {
                    persons.add(parser.parse(resultSet));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            connection.close();
        }
        return persons;
    }

    public List<Person> gteAll() throws SQLException {
        DBPersonParser parser = new DBPersonParser();
        List<Person> persons = new ArrayList<Person>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");
            try {
                while (resultSet.next()) {
                    persons.add(parser.parse(resultSet));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            connection.close();
        }
        return persons;
    }

    private static Connection getConnection() throws SQLException{
        if (connection != null && !connection.isClosed())
            return connection;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
