package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO interface.
 */
public interface DepartmentDao {

  List<Department> getDepartments();
  Department getDepartmentById(Integer departmentId);

}
