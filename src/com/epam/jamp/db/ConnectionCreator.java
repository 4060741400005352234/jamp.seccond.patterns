package com.epam.jamp.db;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionCreator {

    //public static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String url = "jdbc:oracle:thin:@eprumossd0143:1523:cheetah";
    public static final String name = "spoobk";
    public static final String password = "spoobk";

    private static volatile ConnectionCreator instance;

    private ConnectionCreator() {
    }

    public static ConnectionCreator getInstance() {
        if (instance == null) {
            synchronized (ConnectionCreator.class) {
                if (instance == null) {
                    instance = new ConnectionCreator();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
