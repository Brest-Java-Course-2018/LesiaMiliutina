package com.epam.brest.course.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * POJO Department for model.
 */
public class Department {

  /**
   * Department id.
   */
  private Integer departmentId;
  /**
   * Name of department.
   */
  @NotEmpty
  @Size(min = 2, max = 255)
  private String departmentName;
  /**
   * Description of department.
   */
  @Size(max = 255)
  private String description;

  /**
   * Default constructor.
   */
  public Department() {
  }

  /**
   * Constructor with arguments.
   * @param dName name of department.
   * @param dDesc department description.
   */
  public Department(final String dName,
                    final String dDesc) {
    this.departmentName = dName;
    this.description = dDesc;
  }

  /**
   * Getter for departmentId field.
   * @return department id.
   */
  public final Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * Setter for departmentId field.
   * @param dId id of department.
   */
  public final void setDepartmentId(final Integer dId) {
    this.departmentId = dId;
  }

  /**
   * Getter for department name.
   * @return name of department.
   */
  public final String getDepartmentName() {
    return departmentName;
  }

  /**
   * Setter for department name.
   * @param dName name of department.
   */
  public final void setDepartmentName(final String dName) {
    this.departmentName = dName;
  }

  /**
   * Getter for department description.
   * @return description.
   */
  public final String getDescription() {
    return description;
  }

  /**
   * Setter for department description.
   * @param dDescription department description.
   */
  public final void setDescription(final String dDescription) {
    this.description = dDescription;
  }

  /**
   * String representation.
   * @return string representation of department object.
   */
  @Override
  public final String toString() {
    return "Department{"
            + "departmentId=" + departmentId
            + ", departmentName='" + departmentName + '\''
            + ", description='" + description + '\'' + '}';
  }
}
