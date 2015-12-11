package com.epam.jamp.manager;

import com.epam.jamp.model.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBPersonManager implements Manager<Person> {

    public static final String url = "jdbc:oracle:thin:@eprumossd0143:1523:cheetah";
    public static final String name = "spoobk";
    public static final String password = "spoobk";

    private static Connection connection = null;

    @Override
    public void write(Person person) throws SQLException {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Person (F_NAME, L_NAME, AGE) VALUES (?,?,?)");
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

    @Override
    public List<Person> readAll() {
        return null;
    }

    @Override
    public Person read(String name) {
        return null;
    }

    private static Connection getConnection() {
        if (connection != null)
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
