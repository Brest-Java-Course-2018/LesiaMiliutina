package com.epam.brest.course.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Rest controller for department.
 */
@RestController
public class EmployeeRestController {

    /**
     * Logger for EmployeeRestController class.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;

    /**
     * Method for getting all employees.
     * @return all employees.
     */
    @GetMapping(value = "/employees")
    final Collection<Employee> getEmployees(){
        LOGGER.debug("rest: getEmployees()");
        return employeeService.getEmployees();
    }
    /**
     * Method for getting rows from table.
     * @param id id of employee.
     * @return employee by its id.
     */
    @GetMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    final Employee getEmployeeById(@PathVariable(value = "id")
                                       final Integer id) {
        LOGGER.debug("rest: getEmployeeById({})", id);
        return employeeService.getEmployeeById(id);
    }

    /**
     * Method for getting all employees of department.
     * @param id id of department.
     * @return all employees of department.
     */
    @GetMapping(value = "/employees/department/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    final Collection<Employee> getEmployeesByDepartmentId(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("rest: getEmployeesByDepartmentId({})", id);
        return employeeService.getEmployeesByDepartmentId(id);
    }

    /**
     * Method for adding rows in table.
     * @param employee added employee object.
     * @return added employee.
     */
    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    final Employee addEmployee(@RequestBody final Employee employee) {
        LOGGER.debug("rest: addEmployee({})", employee);
        return employeeService.addEmployee(employee);
    }

    /**
     * Method for updating.
     * @param employee employee object that needed to be update.
     */
    @PutMapping(value = "/employees")
    final void updateEmployee(@RequestBody final Employee employee) {
        LOGGER.debug("rest: updateEmployee({})", employee);
        employeeService.updateEmployee(employee);
    }

    /**
     * Method for deleting rows from table.
     * @param id department id.
     */
    @DeleteMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    final void deleteEmployeeById(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("rest: deleteEmployeeById({})", id);
        employeeService.deleteEmployeeById(id);
    }

}
