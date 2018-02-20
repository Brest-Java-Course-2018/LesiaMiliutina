package com.epam.brest.course;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Unit tests for utilities.
 */
public class DBUtilsTest {

  private DBUtils dbUtils = new DBUtils();

  /**
   * Connection test.
   * @throws SQLException if connection was not established.
   * @throws ClassNotFoundException
   */
  @org.junit.Test
  public void getConnection()
          throws SQLException, ClassNotFoundException {

    DBUtils dbUtils = new DBUtils();
    Assert.assertNotNull(dbUtils.getConnection());

  }

  /**
   * Test for adding of users.
   * @throws SQLException if there are some problems in utility methods.
   * @throws ClassNotFoundException
   */
  @org.junit.Test
  public void addUserTest()
          throws SQLException, ClassNotFoundException {

    Connection connection = dbUtils.getConnection();
    Assert.assertEquals(1, dbUtils.
            addUser(connection,"log", "pwd", "dsc"));

  }

  /**
   * Test for removing of users.
   * @throws SQLException if there are some problems in utility methods.
   * @throws ClassNotFoundException
   */
  @org.junit.Test
  public void removeUserTest()
          throws SQLException, ClassNotFoundException {

    Connection connection = dbUtils.getConnection();
    dbUtils.createUserTable(connection);
    dbUtils.addUser(connection,"1", "2", "3");

    Assert.assertEquals(0, dbUtils.removeUser(connection,-14));
    Assert.assertEquals(0, dbUtils.removeUser(connection,81));
    Assert.assertEquals(1, dbUtils.removeUser(connection,1));
    Assert.assertEquals(0, dbUtils.removeUser(connection,0));

  }

}
