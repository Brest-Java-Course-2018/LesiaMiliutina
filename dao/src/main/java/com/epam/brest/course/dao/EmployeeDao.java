package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;

import java.util.Collection;

/**
 * Employee DAO interface.
 */
public interface EmployeeDao {

    /**
     * Method for getting all employees.
     * @return all employees.
     */
    Collection<Employee> getEmployees();

    /**
     * Method for getting rows from table.
     * @param employeeId id of employee.
     * @return employee by its id.
     */
    Employee getEmployeeById(Integer employeeId);

    /**
     * Method for getting all employees of department.
     * @param departmentId id of department.
     * @return all employees of department.
     */
    Collection<Employee> getEmployeesByDepartmentId(Integer departmentId);

    /**
     * Method for adding rows in table.
     * @param employee added employee object.
     * @return added employee.
     */
    Employee addEmployee(Employee employee);

    /**
     * Method for updating.
     * @param employee employee object that needed to be update.
     */
    void updateEmployee(Employee employee);

    /**
     * Method for deleting rows from table.
     * @param employeeId department id.
     */
    void deleteEmployeeById(Integer employeeId);
}
