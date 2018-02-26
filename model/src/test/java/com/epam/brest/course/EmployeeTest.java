package com.epam.brest.course;

import org.junit.Assert;

import static org.junit.Assert.*;

public class EmployeeTest {

  public static final String LEICE = "Leice";

  @org.junit.Test
  public void getEmployeeName() {
    Employee employee = new Employee();
    employee.setEmployeeName(LEICE);
    Assert.assertTrue(employee.getEmployeeName().equals(LEICE));
    Assert.assertEquals(LEICE, employee.getEmployeeName());

  }

}