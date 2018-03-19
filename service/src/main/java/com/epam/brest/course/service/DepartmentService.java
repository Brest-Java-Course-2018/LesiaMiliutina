package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Department servive interface.
 */
@Service
public interface DepartmentService {

    /**
     * Method for getting all rows of table.
     * @return all departments.
     */
    Collection<Department> getDepartments();

    /**
     * Method for getting a department.
     * @param departmentId id of department.
     * @return department by its id.
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * Method for adding a department.
     * @param department department for adding.
     * @return added department.
     */
    Department addDepartment(Department department);

    /**
     * Method for updating.
     * @param department department for updating.
     */
    void updateDepartment(Department department);

    /**
     * Method for deleting.
     * @param id id of department to remove.
     */
    void deleteDepartmentById(Integer id);

    /**
     * Method for updating department description.
     * @param departmentId id of department.
     * @param description new description.
     */
    void updateDepartmentDescription(Integer departmentId, String description);

    /**
     * Get all departments.
     * @return collection.
     */
    Collection<DepartmentDto> getDepartmentsDto();

}
