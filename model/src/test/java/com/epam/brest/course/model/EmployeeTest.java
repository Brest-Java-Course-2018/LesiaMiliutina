package com.epam.brest.course.model;

import com.epam.brest.course.model.Employee;
import org.junit.Assert;

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