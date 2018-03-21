package com.epam.brest.course.model;

import org.junit.Assert;

public class EmployeeTest {

  public static final String LEICE = "Leice";
  public static final String MAIL = "mail@test";
  public static final Integer ID = 1;
  public static final Integer SALARY = 28000;


  @org.junit.Test
  public void getEmployeeName() {
    Employee employee = new Employee();
    employee.setEmployeeName(LEICE);
    Assert.assertTrue(employee.getEmployeeName().equals(LEICE));
  }

  @org.junit.Test
  public void getEmployeeId() {
    Employee employee = new Employee();
    employee.setEmployeeId(ID);
    Assert.assertTrue(employee.getEmployeeId().equals(ID));
  }

  @org.junit.Test
  public void getDepartmentId() {
    Employee employee = new Employee();
    employee.setDepartmentId(ID);
    Assert.assertTrue(employee.getDepartmentId().equals(ID));
  }

  @org.junit.Test
  public void getEmployeeMail() {
    Employee employee = new Employee();
    employee.setEmployeeMail(MAIL);
    Assert.assertTrue(employee.getEmployeeMail().equals(MAIL));
  }

  @org.junit.Test
  public void getSalary() {
    Employee employee = new Employee();
    employee.setSalary(SALARY);
    Assert.assertTrue(employee.getSalary().equals(SALARY));
  }
}