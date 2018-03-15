package com.epam.brest.course.service;

import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Implementation of EmployeeService.
 */
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Logger for EmployeeServiceImpl class.
     */
    private static final Logger LOGGER = LogManager.getLogger();


    /**
     * DepartmentDao object for services.
     */
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Constructor with parameter.
     * @param emplDao object of DepartmentDao class.
     */
    public EmployeeServiceImpl(final EmployeeDao emplDao) {
        this.employeeDao = emplDao;
    }

    /**
     * Method for getting all employees.
     *
     * @return all employees.
     */
    @Override
    public final Collection<Employee> getEmployees() {
        LOGGER.debug("getEmployees()");
        return employeeDao.getEmployees();
    }

    /**
     * Method for getting rows from table.
     *
     * @param employeeId id of employee.
     * @return employee by its id.
     */
    @Override
    public final Employee getEmployeeById(final Integer employeeId) {
        LOGGER.debug("getEmployeeById({})", employeeId);
        return employeeDao.getEmployeeById(employeeId);
    }

    /**
     * Method for getting all employees of department.
     *
     * @param departmentId id of department.
     * @return all employees of department.
     */
    @Override
    public final Collection<Employee> getEmployeesByDepartmentId(
            final Integer departmentId) {
        LOGGER.debug("getEmployeesByDepartmentId({})", departmentId);
        return employeeDao.getEmployeesByDepartmentId(departmentId);
    }

    /**
     * Method for adding rows in table.
     *
     * @param employee added employee object.
     * @return added employee.
     */
    @Override
    public final Employee addEmployee(final Employee employee) {
        LOGGER.debug("addEmployee({})", employee);
        return employeeDao.addEmployee(employee);
    }

    /**
     * Method for updating.
     *
     * @param employee employee object that needed to be update.
     */
    @Override
    public final void updateEmployee(final Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        employeeDao.updateEmployee(employee);
    }

    /**
     * Method for deleting rows from table.
     *
     * @param employeeId department id.
     */
    @Override
    public final void deleteEmployeeById(final Integer employeeId) {
        LOGGER.debug("deleteEmployeeById({})", employeeId);
        employeeDao.deleteEmployeeById(employeeId);
    }
}
