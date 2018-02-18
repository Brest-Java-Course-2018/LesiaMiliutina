package com.epam.brest.course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class DBUtilsTest {

    private Connection connection;

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }


    // Test for adding a user

    @Before
    public void setUpDB() throws SQLException, ClassNotFoundException {

        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(databaseURL, "sa", "");
    }

    @Before
    public void createTable() throws SQLException {

        String request =
                "CREATE TABLE app_user (" +
                        "user_id INT NOT NULL AUTO_INCREMENT," +
                        "login VARCHAR (255) NOT NULL ," +
                        "password VARCHAR (255) NOT NULL ," +
                        "description VARCHAR (255) NULL," +
                        "PRIMARY KEY (user_id))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(request);
        }
    }

    @Test
    public void addUserTest() throws SQLException {

        String request = "SELECT count(*)" +
                "FROM app_user " +
                "WHERE login = 'log' " +
                "AND password = 'pwd' " +
                "AND description = 'dsc'";

        try (Statement statement = connection.createStatement()) {

            new DBUtils().addUser(connection, "log", "pwd", "dsc");
            ResultSet resultSet = statement.executeQuery(request);
            resultSet.next();
            Assert.assertEquals(1, resultSet.getRow());
        }
    }

    @After
    public void breakDBConnection() throws SQLException {

        String request = "DROP TABLE app_user";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(request);
        }
        connection.close();
    }
}
