/**
 * Class in this package contains methods
 * for interaction with database.
 *
 * @author Lesia Miliutina
 * @version 1.0
 */
package com.epam.brest.course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utilities class for database.
 */
public class DBUtils {

  /**
   * Establishing a connection to database.
   * @return Connection the context of connection.
   * @throws ClassNotFoundException if "org.h2.Driver" was not found.
   * @throws SQLException if connection was not established.
   */
  public final Connection getConnection()
          throws ClassNotFoundException, SQLException {
    // URL
    // 'test_db' is name of database
    System.out.println("Connect to database.");
    String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";

    Class.forName("org.h2.Driver");
    Connection connection =
            DriverManager.getConnection(databaseURL, "sa", "");
    return connection;
  }

  /**
   *
   * @param connection the context of connection.
   * @throws SQLException if static SQL statement was not created.
   */
  public final void createUserTable(final Connection connection)
          throws SQLException {
    // create statement - select
    // prepared - update, insert (parameterize)
    // call - stored procedures
    System.out.println("Create app_user table.");

    String createTable =
            "CREATE TABLE app_user ("
                    + "user_id INT NOT NULL AUTO_INCREMENT,"
                    + "login VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "description VARCHAR(255) NULL,"
                    + "PRIMARY KEY(user_id))";
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(createTable);
    }

  }

  /**
   *Add user to table.
   * @param connection the context of connection.
   * @param login as user's login.
   * @param password as user's password.
   * @param description as description of user.
   * @return number of added users.
   * @throws SQLException if precompiled SQL statement was not created.
   */
  public final int addUser(final Connection connection,
                            final String login,
                            final String password,
                            final String description)
          throws SQLException {

    System.out.println(String.format("Add user: %s", login));

    String newUser = "INSERT INTO app_user (login, password, description) "
            + "VALUES (?,?,?)";
    String[] values = {
            login, password, description
    };

    PreparedStatement preparedStatement = connection.prepareStatement(newUser);
    for (int i = 0; i < values.length; i++) {
      preparedStatement.setString(i + 1, values[i]);
    }
    return preparedStatement.executeUpdate();

  }

  /**
   * Delete user from table by its id.
   * @param connection the context of connection.
   * @param id as user's id.
   * @return number of deleted rows.
   * @throws SQLException if precompiled SQL statement was not created.
   */
  public final int removeUser(final Connection connection, final int id)
          throws SQLException {

    System.out.println("(1) row was deleted.");

    String delUser = "DELETE FROM app_user WHERE user_id = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(delUser);
    preparedStatement.setInt(1, id);

    return preparedStatement.executeUpdate();

  }

  /**
   * Display table.
   * @param connection the context of connection.
   * @throws SQLException if static SQL statement was not created.
   */
  public final void getUsers(final Connection connection) throws SQLException {

    System.out.println("Get users: ");
    String getRecords = "SELECT user_id, login, description "
            + "FROM app_user ORDER BY user_id";
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
