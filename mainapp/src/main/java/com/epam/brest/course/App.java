package com.epam.brest.course;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Main application class.
 */
public final class App {

  /**
   * Class constructor.
   */
  private App() {
    // Private constructor
  }

  /**
   * Main method of application.
   * @param args program arguments.
   * @throws SQLException if there were some problems in DBUtils class methods.
   * @throws ClassNotFoundException if connection was not established.
   */
  public static void main(final String[] args)
          throws SQLException, ClassNotFoundException {

    System.out.println("Hello World!");

    DBUtils dbUtils = new DBUtils();
    Connection connection = dbUtils.getConnection();

    dbUtils.createUserTable(connection);
    dbUtils.addUser(connection, "admin", "admin", "User admin");
    dbUtils.addUser(connection, "admin1", "admin1", "User admin");
    dbUtils.addUser(connection, "admin2", "admin2", "User admin");
    dbUtils.getUsers(connection);
    dbUtils.removeUser(connection, 2);
    dbUtils.removeUser(connection, 1);
    dbUtils.getUsers(connection);

  }

}
