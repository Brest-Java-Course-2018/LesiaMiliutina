package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;

/**
 * Department servive interface.
 */
public interface DepartmentService {

    /**
     * Method for getting a department.
     * @param departmentId id of department.
     * @return department by its id.
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * Method for updating department description.
     * @param departmentId id of department.
     * @param description new description.
     */
    void updateDepartmentDescription(Integer departmentId, String description);

}
