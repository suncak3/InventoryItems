package org.example.dao;

import java.sql.*;

public class DBConnector {
    private static final String POSTGRES_HOST = "ep-summer-hall-a2xvifpm.eu-central-1.aws.neon.tech";
    private static final String PORT = "5432";
    private static final String POSTGRES_DATABASE = "verceldb";
    private static final String URL = "jdbc:postgresql://" + POSTGRES_HOST + ":" + PORT + "/"
            + POSTGRES_DATABASE;
    private static final String USER = "default";
    private static final String PASSWD = "3Ay5DTvBVqMp";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWD);
    }
}
