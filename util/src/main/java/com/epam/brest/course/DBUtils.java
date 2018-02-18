package com.epam.brest.course;

import java.sql.*;

public class DBUtils {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // URL
        // 'test_db' is name of database
        System.out.println("Connect to database.");
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";

        Class.forName("org.h2.Driver");
        Connection connection =
                DriverManager.getConnection(databaseURL, "sa", "");
        return connection;
    }

    public void createUserTable(Connection connection) throws SQLException {
        // create statement - select
        // prepared - update, insert (parameterize)
        // call - stored procedures
        System.out.println("Create app_user table.");

        String createTable =
                "CREATE TABLE app_user (" +
                        "user_id INT NOT NULL AUTO_INCREMENT," +
                        "login VARCHAR(255) NOT NULL," +
                        "password VARCHAR(255) NOT NULL," +
                        "description VARCHAR(255) NULL," +
                        "PRIMARY KEY(user_id))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
        }
    }

    public void addUser(Connection connection, String login, String password, String description) throws SQLException {

        System.out.println(String.format("Add user: %s", login));

        String newUser = "INSERT INTO app_user (login, password, description) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, description);
        preparedStatement.executeUpdate();

    }

    public void removeUser(Connection connection, int id) throws SQLException {

        System.out.println("(1) row was deleted.");

        String delUser = "DELETE FROM app_user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delUser);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }


    public void getUsers(Connection connection) throws SQLException {

        System.out.println("Get users: ");
        String getRecords = "SELECT user_id, login, description " +
                "FROM app_user ORDER BY user_id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getRecords);
        while (resultSet.next()) {
            System.out.println(String.format("User: %s, %s, %s",
                    resultSet.getString("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("description")));
        }

    }

}
