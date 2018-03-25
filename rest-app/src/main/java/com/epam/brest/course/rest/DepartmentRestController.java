package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
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
public class DepartmentRestController {

    /**
     * Logger for DepartmentRestController class.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * DepartmentService object for DepartmentRestController class.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Get departments.
     * @return all departments.
     */
    @GetMapping(value = "/departments")
    final Collection<DepartmentDto> getDepartmentsDto() {
        LOGGER.debug("rest: getDepartments()");
        return departmentService.getDepartmentsDto();
    }

    /**
     * Get department.
     * @param id id.
     * @return department.
     */
    @GetMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    final Department getDepartmentById(@PathVariable(value = "id")
                                 final Integer id) {
        LOGGER.debug("rest: getDepartmentById({})", id);
        return departmentService.getDepartmentById(id);
    }

    /**
     * Add department.
     * @param department department.
     * @return added department.
     */
    @PostMapping(value = "/departments")
    @ResponseStatus(HttpStatus.CREATED)
    final Department addDepartment(@RequestBody
                             final Department department) {
        LOGGER.debug("rest: addDepartment({})", department);
        return departmentService.addDepartment(department);

    }

    /**
     * Delete department.
     * @param id id of department.
     */
    @DeleteMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    final void deleteDepartment(@PathVariable(value = "id")
                                  final Integer id) {
        LOGGER.debug("rest: deleteDepartment({})", id);
        departmentService.deleteDepartmentById(id);
    }

    /**
     * Method for updating.
     * @param department department for updating.
     */
    @PutMapping(value = "/departments")
    final void updateDepartment(@RequestBody final Department department) {
        LOGGER.debug("rest: updateDepartment({})", department);
        departmentService.updateDepartment(department);
    }

}
