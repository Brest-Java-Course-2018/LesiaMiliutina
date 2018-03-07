package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;

/**
 * Department servive interface.
 */
public interface DepartmentService {

    Department getDepartmentById(Integer departmentId);

    void updateDepartmentDescription(Integer departmentId, String description);
}
