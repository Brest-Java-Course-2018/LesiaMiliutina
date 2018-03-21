package com.epam.brest.course.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

/**
 * POJO Employee for model.
 */
public class Employee {

  /**
   * Employee id.
   */
  private Integer employeeId;
  /**
   * Name of employee.
   */
  @NotEmpty
  @Size(min = 2, max = 255)
  private String employeeName;
  /**
   * Employee mail address.
   */
  @NotEmpty
  @Email
  private String employeeMail;
  /**
   * Employee's salary.
   */
  @NotNull
  @Positive
  private Integer salary;
  /**
   * Department id.
   */
  private Integer departmentId;

  /**
   * Default constructor.
   */
  public Employee() {
  }

  /**
   * Constructor with arguments.
   * @param eName name of employee.
   * @param eMail mail address of employee.
   * @param eSalary employee's salary.
   * @param depId id of department.
   */
  public Employee(final String eName,
                  final String eMail,
                  final Integer eSalary,
                  final Integer depId) {
    this.employeeName = eName;
    this.employeeMail = eMail;
    this.salary = eSalary;
    this.departmentId = depId;
  }

  /**
   * Getter for employee id.
   * @return employee id.
   */
  public final Integer getEmployeeId() {
    return employeeId;
  }

  /**
   * Setter for employee id.
   * @param eId employee id.
   */
  public final void setEmployeeId(final Integer eId) {
    this.employeeId = eId;
  }


  /**
   * Getter for employee name.
   * @return name of employee.
   */
  public final String getEmployeeName() {
    return employeeName;
  }

  /**
   * Setter for employee name.
   * @param eName employee name.
   */
  public final void setEmployeeName(final String eName) {
    this.employeeName = eName;
  }

  /**
   * Getter for employee mail.
   * @return email.
   */
  public final String getEmployeeMail() {
    return employeeMail;
  }

  /**
   * Setter for employee mail.
   * @param eMail mail.
   */
  public final void setEmployeeMail(final String eMail) {
    this.employeeMail = eMail;
  }

  /**
   * Getter for employee salary.
   * @return salary.
   */
  public final Integer getSalary() {
    return salary;
  }


  /**
   * Setter for employee salary.
   * @param eSalary employee salary.
   */
  public final void setSalary(final Integer eSalary) {
    this.salary = eSalary;
  }

  /**
   * Get id of employee's department.
   * @return department id.
   */
  public final Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * Set employee's department.
   * @param dId department id.
   */
  public final void setDepartmentId(final Integer dId) {
    this.departmentId = dId;
  }

  /**
   * String representation.
   *
   * @return string representation of employee object.
   */
  @Override
  public final String toString() {
    return "Employee{"
            + "employeeId=" + employeeId
            + ", employeeName='" + employeeName + '\''
            + ", employeeMail='" + employeeMail + '\''
            + ", salary=" + salary
            + ", departmentId=" + departmentId + '}';
  }
}
