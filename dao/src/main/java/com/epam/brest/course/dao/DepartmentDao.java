package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.Collection;

/**
 * Department DAO interface.
 */
public interface DepartmentDao {

  /**
   * Method for getting all rows of table.
   * @return all departments.
   */
  Collection<Department> getDepartments();

  /**
   * Method for getting rows from table.
   * @param departmentId department id.
   * @return department by its id value.
   */
  Department getDepartmentById(Integer departmentId);

  /**
   * Method for adding rows in table.
   * @param department added department object.
   * @return added department.
   */
  Department addDepartment(Department department);

  /**
   * Method for updating.
   * @param department department object that needed to be update.
   */
  void updateDepartment(Department department);

  /**
   * Method for deleting rows from table.
   * @param id department id.
   */
  void deleteDepartmentById(Integer id);

}
