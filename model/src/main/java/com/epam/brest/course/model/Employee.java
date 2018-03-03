package com.epam.brest.course.model;

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
  private String employeeName;
  /**
   * Employee's salary.
   */
  private Integer salary;
  /**
   * Department id.
   */
  private Integer departmentId;

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
   * @return string representation of employee object.
   */
  @Override
  public final String toString() {
    return "Employee{"
            + "employeeId=" + employeeId
            + ", employeeName='" + employeeName + '\''
            + ", salary=" + salary
            + ", departmentId=" + departmentId + '}';
  }
}
